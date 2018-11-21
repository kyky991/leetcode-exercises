#ifndef LEETCODE150_H
#define LEETCODE150_H

#include "common.h"
#include "leetcode100.h"

//101. Symmetric Tree
bool isSymmetric(TreeNode *left, TreeNode *right)
{
    if (left == NULL && right == NULL)
        return true;
    if (left && right && left->val == right->val) {
        return isSymmetric(left->left, right->right) && isSymmetric(left->right, right->left);
    }
    return false;
}

bool isSymmetric(TreeNode* root)
{
    if (root == NULL)
        return true;

    return isSymmetric(root->left, root->right);
}

//102. Binary Tree Level Order Traversal
void levelOrder(vector<vector<int>> &vv, TreeNode *node, int level)
{
    if (node) {
        if (vv.size() <= level)
            vv.push_back(vector<int>());

        vv[level].push_back(node->val);

        levelOrder(vv, node->left, level + 1);
        levelOrder(vv, node->right, level + 1);
    }
}

vector<vector<int>> levelOrder(TreeNode* root)
{
    vector<vector<int>> res;
    levelOrder(res, root, 0);
    return res;
}

//103. Binary Tree Zigzag Level Order Traversal
void zigzagLevelOrder(vector<vector<int>> &vv, TreeNode *node, int level)
{
    if (node) {
        if (vv.size() <= level)
            vv.push_back(vector<int>());

        if (level % 2 == 0)
            vv[level].push_back(node->val);
        else
            vv[level].insert(vv[level].begin(), node->val);

        zigzagLevelOrder(vv, node->left, level + 1);
        zigzagLevelOrder(vv, node->right, level + 1);
    }
}

vector<vector<int>> zigzagLevelOrder(TreeNode* root)
{
    vector<vector<int>> res;
    zigzagLevelOrder(res, root, 0);
    return res;
}

//104. Maximum Depth of Binary Tree
int maxDepth(TreeNode* root)
{
    if (root == NULL)
        return 0;

    return 1 + max(maxDepth(root->left), maxDepth(root->right));
}

//105. Construct Binary Tree from Preorder and Inorder Traversal
TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder, int preStart, int inStart, int inEnd)
{
    if (preStart > preorder.size() - 1 || inStart > inEnd)
        return NULL;

    TreeNode *root = new TreeNode(preorder[preStart]);
    int index = 0;
    for (int i = inStart; i <= inEnd; ++i) {
        if (root->val == inorder[i]) {
            index = i;
            break;
        }
    }
    root->left = buildTree(preorder, inorder, preStart + 1, inStart, index - 1);
    root->right = buildTree(preorder, inorder, preStart + (index - inStart) + 1 , index + 1, inEnd);
    return root;
}

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder)
{
    return buildTree(preorder, inorder, 0, 0, inorder.size() - 1);
}

//106. Construct Binary Tree from Inorder and Postorder Travers al
TreeNode *buildTreeFromInorderAndPostorder(vector<int>& inorder, vector<int>& postorder, int postStart, int inStart, int inEnd)
{
    if (postStart < 0 || inStart > inEnd)
        return NULL;

    TreeNode *root = new TreeNode(postorder[postStart]);
    int index = 0;
    for (int i = inStart; i <= inEnd; ++i) {
        if (root->val == inorder[i]) {
            index = i;
            break;
        }
    }
    root->right = buildTreeFromInorderAndPostorder(inorder, postorder, postStart - 1, index + 1, inEnd);
    root->left = buildTreeFromInorderAndPostorder(inorder, postorder, postStart - (inEnd - index) - 1, inStart, index - 1);
    return root;
}

TreeNode* buildTreeFromInorderAndPostorder(vector<int>& inorder, vector<int>& postorder)
{
    return buildTreeFromInorderAndPostorder(inorder, postorder, postorder.size() - 1, 0, inorder.size() - 1);
}

//107. Binary Tree Level Order Traversal II
void levelOrderBottom(vector<vector<int>> &vv, TreeNode* node, int level)
{
    if (node) {
        if (vv.size() <= level)
            vv.push_back(vector<int>());

        vv[level].push_back(node->val);
        levelOrderBottom(vv, node->left, level + 1);
        levelOrderBottom(vv, node->right, level + 1);
    }
}

