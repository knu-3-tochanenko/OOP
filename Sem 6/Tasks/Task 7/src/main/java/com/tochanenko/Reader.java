package com.tochanenko;

public class Reader implements Runnable {

    private RWPattern safeLock;
    private String name;
    private long readingTime;

    public Reader(String name, RWPattern lock, long readingTime) {
        this.name = name;
        this.safeLock = lock;
        this.readingTime = readingTime;
    }

    @Override
    public void run() {
        safeLock.lockReader();
        try {
            read();
        } catch (InterruptedException e) {
            System.out.println( "InterruptedException when reading");
            Thread.currentThread().interrupt();
        } finally {
            safeLock.unlockReader();
        }
    }

    public void read() throws InterruptedException {
        System.out.println( String.format("%s begin", name));
        Thread.sleep(readingTime);
        System.out.println( String.format("%s finish after reading %dms", name, readingTime));
    }
}