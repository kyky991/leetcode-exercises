#ifndef LEETCODEEASYDYNAMIC
#define LEETCODEEASYDYNAMIC

#include <vector>

using namespace std;

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

//198. House Robber
int rob(vector<int>& nums)
{
    int a = 0;
    int b = 0;

    for (int i = 0; i < nums.size(); ++i) {
        if (i % 2 == 0) {
            a = max(a + nums[i], b);
        } else {
            b = max(a, b + nums[i]);
        }
    }

    return max(a, b);
}

//303. Range Sum Query - Immutable
class NumArray {
public:
    NumArray(vector<int> nums) {
        int sum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            sum += nums[i];
            n.push_back(sum);
        }
    }

    int sumRange(int i, int j) {
        if (i == 0)
            return n[j];

        return n[j] - n[i - 1];
    }
private:
    vector<int> n;
};


#endif // LEETCODEEASYDYNAMIC

