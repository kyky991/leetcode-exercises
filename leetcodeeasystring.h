#ifndef LEETCODEEASYSTRING
#define LEETCODEEASYSTRING

#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <stack>

using namespace std;

/*
//14. Longest Common Prefix
string longestCommonPrefix(vector<string>& strs)
{
    int size = strs.size();
    if (size < 1)
        return "";

    string ret = "";
    string s = strs[0];
    int j = 0;
    while (j < s.size()) {
        int i = 1;
        for (; i < size; ++i) {
            if (j < strs[i].size() && strs[i][j] == s[j]) {

            } else {
                j = s.size();
                break;
            }
        }
        if (i == size)
            ret.push_back(s[j]);

        j++;
    }
    return ret;
}
*/

/*
//20. Valid Parentheses
bool isValid(string s)
{
    stack<char> sk;
    for (auto c : s) {
        if (c == '(')
            sk.push(')');
        else if (c == '{')
            sk.push('}');
        else if (c == '[')
            sk.push(']');
        else if (!sk.empty() && c == sk.top())
            sk.pop();
        else
            return false;
    }
    return sk.empty();
}
*/

/*
//28. Implement strStr()
int strStr(string haystack, string needle)
{
    if (needle.size() == 0)
        return 0;

    int i = 0;
    int j = 0;
    while (i < haystack.size() - needle.size() && j < needle.size()) {
        if (haystack[i] == needle[j]) {
            i++;
            j++;
        } else {
            i -= j;
            j = 0;
            i++;
        }
    }
    return j == needle.size() ? i - j : -1;
}
*/
/*
//38. Count and Say
string countAndSay(int n)
{
    return "";
}
*/
/*
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

//125. Valid Palindrome
bool isPalindrome(string s)
{
    int left = 0;
    int right = s.size() - 1;
    while (left < right) {
        if (!isalnum(s[left])) {
            left++;
            continue;
        }
        if (!isalnum(s[right])) {
            right--;
            continue;
        }
        if (tolower(s[left]) != tolower(s[right])) {
            return false;
        } else {
            left++;
            right--;
        }
    }
    return true;
}
*/
//344. Reverse String
string reverseString(string s)
{
    int left = 0;
    int right = s.size() - 1;
    while (left < right) {
        char c = s[left];
        s[left] = s[right];
        s[right] = c;

        left++;
        right--;
    }
    return s;
}

//345. Reverse Vowels of a String
string reverseVowels(string s)
{
    unordered_set<char> cs = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

    int left = 0;
    int right = s.size() - 1;
    while (left < right) {
        if (cs.count(s[left]) && cs.count(s[right])) {
            char c = s[left];
            s[left] = s[right];
            s[right] = c;

            left++;
            right--;
        } else if (!cs.count(s[left])){
            left++;
        } else if (!cs.count(s[right])) {
            right--;
        }
    }
    return s;
}

//383. Ransom Note
bool canConstruct(string ransomNote, string magazine)
{
    unordered_map<char, int> m;
    for (auto c : magazine)
        m[c]++;
    for (auto c : ransomNote)
        if (--m[c] < 0)
            return false;
    return true;
}

//434. Number of Segments in a String
int countSegments(string s)
{
    int res = 0;
    s.push_back(' ');
    for(int i = 1; i < s.size(); ++i)
        if(s[i] == ' ' && s[i-1] != ' ') ++res;
    return res;
}

//443. String Compression
int compress(vector<char>& chars)
{
    int i = 0, cnt = 0, t = 0;
    for (int j = 0; j < chars.size(); ++j) if (j+1 == chars.size() || chars[j+1] != chars[j]) {
        int m = t;
        chars[t++] = chars[j];
        if (j >= i + 1) {
            string s = to_string(j-i+1);
            for (char c:s) chars[t++] = c;
        }
        i = j + 1;
        cnt += t-m;
    }
    return cnt;

    int result = 0;

    unordered_map<char, int> m;
    for (auto c : chars)
        m[c]++;

    char last = 0;
    vector<char>::iterator iter = chars.begin();
    while (iter != chars.end()) {
        if (last == *iter && m[*iter] > 0) {
            if (m.count(*iter)) {
                string s = to_string(m[*iter]);
                m[*iter] = 0;
                for (auto c : s) {
                    last = *iter;
                    *iter++ = c;
                }
                result++;
            } else {
                last = *iter;
                iter = chars.erase(iter);
            }
        } else {
            last = *iter;
            iter++;
        }
    }

    return result;
}

//459. Repeated Substring Pattern
bool repeatedSubstringPattern(string s)
{
    return (s + s).substr(1, s.size() * 2 - 2).find(s) != -1;
}


