package fun.longtao.waiter;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;

public class PlusPlusTest {
    public static void main(String[] args) throws JsonProcessingException {
        File file = new File("C:\\Users\\wuwei\\Desktop\\苏通");
        File[] files = file.listFiles();
        for (File file1 : files) {
            file1.getParentFile().getName();
            System.out.println(file1.getName());
        }

    }

    static int get() {
        int i = 1, n = 0;
        try {
            i++;
            int b = 0 / n;
            return ++i;
        } catch (Exception e) {
            return ++i;
        } finally {
            return ++i;
        }

    }
}
