package org.study.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class Request {

    private static final int BUFF_SIZE = 2048;
    private final InputStream is;
    private String uri;

    public Request(InputStream is) {
        this.is = is;
    }

    // 对输入进行解析，获取输入流
    public void parse() throws IOException {
        StringBuilder sb = new StringBuilder(BUFF_SIZE);
        byte[] buffer = new byte[BUFF_SIZE];
        int i;
        while ((i = is.read(buffer, 0, BUFF_SIZE)) != -1){
            for (int j = 0; j < i; j++) {
                sb.append(buffer[j]);
            }
        }
        System.out.println(sb);
        uri = parseUri(sb.toString());
    }

    // 获取uri
    public String getUri(){
        return uri;
    }

    // 从请求中解析出uri
    private String parseUri(String requestString){
        if (requestString == null){
            return null;
        }
        int idx1 = requestString.indexOf(' ');
        if (idx1 != -1){
            int idx2 = requestString.indexOf(' ', idx1 + 1);
            if (idx2 > idx1){
                return requestString.substring(idx1 + 1, idx2);
            }
        }
        return null;
    }
}
