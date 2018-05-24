#ifndef LEETCODEEASYLINKEDLIST
#define LEETCODEEASYLINKEDLIST

#include "common.h"

//21. Merge Two Sorted Lists
ListNode* mergeTwoLists(ListNode* l1, ListNode* l2)
{
    ListNode head(0);
    ListNode *tmp = &head;
    while (l1 && l2) {
        if (l1->val < l2->val) {
            tmp->next = l1;
            l1 = l1->next;
        } else {
            tmp->next = l2;
            l2 = l2->next;
        }
        tmp = tmp->next;
    }
    tmp->next = l1 ? l1 : l2;

    return head.next;
}

//83. Remove Duplicates from Sorted List
ListNode* deleteDuplicates(ListNode* head)
{
    ListNode *tmp = head;
    while (tmp && tmp->next) {
        if (tmp->next->val == tmp->val) {
            tmp->next = tmp->next->next;
        } else {
            tmp = tmp->next;
        }
    }
    return head;
}

//141. Linked List Cycle
bool hasCycle(ListNode *head)
{
    ListNode *tmp = head;
    ListNode *tmp1 = head;
    while (tmp && tmp1 && tmp1->next) {
        tmp = tmp->next;
        tmp1 = tmp1->next->next;

        if (tmp == tmp1)
            return true;
    }
    return false;
}

//160. Intersection of Two Linked Lists
ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
    ListNode *tmp1 = headA;
    ListNode *tmp2 = headB;
    if (tmp1 == NULL || tmp2 == NULL)
        return NULL;

    while (tmp1 != NULL && tmp2 != NULL && tmp1 != tmp2) {
        tmp1 = tmp1->next;
        tmp2 = tmp2->next;

        if (tmp1 == tmp2)
            return tmp1;

        if (tmp1 == NULL)
            tmp1 = headB;
        if (tmp2 == NULL)
            tmp2 = headA;
    }

    return tmp1;
}

//203. Remove Linked List Elements
ListNode* removeElements(ListNode* head, int val)
{
    //////////////////////////////
    if (head == NULL)
        return NULL;
    head->next = removeElements(head->next, val);
    if (head->val == val)
        return head->next;
    else
        return head;

    /////////////////////////////
    ListNode h(0);
    h.next = head;
    ListNode *tmp = &h;
    while (tmp->next) {
        if (tmp->next->val == val) {
            tmp->next = head->next;
        } else {
            tmp = tmp->next;
        }
        head = head->next;
    }
    return h.next;
}

//206. Reverse Linked List
ListNode* reverseList(ListNode* head)
{
    if (head == NULL)
        return NULL;

    ListNode *p = head->next;
    head->next = NULL;
    while (p) {
        ListNode *tmp = p->next;
        p->next = head;
        head = p;
        p = tmp;
    }
    return head;
}

//234. Palindrome Linked List
bool isPalindrome(ListNode* head)
{
    ListNode *p = new ListNode(0);
    ListNode *tmp = head;
    while (tmp) {
        ListNode *node = new ListNode(0);
        p->val = tmp->val;
        node->next = p;
        p = node;

        tmp = tmp->next;
    }

    tmp = head;
    while (tmp) {
        if (tmp->val != p->val) {
            return false;
        }
        tmp = tmp->next;
        p = p->next;
    }
    return true;
}

//237. Delete Node in a Linked List
void deleteNode(ListNode* node)
{
    node->val = node->next->val;
    node->next = node->next->next;
}
#endif // LEETCODEEASYLINKEDLIST

