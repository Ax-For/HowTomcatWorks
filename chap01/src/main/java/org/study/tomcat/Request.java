package org.study.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dongyafei
 * @date 2021/11/29
 */
public class Request {

    // 缓存区大小
    private static final int BUFF_SIZE = 2048;
    // 输入流
    private final InputStream is;
    private String uri;

    public Request(InputStream is) {
        this.is = is;
    }

    // 对输入进行解析，获取输入流
    public void parse() {
        System.out.println("开始Request解析");
        StringBuilder sb = new StringBuilder(BUFF_SIZE);
        byte[] buffer = new byte[BUFF_SIZE];
        int i;
        try {
            System.out.println("开始读取输入流");
            if ((i = is.read(buffer, 0, BUFF_SIZE)) != -1) {
                for (int j = 0; j < i; j++) {
                    sb.append((char)buffer[j]);
                }
            }
            System.out.println("输入流读取完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("请求信息解析完成");
        System.out.println(sb);
        uri = parseUri(sb.toString());
    }

    // 获取uri
    public String getUri() {
        return uri;
    }

    // 从请求中解析出uri
    private String parseUri(String requestString) {
        if (requestString == null) {
            return null;
        }
        int idx1 = requestString.indexOf(' ');
        if (idx1 != -1) {
            int idx2 = requestString.indexOf(' ', idx1 + 1);
            if (idx2 > idx1) {
                return requestString.substring(idx1 + 1, idx2);
            }
        }
        return null;
    }
}
