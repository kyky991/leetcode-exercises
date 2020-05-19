package com.zing.leetcode.explore.learn.linkedlist;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Intersection of Two Linked Lists
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = 0, sizeB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null || curB != null) {
            if (curA != null) {
                sizeA++;
                curA = curA.next;
            }
            if (curB != null) {
                sizeB++;
                curB = curB.next;
            }
        }
        int offset = Math.abs(sizeA - sizeB);
        ListNode node1 = sizeA >= sizeB ? headA : headB;
        ListNode cur2 = sizeA >= sizeB ? headB : headA;
        ListNode cur1 = node1;
        while (offset > 0) {
            cur1 = cur1.next;
            offset--;
        }
        while (cur1 != null && cur2 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

    /**
     * Remove Nth Node From End of List
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        start.next = head;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    /**
     * Reverse Linked List
     */
    public ListNode reverseList(ListNode head) {
        ListNode start = new ListNode(0);
        start.next = head;

        while (head != null && head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = start.next;
            start.next = next;
        }
        return start.next;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * Remove Linked List Elements
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode start = new ListNode(0);
        start.next = head;

        ListNode cur = start;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return start.next;
    }

    /**
     * Odd Even Linked List
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;
            while (even != null && even.next != null) {
                odd.next = even.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    /**
     * Palindrome Linked List
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        slow = reverseList2(slow);
        fast = head;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    /**
     * Merge Two Sorted Lists
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
            } else if (l1 != null) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return head.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return head.next;
    }

    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }

    /**
     * Add Two Numbers
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        int add = 0;
        while (l1 != null || l2 != null || add > 0) {
            int sum = add;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            add = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }

        return newHead.next;
    }

    /**
     * Flatten a Multilevel Doubly Linked List
     */
    public MultilevelNode flatten(MultilevelNode head) {
        MultilevelNode cur = head;
        while (cur != null) {
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }
            MultilevelNode child = cur.child;
            while (child.next != null) {
                child = child.next;
            }
            child.next = cur.next;
            if (cur.next != null) {
                cur.next.prev = child;
            }
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;
        }
        return head;
    }

    /**
     * Copy List with Random Pointer
     */
    public RandomNode copyRandomList(RandomNode head) {
        Map<RandomNode, RandomNode> map = new HashMap<>();

        RandomNode newNode = new RandomNode(0);
        RandomNode cur = head;
        RandomNode newCur = newNode;

        while (cur != null) {
            RandomNode node = new RandomNode(cur.val);
            map.put(cur, node);

            newCur.next = node;
            cur = cur.next;
            newCur = newCur.next;
        }
        cur = head;
        newCur = newNode.next;
        while (cur != null) {
            if (cur.random != null) {
                newCur.random = map.get(cur.random);
            }

            cur = cur.next;
            newCur = newCur.next;
        }
        return newNode.next;
    }
}