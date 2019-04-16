import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Class: CountDownLatchDemo
 * @Author: chaos
 * @Date: 2019/4/16 11:51
 * @Version 1.0
 */
public class CountDownLatchDemo {

    public void tasks(int nThread) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);

        ExecutorService service = Executors.newFixedThreadPool(nThread);

        for (int i = 0; i < nThread; i++) {
            service.submit(() ->{
                try {
                    try {
                        System.out.println("Start run task");
                        startGate.await();
                        System.out.println("Finish run task");
                    } finally {
                        endGate.countDown();
                        System.out.println("Open End latch");
                    }
                } catch (Exception e){ }
            });
        }

        Thread.sleep(1000);

        long start = System.nanoTime();
        System.out.println("Start tasks: " + start);
        System.out.println("Open start latch: " + start);
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("End tasks: " + end);
        service.shutdown();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        demo.tasks(5);
    }
}
