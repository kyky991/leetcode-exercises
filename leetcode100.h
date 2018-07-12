#ifndef LEETCODE100_H
#define LEETCODE100_H

#include "common.h"

//51. N-Queens
vector<vector<string>> solveNQueens(int n)
{
    return vector<vector<string>>();
}

//53. Maximum Subarray
int maxSubArray(vector<int>& nums)
{
    int res = nums[0];
    int sum = 0;
    for (int i = 0; i < nums.size(); ++i) {
        sum += nums[i];
        res = max(res, sum);
        sum = max(sum, 0);
    }
    return res;
}

//54. Spiral Matrix
vector<int> spiralOrder(vector<vector<int>>& matrix)
{
    vector<int> res;
    int m = matrix.size();
    if (m == 0)
        return res;

    int n = matrix[0].size();
    int rb = 0, re = m - 1;
    int cb = 0, ce = n - 1;

    while (rb <= re && cb <= ce) {
        for (int j = cb; j <= ce; ++j) {
            res.push_back(matrix[rb][j]);
        }
        rb++;
        for (int j = rb; j <= re; ++j) {
            res.push_back(matrix[j][ce]);
        }
        ce--;

        if (rb <= re)
            for (int j = ce; j >= cb; --j)
                res.push_back(matrix[re][j]);
        re--;

        if (cb <= ce)
            for (int j = re; j >= rb; --j)
                res.push_back(matrix[j][cb]);
        cb++;
    }
    return res;
}

//55. Jump Game
bool canJump(vector<int>& nums)
{
    int n = nums.size();
#if 1
    int reach = 0, i = 0;
    for (; i < n && i <= reach; ++i) {
        reach = max(reach, nums[i] + i);
    }
    return i == n;
#elif
    if (n < 2)
        return true;

    int curMax = 0, nextMax = 0, i = 0;
    while (curMax - i + 1 > 0) {
        for (; i <= curMax; ++i) {
            nextMax = max(nums[i] + i, nextMax);
            if (nextMax >= n - 1)
                return true;
        }
        curMax = nextMax;
    }
    return false;
#endif
}

//56. Merge Intervals
bool intervalCmp(const Interval &l, const Interval &r)
{
    return l.start < r.start;
}

vector<Interval> merge(vector<Interval>& intervals)
{
    sort(intervals.begin(), intervals.end(), intervalCmp);

    vector<Interval> res;
    int n = intervals.size();
    for (int i = 1; i < n; ++i) {
        if (intervals[i].start <= intervals[i - 1].end) {
            Interval tmp;
            tmp.start = min(intervals[i - 1].start, intervals[i].start);
            tmp.end = max(intervals[i - 1].end, intervals[i].end);
            intervals[i] = tmp;
        } else {
            res.push_back(intervals[i - 1]);
        }
    }
    if (n > 0)
        res.push_back(intervals[n - 1]);
    return res;
}

//57. Insert Interval
vector<Interval> insert(vector<Interval>& intervals, Interval newInterval)
{
    intervals.push_back(newInterval);

    return merge(intervals);
}

//58. Length of Last Word
int lengthOfLastWord(string s)
{
    int result = 0;
    for (int i = s.size() - 1; i >= 0; --i) {
        if (s[i] != ' ')
            result++;
        else if (result > 0)
            break;
    }
    return result;
}


//59. Spiral Matrix II
vector<vector<int>> generateMatrix(int n)
{
    vector<vector<int>> res(n, vector<int>(n));
    int rb = 0, re = n - 1;
    int cb = 0, ce = n - 1;
    int num = 1;
    while (rb <= re && cb <= ce) {
        for (int i = cb; i <= ce; ++i) {
            res[rb][i] = num++;
        }
        rb++;

        for (int i = rb; i <= re; ++i) {
            res[i][ce] = num++;
        }
        ce--;

        if (rb <= re) {
            for (int i = ce; i >= cb; --i) {
                res[re][i] = num++;
            }
        }
        re--;

        if (cb <= ce) {
            for (int i = re; i >= rb; --i) {
                res[i][cb] = num++;
            }
        }
        cb++;
    }
    return res;
}

//60. Permutation Sequence
string getPermutation(int n, int k)
{
    int sum = 1;
    vector<int> factorial(n + 1);
    factorial[0] = 1;
    for (int i = 1; i <= n; ++i) {
        sum *= i;
        factorial[i] = sum;
    }

    vector<int> nums;
    for (int i = 1; i <= n; ++i) {
        nums.push_back(i);
    }

    k--;

    string res;
    for (int i = 1; i <= n ; ++i) {
        int index = k / factorial[n - i];
        res += char(nums[index] - '0');
        nums.erase(nums.begin() + index);
        k -= index * factorial[n - i];
    }
    return res;
}

