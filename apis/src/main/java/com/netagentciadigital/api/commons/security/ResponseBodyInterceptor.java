package com.netagentciadigital.api.commons.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 *	Coloca Token no Cookie e retira do body
 */
@ControllerAdvice
public class ResponseBodyInterceptor implements ResponseBodyAdvice<Object>{

    @Value("${application.security.enable-https}")
    private boolean enableHttps;

    /**
     *  quando o metodo para de request for interceptado for postAccessToken
     *  para a classe OAuth2AccessToken, retorna true para podermos
     *  executar o metodo beforeBodyWrite(...)
     *
     * */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType contentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> converterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {

        if(body instanceof OAuth2AccessToken) {
            return getOAuth2AccessToken((OAuth2AccessToken)body, (ServletServerHttpRequest) request, (ServletServerHttpResponse) response);
        }

        return body;//countRequestsLimit(body, (ServletServerHttpRequest) request, (ServletServerHttpResponse) response);
    }

    /*private Object countRequestsLimit(Object body, ServletServerHttpRequest request, ServletServerHttpResponse response) {
        HttpServletRequest req  = request.getServletRequest();
        HttpServletResponse resp  = response.getServletResponse();

        addCountRequestsLimit(req, resp);

        return body;
    }

    private void addCountRequestsLimit(HttpServletRequest req, HttpServletResponse resp) {

        resp.addHeader("api-request-counter", "1");
        resp.addHeader("api-request-limit", "1");
        resp.addHeader("api-request-expire", "1");
    }*/

    private OAuth2AccessToken getOAuth2AccessToken(OAuth2AccessToken body, ServletServerHttpRequest request, ServletServerHttpResponse response) {
        HttpServletRequest req  = request.getServletRequest();
        HttpServletResponse resp  = response.getServletResponse();

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        assert body != null;
        String refreshToken = body.getRefreshToken().getValue();
        String accessToken = body.getValue();

        addRefreshTokenNoCookie(refreshToken, req, resp);
        addAccessTokenNoCookie(accessToken, req, resp);
        removerRefreshTokenDoBody(token);

        return body;
    }

    private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
        Map<String, Object> adicionalAtributes = new HashMap();
        token.setAdditionalInformation(adicionalAtributes);
    }

    private void addRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(enableHttps);
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);//30 dias para expiracao
        resp.addCookie(refreshTokenCookie);
    }

    private void addAccessTokenNoCookie(String accessToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(enableHttps);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 17);//17 min para expiracao
        resp.addCookie(accessTokenCookie);
    }

}
