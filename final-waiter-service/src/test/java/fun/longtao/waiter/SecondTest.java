package fun.longtao.waiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondTest {
    public static void main(String[] args) {
        String code = "4444444444444448";

        //使用正则表达式判断字符串是否为16位小写字母数字组成
        String pattern = "[a-z0-9]{16}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(code);
        if (!m.matches()) {
            System.out.println("error");
            return;
        }

        //字符串逆序
        StringBuilder stringBuilder = new StringBuilder(code);
        String str = stringBuilder.reverse().toString();

        int oddSum = 0;
        int evenSum = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if ((i + 1) % 2 == 1) {
                //奇数字符
                oddSum += transferChar(aChar);
            } else {
                //偶数字符
                if (isDigital(aChar)) {
                    //
                    int even = transferChar(aChar) * 2;
                    evenSum += even > 9 ? even - 9 : even;
                }
            }
        }

        if ((oddSum + evenSum) % 10 == 0) {
            System.out.println("ok");
        } else {
            System.out.println("error");
        }


    }

    /**
     * 判断字符是否是数字
     *
     * @param aChar
     * @return
     */
    private static boolean isDigital(char aChar) {
        return aChar >= '0' && aChar <= '9';
    }

    /**
     * 将字母或数字字符转换成int
     *
     * @param aChar
     * @return
     */
    private static int transferChar(char aChar) {
        if (aChar >= 'a' && aChar <= 'z') {
            return (aChar - 'a' + 1) % 9;
        } else {
            return Integer.parseInt(String.valueOf(aChar));
        }
    }

}