//61. Rotate List
ListNode* rotateRight(ListNode* head, int k)
{
#if 1
    if (!head)
        return head;

    int len = 1;
    ListNode *newH, *tail;
    newH = tail = head;

    while (tail->next) {
        tail = tail->next;
        len++;
    }
    tail->next = head;

    if (k %= len) {
        for (int i = 0; i < len - k; ++i) {
            tail = tail->next;
        }
    }
    newH = tail->next;
    tail->next = NULL;
    return newH;
#else
    int n = 0;
    ListNode *tmp = head;
    ListNode *last = NULL;
    while (tmp) {
        n++;
        if (tmp->next == NULL)
            last = tmp;
        tmp = tmp->next;
    }

    if (n == 0)
        return NULL;

    ListNode *mid = NULL;

    k %= n;
    k = n - k;
    tmp = head;
    while (k && tmp) {
        if (k == 1)
            mid = tmp;
        tmp = tmp->next;
        k--;
    }
    mid->next = NULL;

    if (tmp) {
        last->next = head;
        head = tmp;
    }

    return head;
#endif
}

//62. Unique Paths
int uniquePaths(int m, int n)
{
#if 1
    vector<int> res(m, 1);
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            res[j] += res[j - 1];
        }
    }
    return res[m - 1];
#else
    vector<vector<int>> res(m, vector<int>(n, 1));
    for (int i = 1; i < m; ++i) {
        for (int j = 1; j < n; ++j) {
            res[i][j] = res[i - 1][j] + res[i][j - 1];
        }
    }
    return res[m - 1][n - 1];
#endif
}

//63. Unique Paths II
int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid)
{
    int m = obstacleGrid.size();
    int n = obstacleGrid[0].size();
    vector<int> res(m, 0);
    res[0] = 1;
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            if (obstacleGrid[i][j])
                res[j] = 0;
            else
                res[j] += res[j - 1];
        }
    }
    return res[m - 1];
}

