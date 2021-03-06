package org.study.tomcat;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author dongyafei
 * @date 2021/11/30
 */
public class ServletProcessor {

    public void process(Request request, Response response){
        // 获取ServletName
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass = null;
        try{
            myClass = loader.loadClass(servletName);
        }
        catch(ClassNotFoundException e){
            System.out.println(e.toString());
        }
        Servlet servlet = null;
        try{
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch(Throwable e){
            System.out.println(e.toString());
        }
    }

}
