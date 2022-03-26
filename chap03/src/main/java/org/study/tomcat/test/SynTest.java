package org.study.tomcat.test;

/**
 * @author dongyafei
 * @date 2022/3/17
 * synchronized 关键字测试
 */
public class SynTest implements Runnable{

    private int count = 0;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            if ("t1".equals(name)){
                methodAdd();
            }
            else{
                methodPrint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodAdd() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count  + "  -> " + Thread.activeCount());
            Thread.sleep(500);
        }
    }

    public synchronized void methodPrint() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " count : " + count + "  -> " + Thread.activeCount());
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        SynTest o = new SynTest();
        Thread t1 = new Thread(o, "t1");
        Thread t2 = new Thread(o, "t2");
        t2.start();
        t1.start();
        while (Thread.activeCount() > 2){
        }
        System.out.println("线程执行结束");
    }
}
