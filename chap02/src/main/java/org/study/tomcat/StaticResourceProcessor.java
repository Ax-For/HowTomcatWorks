package org.study.tomcat;

/**
 * @author dongyafei
 * @date 2021/11/30
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        response.sendStaticResource();
    }

}
