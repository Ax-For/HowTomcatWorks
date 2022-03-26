package org.study.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUT_DOWN = "/SHUTDOWN";
    private boolean isConnectable = true;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    // 等待Socket连接
    public void await(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9094, 2, InetAddress.getByName("127.0.0.1"));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (isConnectable){
            try
            {
                Socket socket = serverSocket.accept();
                System.out.println("收到一次请求");
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                System.out.println("处理Request");
                Request request = new Request(is);
                request.parse();

                System.out.println("处理Response");
                Response response = new Response(os);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();
                System.out.println("关闭Socket");
                System.out.println("\n===================================\n");

                isConnectable = !SHUT_DOWN.equals(request.getUri());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
