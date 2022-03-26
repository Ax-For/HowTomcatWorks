package org.study.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class SocketTest1 {

    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 9094);
        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);
        // 针对输出文本流的读取
        InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
            writer.print("GET /index.html HTTP/1.1\r\n");
            writer.print("Host: 127.0.0.1:9094\r\n");
            writer.print("Connection: Close\r\n");
            writer.print("\r\n");
            StringBuilder sb = new StringBuilder(8096);
            String line;
            // 阻塞式读取
            while((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
