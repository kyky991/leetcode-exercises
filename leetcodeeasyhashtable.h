#ifndef LEETCODEEASYHASHTABLE_H
#define LEETCODEEASYHASHTABLE_H

#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <iosfwd>
#include <sstream>

using namespace std;

//136. Single Number
int singleNumber(vector<int>& nums)
{
    //A XOR A = 0
    int result = 0;

    for (int i = 0; i < nums.size(); ++i) {
        result ^= nums[i];
    }

    return result;
}

//202. Happy Number
bool isHappy(int n)
{
    int num = n;
    vector<int> tmp;
    while (1) {
        int sum = 0;
        while (num) {
            int i = num % 10;
            int j = num / 10;
            num = j;
            sum += i * i;
        }

        if (sum == 1) {
            return true;
        }

        vector<int>::iterator result = find(tmp.begin(), tmp.end(), sum);
        if (result != tmp.end()) {
            return false;
        }

        num = sum;
        tmp.push_back(sum);
    }
}

//204. Count Primes
int countPrimes(int n)
{
    int result = 0;
    int s = (int)sqrt(n);
    vector<bool> isPrimes(n, true);
    for (int i = 2; i < n; ++i) {
        result++;
        if (isPrimes[i]) {
            continue;
        }

        result++;

        if (i > s) {
            continue;
        }

        for (int j = i * i; j < n; j += i) {
            isPrimes[j] = false;
        }
    }

    return result;
}

//205. Isomorphic Strings
bool isIsomorphic(string s, string t)
{
    int m1[256] = { 0 }, m2[256] = { 0 };
    int len = s.length();
    for (int i = 0; i < len; ++i) {
        if (m1[s[i]] != m2[t[i]]) {
            return false;
        }
        m1[s[i]] = i + 1;
        m2[t[i]] = i + 1;
    }
    return true;
}

//242. Valid Anagram
bool isAnagram(string s, string t)
{
    int m1[256] = { 0 }, m2[256] = { 0 };

    for (int i = 0; i < s.length(); ++i) {
        m1[s[i]]++;
    }
    for (int i = 0; i < t.length(); ++i) {
        m2[t[i]]++;
    }

    for (int i = 0; i < 256; ++i) {
        if (m1[i] != m2[i]) {
            return false;
        }
    }

    return true;
}

//290. Word Pattern
bool wordPattern(string pattern, string str)
{
    int i = 0;
    int len = pattern.length();
    unordered_map<char, int> ci;
    unordered_map<string, int> si;
    istringstream in(str);
    for (string word; in >> word; ++i) {
        if (i == len || ci[pattern[i]] != si[word]) {
            return false;
        }
        ci[pattern[i]] = si[word] = i + 1;
    }
    return i == len;
}

//349. Intersection of Two Arrays
vector<int> intersection(vector<int>& nums1, vector<int>& nums2)
{
    unordered_set<int> s1(nums1.begin(), nums1.end());

    vector<int> result;

    int n = nums2.size();
    for (int i = 0; i < n; ++i) {
        if (s1.count(nums2[i])) {
            result.push_back(nums2[i]);
            s1.erase(nums2[i]);
        }
    }

    return result;
}

//350. Intersection of Two Arrays II
vector<int> intersect(vector<int>& nums1, vector<int>& nums2)
{
    vector<int> result;
    unordered_map<int, int> tmp;
    for (int i = 0; i < nums1.size(); ++i) {
        tmp[nums1[i]]++;
    }
    for (int i = 0; i < nums2.size(); ++i) {
        if (--tmp[nums2[i]] >= 0) {
            result.push_back(nums2[i]);
        }
    }
    return result;
}


//387. First Unique Character in a String
int firstUniqChar(string s)
{
    unordered_map<char, int> tmp;
    for (int i = 0; i < s.length(); ++i) {
        tmp[s[i]]++;
    }

    for (int i = 0; i < s.length(); ++i) {
        if (tmp[s[i]] == 1) {
            return i;
        }
    }

    return -1;
}

