package com.tochanenko;

import com.tochanenko.lfqueue.LockFreeQueue;

public class Adder implements Runnable {
    private LockFreeQueue<Integer> queue;
    int start, end, step;

    public Adder(LockFreeQueue<Integer> queue, int start, int end, int step) {
        this.queue = queue;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i += step)
            queue.offer(i);
    }
}