//64. Minimum Path Sum
int minPathSum(vector<vector<int>>& grid)
{
    int m = grid.size();
    int n = grid[0].size();
    vector<vector<int>> dp(m, vector<int>(n, 0));
    dp[0] = grid[0];
    for (int i = 1; i < n; ++i) {
        dp[0][i] = grid[0][i] + dp[0][i - 1];
    }
    for (int j = 1; j < m; ++j) {
        dp[j][0] = grid[j][0] + dp[j - 1][0];
    }
    for (int i = 1; i < m; ++i) {
        for (int j = 1; j < n; ++j) {
            dp[i][j] = grid[i][j] + min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m - 1][n - 1];
}

//65. Valid Number
bool isNumber(string s)
{
    if (!s.empty()) {
        s.erase(0, s.find_first_not_of(" "));
        s.erase(s.find_last_not_of(" ") + 1);
    }

    bool pointSeen = false;
    bool eSeen = false;
    bool numberSeen = false;
    bool numberAfterE = true;
    for(int i = 0; i < s.length(); i++) {
        if('0' <= s[i] && s[i] <= '9') {
            numberSeen = true;
            numberAfterE = true;
        } else if(s[i] == '.') {
            if(eSeen || pointSeen) {
                return false;
            }
            pointSeen = true;
        } else if(s[i] == 'e') {
            if(eSeen || !numberSeen) {
                return false;
            }
            numberAfterE = false;
            eSeen = true;
        } else if(s[i] == '-' || s[i] == '+') {
            if(i != 0 && s[i - 1] != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }

    return numberSeen && numberAfterE;
}

//66. Plus One
vector<int> plusOne(vector<int>& digits)
{
    int n = digits.size();
    for (int i = n - 1; i >= 0; --i) {
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
    }
    digits[0] = 1;
    digits.push_back(0);
    return digits;
}

//67. Add Binary
string addBinary(string a, string b)
{
    string res = "";

    int i = a.size() - 1, j = b.size() - 1;
    int c = 0;

    while (i >= 0 || j >= 0 || c > 0) {
        c += (i >= 0 ? a[i--] - '0' : 0);
        c += (j >= 0 ? b[j--] - '0' : 0);
        res = char(c % 2 + '0') + res;
        c /= 2;
    }

    return res;
}

//68. Text Justification
vector<string> fullJustify(vector<string>& words, int maxWidth)
{
    vector<string> res;
    for (int i = 0, k, l; i < words.size(); i += k) {
        for (k = l = 0; i + k < words.size() && l + words[i + k].size() <= maxWidth - k; ++k) {
            l += words[i + k].size();
        }
        string tmp = words[i];
        for (int j = 0; j < k - 1; ++j) {
            if (i + k >= words.size())
                tmp += " ";
            else
                tmp += string((maxWidth - l) / (k - 1) + (j < (maxWidth - l) % (k - 1)), ' ');
            tmp += words[i + j + 1];
        }
        tmp += string(maxWidth - tmp.size(), ' ');
        res.push_back(tmp);
    }
    return res;
}

//69. Sqrt(x)
int mySqrt(int x)
{
    if (x <= 0)
        return 0;

    int left = 1, right = x;
    int mid = left + (right - left) / 2;

    while (left <= right) {
        if (mid == x / mid) {
            return mid;
        } else {
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
    }

    return right;
}

//70. Climbing Stairs
int climbStairs(int n)
{
    if (n < 0)
        return 0;
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;

    int a = 2;//[n - 1]
    int b = 1;//[n - 2]
    int res = 0;//[n]

    for (int i = 2; i < n; ++i) {
        res = a + b;
        b = a;
        a = res;
    }

    return res;
}

//71. Simplify Path
string simplifyPath(string path)
{
    stack<string> s;
    string tmp;
    stringstream ss(path);
    while (getline(ss, tmp, '/')) {
        if (tmp.empty()) {

        } else if (tmp == ".") {

        } else if (tmp == "..") {
            if (!s.empty())
                s.pop();
        } else {
            s.push(tmp);
        }
    }

    string res;
    while (!s.empty()) {
        res = "/" + s.top() + res;
        s.pop();
    }
    return res.empty() ? "/" : res;
}

//72. Edit Distance
int minDistance(string word1, string word2)
{
    int m = word1.size();
    int n = word2.size();

    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    for (int i = 1; i <= m; ++i) {
        dp[i][0] = i;
    }
    for (int j = 1; j <= n; ++j) {
        dp[0][j] = j;
    }
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (word1[i - 1] == word2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = min(dp[i - 1][j - 1] + 1, min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
    }
    return dp[m][n];
}

//73. Set Matrix Zeroes
void setZeroes(vector<vector<int>>& matrix)
{
    unordered_set<int> rows;
    unordered_set<int> cols;
    int m = matrix.size();
    int n = matrix[0].size();
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == 0) {
                rows.insert(i);
                cols.insert(j);
            }
        }
    }
    for (auto r : rows) {
        for (int i = 0; i < n; ++i) {
            matrix[r][i] = 0;
        }
    }
    for (auto c : cols) {
        for (int i = 0; i < m; ++i) {
            matrix[i][c] = 0;
        }
    }
}

//74. Search a 2D Matrix
bool searchMatrix(vector<vector<int>>& matrix, int target)
{
    int m = matrix.size();
    if (m == 0)
        return false;

    int n = matrix[0].size();

    int start = 0, end = m * n - 1;
    while (start <= end) {
        int mid = (start + end) / 2;
        int value = matrix[mid / n][mid % n];
        if (target < value) {
            end = mid - 1;
        } else if (target > value) {
            start = mid + 1;
        } else {
            return true;
        }
    }

    return false;
}

//75. Sort Colors
void sortColors(vector<int>& nums)
{
    int n = nums.size();
#if 1
    int second = n - 1, zero = 0;
    for (int i = 0; i <= second; ++i) {
        while (nums[i] == 2 && i < second) {
            swap(nums[i], nums[second--]);
        }
        while (nums[i] == 0 && i > zero) {
            swap(nums[i], nums[zero++]);
        }
    }
#else
    for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
            if (nums[i] >= nums[j]) {
                swap(nums[i], nums[j]);
            }
        }
    }
#endif
}

//76. Minimum Window Substring
string minWindow(string s, string t)
{
    vector<int> map(128, 0);
    for (auto c : t)
        map[c]++;
    int count = t.size(), begin = 0, end = 0, d = INT_MAX, head = 0;
    while (end < s.size()) {
        if (map[s[end++]]-- > 0) {
            count--;
        }
        while (count == 0) {
            if (end - begin < d) {
                head = begin;
                d = end - head;
            }
            if (map[s[begin++]]++ == 0) {
                count++;
            }
        }
    }
    return d == INT_MAX ? "" : s.substr(head, d);
}

