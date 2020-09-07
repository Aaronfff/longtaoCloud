package fun.longtao.waiter;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class FluxTest {
    public void test() throws ExecutionException, InterruptedException {
//        Flux.range(0, 5).doOnRequest(n-> log.info("Request {} number", n)).subscribe(System.out::println);
//        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        LinkedList linkedList = new LinkedList();
        linkedList.add(1, "a");

        AtomicInteger integer = new AtomicInteger(1);


        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2L);
                return "hahah";
            }
        });
        long middleTime = System.currentTimeMillis();
        String s = submit.get();
        System.out.println(s);
        long endTime = System.currentTimeMillis();

        System.out.println("middleTime:" + (middleTime - startTime));
        System.out.println("endTime:" + (endTime - startTime));

    }

    public static void main(String[] args) {
        int i = 1;
        int a = i++;
        System.out.println(a);
    }
}
