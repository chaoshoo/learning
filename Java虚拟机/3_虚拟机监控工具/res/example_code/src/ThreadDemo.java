/**
 * @Class: ThreadDemo
 * @Author: chaos
 * @Date: 2019/4/27 17:46
 * @Version 1.0
 */
public class ThreadDemo {

    private static final String lock1 = "lock1";
    private static final String lock2 = "lock2";
    private static final String lock3 = "lock3";

    public void busyThread() {
        new Thread(() -> {
            while (true) {}
        }, "busy_thread").start();
    }

    public void waitThread() {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    lock1.wait();
                } catch (Exception e) {}
            }
        }, "wait_thread").start();
    }

    public void deadLockThread () {
        new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(500);
                    synchronized (lock3) {

                    }
                } catch (Exception e) {}
            }
        }, "dead_lock_thread1").start();

        new Thread(() -> {
            synchronized (lock3) {
                try {
                    Thread.sleep(500);
                    synchronized (lock2) {

                    }
                } catch (Exception e) {}
            }
        }, "dead_lock_thread2").start();
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.busyThread();
        demo.waitThread();
        demo.deadLockThread();
    }

}
