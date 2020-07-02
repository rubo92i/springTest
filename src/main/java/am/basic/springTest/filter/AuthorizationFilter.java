package am.basic.springTest.filter;

import am.basic.springTest.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.springTest.util.constants.Messages.SESSION_EXPIRED_MESSAGE;
import static am.basic.springTest.util.constants.Pages.START_URL;
import static am.basic.springTest.util.constants.ParameterKeys.MESSAGE_ATTRIBUTE_KEY;
import static am.basic.springTest.util.constants.ParameterKeys.USER_ATTRIBUTE_KEY;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Authorization filter constructed");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE_KEY);
        if (user == null) {
            request.setAttribute(MESSAGE_ATTRIBUTE_KEY, SESSION_EXPIRED_MESSAGE);
            request.getRequestDispatcher(START_URL).forward(request, response);
        }else {
            chain.doFilter(request,response);
        }


    }


    @Override
    public void destroy() {
        System.out.println("Authorization filter destroyed");
    }
}
