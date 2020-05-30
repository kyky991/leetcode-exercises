package com.zing.basic;

/**
 * @author Zing
 * @date 2020-05-30
 */
public class Test {

    public static int reverse(int n) {
        int res = 0;
        while (n != 0) {
            if ((n * 10L) > Integer.MAX_VALUE || (n * 10L) < Integer.MIN_VALUE) {
                return 0;
            }
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(120));
        System.out.println(reverse(1000));
        System.out.println(reverse(12345));
        System.out.println(reverse(-123));
        System.out.println(reverse(Integer.MAX_VALUE));
    }

}
