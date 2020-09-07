package fun.longtao.waiter;

public class ThirdTest {

    public static void main(String[] args) {
        int n = 4;
        int m = 8;

        int i = verifyMoney(n, m);
        System.out.println(i);
    }

    private static int verifyMoney(int n, int m) {
        int count = 0;
        if (m / 10 > n) {
            //总面值超过了最大可组合的数值
            return 0;
        }
        //10分游戏币可使用的最大个数
        int maxTenNum = m / 10;

        int cycleCount = 0;

        //10分游戏币个数循环
        for (int i = 0; i <= maxTenNum; i++) {
            cycleCount++;

            //减去10分游戏币后的剩余总面值
            int mWithoutTen = m - i * 10;
            int maxFiveNum = mWithoutTen / 5;
            if (maxFiveNum > n - i) {
                //减去10分游戏币后的剩余总面值超过了最大可组合的数值，继续循环
                continue;
            }
            if (mWithoutTen < n - i) {
                //减去10分游戏币后的剩余总面值不足最小可组合的数值，直接跳出当前循环
                break;
            }

            //5分游戏币个数循环
            for (int j = 0; j <= maxFiveNum; j++) {
                cycleCount++;
                int mWithoutFive = mWithoutTen - j * 5;
                int maxTwoNum = mWithoutFive / 2;
                if (maxTwoNum > n - i - j) {
                    continue;
                }
                if (mWithoutFive < n - i - j) {
                    break;
                }

                //2分游戏币个数循环
                for (int k = 0; k <= maxTwoNum; k++) {
                    cycleCount++;
                    int mWithoutTwo = mWithoutFive - k * 2;
                    if (mWithoutTwo == n - i - j - k) {
                        //当前组合剩余面值等于剩余1分游戏币个数时，组合成功
                        System.out.println("1分：" + mWithoutTwo + "  2分：" + k + "  5分：" + j + "  10分：" + i);
                        count++;
                    }
                }
            }
        }
        System.out.println(cycleCount);
        return count;
    }
}
