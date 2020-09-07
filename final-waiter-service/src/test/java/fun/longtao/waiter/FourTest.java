package fun.longtao.waiter;

public class FourTest {
    public static void main(String[] args) {


        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {
                for (int c = 1; c <= 9; c++) {
                    for (int d = 1; d <= 9; d++) {
                        if (a != b && c != d && a != d && b != c && (a * c) == (b * d)) {
                            System.out.println((a * 10 + b) + "*" + (c * 10 + d) + "=" + (b * 10 + a) + "*" + (d * 10 + c));
                        }
                    }
                }
            }
        }
    }
}
