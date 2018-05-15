#ifndef LEETCODEBITMANIPULATION
#define LEETCODEBITMANIPULATION

#include <stdint.h>
#include <iostream>

#include <QDebug>

using namespace std;

//190. Reverse Bits
uint32_t reverseBits(uint32_t n)
{
    n = (n >> 16) | (n << 16);
    n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
    n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
    n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
    n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
    return n;

    string s = "";
    while (n) {
        s.push_back(char(n % 2 + '0'));
        n /= 2;
    }

    reverse(s.begin(), s.end());

    qDebug() << s.c_str();

    uint32_t res = 0;
    int p = 32 - s.size();
    for (int i = 0; i < s.size(); ++i) {
        if (s[i] == '1')
            res += pow(2, p);
        p++;
    }
    return res;
}

//191. Number of 1 Bits
int hammingWeight(uint32_t n)
{
    int res = 0;
    while (n) {
#if 1
        n = n & (n - 1);
        res++;
#else
        if (n & 1)
            res++;
        n = (n >> 1);
#endif
    }
    return res;
}

//342. Power of Four
bool isPowerOfFour(int num)
{
    return (num > 0) && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;

    if (num <= 0)
        return false;

    while (num) {
        if (num % 4 != 0)
            return false;
        num /= 4;
    }
    return true;
}

//371. Sum of Two Integers
int getSum(int a, int b)
{
    return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
}

//405. Convert a Number to Hexadecimal
string toHex(int num)
{
    if (num == 0)
        return "0";

    vector<char> v = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    string res = "";
    int count = 0;
    while (num && count++ < 8) {
        res = v[num & 0xf] + res;
        num >>= 4;
    }

    return res;
}

//461. Hamming Distance
int hammingDistance(int x, int y)
{
    int a = x ^ y;
    int res = 0;
    while (a > 0) {
        if (a & 1)
            res++;
        a >>= 1;
    }
    return res;
}

//476. Number Complement
int findComplement(int num)
{
    int off = 1;
    while (1) {
        if (num >> off)
            off++;
        else
            break;
    }

    num = ~num;
    num <<= (32 - off);
    num >>= (32 - off);

    return num;
}

//693. Binary Number with Alternating Bits
bool hasAlternatingBits(int n)
{
    return !((n - (n >> 2)) & (n - (n >> 2) - 1));
}

//762. Prime Number of Set Bits in Binary Representation
bool isPrime(int value)
{
    if (value < 2)
        return false;

    int k = sqrt(value);
    for (int i = 2; i <= k; ++i) {
        if (value % i == 0)
            return false;
    }
    return true;
}

int countPrimeSetBits(int L, int R)
{
    int res = 0;
    for (int i = L; i <= R; ++i) {
        int count = 0;
        int tmp = i;
        while (tmp) {
            tmp = tmp & (tmp - 1);
            count++;
        }
        if (isPrime(count))
            res++;
    }
    return res;
}

//784. Letter Case Permutation
vector<string> letterCasePermutation(string S)
{
    vector<string> res;
    res.push_back(S);

    for (int i = 0; i < S.size(); ++i) {
        if (isalpha(S[i])) {
            int size = res.size();

            res.insert(res.begin(), res.begin(), res.end());

            for (int j = 0; j < size; ++j) {
                res[j][i] ^= 1 << 5;
            }
        }
    }

    return res;
}
#endif // LEETCODEBITMANIPULATION

