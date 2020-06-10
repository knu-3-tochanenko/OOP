package com.tochanenko;

import com.tochanenko.lfqueue.LockFreeQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LockFreeQueue<Integer> queue = new LockFreeQueue<>();

        Adder adder1 = new Adder(queue, 0, 100, 1);
        Adder adder2 = new Adder(queue, 900, 999, 1);

        Thread thread1 = new Thread(adder1);
        Thread thread2 = new Thread(adder2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        queue.print();
    }


}