package com.zing.basic;

import java.util.Arrays;

/**
 * @author Zing
 * @date 2020-05-29
 */
public class TestAlgorithm {

    public static int fib(int n) {
        if (n <= 0) {
            return n;
        }
        int[] memo = new int[n + 1];
        return fib(memo, n);
    }

    private static int fib(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fib(memo, n - 1) + fib(memo, n - 2);
        return memo[n];
    }

    public static int dpFib(int n) {
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int dpFib2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1, cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 在求所有子问题 + 1 的最小值
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(dpFib(5));
        System.out.println(dpFib2(5));
        System.out.println(coinChange(new int[]{1, 2 ,5}, 11));
    }

}
