package fun.longtao.jpademo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class MyThread implements Runnable {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));


    @Override
    public void run() {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = local.get();
        System.out.println("------"+Thread.currentThread().getId()+"----------");
        if(simpleDateFormat != null){
            String format = simpleDateFormat.format(date);
            System.out.println(System.identityHashCode(simpleDateFormat));
            System.out.println(format);
        }

        System.out.println("------线程执行结束------");
    }
}
