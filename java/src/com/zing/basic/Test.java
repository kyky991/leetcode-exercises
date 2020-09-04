package com.zing.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    //线段分割法
    private static List<Integer> divide(double money, int n) {
        //验证参数合理校验
        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
        int fen = (int) (money * 100);
        if (fen < n || n < 1) {
            System.out.println("红包个数必须大于0，并且最小红包不少于1分");
        }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //红包个数和板砖个数的关系
        while (boards.size() <= n) {
            int index = new Random().nextInt(fen - 1) + 1;
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }

        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size() - 1; i++) {
            Integer e = boards.get(i + 1) - boards.get(i);
            list.add(e);
        }
        return list;

    }

    public static void main(String[] args) {

        List<Integer> accountList = divide(50, 10);
        System.out.println(accountList);

        System.out.println(reverse(120));
        System.out.println(reverse(1000));
        System.out.println(reverse(12345));
        System.out.println(reverse(-123));
        System.out.println(reverse(Integer.MAX_VALUE));
    }

}
