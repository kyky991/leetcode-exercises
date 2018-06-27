#ifndef LEETCODE100_H
#define LEETCODE100_H

#include "common.h"

//51. N-Queens
vector<vector<string>> solveNQueens(int n)
{

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
    int reach = 0;
    for (int i = 0; i < n && i <= reach; ++i) {
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

#endif // LEETCODE100_H

