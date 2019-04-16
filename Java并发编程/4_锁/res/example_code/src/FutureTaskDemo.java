import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Class: FutureTaskDemo
 * @Author: chaos
 * @Date: 2019/4/16 14:43
 * @Version 1.0
 */
public class FutureTaskDemo {

    public void task() throws Exception {
        FutureTask<Long> futureTask = new FutureTask<Long>(() -> {
            Thread.sleep(1000);
            return System.nanoTime();
        });
        System.out.println("Start run task");
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(futureTask);
        System.out.println("Start get task result");
        Long result = futureTask.get();
        System.out.println("Finish get task result: " + result);
        service.shutdown();
    }

    public static void main(String[] args) throws Exception {
        FutureTaskDemo demo = new FutureTaskDemo();
        demo.task();
    }
}
