package com.tochanenko;

import java.util.AbstractSet;

class MyConsumer implements Runnable {
    final int start, end;
    AbstractSet list;

    MyConsumer(int start, int end, AbstractSet list) {
        this.start = start;
        this.end = end;
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = start; i < end; ++i)
            list.remove(i);
    }
}