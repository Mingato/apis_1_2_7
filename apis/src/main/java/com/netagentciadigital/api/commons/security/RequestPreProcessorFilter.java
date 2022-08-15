package com.netagentciadigital.api.commons.security;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Administrador
 *	Antes de fazer a requisicao, pega o token do cookie para poder ser usado
 */
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestPreProcessorFilter implements Filter {

    private final int API_REQUEST_LIMIT = 180;
    private final int API_REQUESTS_EXPIRE_SECONDS = 60;

    private final LoadingCache<String, Integer> requestCountsPerIpAddress;


    public RequestPreProcessorFilter(){
        requestCountsPerIpAddress = Caffeine.newBuilder().
                expireAfterWrite(API_REQUESTS_EXPIRE_SECONDS, TimeUnit.SECONDS).build(key -> 0);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req  = ((HttpServletRequest) request);
        HttpServletResponse resp  = ((HttpServletResponse) response);

        if (verifyLimitsRequestsPerClient(req, resp)){
            return;
        }

        log.info(req.getMethod() +" " + req.getRequestURI() +
                (req.getQueryString() != null ? "?" + req.getQueryString(): ""));


        req = verifyURIs(req);

        chain.doFilter(req, resp);
    }

    private HttpServletRequest verifyURIs(HttpServletRequest req) {
        String refreshToken = "";
        if(req.getRequestURI().contains("/oauth/token")
                && "refresh_token".equalsIgnoreCase(req.getParameter("grant_type"))
                && req.getCookies() != null) {

            for(Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                }
            }

            req = new MyServletRequestWrapper(req, refreshToken);
        }else if(!req.getRequestURI().contains("/login.html") &&
                !req.getRequestURI().contains("/actuator/") &&
                !req.getRequestURI().contains("/jwks.json")){

            String accessToken = null;

            if(req.getCookies() != null) {
                for(Cookie cookie : req.getCookies()) {
                    if(cookie.getName().equals("accessToken")) {
                        accessToken = cookie.getValue();
                    }
                    if(cookie.getName().equals("refreshToken")) {
                        refreshToken = cookie.getValue();
                    }
                }
            }

            if(accessToken != null) {
                req = new MyServletRequestWrapper(req, refreshToken, accessToken);
            }

        }
        return req;
    }

    private boolean verifyLimitsRequestsPerClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientIpAddress = getClientIP(req);
        if(isMaximumRequestsPerSecondExceeded(clientIpAddress, resp)){
            resp.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            resp.getWriter().write("Too many requests");
            return true;
        }
        return false;
    }

    private boolean isMaximumRequestsPerSecondExceeded(String clientIpAddress, HttpServletResponse resp){
        Integer requests = 0;
        requests = requestCountsPerIpAddress.get(clientIpAddress);
        if(requests != null){
            if(requests > API_REQUEST_LIMIT) {
                requestCountsPerIpAddress.asMap().remove(clientIpAddress);
                requestCountsPerIpAddress.put(clientIpAddress, requests);
                return true;
            }

        } else {
            requests = 0;
        }
        requests++;
        requestCountsPerIpAddress.put(clientIpAddress, requests);
        resp.addHeader("api-request-counter", String.valueOf(requests));
        resp.addHeader("api-request-limit", String.valueOf(API_REQUEST_LIMIT));
        resp.addHeader("api-request-expire", String.valueOf(API_REQUESTS_EXPIRE_SECONDS));
        return false;
    }

    public String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0]; // voor als ie achter een proxy zit
    }


    public JwtAccessTokenConverter accessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     *como o getParameter map nao pode ser mudado, entao esta classe é criada para altera-lo
     */
    static class MyServletRequestWrapper extends HttpServletRequestWrapper {
        private final String refreshToken;
        private String accessToken;

        private final Map<String, String> headerMap = new HashMap<>();

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken, String accessToken) {
            super(request);
            this.refreshToken = refreshToken;
            this.accessToken = accessToken;
            addHeader("Authorization", "Bearer " + accessToken);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[] {refreshToken});
            map.put("Authorization", new String[] {"Bearer " + accessToken});
            map.setLocked(true);
            return map;
        }

        //////////HEADERS///

        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            names.addAll(headerMap.keySet());
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = Collections.list(super.getHeaders(name));
            if (headerMap.containsKey(name)) {
                values.add(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }
    }
}
