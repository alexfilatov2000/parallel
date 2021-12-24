package com.filatov.lab1.task3;

public class Task3 implements Runnable {

    private final MichaelAndScottQueue<String> michaelAndScottQueue;

    public Task3(MichaelAndScottQueue<String> michaelAndScottQueue) {
        this.michaelAndScottQueue = michaelAndScottQueue;
    }

    public static void main(String[] args) throws InterruptedException {
        MichaelAndScottQueue<String> michaelAndScottQueue = new MichaelAndScottQueue<>();
        Thread[] arr = new Thread[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread(new Task3(michaelAndScottQueue));
            arr[i].start();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].join();
        }

        michaelAndScottQueue.PrintList();
    }


    @Override
    public void run() {
        michaelAndScottQueue.add(Thread.currentThread().getName());
        if (Math.random() > 0.5) {
            michaelAndScottQueue.remove();
        }
    }
}
