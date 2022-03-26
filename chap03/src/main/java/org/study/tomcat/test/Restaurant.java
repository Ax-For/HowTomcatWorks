package org.study.tomcat.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author dongyafei
 * @date 2022/3/20
 */

public class Restaurant {

    private static volatile int count;
    private final int maxMeal;
    private volatile int meal;

    public Restaurant(int max) {
        maxMeal = max;
    }

    // 做饭任务
    public synchronized void offer() throws InterruptedException {
        while (meal == maxMeal) {
            wait();
        }
        meal++;
        notifyAll();
    }

    // 取餐任务
    public synchronized void take() throws InterruptedException {
        while (meal == 0) {
            wait();
        }
        meal--;
        notifyAll();
    }

    public static void main(String[] args) {

        for (int j = 0; j < 100; j++) {

            Restaurant restaurant = new Restaurant(10);
            ExecutorService exec = Executors.newCachedThreadPool();
            // 10个厨师，10个服务员. 各自拥有独立线程.
            for (int i = 0; i < 10; i++) {
                exec.submit(new Chef(restaurant));
                exec.submit(new Waiter(restaurant));
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println("Restaurant thread interrupted accidently!");
            }
            exec.shutdownNow();
        }
    }
}

class Chef implements Runnable {

    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        synchronized (this){
            this.restaurant = restaurant;
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                restaurant.offer();
            }
        } catch (InterruptedException ie) {
            System.out.println("Chef#" + Thread.currentThread().getId() + " interrupted during waiting!");
        }
        System.out.println("Chef#" + Thread.currentThread().getId() + " stopped correctly!");
    }
}


class Waiter implements Runnable {

    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        synchronized (this){
            this.restaurant = restaurant;
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                restaurant.take();
            }
        } catch (InterruptedException ie) {
            System.out.println("Waiter#" + Thread.currentThread().getId() + " interrupted during waiting!");
        }
        System.out.println("Waiter#" + Thread.currentThread().getId() + " stopped correctly!");
    }
}


