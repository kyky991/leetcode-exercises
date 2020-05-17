package com.zing.leetcode.explore.learn.linkedlist;

/**
 * @author Zing
 * @date 2020-05-17
 */
public class LinkedListSolution {

    /**
     * Linked List Cycle
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Linked List Cycle II
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != entry) {
                    slow = slow.next;
                    entry = entry.next;
                }
                return entry;
            }
        }
        return null;
    }
}
