public class ThreadLogger {

    public static Thread logThreads(ThreadGroup group) {
        Runnable log = () -> {
            while (group.activeCount() > 0) {
                Thread[] threads = new Thread[group.activeCount()];
                int count = group.enumerate(threads);
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    str.append(threads[i]);
                    str.append("\n");
                }
                System.out.println("Group " + group.getName() + ";\n" +
                        "Threads: " + str.toString() + "\n");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread logThread = new Thread(log);
        logThread.start();
        return logThread;
    }
}
