#ifndef LEETCODEEASYMATH
#define LEETCODEEASYMATH

#include <iostream>
#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

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

//168. Excel Sheet Column Title
string convertToTitle(int n)
{
    string s = "";

    int tmp = (n - 1) % 26;
    s = char(tmp + 'A') + s;

    int d = (n - 1) / 26;
    if (d > 0) {
        s = convertToTitle(d) + s;
    }

    return s;
}

//171. Excel Sheet Column Number
int titleToNumber(string s)
{
    int result = 0;
    int n = s.size() - 1;
    while (n >= 0) {
        result += (s[n] - 'A' + 1) * pow(26, s.size() - n - 1);
        n--;
    }
    return result;
}

//172. Factorial Trailing Zeroes
int trailingZeroes(int n)
{
    int result = 0;
    int tmp = n;
    while (tmp > 0) {
        tmp = n / 5;
        result += tmp;
        n = tmp;
    }
    return result;
}

//231. Power of Two
bool isPowerOfTwo(int n)
{
    if (n < 1) {
        return false;
    }

    while (n > 1) {
        if (n % 2 == 1) {
            return false;
        }
        n /= 2;
    }
    return true;
}

//258. Add Digits
int addDigits(int num)
{
    return 1 + (num - 1) % 9;
}

//263. Ugly Number
bool isUgly(int num)
{
    for (int i = 2; i < 6 && num; ++i) {
        while (num % i == 0) {
            num /= i;
        }
    }
    return num == 1;
}

//326. Power of Three
bool isPowerOfThree(int n)
{
    if (n < 1) {
        return false;
    }

    while (n > 1) {
        int tmp = n % 3;
        if (tmp > 0 && tmp < 3) {
            return false;
        }
        n /= 3;
    }
    return true;
}

//367. Valid Perfect Square
bool isPerfectSquare(int num)
{
    int i = 1;
    while (num > 0) {
        num -= i;
        i += 2;
    }
    return num == 0;
}

//400. Nth Digit
int findNthDigit(int n)
{
    int digits = 1;
    long base = 9;
    while (n - base * digits > 0) {
        n -= base * digits;
        base *= 10;
        digits++;
    }

    int index = n % digits;
    if (index == 0) {
        index = digits;
    }

    long num = 1;
    for (int i = 1; i < digits; ++i) {
        num *= 10;
    }
    num += (index == digits) ? n / digits - 1 : n / digits;

    for (int i = index; i < digits; ++i) {
        num /= 10;
    }
    return num % 10;
}

//415. Add Strings
string addStrings(string num1, string num2)
{
    string result = "";
    int k = 0;
    int i = num1.size() - 1;
    int j = num2.size() - 1;
    while (i >= 0 || j >= 0 || k > 0) {
        int n1 = (i >= 0 ? num1[i--] - '0' : 0);
        int n2 = (j >= 0 ? num2[j--] - '0' : 0);
        result = char((k + n1 + n2) % 10 + '0') + result;
        k = (k + n1 + n2) / 10;
    }
    return result;
}

//441. Arranging Coins
int arrangeCoins(int n)
{
    ////
    return (int)((-1 + sqrt(1 + 8 * (long)n)) / 2);

    ////
    int step = 1;
    int sum = step;
    while (n - sum >= 0) {
        step++;
        sum += step;
    }
    return step - 1;
}

//453. Minimum Moves to Equal Array Elements
int minMoves(vector<int>& nums)
{
    int minNum = nums[0];
    for (int n : nums)
        minNum = min(minNum, n);

    int ret = 0;
    for (int n : nums)
        ret += n - minNum;
    return ret;
}

//507. Perfect Number
bool checkPerfectNumber(int num)
{
    if (num == 1)
        return false;

    int ret = 0;
    int i = 2;
    while (i <= sqrt(num)) {
        if (num % i == 0) {
            ret += i;
            if (i != num / i)
                ret += num / i;
        }
        i++;
    }
    ret++;

    return ret == num;
}

//598. Range Addition II
int maxCount(int m, int n, vector<vector<int>>& ops)
{
    int h = ops.size();
    if (h == 0)
        return m * n;

    int minH = INT_MAX;
    int minW = INT_MAX;
    for (int i = 0; i < h; ++i) {
        minH = min(minH, ops[i][0]);
        minW = min(minW, ops[i][1]);
    }
    return minH * minW;
}

//633. Sum of Square Numbers
bool judgeSquareSum(int c)
{
    int tmp = sqrt(c);

    int left = 0, right = tmp;
    while (left <= right) {
        int cur = left * left + right * right;
        if (cur < c) {
            left++;
        } else if (cur > c) {
            right--;
        } else {
            return true;
        }
    }
    return false;
}

//728. Self Dividing Numbers
vector<int> selfDividingNumbers(int left, int right)
{
    vector<int> ret;
    for (int i = left; i <= right; ++i) {
#if 1
        int j = i;
        for (; j > 0; j /= 10)
            if ((j % 10 == 0) || (i % (j % 10) != 0))
                break;
        if (j == 0)
            ret.push_back(i);
#else
        ///////////////////////
        unordered_set<int> s;
        int tmp = i;
        while (tmp) {
            s.insert(tmp % 10);
            tmp /= 10;
        }
        if (s.size() == 1) {
            ret.push_back(i);
            continue;
        }
        if (s.count(0))
            continue;

        int cnt = 0;
        for (int n : s) {
            if (i % n != 0)
                break;
            cnt++;
        }
        if (cnt == s.size()) {
            ret.push_back(i);
        }
#endif
    }
    return ret;
}

//812. Largest Triangle Area
double largestTriangleArea(vector<vector<int>>& points)
{

}
#endif // LEETCODEEASYMATH

