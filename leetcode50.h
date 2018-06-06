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
string longestPalindromeSubstring(string s)
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

//6. ZigZag Conversion
string convert(string s, int numRows)
{
    vector<string> ss(numRows);
    int size = s.size();

    int i = 0;
    while (i < size) {
        for (int m = 0; m < numRows && i < size; ++m) {
            ss[m].push_back(s[i++]);
        }
        for (int n = numRows - 2; n > 0 && i < size; --n) {
            ss[n].push_back(s[i++]);
        }
    }
    string res = "";
    for (int i = 0; i < ss.size(); ++i) {
        res += ss[i];
    }
    return res;
}

//7. Reverse Integer
int reverse(int x) {
    long result = 0;
    while (x != 0) {
        result = result * 10 + x % 10;
        x /= 10;
        if (result < INT_MIN || result > INT_MAX)
            return 0;
    }
    return result;
}

//8. String to Integer (atoi)
int myAtoi(string str)
{
    int sign = 1, base = 0, i = 0;
    while (str[i] == ' ') {
        i++;
    }
    if (str[i] == '-' || str[i] == '+') {
        sign = 1 - 2 * (str[i++] == '-');
    }

    while (str[i] >= '0' && str[i] <= '9') {
        if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str[i] - '0' > 7)) {
            if (sign == 1)
                return INT_MAX;
            else
                return INT_MIN;
        }
        base = 10 * base + (str[i++] - '0');
    }
    return base * sign;
}

//9. Palindrome Number
bool isPalindrome(int x)
{
    if (x < 0 || (x != 0 && x % 10 == 0))
        return false;
    int result = 0;
    while (x > result) {
        result = result * 10 + x % 10;
        x /= 10;
    }
    return (result == x || x == result / 10);
}

//10. Regular Expression Matching
bool isMatch(string s, string p)
{
    int m = s.size();
    int n = p.size();

    vector<vector<bool>> f(m + 1, vector<bool>(n + 1, false));
    f[0][0] = true;

    for (int i = 1; i <= m; ++i) {
        f[i][0] = false;
    }
    for (int j = 1; j <= n; ++j) {
        f[0][j] = j > 1 && '*' == p[j - 1] && f[0][j - 2];
    }

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] != '*') {
                f[i][j] = f[i - 1][j - 1] &&(s[i - 1] == p[j - 1] || '.' == p[j - 1]);
            } else {
                f[i][j] = f[i][j - 2] || (s[i - 1] == p[j - 2] || '.' == p[j - 2]) && f[i - 1][j];
            }
        }
    }

    return f[m][n];
}

//11. Container With Most Water
int maxArea(vector<int>& height)
{
    int res = 0;
    int i = 0, j = height.size() - 1;
    while (i < j) {
        int h = min(height[i], height[j]);
        res = max(res, (j - i) * h);
        while (height[i] <= h && i < j)
            i++;
        while (height[j] <= h && i < j)
            j--;
    }
    return res;
}

//12. Integer to Roman
string intToRoman(int num)
{
    string res = "";

    int q = num / 1000;
    int r = num % 1000;
    if (q > 0) {
        res.append(q, 'M');
    }

    q = r;
    r = q % 100;
    q = q / 100;
    if (q >= 9) {
        res += "CM";
    } else if (q >= 5) {
        res += 'D';
        res.append(q - 5, 'C');
    } else if (q >= 4) {
        res += "CD";
    } else {
        res.append(q, 'C');
    }

    q = r;
    r = q % 10;
    q = q / 10;
    if (q >= 9) {
        res += "XC";
    } else if (q >= 5) {
        res += 'L';
        res.append(q - 5, 'X');
    } else if (q >= 4) {
        res += "XL";
    } else {
        res.append(q, 'X');
    }

    q = r;

    if (q >= 9) {
        res += "IX";
    } else if (q >= 5) {
        res += 'V';
        res.append(q - 5, 'I');
    } else if (q >= 4) {
        res += "IV";
    } else {
        res.append(q, 'I');
    }
    return res;
}

//13. Roman to Integer
int romanToInt(string s)
{
    unordered_map<char, int> lut = {
        { 'I', 1 },
        { 'V', 5 },
        { 'X', 10 },
        { 'L', 50 },
        { 'C', 100 },
        { 'D', 500 },
        { 'M', 1000 }
    };

    int result = lut[s.back()];
    for (int i = s.length() - 2; i >= 0; --i) {
        if (lut[s[i + 1]] <= lut[s[i]]) {
            result += lut[s[i]];
        } else {
            result -= lut[s[i]];
        }
    }
    return result;
}

//14. Longest Common Prefix
string longestCommonPrefix(vector<string>& strs)
{
    int size = strs.size();
    if (size < 1)
        return "";

    string ret = "";
    string pre = strs[0];
    int j = 0;
    while (j < pre.size()) {
        int i = 1;
        while (i < size) {
            if (j < strs[i].size() && strs[i][j] == pre[j]) {
                i++;
            } else {
                j = pre.size();
                break;
            }
        }
        if (i == size)
            ret.push_back(pre[j]);

        j++;
    }
    return ret;
}
#endif // LEETCODE50

