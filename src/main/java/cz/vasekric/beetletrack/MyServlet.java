package cz.vasekric.beetletrack;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by vasek on 04.11.2015.
 */
public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
