package com.zing.basic;

import com.zing.structure.TreeNode;

import java.util.*;

/**
 * @author Zing
 * @date 2020-05-29
 */
public class TestAlgorithm {

    public int fib(int n) {
        if (n <= 0) {
            return n;
        }
        int[] memo = new int[n + 1];
        return fib(memo, n);
    }

    private int fib(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fib(memo, n - 1) + fib(memo, n - 2);
        return memo[n];
    }

    public int dpFib(int n) {
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int dpFib2(int n) {
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

    public int coinChange(int[] coins, int amount) {
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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, res);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
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

    public List<List<String>> solveNQueens(int n) {
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

    private void backtrack(char[][] board, int row, List<List<String>> res) {
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

    private boolean isValid(char[][] board, int row, int col) {
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

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String plusOne(String cur, int i) {
        char[] chars = cur.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return String.valueOf(chars);
    }

    private String minusOne(String cur, int i) {
        char[] chars = cur.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return String.valueOf(chars);
    }

    public int openLock2(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        Set<String> visited = new HashSet<>();
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);

        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> tmp = new HashSet<>();
            for (String cur : q1) {
                if (deads.contains(cur)) {
                    continue;
                }
                if (q2.contains(cur)) {
                    return step;
                }

                visited.add(cur);

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        tmp.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        tmp.add(down);
                    }
                }
            }
            step++;

            q1 = q2;
            q2 = tmp;
        }
        return -1;
    }

    String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            System.out.println("left:" + left + " right:" + right);

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    boolean checkInclusion(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            System.out.println("left:" + left + " right:" + right);

            while (right - left >= t.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    List<Integer> findAnagrams(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (right - left >= t.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }

    int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;

                window.put(d, window.get(d) - 1);
            }

            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        TestAlgorithm algorithm = new TestAlgorithm();
        System.out.println(algorithm.fib(5));
        System.out.println(algorithm.dpFib(5));
        System.out.println(algorithm.dpFib2(5));
        System.out.println(algorithm.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(algorithm.permute(new int[]{1, 2, 5}));
        System.out.println(algorithm.solveNQueens(4));
        System.out.println(algorithm.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(algorithm.checkInclusion("ADOBECODEBANC", "ED"));
        System.out.println(algorithm.findAnagrams("ADODECODEBANC", "ED"));
        System.out.println(algorithm.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(algorithm.lengthOfLongestSubstring("bbbbb"));
    }

}
