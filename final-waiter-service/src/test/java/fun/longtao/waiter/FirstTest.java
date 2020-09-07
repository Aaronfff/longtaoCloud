package fun.longtao.waiter;

public class FirstTest {

    public static void main(String[] args) {
        int[] num = new int[]{10, 7, 5, 4};
        int year = 10;

        while (year > 0) {
            adjustment(num);
            year--;
        }
    }

    private static void adjustment(int[] num) {
        //临时最大值下标，默认0
        int index = 0;
        for (int i = 1; i < num.length; i++) {

            if (num[i] > num[index]) {
                //当前值大于临时最大值，则当前值下标赋值给临时最大值下标，原有最大值+1
                num[index]++;
                index = i;
            }else {
                //当前值小于或等于临时最大值，则当前值+1
                num[i]++;
            }
        }
        num[index] -= num.length - 1;
    }
}
