#ifndef LEETCODEGREEDY
#define LEETCODEGREEDY

#include <vector>

using namespace std;

//455. Assign Cookies
int findContentChildren(vector<int>& g, vector<int>& s)
{
    sort(g.begin(), g.end());
    sort(s.begin(), s.end());

    int res = 0;
    int idx = 0;
    for (int i = 0; i < g.size(); ++i) {
        for (int j = idx; j < s.size(); ++j) {
            if (g[i] <= s[j]) {
                res++;
                idx = j + 1;
                break;
            }
        }
    }
    return res;
}


#endif // LEETCODEGREEDY

