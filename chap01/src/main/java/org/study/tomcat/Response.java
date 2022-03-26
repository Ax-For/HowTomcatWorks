package org.study.tomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class Response {

    // 缓存区大小
    private static final int BUFF_SIZE = 1024;
    // 保存请求对象
    private Request request;
    // 输出流
    private final OutputStream os;

    public Response(OutputStream os) {
        this.os = os;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            // 文件存在，则读取该文件
            if (file.exists()) {
                fis = new FileInputStream(file);
                // 设置返回头信息
                String header = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n\r\n";
                os.write(header.getBytes());
                byte[] buffer = new byte[BUFF_SIZE];
                int i;
                while ((i = fis.read(buffer, 0, BUFF_SIZE)) != -1) {
                    os.write(buffer, 0, i);
                }
            }
            // 文件不存在，则报错404
            else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                os.write(errorMessage.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
