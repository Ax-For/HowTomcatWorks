package org.study.tomcat;

import java.io.File;
import java.io.OutputStream;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class Response {

    private static final int BUFF_SIZE = 1024;
    private Request request;
    private final OutputStream os;

    public Response(OutputStream os) {
        this.os = os;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource(){
        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (file.exists()){

        }else{

        }
    }
}
