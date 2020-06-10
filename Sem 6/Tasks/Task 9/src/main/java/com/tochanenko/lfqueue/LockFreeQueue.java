package com.tochanenko.lfqueue;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

public class LockFreeQueue<E> {
    private Node<E> head;
    private Node<E> tail;

    public LockFreeQueue() {
        head = tail = new Node<E>(null);
    }

    public boolean offer(E item) {
        checkNotNull(item);
        Node<E> node = new Node<E>(item);

        for (Node<E> t = tail, p = t; ; ) {
            Node<E> q = p.next;
            if (q == null) {
                if (p.casNext(null, node)) {
                    if (p != t) {
                        casTail(t, node);
                    }
                    return true;
                }
            } else if (q == p) {
                p = (t != (t = tail)) ? t : head;
            } else {
                p = (t != (t = tail)) ? t : q;
            }
        }
    }

    public void print() {
        Node<E> node = head;
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }

    public E poll() {
        restartFromHead:
        for (; ; ) {
            for (Node<E> h = head, p = h, q; ; ) {
                E item = p.item;
                if (item != null && p.casItem(item, null)) {
                    if (p != h) {
                        updateHead(h, ((q = p.next) != null ? q : p));
                    }
                    return item;
                } else if ((q = p.next) == null) {
                    updateHead(h, p);
                    return null;
                } else if (q == p) {
                    continue restartFromHead;
                } else {
                    p = q;
                }
            }
        }
    }

    private void updateHead(Node<E> h, Node<E> p) {
        if (h != p && casHead(h, p)) {
            h.lazyNext(h);
        }
    }

    private boolean casHead(Node<E> h, Node<E> p) {
        return UNSAFE.compareAndSwapObject(this, headOffset, h, p);
    }

    private boolean casTail(Node<E> t, Node<E> node) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, t, node);
    }

    private static void checkNotNull(Object item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private static final sun.misc.Unsafe UNSAFE;
    private static final long tailOffset;
    private static final long headOffset;

    static {
        try {
            Constructor con = Unsafe.class.getDeclaredConstructor();
            con.setAccessible(true);
            UNSAFE = (Unsafe) con.newInstance(null);
            Class k = LockFreeQueue.class;
            tailOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("tail"));
            headOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("head"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}