vector<vector<int>> levelOrderBottom(TreeNode* root)
{
    vector<vector<int>> res;
    levelOrderBottom(res, root, 0);

    reverse(res.begin(), res.end());
    return res;
}

//108. Convert Sorted Array to Binary Search Tree
TreeNode* sortedArrayToBST(vector<int>& nums, int start, int end)
{
    if (start > end)
        return NULL;

    int mid = (start + end) / 2;

    TreeNode *node = new TreeNode(nums[mid]);
    node->left = sortedArrayToBST(nums, start, mid - 1);
    node->right = sortedArrayToBST(nums, mid + 1, end);
    return node;
}

TreeNode* sortedArrayToBST(vector<int>& nums)
{
    return sortedArrayToBST(nums, 0, nums.size() - 1);
}

//109. Convert Sorted List to Binary Search Tree
TreeNode *sortedListToBST(ListNode *head, ListNode *tail)
{
    if (head == tail)
        return NULL;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != tail && fast->next != tail) {
        slow = slow->next;
        fast = fast->next->next;
    }

    TreeNode *root = new TreeNode(slow->val);
    root->left = sortedListToBST(head, slow);
    root->right = sortedListToBST(slow->next, tail);
    return root;
}

TreeNode* sortedListToBST(ListNode* head)
{
    return sortedListToBST(head, NULL);
}

//110. Balanced Binary Tree
bool isBalanced(TreeNode* root)
{
    if (root) {
        if (abs(maxDepth(root->left) - maxDepth(root->right)) > 1)
            return false;
        else
            return isBalanced(root->left) && isBalanced(root->right);
    }
    return true;
}

//112. Path Sum
bool hasPathSum(TreeNode* root, int sum)
{
    if (root == NULL)
        return false;

    if (root->left == NULL && root->right == NULL && sum - root->val == 0)
        return true;

    return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);
}

//113. Path Sum II
void pathSum(vector<vector<int>> &vv, vector<int> v, TreeNode *node, int sum)
{
    if (node) {
        v.push_back(node->val);

        if (node->left == NULL && node->right == NULL && sum - node->val == 0) {
            vv.push_back(v);
            return;
        }

        pathSum(vv, v, node->left, sum - node->val);
        pathSum(vv, v, node->right, sum - node->val);
    }
}

vector<vector<int>> pathSum(TreeNode* root, int sum)
{
    vector<vector<int>> res;
    pathSum(res, vector<int>(), root, sum);
    return res;
}

//114. Flatten Binary Tree to Linked List
void flatten(TreeNode* root)
{
#if 1
    TreeNode *now = root;
    while (now) {
        if (now->left) {
            TreeNode *pre = now->left;
            while (pre->right) {
                pre = pre->right;
            }
            pre->right = now->right;
            now->right = now->left;
            now->left = NULL;
        }
        now = now->right;
    }
#else
    stack<TreeNode *> s;
    vector<TreeNode *> v;
    TreeNode *cur = root;
    while (cur || !s.empty()) {
        while (cur) {
            s.push(cur);
            v.push_back(cur);
            cur = cur->left;
        }
        cur = s.top();
        s.pop();

        cur = cur->right;
    }

    cur = root;
    for (int i = 1; i < v.size(); ++i) {
        cur->left = NULL;
        cur->right = v[i];
        cur = cur->right;
    }
#endif
}

//115. Distinct Subsequences
int numDistinct(string s, string t)
{
    int m = s.size(), n = t.size();
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

    for (int i = 0; i <= m; ++i) {
        dp[0][i] = 1;
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (t[i] == s[j]) {
                dp[i + 1][j + 1] = dp[i][j] + dp[i + 1][j];
            } else {
                dp[i + 1][j + 1] = dp[i + 1][j];
            }
        }
    }

    return dp[n][m];
}

