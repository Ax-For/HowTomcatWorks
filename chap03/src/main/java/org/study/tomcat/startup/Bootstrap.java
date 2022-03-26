package org.study.tomcat.startup;

import org.study.tomcat.connector.http.HttpConnector;

/**
 * @author dongyafei
 * @date 2022/3/12
 * 启动类
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }

}
