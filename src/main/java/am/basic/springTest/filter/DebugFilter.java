package am.basic.springTest.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DebugFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Debug filter constructed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //printRequest((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Authorization filter constructed");
    }


    private void printRequest(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if (key.contains("pass") || key.contains("token") || key.contains("code")) {
                value = "******";
            }
            map.put("Header: " + key, value);
        }
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            if (key.contains("pass") || key.contains("token") || key.contains("code")) {
                value = "******";
            }
            map.put("Parameter: " + key, value);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                map.put("Cookie: " + cookie.getName(), cookie.getValue());

            }
        }

        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put("parameter: " + key, value);
        }
        map.put("RequestIPAdrress", request.getRemoteAddr());
        map.put("RemoteUser", request.getRemoteUser());
        map.put("Method", request.getMethod());
        map.put("QueryString", request.getQueryString());
        map.put("AuthType", request.getAuthType());
        map.put("ContextPath", request.getContextPath());
        map.put("PathInfo", request.getPathInfo());
        map.put("PathTranslated", request.getPathTranslated());
        map.put("RequestedSessionId", request.getRequestedSessionId());
        map.put("RequestURI", request.getRequestURI());
        map.put("RequestURL", request.getRequestURL().toString());
        map.put("ServletPath", request.getServletPath());
        map.put("ContentType", request.getContentType());
        map.put("LocalName", request.getLocalName());
        map.put("Protocol", request.getProtocol());
        map.put("RemoteAddr", request.getRemoteAddr());
        map.put("ServerName", request.getServerName());


        System.out.println("\n*****#### REQUEST START ####******");
        map.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("*****#### REQUEST END ####******\n");


    }
}
