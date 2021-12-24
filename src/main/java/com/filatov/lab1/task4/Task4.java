package com.filatov.lab1.task4;

public class Task4 implements Runnable{

    private final HarrisOrderedList<String> harrisOrderedList;

    public Task4(HarrisOrderedList<String> harrisOrderedList) {
        this.harrisOrderedList = harrisOrderedList;
    }

    public static void main(String[] args) throws InterruptedException {
        HarrisOrderedList<String> harrisOrderedList = new HarrisOrderedList<>();
        Thread[] arr = new Thread[10];

        for (int i = 0; i < 5; i++) {
            arr[i] = new Thread(new Task4(harrisOrderedList));
            arr[i].start();
        }

        for (int i = 0; i < 5; i++) {
            arr[i].join();
        }

        harrisOrderedList.PrintList();
    }

    @Override
    public void run() {
        String currThreadName = Thread.currentThread().getName();
        harrisOrderedList.add(currThreadName);

        if (currThreadName.equals("Thread-4")) {
            harrisOrderedList.remove(currThreadName);
            System.out.println("Remove: " + currThreadName);
        }
    }

}
