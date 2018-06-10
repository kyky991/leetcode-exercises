#ifndef LEETCODEEASYARRAY_H
#define LEETCODEEASYARRAY_H

#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

//581.Shortest Unsorted Continuous Subarray
int findUnsortedSubarray(vector<int>& nums)
{
    int size = nums.size();
    int start = -1;
    int end = -2;
    int minVal = INT_MAX;
    int maxVal = INT_MIN;
    for (int i = 0; i < size; ++i) {
        maxVal = max(maxVal, nums[i]);
        minVal = min(minVal, nums[size - 1 - i]);

        if (nums[i] < maxVal)
            end = i;
        if (nums[size - 1 - i] > minVal)
            start = size - 1 - i;
    }

    return end - start + 1;
}

//605.Can Place Flowers
bool canPlaceFlowers(vector<int>& flowerbed, int n)
{
    int cnt = 1;
    int result = 0;
    for (int i = 0; i < flowerbed.size(); ++i) {
        if (flowerbed[i] == 0) {
            cnt++;
        } else {
            result += (cnt - 1) / 2;
            cnt = 0;
        }
    }
    if (cnt != 0)
        result += cnt / 2;

    return result >= n;
}

//628.Maximum Product of Three Numbers
int maximumProduct(vector<int>& nums)
{
    sort(nums.begin(), nums.end());

    int size = nums.size();
    int max1 = nums[0] * nums[1] * nums[size - 1];
    int max2 = nums[size - 1] * nums[size - 2] * nums[size - 3];

    return max1 > max2 ? max1 : max2;
}

//643.Maximum Average Subarray I
double findMaxAverage(vector<int>& nums, int k)
{
    double sum = 0, result = INT_MIN;
    for (int i = 0; i < nums.size(); ++i) {
        if (i < k) {
            sum += nums[i];
        } else {
            result = max(result, sum);
            sum += nums[i] - nums[i - k];
        }
    }
    result = max(result, sum);

    return result / k;
}

//661.Image Smoother
bool isValid(int x, int y, int rows, int cols)
{
    if (x > -1 && x < rows && y > -1 && y < cols) {
        return true;
    }
    return false;
}

vector<vector<int>> imageSmoother(vector<vector<int>>& M)
{
    vector<vector<int>> result;

    int rows = M.size();
    int cols = M[0].size();
    if (rows == 0 || cols == 0) {
        return result;
    }

    for (int i = 0; i < rows; ++i) {
        vector<int> tmp;
        for (int j = 0; j < cols; ++j) {
            int sum = 0;
            int cnt = 0;
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (isValid(m + i, n + j, rows, cols)) {
                        sum += M[m + i][n + j];
                        cnt++;
                    }
                }
            }
            tmp.push_back(sum / cnt);
        }
        result.push_back(tmp);
    }

    return result;
}

//665.Non-decreasing Array
bool checkPossibility(vector<int>& nums)
{
    int size = nums.size();
    int cnt = 0;
    for (int i = 1; i < size; ++i) {
        if (nums[i - 1] > nums[i]) {
            cnt++;
            if (i - 2 < 0 || nums[i - 2] <= nums[i]) {
                nums[i - 1] = nums[i];
            } else {
                nums[i] = nums[i - 1];
            }
        }
        if (cnt > 1) {
            return false;
        }
    }

    return true;
}

//674.Longest Continuous Increasing Subsequence
int findLengthOfLCIS(vector<int>& nums)
{
    int result = 0;
    int cnt = 0;
    for (int i = 0; i < nums.size(); ++i) {
        if (i == 0 || nums[i - 1] < nums[i]) {
            cnt++;
            result = max(result, cnt);
        } else {
            cnt = 1;
        }
    }

    return result;
}

//695.Max Area of Island
int dfs(vector<vector<int>>& grid, int i, int j)
{
    int rows = grid.size();
    int cols = grid[0].size();

    if (i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == 1) {
        grid[i][j] = 0;
        return 1 + dfs(grid, i, j + 1) + dfs(grid, i, j - 1) + dfs(grid, i - 1, j) + dfs(grid, i + 1, j);
    }
    return 0;
}

int maxAreaOfIsland(vector<vector<int>>& grid)
{
    int rows = grid.size();
    int cols = grid[0].size();

    int result = 0;
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            if (grid[i][j]) {
                result = max(result, dfs(grid, i,  j));
            }
        }
    }

    return result;
}

//697.Degree of an Array
int findShortestSubArray(vector<int>& nums)
{
    unordered_map<int,vector<int>> mp;
    int size = nums.size();
    for (int i = 0; i < size; ++i) {
        mp[nums[i]].push_back(i);
    }

    int degree = 0;
    for (auto it = mp.begin(); it != mp.end(); ++it) {
        degree = max(degree, (int)it->second.size());
    }

    int result = size;
    for (auto it = mp.begin(); it != mp.end(); ++it) {
        if (degree == it->second.size()) {
            result = min(result, it->second.back() - it->second[0] + 1);
        }
    }

    return result;
}

//717.1-bit and 2-bit Characters
bool isOneBitCharacter(vector<int>& bits)
{
    int size = bits.size();
    int i = 0;
    while (i < size - 1) {
        if (bits[i] == 0) {
            i++;
        } else {
            i += 2;
        }
    }

    return i == size - 1;
}

//724.Find Pivot Index
int pivotIndex(vector<int>& nums)
{
    int size = nums.size();
    for (int i = 0; i < size; ++i) {
        int sum = 0;
        int m = 0;
        while (m < i) {
            sum += nums[m++];
        }

        int sum2 = 0;
        m = i + 1;
        while (m < size) {
            sum2 += nums[m++];
        }

        if (sum == sum2) {
            return i;
        }
    }

    return -1;
}

//746. Min Cost Climbing Stairs
int minCostClimbingStairs(vector<int>& cost)
{
    int size = cost.size();
    vector<int> dp(size);
    dp[0] = cost[0];
    dp[1] = cost[1];
    for (int i = 2; i < size; ++i) {
        dp[i] = cost[i] + min(dp[i - 2], dp[i - 1]);
    }
    return min(dp[size - 1], dp[size - 2]);
}

//747. Largest Number At Least Twice of Others
int dominantIndex(vector<int>& nums)
{
    int size = nums.size();
    for (int i = 0; i < size; ++i) {
        int cnt = 0;
        for (int j = 0; j < size; ++j) {
            if (j != i) {
                if (nums[i] / 2 >= nums[j]) {
                    cnt++;
                }
            }
        }
        if (cnt == size - 1) {
            return i;
        }
    }
    return -1;
}

//766. Toeplitz Matrix
bool isToeplitzMatrix(vector<vector<int>>& matrix)
{
    int rows = matrix.size();
    int cols = matrix[0].size();
    for (int i = 0; i < rows - 1; ++i) {
        for (int j = 0; j < cols - 1; ++j) {
            if (matrix[i][j] != matrix[i + 1][j + 1]) {
                return false;
            }
        }
    }

    return true;
}

#endif // LEETCODEEASYARRAY_H

