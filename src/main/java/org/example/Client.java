package org.example;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.omg.SendingContext.RunTime;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;




public class Client {

    class ShutdownClientOperation extends Thread{
        @Override
        public void run() {
            try {
                client.stop();
                System.out.println("Client closed");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    HttpClient client;
    String URL = "http://localhost:8070/example";


    Client() throws Exception {
        SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
        client = new HttpClient(sslContextFactory);
        client.setFollowRedirects(true);
        client.start();
        Runtime.getRuntime().addShutdownHook(new ShutdownClientOperation());
        System.out.println(Runtime.getRuntime());
//        startClient();
    }
//    public static void main(String[] args) throws Exception{
//        new Client();
//
//    }

//    void startClient() throws Exception {
//        client.start();
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String s = null;
//        while(s!="exit"){
//            System.out.println("Enter the desired operation");
//            s=bf.readLine();
//            if(s.contains("g")){
//                sendGet(URL);
//            }
//            else if(s.contains("p"))
//                sendPost(URL);
//            else
//                break;
//        }
//    }

    public ContentResponse makeRequest(String method, String url) throws InterruptedException, ExecutionException, TimeoutException {
        switch(method){
            case "POST":
                return sendPost(url);
            default:
                return sendGet(url);
        }
    }

    public ContentResponse sendGet(String url) throws InterruptedException, ExecutionException, TimeoutException {
        return client.GET(url+"?name=Unknown");
    }

    public ContentResponse sendPost(String url) throws InterruptedException, ExecutionException, TimeoutException {
        Request request = client.POST(url);
        request = request.param("name", "Anshul");
        return request.send();
    }

//    askParam(){
//
//    }
}
