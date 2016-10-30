package com.master.core.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCreation {

    // sample 1, extends Thread
    public static void createByExtendsThread() {
        Thread sampleThread = new SampleFirstThread("Sample");
        sampleThread.start();
    }

    // sample 2, implements runnable
    public static void createByImplementsRunnable() {
        Thread sampleThread = new Thread(new SampleFirstThread("Sample second"));
        sampleThread.start();
    }

    //sample 3, Executor service
    public static void createByExecutor() {
        ExecutorService es = Executors.newFixedThreadPool(20);
        //ExecutorService es = Executors.newCachedThreadPool();
        //ExecutorService es = Executors.newFixedThreadPool();

        for(int i=0; i<100; i++) {
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + " is running");
                    return null;
                }
            };
            es.submit(task);
        }
        es.shutdown();
    }



    public static void anonymousCreation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + " is running");
            }
        }).start();
    }

    public static void main(String[] args) {
        createByExecutor();
    }

}


class SampleFirstThread extends Thread {
    private String name;
    public SampleFirstThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(this.name + Thread.currentThread().getId() + Thread.currentThread().getName() + " is running");
    }
}

class SampleSecondThread implements Runnable {
    private String name;

    public SampleSecondThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(this.name + Thread.currentThread().getId() + Thread.currentThread().getName() + " is running");
    }
}