//116. Populating Next Right Pointers in Each Node
void connect(vector<vector<TreeLinkNode *>> &vv, TreeLinkNode *node, int level)
{
    if (node) {
        if (vv.size() <= level)
            vv.push_back(vector<TreeLinkNode *>());

        vv[level].push_back(node);
        connect(vv, node->left, level + 1);
        connect(vv, node->right, level + 1);
    }
}

void connect(TreeLinkNode *root)
{
#if 1
    if (root == NULL)
        return;
    TreeLinkNode *pre = root;
    TreeLinkNode *cur = NULL;
    while (pre->left) {
        cur = pre;
        while (cur) {
            cur->left->next = cur->right;
            if (cur->next) {
                cur->right->next = cur->next->left;
            }
            cur = cur->next;
        }
        pre = pre->left;
    }
#else
    vector<vector<TreeLinkNode *>> vv;
    connect(vv, root, 0);

    for (int i = 0; i < vv.size(); ++i) {
        for (int j = 0; j < vv[i].size() - 1; ++j) {
            vv[i][j]->next = vv[i][j + 1];
        }
    }
#endif
}

//117. Populating Next Right Pointers in Each Node II
void connect2(TreeLinkNode *root)
{
    TreeLinkNode *head = NULL;
    TreeLinkNode *pre = NULL;
    TreeLinkNode *cur = root;

    while (cur) {
        while (cur) {
            if (cur->left) {
                if (pre) {
                    pre->next = cur->left;
                } else {
                    head = cur->left;
                }
                pre = cur->left;
            }

            if (cur->right) {
                if (pre) {
                    pre->next = cur->right;
                } else {
                    head = cur->right;
                }
                pre = cur->right;
            }
            cur = cur->next;
        }

        cur = head;
        head = NULL;
        pre = NULL;
    }
}

//118. Pascal's Triangle
vector<vector<int>> generate(int numRows)
{
    vector<vector<int>> res;
    for (int i = 0; i < numRows; ++i) {
        vector<int> v(i + 1, 1);
        for (int j = 1; j < i; ++j) {
            v[j] = res[i - 1][j - 1] + res[i - 1][j];
        }
        res.push_back(v);
    }
    return res;
}

//119. Pascal's Triangle II
vector<int> getRow(int rowIndex)
{
    vector<int> res(rowIndex + 1, 0);
    res[0] = 1;
    for (int i = 1; i < res.size(); ++i) {
        for (int j = i;  j >= 1; --j) {
            res[j] += res[j - 1];
        }
    }
    return res;
}

//120. Triangle
int minimumTotal(vector<vector<int>>& triangle)
{
    int n = triangle.size();
    vector<int> dp(triangle.back());

    for (int row = n - 2; row >= 0; --row) {
        for (int i = 0; i <= row; ++i) {
            dp[i] = min(dp[i], dp[i + 1]) + triangle[row][i];
        }
    }
    return dp[0];
}

//121. Best Time to Buy and Sell Stock
int maxProfit(vector<int>& prices)
{
    int minVal = INT_MAX;
    int res = 0;
    for (int i = 0; i < prices.size(); ++i) {
        minVal = min(minVal, prices[i]);
        res = max(res, prices[i] - minVal);
    }
    return res;
}

//122. Best Time to Buy and Sell Stock II
int maxProfit2(vector<int>& prices)
{
    int res = 0, cur = 0;
    for (int i = 1; i < prices.size(); ++i) {
        cur = prices[i] - prices[i - 1];
        cur = max(0, cur);
        res += cur;
    }
    return res;
}

//123. Best Time to Buy and Sell Stock III
int maxProfit3(vector<int>& prices)
{
    if (prices.size() <= 1)
        return 0;
    else {
        int K = 2;
        int res = 0;
        vector<vector<int>> dp(K + 1, vector<int>(prices.size(), 0));
        for (int i = 1; i <= K; ++i) {
            int tmp = dp[i - 1][0] - prices[0];
            for (int j = 1; j < prices.size(); ++j) {
                dp[i][j] = max(dp[i][j - 1], prices[j] + tmp);
                tmp = max(tmp, dp[i - 1][j] - prices[j]);
                res = max(dp[i][j], res);
            }
        }
        return res;
    }
}

