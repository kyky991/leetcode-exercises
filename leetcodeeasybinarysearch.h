#ifndef LEETCODEEASYBINARYSEARCH
#define LEETCODEEASYBINARYSEARCH

#include <vector>

using namespace std;

//278. First Bad Version
bool isBadVersion(int version)
{
    return true;
}

int firstBadVersion(int n)
{
    int start = 1;
    int end = n;
    while (start < end) {
        int mid = start + (end - start) / 2;
        if (!isBadVersion(mid)) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    return start;
}

//475. Heaters
int findRadius(vector<int>& houses, vector<int>& heaters)
{

}


//744. Find Smallest Letter Greater Than Target
char nextGreatestLetter(vector<char>& letters, char target)
{
    for (int i = 0; i < letters.size(); ++i) {
        if (letters[i] < target)
            return letters[i];
    }
    return letters[0];
}

#endif // LEETCODEEASYBINARYSEARCH

