package org.study.tomcat.test;

/**
 * @author dongyafei
 * @date 2022/3/21
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null){
            System.out.println(loader);
            loader = loader.getParent();
        }
    }
}
