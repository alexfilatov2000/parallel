package com.filatov.lab1.task1;

import java.util.concurrent.LinkedBlockingQueue;

public class Task1 implements Runnable{

    private final Mutex mutex;

    public Task1(Mutex mutex) {
        this.mutex = mutex;
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> waitingThreads = new LinkedBlockingQueue<>();
        Mutex mutex1 = new Mutex(waitingThreads);
        Mutex mutex2 = new Mutex(waitingThreads);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                new Thread(new Task1(mutex1)).start();
            } else {
                new Thread(new Task1(mutex2)).start();
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            mutex1._notify();
        }

    }

    @Override
    public void run() {
        try {
            mutex._lock();
            mutex._wait();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        } finally {
            mutex._unlock();
        }
    }
}
