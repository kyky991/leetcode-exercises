package com.zing.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
            cur.next = new RandomListNode(cur.label);
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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Integer.MIN_VALUE & (Integer.MIN_VALUE - 1));
        System.out.println(s.NumberOf1(Integer.MIN_VALUE));
    }
}