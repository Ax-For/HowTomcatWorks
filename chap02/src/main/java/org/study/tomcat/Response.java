package org.study.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author dongyafei
 * @date 2021/11/30
 */
public class Response implements ServletResponse {

    private static final int BUFF_SIZE = 1024;
    private final OutputStream os;
    private Request request;

    public Response(OutputStream os) {
        this.os = os;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource(){
        File file = new File(Constants.WEB_ROOT, request.getUri());
        try {
            if (file.exists()) {
                String header = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type : text/html\r\n\r\n";
                os.write(header.getBytes(StandardCharsets.UTF_8));
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = new byte[BUFF_SIZE];
                    int i;
                    while ((i = fis.read(buffer, 0, BUFF_SIZE)) != -1) {
                        os.write(buffer, 0, i);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                os.write(errorMessage.getBytes(StandardCharsets.UTF_8));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(os,true);
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