//77. Combinations
void combine(vector<vector<int>> &vv, vector<int> v, int index, int n, int k)
{
    if (v.size() == k) {
        vv.push_back(v);
    } else {
        for (int i = index; i <= n; ++i) {
            v.push_back(i);
            combine(vv, v, i + 1, n, k);
            v.pop_back();
        }
    }
}

vector<vector<int>> combine(int n, int k)
{
    vector<vector<int>> res;
    combine(res, vector<int>(), 1, n, k);
    return res;
}

//78. Subsets
void subsets(vector<vector<int>> &vv, vector<int> v, int index, vector<int> &nums, int k)
{
    if (v.size() == k) {
        vv.push_back(v);
    } else {
        for (int i = index; i < nums.size(); ++i) {
            v.push_back(nums[i]);
            subsets(vv, v, i + 1, nums, k);
            v.pop_back();
        }
    }
}

vector<vector<int>> subsets(vector<int>& nums)
{
    int n = nums.size();
    vector<vector<int>> res;
    for (int i = 0; i <= n; ++i) {
        subsets(res, vector<int>(), 0, nums, i);
    }
    return res;
}

//79. Word Search
bool exist(vector<vector<char>> &board, vector<vector<int>> &dfs, int i, int j, string word)
{
    if (word.empty())
        return true;

    if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size()) {
        return false;
    }

    if (dfs[i][j] == 1)
        return false;

    if (board[i][j] == word[0]) {
        dfs[i][j] = 1;
        bool flag =  exist(board, dfs, i - 1, j, word.substr(1))
                || exist(board, dfs, i + 1, j, word.substr(1))
                || exist(board, dfs, i, j - 1, word.substr(1))
                || exist(board, dfs, i, j + 1, word.substr(1));
        dfs[i][j] = 0;
        return flag;
    }
    return false;
}

bool exist(vector<vector<char>>& board, string word)
{
    int m = board.size();
    if (m == 0 || word.empty())
        return word.empty();

    int n = board[0].size();
    vector<vector<int>> dfs(m, vector<int>(n, 0));

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (exist(board, dfs, i, j, word)) {
                return true;
            }
        }
    }
    return false;
}

//80. Remove Duplicates from Sorted Array II
int removeDuplicates2(vector<int>& nums)
{
    unordered_map<int, int> m;
    int index = 0;
    int i = 0;
    while (i < nums.size()) {
        m[nums[i]]++;
        nums[index] = nums[i];
        if (m[nums[i]] == 3) {
            m[nums[i]]--;
        } else {
            index++;
        }
        i++;
    }
    return index;
}

//81. Search in Rotated Sorted Array II
bool search2(vector<int>& nums, int target)
{
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = (l + r) / 2;
        if (nums[mid] == target)
            return true;

        if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
            l++;
            r--;
        } else if (nums[mid] < nums[l]) {
            if (nums[mid] < target && target <= nums[r])
                l = mid + 1;
            else
                r = mid - 1;
        } else {
            if (nums[mid] > target && target >= nums[l])
                r = mid - 1;
            else
                l = mid + 1;
        }
    }

    return false;
}

//82. Remove Duplicates from Sorted List II
ListNode* deleteDuplicates2(ListNode* head)
{
    if (head == NULL)
        return NULL;

    ListNode p(0);
    p.next = head;
    ListNode *pre = &p;

    ListNode *tmp = head;
    while (tmp) {
        while (tmp->next && tmp->val == tmp->next->val) {
            tmp = tmp->next;
        }
        if (pre->next == tmp) {
            pre = pre->next;
        } else {
            pre->next = tmp->next;
        }
        tmp = tmp->next;
    }
    return p.next;
}

//83. Remove Duplicates from Sorted List
ListNode* deleteDuplicates3(ListNode* head)
{
    if (head == NULL)
        return NULL;

    ListNode *tmp = head;
    while (tmp->next) {
        if (tmp->val == tmp->next->val) {
            tmp->next = tmp->next->next;
        } else {
            tmp = tmp->next;
        }
    }
    return head;
}

//84. Largest Rectangle in Histogram
int largestRectangleArea(vector<int>& heights)
{
    int res = 0;
    heights.push_back(0);
    vector<int> index;

    for (int i = 0; i < heights.size(); ++i) {
        while (index.size() > 0 && heights[index.back()] >= heights[i]) {
            int h = heights[index.back()];
            index.pop_back();

            int idx = index.size() > 0 ? index.back() : -1;
            if (h * (i - idx - 1) > res)
                res = h * (i - idx - 1);
        }
        index.push_back(i);
    }

    return res;
}

