import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadLoggerTest {

    private int time1 = 7000;
    private int time2 = 8000;

    @Test
    public void logThreads() throws InterruptedException {
        ThreadGroup group = new ThreadGroup(Thread.currentThread().getThreadGroup(), "group1");
        new Thread(group, new ThreadRunner(time1)).start();
        new Thread(group, new ThreadRunner(time1)).start();
        new Thread(group, new ThreadRunner(time1)).start();
        Thread logger = ThreadLogger.logThreads(group);
        assertTrue(logger.isAlive());
        Thread.sleep(time2 * 2);
        assertFalse(logger.isAlive());
    }
}