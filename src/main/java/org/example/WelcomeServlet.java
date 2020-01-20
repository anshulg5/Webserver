package org.example;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WelcomeServlet extends HttpServlet{



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setStatus(HttpStatus.OK_200);

        PrintWriter pw = resp.getWriter();
        pw.println("<html><body>");
        pw.println("Welcome to servlet");
        pw.println("</body></html>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        resp.setContentType("text/html");
        resp.setStatus(HttpStatus.OK_200);

        Map<String, String[]> e = req.getParameterMap();
        System.out.println("\nEnumeration over list: ");
        //         print the enumeration
        e.forEach((k,v)->System.out.println("key: "+k+" ,value: "+v[0]));

        PrintWriter pw = resp.getWriter();
        pw.println("<html><body>");
        pw.println("Welcome to servlet");
        pw.println("</body></html>");
        pw.close();
    }
}