//520. Detect Capital
bool detectCapitalUse(string word)
{
    int cnt = 0;
    for (auto c : word) {
        cnt += (tolower(c) - c);
    }
    if (cnt == word.size() * 32)
        return true;

    if (tolower(word[0]) - word[0] == cnt)
        return true;

    return false;
}

//521. Longest Uncommon Subsequence I
int findLUSlength(string a, string b)
{
    return -1;
}

//541. Reverse String II
string reverseStr(string s, int k)
{
    if (k <= 0)
        return s;

    for (int i = 0; i < s.size(); i += 2 * k) {
        int left = i;
        int right = (left + k - 1) < s.size() ? (left + k - 1) : s.size() - 1;
        while (left < right) {
            swap(s[left], s[right]);
            left++;
            right--;
        }
    }
    return s;
}

//551. Student Attendance Record I
bool checkRecord(string s)
{
    int Ls = 0;
    int As = 0;
    for (auto c : s) {
        if (c == 'L') {
            Ls++;
        } else {
            Ls = 0;
            if (c == 'A')
                As++;
        }
        if (Ls > 2 || As > 1)
            return false;
    }
    return true;
}

//557. Reverse Words in a String III
string reverseWords(string s)
{
    int left = -1;
    int right = -1;
    for (int i = 0; i < s.size(); ++i) {
        if (s[i] != ' ') {
            left = left == -1 ? i : left;
            right = i;
        }
        if (s[i] == ' ' || i == s.size() - 1) {
            while (left < right) {
                swap(s[left], s[right]);
                left++;
                right--;
            }
            left = -1;
            right = left;
        }
    }
    return s;
}

//657. Judge Route Circle
bool judgeCircle(string moves)
{
    unordered_map<char, int> m;
    for (auto c : moves) {
        m[c]++;
    }
    return (m['U'] == m['D']) && (m['L'] == m['R']);
}

//680. Valid Palindrome II
bool validPalindrome(string s)
{
    int cnt = 0;
    int left = 0;
    int right = s.size() - 1;
    while (left < right) {
        if (s[left] == s[right]) {
            left++;
            right--;
        } else {
            if (cnt == 0) {
                left++;
                cnt++;
            } else if (cnt == 1) {
                left--;
                right--;
                cnt++;
            } else {
                return false;
            }
        }
    }
    return true;
}

//686. Repeated String Match
int repeatedStringMatch(string A, string B)
{
    int i = 1;
    string t = A;
    while(A.size() < B.size()){
        A += t;
        i++;
    }
    if (strstr(A.c_str(), B.c_str())) return i;
    A += t;
    if (strstr(A.c_str(), B.c_str())) return i + 1;
    return -1;
}

//696. Count Binary Substrings
int countBinarySubstrings(string s)
{
    int cur = 1, pre = 0, res = 0;
    for (int i = 1; i < s.size(); ++i) {
        if (s[i] == s[i - 1]) {
            cur++;
        } else {
            res += min(cur, pre);
            pre = cur;
            cur = 1;
        }
    }
    return res + min(cur, pre);
}

//819. Most Common Word
string mostCommonWord(string paragraph, vector<string>& banned)
{
    unordered_map<string, int> m;
    for (int i = 0; i < paragraph.size();) {
        string s = "";
        while (i < paragraph.size() && isalpha(paragraph[i]))
            s.push_back(tolower(paragraph[i++]));
        while (i < paragraph.size() && !isalpha(paragraph[i]))
            i++;
        m[s]++;
    }
    for (auto s : banned)
        m[s] = 0;

    string res = "";
    int cnt = 0;
    for (auto p : m) {
        if (p.second > cnt) {
            res = p.first;
            cnt = p.second;
        }
    }
    return res;
}

//824. Goat Latin
string toGoatLatin(string S)
{
    unordered_set<char> vowels = { 'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};
    vector<string> ss;

    for (int i = 0; i < S.size();) {
        string s = "";
        while (i < S.size() && isalpha(S[i]))
            s.push_back(S[i++]);
        while (i < S.size() && !isalpha(S[i]))
            i++;

        if (!vowels.count(s[0])) {
            s.push_back(s[0]);
            s = s.substr(1);
        }
        s += "ma";
        for (int j = 0; j < ss.size(); ++i) {
            s.push_back('a');
        }

        ss.push_back(s);
    }

    string res = "";
    for (int i = 0; i < ss.size(); ++i) {
        res += ss[i];
        if (i != ss.size() - 1)
            res += ' ';
    }
    return res;
}


#endif // LEETCODEEASYSTRING

