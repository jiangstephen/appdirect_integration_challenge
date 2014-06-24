package com.appdirect.integration.challenge;

import java.io.IOException;
import javax.servlet.http.*;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("{ \"name\": \"AppDirect Integration Challeange\" }");
    }
}
