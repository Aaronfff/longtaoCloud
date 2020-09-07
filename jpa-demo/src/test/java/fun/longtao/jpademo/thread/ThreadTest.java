package fun.longtao.jpademo.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public int a = 5;

    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        for (int i = 0;i<5;i++){
//            Thread thread = new Thread(myThread);
//            thread.start();
//        }

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(i);
            }

        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
