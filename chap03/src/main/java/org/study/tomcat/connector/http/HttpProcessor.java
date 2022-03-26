package org.study.tomcat.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author dongyafei
 * @date 2022/3/12
 */
public class HttpProcessor {


    public HttpProcessor(HttpConnector httpConnector) {

    }

    public void process(Socket socket) {

        SocketInputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // 获取输入输出流
            inputStream = new SocketInputStream(socket.getInputStream(), 2048);
            outputStream = socket.getOutputStream();

            // 构建Request
            HttpRequest request = new HttpRequest(inputStream);

            //
            HttpResponse response = new HttpResponse(outputStream);
            response.setRequest(request);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
