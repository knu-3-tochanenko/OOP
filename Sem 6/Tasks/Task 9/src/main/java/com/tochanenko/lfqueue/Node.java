package com.tochanenko.lfqueue;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

class Node<E> {
    volatile E item;
    volatile Node<E> next;

    Node(E item) {
        UNSAFE.putObject(this, itemOffset, item);
    }

    boolean casItem(E oldItem, E it) {
        return UNSAFE.compareAndSwapObject(this, itemOffset, oldItem, it);
    }

    boolean casNext(Node<E> oldNext, Node<E> n) {
        return UNSAFE.compareAndSwapObject(this, nextOffset, oldNext, n);
    }

    public void lazyNext(Node<E> h) {
        UNSAFE.putOrderedObject(this, nextOffset, h);
    }

    private static final sun.misc.Unsafe UNSAFE;
    private static final long itemOffset;
    private static final long nextOffset;

    static {
        try {
            Constructor con = sun.misc.Unsafe.class.getDeclaredConstructor();
            con.setAccessible(true);
            UNSAFE = (Unsafe) con.newInstance(null);
            Class k = Node.class;
            itemOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("item"));
            nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }


}