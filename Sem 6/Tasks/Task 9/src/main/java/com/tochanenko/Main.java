package com.tochanenko;

import com.tochanenko.lfqueue.LockFreeQueue;

public class Main {
    public static void main(String[] args) {
        LockFreeQueue<Integer> Queue = new LockFreeQueue<Integer>();

        Thread[] thread = new Thread[8];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(() -> {
                int t = (int) (Math.random() * 100);
                Queue.enq(t);
            });
        }

        for (Thread thread1 : thread) {
            thread1.start();
        }

        for (Thread thread1 : thread) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Queue.show(Queue);

        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(() -> {
                Queue.deq();
            });
        }

        for (Thread thread1 : thread) {
            thread1.start();
        }

        for (Thread thread1 : thread) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Queue.show(Queue);
    }
}