//124. Binary Tree Maximum Path Sum
int maxPathSum(TreeNode *node, int &count)
{
    if (node == NULL)
        return 0;

    int left = max(0, maxPathSum(node->left, count));
    int right = max(0, maxPathSum(node->right, count));

    count = max(count, left + right + node->val);

    return max(left, right) + node->val;
}

int maxPathSum(TreeNode* root)
{
    int res = INT_MIN;
    maxPathSum(root, res);
    return res;
}

//125. Valid Palindrome
bool isPalindrome(string s)
{
    int start = 0, end = s.size() - 1;
    while (start < end) {
        if (!isalnum(s[start])) {
            start++;
            continue;
        }
        if (!isalnum(s[end])) {
            end--;
            continue;
        }
        if (tolower(s[start]) != tolower(s[end])) {
            return false;
        } else {
            start++;
            end--;
        }
    }
    return true;
}

//126. Word Ladder II
vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList)
{
    vector<vector<string>> res;
    queue<vector<string>> paths;
    unordered_set<string> wordDict(wordList.begin(), wordList.end());

    paths.push({beginWord});
    int level = 1;
    int minLevel = INT_MAX;

    unordered_set<string> visited;

    while (!paths.empty()) {
        vector<string> path = paths.front();
        paths.pop();
        if (path.size() > level) {
            for (string w : visited)
                wordDict.erase(w);
            visited.clear();
            if (path.size() > minLevel)
                break;
            else
                level = path.size();
        }
        string last = path.back();

        for (int i = 0; i < last.size(); ++i) {
            string news = last;
            for (char c = 'a'; c <= 'z'; ++c) {
                news[i] = c;
                if (wordDict.find(news) != wordDict.end()) {
                    vector<string> newpath = path;
                    newpath.push_back(news);
                    visited.insert(news);
                    if (news == endWord) {
                        minLevel = level;
                        res.push_back(newpath);
                    } else {
                        paths.push(newpath);
                    }
                }
            }
        }
    }

    return res;
}

//127. Word Ladder
void addNextWords(string word, unordered_set<string> &wordDict, queue<string> &visited)
{
    wordDict.erase(word);
    for (int i = 0; i < word.size(); ++i) {
        string news = word;
        for (char c = 'a'; c <= 'z'; ++c) {
            news[i] = c;
            if (wordDict.find(news) != wordDict.end()) {
                visited.push(news);
                wordDict.erase(news);
            }
        }
    }
}

int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
    unordered_set<string> wordDict(wordList.begin(), wordList.end());
    queue<string> visited;

    addNextWords(beginWord, wordDict, visited);

    int res = 1;
    while (!visited.empty()) {
        int num = visited.size();
        for (int i = 0; i < num; ++i) {
            string s = visited.front();
            visited.pop();
            if (s == endWord)
                return res;
            addNextWords(s, wordDict, visited);
        }
        res++;
    }
    return 0;
}

//128. Longest Consecutive Sequence
int longestConsecutive(vector<int>& nums) {
#if 1
    unordered_map<int, int> m;
    int res = 0;
    for (int num : nums) {
        if (m[num])
            continue;
        int left = m[num - 1] ? m[num - 1] : 0;
        int right = m[num + 1] ? m[num + 1] : 0;

        int sum = left + right + 1;
        m[num] = sum;

        res = max(res, sum);

        m[num - left] = sum;
        m[num + right] = sum;
    }
    return res;
#else
    int size = nums.size();
    if (size < 2) {
        return size;
    }

    sort(nums.begin(), nums.end());

    int res = 1;
    int cnt = 1;
    for (int i = 1; i < nums.size(); ++i) {
        if (nums[i] == nums[i - 1] + 1) {
            cnt++;
        } else if (nums[i] != nums[i - 1]) {
            cnt = 1;
        }
        res = max(res, cnt);
    }
    return res;
#endif
}

//129. Sum Root to Leaf Numbers
int sumNumbers(TreeNode *node, int sum)
{
    if (node == NULL)
        return 0;
    if (node->left == NULL && node->right == NULL)
        return sum * 10 + node->val;
    return sumNumbers(node->left, sum * 10 + node->val) + sumNumbers(node->right, sum * 10 + node->val);
}