//389. Find the Difference
char findTheDifference(string s, string t)
{
#if 1
    char r = 0;
    for (char c : s) r ^= c;
    for (char c : t) r ^= c;
    return r;
#else
    char result;
    unordered_map<char, int> tmp;
    for (int i = 0; i < s.length(); ++i) {
        tmp[s[i]]++;
    }

    for (int i = 0; i < t.length(); ++i) {
        if (--tmp[t[i]] < 0) {
            result = t[i];
        }
    }
    return result;
#endif
}

//409. Longest Palindrome
int longestPalindrome(string s)
{
    unordered_map<char, int> m;
    for (char c : s) {
        m[c]++;
    }

    int result = 0;
    int maxOdd = 0;
    for (auto c : m) {
        if (c.second % 2 == 0) {
            result += c.second;
        } else {
            result += (c.second - 1);
            maxOdd = max(maxOdd, c.second);
        }
    }
    result += (maxOdd > 0);
    return result;
}

//438. Find All Anagrams in a String
vector<int> findAnagrams(string s, string p)
{
    vector<int> result;

    vector<int> sv(26, 0), pv(26, 0);

    if (s.size() < p.size()) {
        return result;
    }

    for (int i = 0; i < p.size(); ++i) {
        sv[s[i] - 'a']++;
        pv[p[i] - 'a']++;
    }
    if (sv == pv) {
        result.push_back(0);
    }

    for (int i = p.size(); i < s.size(); ++i) {
        sv[s[i] - 'a']++;
        sv[s[i - p.size()] - 'a']--;

        if (sv == pv) {
            result.push_back(i - p.size() + 1);
        }
    }

    return result;
}

//447. Number of Boomerangs
int numberOfBoomerangs(vector<pair<int, int>>& points)
{
    int result = 0;

    for (int i = 0; i < points.size(); ++i) {
        unordered_map<int, int> tmp;
        for (int j = 0; j < points.size(); ++j) {
            int x = points[i].first - points[j].first;
            int y = points[i].second - points[j].second;
            tmp[x * x + y * y]++;
        }

        unordered_map<int, int>::iterator iter = tmp.begin();
        while (iter != tmp.end()) {
            result += iter->second * (iter->second - 1);
            iter++;
        }
    }

    return result;
}

//463. Island Perimeter
int islandPerimeter(vector<vector<int>>& grid)
{
    int rows = grid.size();
    int cols = grid[0].size();
#if 0
    int result = 0;
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            if (grid[i][j] != 1)
                continue;

            int tmp = 0;
            if (i == 0) {
                tmp++;
            }
            if (i == rows - 1) {
                tmp++;
            }
            if (j == 0) {
                tmp++;
            }
            if (j == cols - 1) {
                tmp++;
            }
            if (i - 1 >= 0 && grid[i - 1][j] == 0) {
                tmp++;
            }
            if (i + 1 < rows && grid[i + 1][j] == 0) {
                tmp++;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 0) {
                tmp++;
            }
            if (j + 1 < cols && grid[i][j + 1] == 0) {
                tmp++;
            }
            if (tmp > 1 && tmp < 4) {
                result += tmp;
            }

        }
    }

    return result;
#else
    int cnt = 0;
    int repeat = 0;
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            if (grid[i][j] == 1) {
                cnt++;
                if (i != 0 && grid[i - 1][j] == 1) {
                    repeat++;
                }
                if (j != 0 && grid[i][j - 1] == 1) {
                    repeat++;
                }
            }
        }
    }
    return 4 * cnt - 2 * repeat;
#endif
}

//500. Keyboard Row
vector<string> findWords(vector<string>& words)
{
    std::unordered_set<char> dict1 = { 'q','Q','w','W','e','E','r','R','t','T','y','Y','u','U','i','I','o','O','p','P' };
    std::unordered_set<char> dict2 = { 'a','A','s','S','d','D','f','F','g','G','h','H','j','J','k','K','l','L'};
    std::unordered_set<char> dict3 = { 'z','Z','x','X','c','C','v','V','b','B','n','N','m','M'};

    vector<string> res;

    for (auto &word : words) {
        bool d1 = true, d2 = true, d3 = true;
        for (auto & ch : word) {
            if (d1) {
                auto it = dict1.find(ch);
                if (it == dict1.end()) d1 = false;
            }
            if (d2) {
                auto it = dict2.find(ch);
                if (it == dict2.end()) d2 = false;
            }
            if (d3) {
                auto it = dict3.find(ch);
                if (it == dict3.end()) d3 = false;
            }
        }
        if (d1 || d2 || d3) res.push_back(word);
    }

    return res;
}

