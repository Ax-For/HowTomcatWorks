package org.study.tomcat.test.abstarct;

/**
 * @author dongyafei
 * @date 2022/3/26
 */
public abstract class Animal {
    Animal(String msg) {
        System.out.println("I'm Animal with [ " + msg + " ]");
    }

}


class Cat extends Animal implements Cloneable {

    Cat(String msg) {
        super(msg);
    }

    public static void main(String[] args) {
        Cat cat = new Cat("cat");
    }
}

class Test{
    public static void main(String[] args) {
        Cat cat = new Cat("cat");
    }
}
