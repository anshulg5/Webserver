package org.example;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnectionStatistics;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;

/**
 * Hello world!
 */
public class App {

    static Server server;

    static class ShutdownOperation extends Thread{
        @Override
        public void run() {
            try {
                server.stop();
                System.out.println("Server closed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println("Hello World!");
//        System.out.println("hi");
        server = new Server(8070);
//        server.setStopAtShutdown(true);
        Runtime.getRuntime().addShutdownHook(new ShutdownOperation());
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
//
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(7070);
        server.addConnector(connector);

        ServletContextHandler handler = new ServletContextHandler(server, "/");

        handler.addServlet(WelcomeServlet.class, "/");
        handler.addServlet(ExampleServlet.class, "/example");

//        HelloHandler handler = new HelloHandler();
//        server.setHandler(handler);
        server.start();
    }

//    public static class HelloHandler extends AbstractHandler {
//
//        public HelloHandler() {
//            this("Hello Java Code Geeks - First Handler");
//        }
//
//        public HelloHandler(String arg) {
//            this(arg, null);
//        }
//
//        public HelloHandler(String arg1, String arg2){
//            this.greetmessage = arg1;
//            this.bodymessage = arg2;
//        }
//        public void handle(String target, Request baseRequest, HttpServletRequest request,
//                           HttpServletResponse response) throws IOException, ServletException {
//            response.setContentType("text/html; charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_OK);
//            System.out.println("hi");
//
//            PrintWriter out = response.getWriter();
//
//            out.println(greetmessage);
//            if(bodymessage != null){
//                out.println(bodymessage);
//            }
//
//            baseRequest.setHandled(true);
//
//        }
//
//        final String greetmessage;
//        final String bodymessage;
//
//    }
}


