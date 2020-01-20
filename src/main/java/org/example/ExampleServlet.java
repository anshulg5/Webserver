package org.example;

import org.eclipse.jetty.client.HttpContentResponse;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ExampleServlet extends HttpServlet {

    Client client = new Client();

    public ExampleServlet() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpContentResponse client_response = makeClientRequest(req);

        writeResponse(req,resp,client_response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);


//        BufferedReader br = req.getReader();
//        StringBuffer sbf = new StringBuffer();
//        String line=null;
//        while((line = br.readLine())!=null){
//            sbf.append(line);
//        } //not to use with POST request if using getParameter
//        System.out.println(sbf);

//        try {
//            JSONObject jsonObject = JSONObject.fromObject(sbf.toString());
//        } catch (ParseException e) {
//            // crash and burn
//            throw new IOException("Error parsing JSON request string");
//        }

        HttpContentResponse client_response = makeClientRequest(req);

        writeResponse(req,resp, client_response);
    }

    protected HttpContentResponse makeClientRequest(HttpServletRequest req){
        HttpContentResponse response=null;
        try {
            System.out.println(req.getParameter("method"));
            System.out.println(req.getParameter("url"));
            if(req.getParameter("method")!=null && req.getParameter("url")!=null)
                response = (HttpContentResponse)client.makeRequest(req.getParameter("method"), req.getParameter("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(response.getContentAsString());
        return response;
    }

    protected void writeResponse(HttpServletRequest req, HttpServletResponse resp, HttpContentResponse client_response) throws IOException {
        resp.setStatus(HttpStatus.OK_200);
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

//        Map<String, String[]> e = req.getParameterMap();
//        pw.println("\nEnumeration over list: ");
//
//        //print the enumeration
//        e.forEach((k,v)->pw.println("key: "+k+" ,value: "+v[0]+"<br>"));

//        pw.println("<html><body><br>");
//        pw.println("Welcome " + s + "<br>");
//        pw.println("</body></html>");

        if(client_response!=null)
            pw.println(client_response.getContentAsString());
        pw.close();
    }

    class abc{
        String name;
        String age;
    }
}
