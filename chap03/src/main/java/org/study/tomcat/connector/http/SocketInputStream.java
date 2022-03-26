package org.study.tomcat.connector.http;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author dongyafei
 * @date 2022/3/12
 */
public class SocketInputStream extends InputStream{

    public SocketInputStream(InputStream inputStream, int buffSize) {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
