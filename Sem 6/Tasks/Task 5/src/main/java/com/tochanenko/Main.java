package com.tochanenko;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SkipList list = new SkipList(10);
        System.out.println("Start execution flow");
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(new MyProducer(i * 10, i * 10 + 10, list));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(list.toString());

        for (int i = threads.length - 1; i >= 0; --i) {
            threads[i] = new Thread(new MyConsumer(i * 10, i * 10 + 10, list));
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(list.toString());
    }
}
