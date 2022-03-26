package org.study.tomcat.test;

import java.sql.Timestamp;

/**
 * @author dongyafei
 * @date 2022/3/20
 */
public class TSTest {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(timestamp.toString().substring(0, 19));
        System.out.println(timestamp.toString().substring(0, 10));
    }
}
