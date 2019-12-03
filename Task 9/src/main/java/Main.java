public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "group1");
        ThreadGroup group2 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "group2");

        ThreadLogger.logThreads(group1);
        ThreadLogger.logThreads(group2);

        new Thread(group1, new ThreadRunner(7000)).start();
        new Thread(group1, new ThreadRunner(2000)).start();
        new Thread(group2, new ThreadRunner(5000)).start();
        Thread.sleep(6000);
        new Thread(group1, new ThreadRunner(10000)).start();
        new Thread(group2, new ThreadRunner(15000)).start();
    }
}
