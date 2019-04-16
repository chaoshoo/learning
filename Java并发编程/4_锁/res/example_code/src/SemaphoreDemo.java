import java.util.concurrent.*;

/**
 * @Class: SemaphoreDemo
 * @Author: chaos
 * @Date: 2019/4/16 15:06
 * @Version 1.0
 */
public class SemaphoreDemo {

    public void task(int nThread) {
        ExecutorService service = Executors.newFixedThreadPool(nThread);
        Semaphore semaphore = new Semaphore(nThread / 2);

        for (int i = 0; i < nThread; i++) {
            service.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Start run task");
                    Thread.sleep(1000);
                    System.out.println("Finish run task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        service.shutdown();
    }

    public static void main(String[] args) throws Exception {
        SemaphoreDemo demo = new SemaphoreDemo();
        demo.task(10);
    }
}
