package com.filatov.lab1.task2;

public class Task2 implements Runnable{

    private final SkipList<String> skipList;

    public Task2(SkipList<String> skipList) {
        this.skipList = skipList;
    }

    public static void main(String[] args) throws InterruptedException {
        SkipList<String> skipList = new SkipList<>(16, 0.5);
        Thread[] arr = new Thread[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread(new Task2(skipList));
            arr[i].start();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].join();
        }

        skipList.PrintList();
    }

    @Override
    public void run() {
        String currThreadName = Thread.currentThread().getName();
        System.out.println("Add " + currThreadName + ": " + skipList.add(currThreadName));

        if (currThreadName.equals("Thread-3")) {
            System.out.println("Remove " + currThreadName + ": " + skipList.remove(currThreadName));
        }
    }

}
