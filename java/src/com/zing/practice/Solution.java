package com.zing.practice;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Integer.MIN_VALUE & (Integer.MIN_VALUE - 1));
        System.out.println(s.NumberOf1(Integer.MIN_VALUE));
    }
}
