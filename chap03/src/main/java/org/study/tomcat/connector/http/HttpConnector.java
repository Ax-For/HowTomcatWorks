package org.study.tomcat.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2022/3/12
 */
public class HttpConnector implements Runnable{

    boolean stopped;

    private final String scheme = "http";

    public String getScheme(){
        return scheme;
    }

    // 主方法，开启服务器端套接字监听请求，并将请求传递给处理器
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;

        // 初始化服务器端套接字
        try {
            serverSocket = new ServerSocket(port, 2, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!stopped){
            // 等待连接
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            // 利用连接器信息构造处理器对象，并处理该socket连接
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    // 启动连接器，开启新线程进行监听
    public void start() {
        new Thread(this).start();
    }
}
