package com.zing.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, res);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track, res);
            track.removeLast();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0, res);
        return res;
    }

    private static void backtrack(char[][] board, int row, List<List<String>> res) {
        // 路径：board 中小于 row 的那些行都已经成功放置了皇后
        // 选择列表：第 row 行的所有列都是放置皇后的选择
        // 结束条件：row 超过 board 的最后一行
        if (row == board.length) {
            List<String> r = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                r.add(String.valueOf(board[i]));
            }
            res.add(r);
        } else {
            int n = board[row].length;
            for (int col = 0; col < n; col++) {
                if (isValid(board, row, col)) {
                    board[row][col] = 'Q';
                    backtrack(board, row + 1, res);
                    board[row][col] = '.';
                }
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        //check if the column had a queen before.
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(dpFib(5));
        System.out.println(dpFib2(5));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(permute(new int[]{1, 2, 5}));
        System.out.println(solveNQueens(4));
    }

}
