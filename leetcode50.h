#ifndef LEETCODE50
#define LEETCODE50

#include "common.h"

//1. Two Sum
vector<int> twoSum(vector<int>& nums, int target)
{
    vector<int> result;
    result.clear();
    for (int i = 0; i < nums.size(); ++i) {
        for (int j = i + 1; j < nums.size(); ++j) {
             if (nums[i] == target - nums[j]) {
                 result.push_back(i);
                 result.push_back(j);
                 return result;
             }
        }
    }
    return result;
}

//2. Add Two Numbers
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2)
{
    ListNode head(0);
    ListNode *tmp = &head;
    int flag = 0;
    while (l1 || l2) {
        flag += l1 ? l1->val : 0;
        flag += l2 ? l2->val : 0;

        l1 = l1 ? l1->next : NULL;
        l2 = l2 ? l2->next : NULL;

        ListNode *node = new ListNode(0);
        node->val = flag % 10;
        tmp->next = node;
        tmp = tmp->next;

        flag /= 10;
    }

    if (flag > 0) {
        ListNode *node = new ListNode(0);
        tmp->next = node;
    }

    return head.next;
}

//3. Longest Substring Without Repeating Characters
int lengthOfLongestSubstring(string s)
{
    vector<int> v(256, -1);
    int start = -1;
    int m = 0;
    for (int i = 0; i < s.size(); ++i) {
        if (v[s[i]] > start)
            start = v[s[i]];
        v[s[i]] = i;
        m = max(m, i - start);
    }
    return m;
}

//4. Median of Two Sorted Arrays
double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2)
{
    int m = nums1.size();
    int n = nums2.size();
    if (m > n)
        return findMedianSortedArrays(nums2, nums1);

    int minI = 0, maxI = m;
    int half = (m + n + 1) / 2;
    int maxL, minR;

    while (minI <= maxI) {
        int i = (minI + maxI) / 2;
        int j = half - i;
        if (i < m && nums2[j - 1] > nums1[i])
            minI = i + 1;
        else if (i > 0 && nums1[i - 1] > nums2[j])
            maxI = i - 1;
        else {
            if (i == 0)
                maxL = nums2[j - 1];
            else if (j == 0)
                maxL = nums1[i - 1];
            else
                maxL = max(nums1[i - 1], nums2[j - 1]);

            if ((m + n) % 2 == 1)
                return maxL;

            if (i == m)
                minR = nums2[j];
            else if (j == n)
                minR = nums1[i];
            else
                minR = min(nums1[i], nums2[j]);

            return (maxL + minR) / 2.0;
        }
    }
}

//5. Longest Palindromic Substring
string longestPalindrome(string s)
{
    if (s.empty())
        return "";
    if (s.size() == 1)
        return s;

    int i = 0, start = 0, maxLen = 1;
    while (i < s.size()) {
        if (s.size() - i <= maxLen / 2)
            break;

        int j = i, k = i;
        while (k < s.size() - 1 && s[k + 1] == s[k]) {
            k++;
        }
        i = k + 1;
        while (k < s.size() - 1 && j > 0 && s[k + 1] == s[j - 1]) {
            k++;
            j--;
        }
        int len = k - j + 1;
        if (len > maxLen) {
            start = j;
            maxLen = len;
        }
    }

    return s.substr(start, maxLen);
}
#endif // LEETCODE50