//85. Maximal Rectangle
int maximalRectangle(vector<vector<char>>& matrix)
{
    int m = matrix.size();
    if (m == 0)
        return 0;

    int n = matrix[0].size();
    vector<int> left(n, 0);
    vector<int> right(n, n);
    vector<int> height(n, 0);
    int res = 0;
    for (int i = 0; i < m; ++i) {
        int curLeft = 0, curRight = n;
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == '1')
                height[j]++;
            else
                height[j] = 0;
        }
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == '1') {
                left[j] = max(left[j], curLeft);
            } else {
                left[j] = 0;
                curLeft = j + 1;
            }
        }
        for (int j = n - 1; j >= 0; --j) {
            if (matrix[i][j] == '1') {
                right[j] = min(right[j], curRight);
            } else {
                right[j] = n;
                curRight = j;
            }
        }
        for (int j = 0; j < n; ++j) {
            res = max(res, (right[j] - left[j]) * height[j]);
        }
    }
    return res;
}

//86. Partition List
ListNode* partition(ListNode* head, int x)
{
#if 1
    ListNode node1(0), node2(0);
    ListNode *p1 = &node1, *p2 = &node2;
    while (head){
        if (head->val < x) {
            p1->next = head;
            p1 = p1->next;
        } else {
            p2->next = head;
            p2 = p2->next;
        }
        head = head->next;
    }
    p2->next = NULL;
    p1->next = node2.next;
    return node1.next;
#else
    ListNode *tmp = head;
    if (tmp == NULL)
        return NULL;

    ListNode p(0);
    p.next = head;

    ListNode *pre = &p;

    while (tmp) {
        if (tmp->val >= x) {
            break;
        } else {
            pre = pre->next;
            tmp = tmp->next;
        }
    }

    ListNode *second = tmp;
    ListNode *sp = pre;
    while (tmp) {
        if (tmp->val >= x) {
            sp = sp->next;
            tmp = tmp->next;
        } else {
            sp->next = tmp->next;
            pre->next = tmp;
            pre = pre->next;
            pre->next = NULL;
            tmp = sp->next;
        }
    }
    pre->next = second;
    return p.next;
#endif
}

//87. Scramble String
bool isScramble(string s1, string s2)
{
    if (s1 == s2)
        return true;

    int n = s1.size();
    int count[26] = { 0 };
    for (int i = 0; i < n; ++i) {
        count[s1[i] - 'a']++;
        count[s2[i] - 'a']--;
    }
    for (int i = 0 ; i < 26; ++i) {
        if (count[i] != 0)
            return false;
    }

    for (int i = 1; i < n; ++i) {
        if (isScramble(s1.substr(0, i), s2.substr(0, i)) && isScramble(s1.substr(i), s2.substr(i)))
            return true;
        if (isScramble(s1.substr(0, i), s2.substr(n - i)) && isScramble(s1.substr(i), s2.substr(0, n - i)))
            return true;
    }
    return false;
}

//88. Merge Sorted Array
void merge(vector<int>& nums1, int m, vector<int>& nums2, int n)
{
    int i = m - 1, j = n - 1, k = m + n - 1;
    while (j >= 0) {
        nums1[k--] = ((i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--]);
    }
}

//89. Gray Code
vector<int> grayCode(int n)
{
    vector<int> res;
    for (int i = 0; i < (1 << n); ++i) {
        res.push_back(i ^ (i >> 1));
    }
    return res;
}

//90. Subsets II
void subsetsWithDup(vector<vector<int>> &vv, vector<int> &v, vector<int> &nums, int index)
{
    vv.push_back(v);
    for (int i = index; i < nums.size(); ++i) {
        if (i == index || nums[i] != nums[i - 1]) {
            v.push_back(nums[i]);
            subsetsWithDup(vv, v, nums, i + 1);
            v.pop_back();
        }
    }
}


vector<vector<int>> subsetsWithDup(vector<int>& nums)
{
    sort(nums.begin(), nums.end());

    vector<vector<int>> res;
    vector<int> v;
    subsetsWithDup(res, v, nums, 0);
    return res;
}

//91. Decode Ways
int numDecodings(string s)
{
    int n = s.size();
    if (n == 0)
        return 0;

    vector<int> dp(n + 1, 0);
    dp[n] = 1;
    dp[n - 1] = s[n - 1] == '0' ? 0 : 1;

    for (int i = n - 2; i >= 0; --i) {
        if (s[i] == '0')
            continue;
        else
            dp[i] = stoi(s.substr(i, 2)) <= 26 ? dp[i + 1] + dp[i + 2] : dp[i + 1];
    }

    return dp[0];
}

#endif // LEETCODE100_H