//575. Distribute Candies
int distributeCandies(vector<int>& candies)
{
    unordered_set<int> tmp;
    for (int i : candies) {
        tmp.insert(i);
    }

    return min(tmp.size(), candies.size() / 2);
}

//594. Longest Harmonious Subsequence
int findLHS(vector<int>& nums)
{
    int result = 0;
    unordered_map<int, int> tmp;
    for (int i : nums) {
        tmp[i]++;
    }

    for (auto it : tmp) {
        if (tmp.count(it.first - 1)) {
            result = max(result, it.second + tmp[it.first - 1]);
        }
    }

    return result;
}

//599. Minimum Index Sum of Two Lists
vector<string> findRestaurant(vector<string>& list1, vector<string>& list2)
{
    int r = list1.size() + list2.size();
    vector<string> result;

    unordered_map<string, int> m1;
    unordered_map<string, int> m2;
    for (int i = 0; i < list1.size(); ++i)
        m1[list1[i]] = i;
    for (int j = 0; j < list2.size(); ++j)
        m2[list2[j]] = j;

    for (auto m : m1) {
        if (m2.count(m.first)) {
            int tmp = m.second + m2[m.first];
            if (tmp < r) {
                r = tmp;
                result.clear();
                result.push_back(m.first);
            } else if (tmp == r) {
                result.push_back(m.first);
            }
        }
    }
    return result;
}

//645. Set Mismatch
vector<int> findErrorNums(vector<int>& nums)
{
    vector<int> result;
    for (int i = 0; i < nums.size(); ++i) {
        while (nums[i] != nums[nums[i] - 1]) {
            swap(nums[i], nums[nums[i] - 1]);
        }
    }

    for (int i = 0; i < nums.size(); ++i) {
        if (nums[i] != i + 1) {
            result.push_back(nums[i]);
            result.push_back(i + 1);
            return result;
        }
    }
    return result;
}

//690. Employee Importance

// Employee info
class Employee {
public:
    // It's the unique ID of each node.
    // unique id of this employee
    int id;
    // the importance value of this employee
    int importance;
    // the id of direct subordinates
    vector<int> subordinates;
};

int bfs(unordered_map<int, Employee *> m, int id)
{
    int result = m[id]->importance;
    for (auto i : m[id]->subordinates) {
        result += bfs(m, i);
    }
    return result;
}

int getImportance(vector<Employee *> employees, int id)
{
    unordered_map<int, Employee *> m;
    for (auto em : employees) {
        m[em->id] = em;
    }
    return bfs(m, id);
}

//720. Longest Word in Dictionary
string longestWord(vector<string>& words)
{
    sort(words.begin(), words.end());
    unordered_set<string> s;
    string result = "";
    for (string str : words) {
        if (str.length() == 1 || s.count(str.substr(0, str.length() - 1))) {
            s.insert(str);
            result = str.length() > result.length() ? str : result;
        }
    }
    return result;
}

//771. Jewels and Stones
int numJewelsInStones(string J, string S)
{
    unordered_set<char> j(J.begin(), J.end());

    unordered_map<char, int> s;
    for (char c : S) {
        s[c]++;
    }

    int result = 0;
    for (char c : j) {
        result += s[c];
    }
    return result;
}

//811. Subdomain Visit Count
vector<string> subdomainVisits(vector<string>& cpdomains)
{
    unordered_map<string, int> m;
    for (string s : cpdomains) {
        int index = s.find(' ');
        int cnt = stoi(s.substr(0, index));
        string domain = s.substr(index + 1, s.length() - index - 1);

        for (int i = domain.length() - 1; i > 0; --i) {
            if (domain[i] == '.') {
                m[domain.substr(i + 1, domain.length() - i - 1)] += cnt;
            }
        }
        m[domain] += cnt;
    }

    vector<string> result;
    for (auto s : m) {
        result.push_back(to_string(s.second) + ' ' + s.first);
    }
    return result;
}
#endif // LEETCODEEASYHASHTABLE_H

