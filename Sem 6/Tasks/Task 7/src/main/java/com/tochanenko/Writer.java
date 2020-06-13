package com.tochanenko;

public class Writer implements Runnable {

    private RWPattern safeLock;
    private String name;
    private long writingTime;

    public Writer(String name, RWPattern lock) {
        this(name, lock, 250L);
    }

    public Writer(String name, RWPattern lock, long writingTime) {
        this.name = name;
        this.safeLock = lock;
        this.writingTime = writingTime;
    }

    @Override
    public void run() {
        safeLock.lockWriter();
        try {
            write();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException when writing");
            Thread.currentThread().interrupt();
        } finally {
            safeLock.unlockWriter();
        }
    }

    public void write() throws InterruptedException {
        System.out.println(String.format("%s begin", name));
        Thread.sleep(writingTime);
        System.out.println(String.format("%s finished after writing %dms", name, writingTime));
    }
}