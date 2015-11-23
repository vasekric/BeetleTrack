package cz.vasekric.beetletrack.webgui.view.filters;

import cz.vasekric.beetletrack.webgui.view.controllers.LoginController;
import cz.vasekric.beetletrack.webgui.view.models.User;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by vasek on 03.10.2015.
 */
@WebFilter
public class LoginFilter implements Filter, Serializable {

    @Inject private User user;
    @Inject private LoginController loginController;

    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (user == null || !loginController.isLoggedIn(user)) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
        }

        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    public void destroy() {
        // Nothing to do here!
    }

}
