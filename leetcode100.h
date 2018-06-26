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

#endif // LEETCODE100_H