int sumNumbers(TreeNode* root)
{
    return sumNumbers(root, 0);
}

//130. Surrounded Regions
void check(vector<vector<char>> &board, int i, int j, int row, int col)
{
    if (board[i][j] == 'O') {
        board[i][j] = '1';
        if (i > 1)
            check(board, i - 1, j, row, col);
        if (j > 1)
            check(board, i, j - 1, row, col);
        if (i + 1 < row)
            check(board, i + 1, j, row, col);
        if (j + 1 < col)
            check(board, i, j + 1, row, col);
    }
}

void solveSurroundedRegions(vector<vector<char>>& board)
{
    int row = board.size();
    if (row < 1)
        return;
    int col = board[0].size();

    for (int i = 0; i < row; ++i) {
        check(board, i, 0, row, col);
        if (col > 1)
            check(board, i, col - 1, row, col);
    }
    for (int j = 1; j < col - 1; ++j) {
        check(board, 0, j, row, col);
        if (row > 1)
            check(board, row - 1, j, row, col);
    }
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            if (board[i][j] == 'O') {
                board[i][j] = 'X';
            }
        }
    }
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            if (board[i][j] == '1') {
                board[i][j] = 'O';
            }
        }
    }
}

//131. Palindrome Partitioning
bool isPalindrome(string s, int start, int end)
{
    while (start <= end) {
        if (s[start++] != s[end--])
            return false;
    }
    return true;
}

void partition(vector<vector<string>> &res, vector<string> &path, string s, int start)
{
    if (start == s.size()) {
        res.push_back(path);
        return;
    }
    for (int i = start; i < s.size(); ++i) {
        string p = s.substr(start, i - start + 1);
        if (isPalindrome(p)) {
            path.push_back(p);
            partition(res, path, s, i + 1);
            path.pop_back();
        }
    }
}

vector<vector<string>> partition(string s)
{
    vector<vector<string>> res;
    vector<string> path;

    partition(res, path, s, 0);

    return res;
}

//132. Palindrome Partitioning II
int minCut(string s)
{
    int n = s.size();
    if (n < 1)
        return -1;

    vector<vector<bool>> pal(n, vector<bool>(n, false));
    vector<int> dp(n);

    for (int i = 0; i < n; ++i)
        dp[i] = i;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= i; ++j) {
            if (s[i] == s[j] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                pal[j][i] = true;
                if (j == 0)
                    dp[i] = 0;
                else
                    dp[i] = min(dp[i], dp[j - 1] + 1);
            }
        }
    }

    return dp[n - 1];
}

//133. Clone Graph
UndirectedGraphNode *clone(unordered_map<UndirectedGraphNode *, UndirectedGraphNode *> &mp, UndirectedGraphNode *node)
{
    if (node == NULL)
        return NULL;

    if (mp.find(node) == mp.end()) {
        mp[node] = new UndirectedGraphNode(node->label);
        for (UndirectedGraphNode *neigh : node->neighbors) {
            mp[node]->neighbors.push_back(clone(mp, neigh));
        }
    }
    return mp[node];
}

UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node)
{
    unordered_map<UndirectedGraphNode *, UndirectedGraphNode *> mp;

    UndirectedGraphNode *copy = clone(mp, node);

    return copy;
}

//134. Gas Station
int canCompleteCircuit(vector<int>& gas, vector<int>& cost)
{
#if 1
    int start = 0, total = 0, tank = 0;
    for (int i = 0; i < gas.size(); ++i) {
        if ((tank = tank + gas[i] - cost[i]) < 0) {
            start = i + 1;
            total += tank;
            tank = 0;
        }
    }
    return (total + tank < 0) ? -1 : start;
#else
    int n = gas.size();

    for (int i = 0; i < n; ++i) {
        if (gas[i] >= cost[i]) {
            int tank = 0;
            int j;
            for (j = i; j - i < n; ++j) {
                tank += gas[j % n];
                tank -= cost[j % n];
                if (tank < 0) {
                    break;
                }
            }
            if (j == n + i) {
                return i;
            }
        }
    }

    return -1;
#endif
}

#endif // LEETCODE150_H
