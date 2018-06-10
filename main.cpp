#include <iostream>
#include "leetcodeeasyarray.h"
#include "leetcodeeasyhashtable.h"
#include "leetcodeeasystring.h"
#include "leetcodebitmanipulation.h"
#include "leetcode50.h"

int main(int argc, char *argv[])
{
    //int a[5] = { 1, 2, 3, 4, 5 };
    int a[7] = {2, 6, 4, 8, 10, 9, 15};

    vector<int> input(a, a + 7);
    findUnsortedSubarray(input);

    vector<string> keys = {"Hello","Alaska","Dad","Peace"};
    vector<string> result = findWords(keys);

    vector<char> chars = {'a', 'a', 'b', 'b', 'b', 'b', 'b'};
    compress(chars);

    uint32_t i = reverseBits(43261596);
    i = hammingWeight(11);
    std::cout << i << std::endl;

    i = lengthOfLongestSubstring("abcabcabb");

    string s = convert("PAYPALISHIRING", 3);

    vector<int> v = {-1};
    threeSum(v);

    return 0;
}
