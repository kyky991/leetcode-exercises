package com.zing.practice;

import com.zing.leetcode.explore.learn.TreeSolution;
import com.zing.structure.ListNode;
import com.zing.structure.RandomListNode;
import com.zing.structure.TreeLinkNode;
import com.zing.structure.TreeNode;

import java.util.*;

/**
 * @author Zing
 * @date 2020-05-31
 */
public class Solution {

    public boolean find(int target, int[][] array) {
        int m = array.length;
        if (m == 0) {
            return false;
        }
        int n = array[0].length;
        if (n == 0) {
            return false;
        }
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (target == array[r][c]) {
                return true;
            } else if (target < array[r][c]) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        int space = 0;
        int oldLen = str.length();
        for (int i = 0; i < oldLen; i++) {
            if (str.charAt(i) == ' ') {
                space++;
            }
        }
        int newLen = oldLen + space * 2;
        str.setLength(newLen);

        int newIdx = newLen - 1;
        int oldIdx = oldLen - 1;
        for (; oldIdx >= 0; oldIdx--) {
            if (str.charAt(oldIdx) == ' ') {
                str.setCharAt(newIdx--, '0');
                str.setCharAt(newIdx--, '2');
                str.setCharAt(newIdx--, '%');
            } else {
                str.setCharAt(newIdx--, str.charAt(oldIdx));
            }
        }
        return str.toString();
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        printListFromTailToHead(res, listNode);
        return res;
    }

