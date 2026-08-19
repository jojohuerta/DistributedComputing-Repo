package ar.edu.itba.pod.concurrency.exercises.e2;

public class SleepyThreads {
    public static void main(String[] args) throws InterruptedException {
        final Thread[] ts = new Thread[2];
        for (int i = 0; i < ts.length; i++) {
            Thread thread = new Thread(new SleeperRunnable(), "sl-" + i);
            thread.start();
            ts[i] = thread;
        }
        for (int j = 0; j < ts.length; j++){
            System.out.println(ts[j].getName());
        }
        // ts[1].interrupt();
        ts[0].join();
    }
} 