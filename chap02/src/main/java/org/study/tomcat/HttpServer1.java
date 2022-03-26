package org.study.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2021/11/30
 */
public class HttpServer1 {

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    public static void main(String[] args) {
        HttpServer1 httpServer = new HttpServer1();
        httpServer.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (true) {
            try (Socket socket = serverSocket.accept())
            {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                Request request = new Request(is);
                request.parse();

                String uri = request.getUri();
                if (SHUTDOWN_COMMAND.equalsIgnoreCase(uri)) {
                    break;
                }
                Response response = new Response(os);
                response.setRequest(request);
                if (uri.startsWith("/servlet")){
                    ServletProcessor servletProcessor = new ServletProcessor();
                    servletProcessor.process(request,response);
                }
                else{
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