    private void printListFromTailToHead(ArrayList<Integer> res, ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(res, listNode.next);
            res.add(listNode.val);
        }
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }
        int val = pre[ps];
        int idx = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == val) {
                idx = i;
                break;
            }
        }
        int leftLen = idx - is;
        TreeNode node = new TreeNode(val);
        node.left = reConstructBinaryTree(pre, ps + 1, ps + leftLen, in, is, idx - 1);
        node.right = reConstructBinaryTree(pre, ps + leftLen + 1, pe, in, idx + 1, ie);
        return node;
    }


    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int minNumberInRotateArray(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return array[left];
    }

    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci2(int n) {
        if (n < 2) {
            return n;
        }
        int pre = 0;
        int cur = 1;
        for (int i = 2; i <= n; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    public int JumpFloor(int target) {
        if (target < 0) {
            return 0;
        } else if (target <= 2) {
            return target;
        }
        int pre = 1;
        int cur = 2;
        for (int i = 3; i <= target; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    public int JumpFloorII(int target) {
        if (target < 1) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else {
            return 2 * JumpFloorII(target - 1);
        }
    }

    public int RectCover(int target) {
        if (target < 1) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    public int NumberOf1(int n) {
        int num = 0;
        if (n < 0) {
            n = Integer.MAX_VALUE & n;
            num++;
        }
        while (n != 0) {
            if ((n & 0x01) == 1) {
                num++;
            }
            n = n >>> 1;
        }
        return num;
    }

    public int NumberOf1II(int n) {
        int num = 0;
        while (n != 0) {
            num++;
            n = n & (n - 1);
        }
        return num;
    }

    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent < 0) {
            exponent = -exponent;
            base = 1 / base;
        }
        return exponent % 2 == 1 ? base * Power(base * base, exponent / 2) : Power(base * base, exponent / 2);
    }

    public void reOrderArray(int[] array) {
        int[] odd = new int[array.length];
        int[] even = new int[array.length];
        int oddLen = 0, evenLen = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                even[evenLen++] = array[i];
            } else {
                odd[oddLen++] = array[i];
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (i < oddLen) {
                array[i] = odd[i];
            } else {
                array[i] = even[i - oddLen];
            }
        }
    }

    public void reOrderArray2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] % 2 == 1 && array[j - 1] % 2 == 0) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    public void reOrderArray3(int[] array) {
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > k) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    j--;
                }
                k++;
            }
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (k > 0) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode ReverseList(ListNode head) {
        ListNode node = new ListNode(0);
        node.next = head;

        while (head != null && head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = node.next;
            node.next = next;
        }

        return node.next;
    }

    public ListNode ReverseList2(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode node = new ListNode(0);

        ListNode cur = node;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return node.next;
    }

    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge2(list1, list2.next);
            return list2;
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }
        return false;
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    public void Mirror(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            Mirror(root.left);
            Mirror(root.right);
        }
    }

    public void Mirror2(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return res;
        }
        int col = matrix[0].length;
        int circle = (Math.min(row, col) + 1) / 2;
        for (int i = 0; i < circle; i++) {
            for (int j = i; j < col - i; j++) {
                res.add(matrix[i][j]);
            }
            for (int j = i + 1; j < row - i; j++) {
                res.add(matrix[j][col - i - 1]);
            }
            for (int j = col - i - 2; j >= i && row - i - 1 != i; j--) {
                res.add(matrix[row - i - 1][j]);
            }
            for (int j = row - i - 2; j > i && col - i - 1 != i; j--) {
                res.add(matrix[j][i]);
            }
        }
        return res;
    }

    public ArrayList<Integer> printMatrix2(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return res;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return res;
        }

        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (top != bottom) {
                for (int i = right - 1; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
            }
            if (left != right) {
                for (int i = bottom - 1; i > top; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
            top++;
            right--;
            bottom--;
        }
        return res;
    }

    public static class Min {

        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> mstack = new Stack<>();

        public void push(int node) {
            stack.push(node);
            if (mstack.empty() || node <= mstack.peek()) {
                mstack.push(node);
            }
        }

        public void pop() {
            if (stack.peek().equals(mstack.peek())) {
                mstack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return mstack.peek();
        }
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        PrintFromTopToBottom(root, res, 0);
        ArrayList<Integer> r = new ArrayList<>();
        for (ArrayList<Integer> re : res) {
            r.addAll(re);
        }
        return r;
    }

    private void PrintFromTopToBottom(TreeNode node, ArrayList<ArrayList<Integer>> res, int level) {
        if (node != null) {
            if (res.size() == level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            PrintFromTopToBottom(node.left, res, level + 1);
            PrintFromTopToBottom(node.right, res, level + 1);
        }
    }

    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    res.add(node.val);

                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }

        return res;
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean VerifySquenceOfBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }

        int val = sequence[end];
        int idx = start;

        while (idx < end && sequence[idx] < val) {
            idx++;
        }

        for (int i = idx + 1; i < end; i++) {
            if (sequence[i] < val) {
                return false;
            }
        }

        return VerifySquenceOfBST(sequence, start, idx - 1) && VerifySquenceOfBST(sequence, idx, end - 1);
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        FindPath(root, target, res, track);
        return res;
    }

    private void FindPath(TreeNode node, int target, ArrayList<ArrayList<Integer>> res, LinkedList<Integer> track) {
        if (node == null) {
            return;
        }

        track.add(node.val);
        if (node.left == null && node.right == null && target == node.val) {
            res.add(new ArrayList<>(track));
        } else {
            FindPath(node.left, target - node.val, res, track);
            FindPath(node.right, target - node.val, res, track);
        }
        track.removeLast();
    }

    public RandomListNode Clone(RandomListNode pHead) {
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode next = cur.next;
            cur.next = new RandomListNode(cur.val);
            cur.next.next = next;
            cur = next;
        }

        cur = pHead;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = pHead;
        RandomListNode newHead = new RandomListNode(0);
        RandomListNode newCur = newHead;
        while (cur != null) {
            RandomListNode next = cur.next.next;

            newCur.next = cur.next;
            cur.next = next;

            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead.next;
    }

    private TreeNode pre = null;

    public TreeNode Convert(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode head = Convert(root.left);
        if (head == null) {
            head = root;
        }

        root.left = pre;
        if (pre != null) {
            pre.right = root;
        }
        pre = root;

        Convert(root.right);

        return head;
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str.length() == 0) {
            return res;
        }
        backtrack(str.toCharArray(), 0, res);
        Collections.sort(res);
        return new ArrayList<>(res);
    }

    private void backtrack(char[] chars, int i, ArrayList<String> res) {
        if (i == chars.length - 1) {
            String val = String.valueOf(chars);
            if (!res.contains(val)) {
                res.add(val);
            }
        } else {
            for (int j = i; j < chars.length; j++) {
                swap(chars, i, j);
                backtrack(chars, i + 1, res);
                swap(chars, i, j);
            }
        }
    }

    private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public int MoreThanHalfNum(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int result = array[0];
        int cnt = 1;
        for (int i = 1; i < array.length; i++) {
            if (cnt == 0) {
                result = array[i];
                cnt = 1;
            } else if (result == array[i]) {
                cnt++;
            } else {
                cnt--;
            }
        }

        cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                cnt++;
            }
        }
        return (cnt > array.length / 2) ? result : 0;
    }

    public ArrayList<Integer> GetLeastNumbers(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length <= 0 || input.length < k) {
            return res;
        }

        for (int i = input.length / 2 + 1; i >= 0; i--) {
            adjustHeap(input, i, input.length);
        }
        for (int i = input.length - 1; i >= 0; i--) {
            int tmp = input[0];
            input[0] = input[i];
            input[i] = tmp;

            adjustHeap(input, 0, i);
        }

        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    private void adjustHeap(int[] arr, int start, int end) {
        int tmp = arr[start];
        for (int k = 2 * start + 1; k < end; k = 2 * k + 1) {
            if (k + 1 < end && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > tmp) {
                arr[start] = arr[k];
                start = k;
            } else {
                break;
            }
        }
        arr[start] = tmp;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        int res = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i] + max, array[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public int NumberOf1Between1AndN(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = i;
            while (tmp != 0) {
                if (tmp % 10 == 1) {
                    res++;
                }
                tmp = tmp / 10;
            }
        }
        return res;
    }

    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int len = numbers.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    public int GetUglyNumber(int index) {
        if (index < 7) {
            return index;
        }
        int p2 = 0, p3 = 0, p5 = 0, num = 1;
        List<Integer> arr = new ArrayList<>();
        arr.add(num);
        while (arr.size() < index) {
            num = Math.min(arr.get(p2) * 2, Math.min(arr.get(p3) * 3, arr.get(p5) * 5));
            if (arr.get(p2) * 2 == num) {
                p2++;
            }
            if (arr.get(p3) * 3 == num) {
                p3++;
            }
            if (arr.get(p5) * 5 == num) {
                p5++;
            }
            arr.add(num);
        }
        return num;
    }

    public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int FirstNotRepeatingChar2(String str) {
        int[] words = new int[58];
        for (int i = 0; i < str.length(); i++) {
            words[((int) str.charAt(i)) - 65]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (words[((int) str.charAt(i)) - 65] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int InversePairs(int[] array) {
        int[] tmp = new int[array.length];
        return InversePairs(array, tmp, 0, array.length - 1);
    }

    private int InversePairs(int[] arr, int[] tmp, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }
        int mid = (lo + hi) >>> 1;
        int leftCount = InversePairs(arr, tmp, lo, mid) % 1000000007;
        int rightCount = InversePairs(arr, tmp, mid + 1, hi) % 1000000007;

        int cnt = 0;

        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                cnt += mid - i + 1;
                if (cnt >= 1000000007) {
                    cnt = cnt % 1000000007;
                }
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= hi) {
            tmp[k++] = arr[j++];
        }
        for (int m = lo; m <= hi; m++) {
            arr[m] = tmp[m - lo];
        }

        return (cnt + leftCount + rightCount) % 1000000007;
    }

    private int InversePairs2(int[] arr, int[] tmp, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }
        int mid = (lo + hi) >>> 1;
        int leftCount = InversePairs2(arr, tmp, lo, mid) % 1000000007;
        int rightCount = InversePairs2(arr, tmp, mid + 1, hi) % 1000000007;

        int cnt = 0;

        int i = mid, j = hi, k = hi;
        while (i >= lo && j >= mid + 1) {
            if (arr[i] <= arr[j]) {
                tmp[k--] = arr[j--];
            } else {
                cnt += j - mid;
                if (cnt >= 1000000007) {
                    cnt = cnt % 1000000007;
                }
                tmp[k--] = arr[i--];
            }
        }
        while (i >= lo) {
            tmp[k--] = arr[i--];
        }
        while (j >= mid + 1) {
            tmp[k--] = arr[j--];
        }
        for (int m = lo; m <= hi; m++) {
            arr[m] = tmp[m];
        }
        return (cnt + leftCount + rightCount) % 1000000007;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
        while (cur1 != cur2) {
            cur1 = cur1 != null ? cur1.next : pHead2;
            cur2 = cur2 != null ? cur2.next : pHead1;
        }
        return cur1;
    }

    public int GetNumberOfK(int[] array, int k) {
        if (array.length == 0) {
            return 0;
        }
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] - min]++;
        }
        if (k - min < 0 || k - min >= bucket.length) {
            return 0;
        }
        return bucket[k - min];
    }

    public int GetNumberOfK2(int[] array, int k) {
        int left = 0, right = array.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int first = left;

        left = 0;
        right = array.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int last = right - 1;
        return last - first + 1;
    }

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    public boolean IsBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int offset = Math.abs(TreeDepth(root.left) - TreeDepth(root.right));
        return offset <= 1 && IsBalanced(root.left) && IsBalanced(root.right);
    }

    public boolean IsBalanced2(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res = res ^ array[i];
        }
        int index = 0;
        while (res != 0 && index < 32) {
            if ((res & 1) == 1) {
                break;
            }
            res = res >> 1;
            index++;
        }

        for (int i = 0; i < array.length; i++) {
            if (bit1(array[i], index)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    private boolean bit1(int val, int index) {
        return ((val >> index) & 1) == 1;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = (int) Math.sqrt(2 * sum); i >= 2; i--) {
            if ((i & 1) == 1 && sum % i == 0 || (sum % i) * 2 == i) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 0, k = (sum / i) - (i - 1) / 2; j < i; j++, k++) {
                    list.add(k);
                }
                res.add(list);
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int lo = 1, hi = 2;
        while (lo < hi) {
            int cur = (lo + hi) * (hi - lo + 1) / 2;
            if (cur == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = lo; i <= hi; i++) {
                    list.add(i);
                }
                res.add(list);
                lo++;
            } else if (cur < sum) {
                hi++;
            } else {
                lo++;
            }
        }
        return res;
    }

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int val = array[i];
            int j = i;
            int left = i + 1, right = array.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                int target = sum - val;
                if (array[mid] == target) {
                    j = mid;
                    break;
                } else if (array[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            int mul = array[i] * array[j];
            if (i != j && mul < min) {
                res = new ArrayList<>();
                res.add(array[i]);
                res.add(array[j]);
                min = mul;
            }
        }
        return res;
    }

    public ArrayList<Integer> FindNumbersWithSum2(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();

        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] + array[j] == sum) {
                res.add(array[i]);
                res.add(array[j]);
                return res;
            } else if (array[i] + array[j] > sum) {
                j--;
            } else {
                i++;
            }
        }

        return res;
    }

    public String LeftRotateString(String str, int n) {
        if (str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        n = n % str.length();
        for (int i = 0; i < n; i++) {
            char c = sb.charAt(0);
            for (int j = 1; j < sb.length(); j++) {
                sb.setCharAt(j - 1, sb.charAt(j));
            }
            sb.setCharAt(sb.length() - 1, c);
        }
        return sb.toString();
    }

    public String LeftRotateString2(String str, int n) {
        int len = str.length();
        if (len == 0) {
            return str;
        }
        n = n % len;
        str += str;
        return str.substring(n, len + n);
    }

    public String LeftRotateString3(String str, int n) {
        int len = str.length();
        if (len == 0) {
            return str;
        }
        n = n % len;
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        reverse(chars, 0, chars.length - 1 - n);
        reverse(chars, chars.length - n, chars.length - 1);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    public String ReverseSentence(String str) {
        String tmp = "";
        StringBuilder res = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == ' ') {
                res.append(tmp);
                res.append(c);
                tmp = "";
            } else {
                tmp = c + tmp;
            }
        }
        res.append(tmp);
        return res.toString();
    }

    public boolean isContinuous(int[] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        int min = 14, max = -1;
        int[] d = new int[14];
        //d[0] = -5;
        for (int i = 0; i < numbers.length; i++) {
            d[numbers[i]]++;
            if (numbers[i] == 0) {
                continue;
            }
            if (d[numbers[i]] > 1) {
                return false;
            }
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return max - min < 5;
    }

    public int LastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        int[] arr = new int[n];
        int i = -1, step = 0, count = n;
        while (count > 0) {
            i++;
            if (i >= n) {
                i = 0;
            }
            if (arr[i] == -1) {
                continue;
            }
            step++;
            if (step == m) {
                arr[i] = -1;
                step = 0;
                count--;
            }
        }
        return i;
    }

    public int Sum(int n) {
        int i = n;
        boolean f = (i > 0) && ((i += Sum(n - 1)) > 0);
        return i;
    }

    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int tmp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = tmp;
        }
        return num1;
    }

    public int StrToInt(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int res = 0;
        boolean negative = false;
        char[] chars = str.toCharArray();
        int limit = -Integer.MAX_VALUE;
        int multmin;

        int i = 0;
        int n = chars.length;
        if (n > 0) {
            char a = chars[0];
            if (a < '0') {
                if (a == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (a != '+') {
                    return 0;
                }
                if (n == 1) {
                    return 0;
                }
                i++;
            }

            // limit 一直是负数
            multmin = limit / 10;
            while (i < n) {
                int d = chars[i++] - '0';
                if (d < 0 || d > 9) {
                    return 0;
                }
                if (res < multmin) {
                    return 0;
                }
                res *= 10;
                if (res < limit + d) {
                    return 0;
                }
                res -= d;
            }
        } else {
            return 0;
        }
        return negative ? res : -res;
    }

    public boolean duplicate(int numbers[], int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }

            numbers[index] += length;
        }
        return false;
    }

    public boolean duplicate2(int numbers[], int length, int[] duplication) {
        boolean[] b = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (b[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            b[numbers[i]] = true;
        }
        return false;
    }

    public int[] multiply(int[] A) {
        int len = A.length;
        int[] B = new int[len];
        if (len != 0) {
            B[0] = 1;
            for (int i = 1; i < len; i++) {
                B[i] = B[i - 1] * A[i - 1];
            }
            int tmp = 1;
            for (int i = len - 2; i >= 0; i--) {
                tmp *= A[i + 1];
                B[i] *= tmp;
            }
        }
        return B;
    }

    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
                return match(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || match(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || match(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return match(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
            return match(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    public boolean isNumeric(char[] str) {
        int len = str.length;
        boolean sign = false, decimal = false, e = false;
        for (int i = 0; i < len; i++) {
            char c = str[i];
            if (c == 'e' || c == 'E') {
                // e后面一定要接数字
                if (i == len - 1) {
                    return false;
                }
                // 不能同时存在两个e
                if (e) {
                    return false;
                }
                e = true;
            } else if (c == '+' || c == '-') {
                // 第二次出现+-符号，则必须紧接在e之后
                if (sign && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
                // 第一次出现+-符号，且不是在字符串开头，则也必须紧接在e之后
                if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
                sign = true;
            } else if (c == '.') {
                // e后面不能接小数点，小数点不能出现两次
                if (e || decimal) {
                    return false;
                }
                decimal = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public boolean isNumeric2(char[] str) {
        return String.valueOf(str).matches("[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?");
    }

    int[] hash = new int[256];
    StringBuilder sb = new StringBuilder();

    //Insert one char from stringstream
    public void Insert(char ch) {
        sb.append(ch);
        hash[ch]++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (char c : sb.toString().toCharArray()) {
            if (hash[c] == 1) {
                return c;
            }
        }
        return '#';
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = pHead;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode node = new ListNode(0);
        node.next = pHead;

        ListNode cur = node;
        ListNode next = cur.next;
        while (next != null) {
            if (next.next != null && next.val == next.next.val) {
                while (next.next != null && next.val == next.next.val) {
                    next = next.next;
                }
                cur.next = next.next;
                next = next.next;
            } else {
                cur = cur.next;
                next = next.next;
            }
        }
        return node.next;
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            //如果有右子树，则找右子树的最左节点
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        //没右子树，则找第一个当前节点是父节点左孩子的节点
        while (pNode.next != null) {
            if (pNode == pNode.next.left) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            return left.val == right.val && isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
        }
    }

    boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot.left);
        queue.offer(pRoot.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> PrintZ(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);

        Stack<TreeNode> s2 = new Stack<>();

        int level = 1;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (level % 2 == 1) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.isEmpty()) {
                    TreeNode node = s1.pop();
                    if (node != null) {
                        list.add(node.val);
                        s2.push(node.left);
                        s2.push(node.right);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    level++;
                }
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s2.isEmpty()) {
                    TreeNode node = s2.pop();
                    if (node != null) {
                        list.add(node.val);
                        s1.push(node.right);
                        s1.push(node.left);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    level++;
                }
            }
        }
        return res;
    }

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Print2(pRoot, res, 0);
        return res;
    }

    void Print2(TreeNode node, ArrayList<ArrayList<Integer>> res, int level) {
        if (node != null) {
            if (res.size() == level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            Print2(node.left, res, level + 1);
            Print2(node.right, res, level + 1);
        }
    }

    public static class Codec {

        String Serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Serialize(root, sb);
            return sb.toString();
        }

        private void Serialize(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                Serialize(node.left, sb);
                Serialize(node.right, sb);
            }
        }

        TreeNode Deserialize(String str) {
            return Deserialize(new LinkedList<>(Arrays.asList(str.split(","))));
        }

        private TreeNode Deserialize(Queue<String> list) {
            if (list.isEmpty()) {
                return null;
            }
            String s = list.poll();
            if ("#".equals(s)) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(s));
                node.left = Deserialize(list);
                node.right = Deserialize(list);
                return node;
            }
        }
    }

    TreeNode KthNode(TreeNode pRoot, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRoot;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            k--;
            if (k == 0) {
                return node;
            }
            node = node.right;
        }
        return null;
    }

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    int count = 0;

    public void Insert(Integer num) {
        if (count % 2 == 0) {
            maxHeap.offer(num);
            int max = maxHeap.poll();
            minHeap.offer(max);
        } else {
            minHeap.offer(num);
            int min = minHeap.poll();
            maxHeap.offer(min);
        }
        count++;
    }

    public Double GetMedian() {
        if (count % 2 == 0) {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            return (double) minHeap.peek();
        }
    }

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) {
            return res;
        }
        int begin;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            begin = i - size + 1;
            if (!q.isEmpty() && begin > q.peekFirst()) {
                q.pollFirst();
            }

            while (!q.isEmpty() && num[q.peekLast()] <= num[i]) {
                q.pollLast();
            }
            q.add(i);
            if (begin >= 0) {
                res.add(num[q.peekFirst()]);
            }
        }
        return res;
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (str.length == 0) {
            return false;
        }
        int[] flag = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPath(matrix, rows, cols, i, j, str, 0, flag)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[] matrix, int rows, int cols, int row, int col, char[] str, int index, int[] flag) {
        int k = row * cols + col;
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[k] != str[index] || flag[k] == 1) {
            return false;
        }
        if (str.length - 1 == index) {
            return true;
        } else {
            flag[k] = 1;
            if (hasPath(matrix, rows, cols, row - 1, col, str, index + 1, flag)
                    || hasPath(matrix, rows, cols, row + 1, col, str, index + 1, flag)
                    || hasPath(matrix, rows, cols, row, col - 1, str, index + 1, flag)
                    || hasPath(matrix, rows, cols, row, col + 1, str, index + 1, flag)) {
                return true;
            }
            flag[k] = 0;
            return false;
        }
    }

    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] track = new boolean[rows][cols];
        return movingCount(threshold, rows, cols, 0, 0, track);
    }

    private int movingCount(int threshold, int rows, int cols, int row, int col, boolean[][] track) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || track[row][col] || sum(row) + sum(col) > threshold) {
            return 0;
        }

        track[row][col] = true;
        return movingCount(threshold, rows, cols, row - 1, col, track)
                + movingCount(threshold, rows, cols, row + 1, col, track)
                + movingCount(threshold, rows, cols, row, col - 1, track)
                + movingCount(threshold, rows, cols, row, col + 1, track)
                + 1;
    }

    private int sum(int n) {
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }

    public int cutRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max = 0;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int cutRope2(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int x = n % 3;
        int y = n / 3;
        if (x == 0) {
            return (int) Math.pow(3, y);
        } else if (x == 1) {
            return 2 * 2 * (int) Math.pow(3, y - 1);
        } else {
            return 2 * (int) Math.pow(3, y);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Integer.MIN_VALUE & (Integer.MIN_VALUE - 1));
        System.out.println(s.NumberOf1(Integer.MIN_VALUE));
        System.out.println(s.Sum(5));
        System.out.println(s.StrToInt("-156a156"));
        System.out.println(s.match("".toCharArray(), ".*".toCharArray()));
        System.out.println(s.isNumeric2("12e+4.3".toCharArray()));

        TreeSolution.Codec codec = new TreeSolution.Codec();
        TreeNode root = codec.deserialize("8,6,5,null,null,7,null,null,10,9,null,null,11,null,null");

        Codec cd = new Codec();
        TreeNode root2 = cd.Deserialize("8,6,5,#,#,7,#,#,10,9,#,#,11,#,#");

        System.out.println(cd.Serialize(root));
        System.out.println(codec.serialize(root2));

        System.out.println(s.PrintZ(root2));
        System.out.println(s.Print2(root2));

        char c = '1';
        System.out.println((int) c);
    }
}
