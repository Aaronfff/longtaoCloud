package fun.longtao.jpademo;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    @Test
    public void test() {
        List<String> aaa = new ArrayList<>();

        aaa.add("aaa");
        aaa.add("bbb");
        aaa.add("ccc");
        aaa.add("ddd");
        aaa.add("eee");

        List<String> bbb = aaa.subList(0, 3);

        aaa.clear();
        aaa.add("one");
        aaa.add("two");
        aaa.add("three");

//        bbb.clear();
//        bbb.add("one");
//        bbb.add(" seven");
//        bbb.remove(0);

        bbb.add("one");

        for (String s : bbb) {
            System.out.println(s);
        }

        for (String s : aaa) {
            System.out.println(s);
        }
    }

    @Test
    public void test2(){

    }
}
