package org.study.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class SocketTest {

    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 19094);
        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);
        InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
            writer.println("GET /index.jsp HTTP/1.1");
            writer.println("Host: 127.0.0.1:9094");
            writer.println("Connection: Close");
            writer.println();
            StringBuilder sb = new StringBuilder(8096);
            while(true){
                if (reader.ready()){
                    int i;
                    while ((i = reader.read()) != -1){
                        sb.append(i);
                    }
                    break;
                }
                Thread.sleep(50);
            }
            System.out.println(sb);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
