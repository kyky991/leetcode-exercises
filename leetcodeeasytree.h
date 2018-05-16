#ifndef LEETCODEEASYTREE
#define LEETCODEEASYTREE

#include <vector>
#include <unordered_set>
#include <unordered_map>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

//100. Same Tree
bool isSameTree(TreeNode* p, TreeNode* q)
{
    if (p && q) {
        if (p->val == q->val)
            return isSameTree(p->left, q->left) && isSameTree(p->right, q->right);
        else
            return false;
    } else if (!p && !q)
        return true;
    else
        return false;
}

//101. Symmetric Tree
void swapLeftRight(TreeNode *node)
{
    if (node && (node->left || node->right)) {
        TreeNode *tmp = node->left;
        node->left = node->right;
        node->right = tmp;

        swapLeftRight(node->left);
        swapLeftRight(node->right);
    }
}

bool isSymmetric(TreeNode *p, TreeNode *q)
{
    if (!p && !q)
        return true;
    else if (!p || !q)
        return false;

    if (p->val != q->val)
        return false;

    return isSymmetric(p->left, q->right) && isSymmetric(p->right, q->left);
}

bool isSymmetric(TreeNode* root)
{
    if (root) {
        return isSymmetric(root->left, root->right);
    } else {
        return true;
    }

    if (root) {
        swapLeftRight(root->right);
        return isSameTree(root->left, root->right);
    } else {
        return true;
    }
}

//104. Maximum Depth of Binary Tree
int maxDepth(TreeNode* root)
{
    if (root) {
        return 1 + max(maxDepth(root->left), maxDepth(root->right));
    } else {
        return 0;
    }
}

//107. Binary Tree Level Order Traversal II
void levelOrder(TreeNode *node, int level, vector<vector<int>> &v)
{
    if (node) {
        if (level > v.size()) {
            v.push_back(vector<int>());
        }
        v[level].push_back(node->val);

        level++;

        levelOrder(node->left, level, v);
        levelOrder(node->right, level, v);
    }
}

vector<vector<int>> levelOrderBottom(TreeNode* root)
{
    vector<vector<int>> res;

    levelOrder(root, 0, res);

    reverse();

    return res;
}

//108. Convert Sorted Array to Binary Search Tree
TreeNode *BST(vector<int> &nums, int start, int end)
{
    if (start <= end) {
        int mid = (start + end + 1) / 2;

        TreeNode *node = new TreeNode(nums[mid]);

        node->left = BST(nums, start, mid - 1);
        node->right = BST(nums, mid + 1, end);

        return node;
    } else {
        return NULL;
    }
}

TreeNode* sortedArrayToBST(vector<int>& nums)
{
    return BST(nums, 0, nums.size() - 1);
}

//110. Balanced Binary Tree
bool isBalanced(TreeNode* root)
{
    if (root) {
        int left = maxDepth(root->left);
        int right = maxDepth(root->right);

        return abs(left - right) <= 1 && isBalanced(root->left) && isBalanced(root->right);
    } else {
        return false;
    }
}

//111. Minimum Depth of Binary Tree
int minDepth(TreeNode* root)
{
    if (root) {
        int min1 = minDepth(root->left);
        int min2 = minDepth(root->right);
        if (min1 == 0 || min2 == 0)
            return max(min1, min2) + 1;
        return min(min1, min2) + 1;
    } else {
        return 0;
    }
}

//112. Path Sum
void pathSum(TreeNode *node, int val, unordered_set<int> &res)
{
    if (node) {
        val += node->val;
        if (!node->left && !node->right)
            res.insert(val);
        pathSum(node->left, val, res);
        pathSum(node->right, val, res);
    }
}

bool hasPathSum(TreeNode* root, int sum)
{
    if (root == NULL)
        return false;
    if (root->left == NULL && root->right == NULL && sum - root->val == 0)
        return true;

    return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);

    unordered_set<int> res;
    int val = 0;
    pathSum(root, val, res);

    return res.count(sum);
}

//226. Invert Binary Tree
TreeNode* invertTree(TreeNode* root)
{
    if (root == NULL)
        return NULL;

    TreeNode *left = root->left;
    TreeNode *right = root->right;

    root->right = invertTree(left);
    root->left = invertTree(right);

    return root;
}

//235. Lowest Common Ancestor of a Binary Search Tree
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q)
{
    while (root != p && root != q) {
        if (p->val <= root->val && q->val <= root->val) {
            root = root->left;
        } else if (p->val >= root->val && q->val >= root->val) {
            root = root->right;
        } else {
            break;
        }
    }
    return root;
}

//257. Binary Tree Paths
void BTPath(TreeNode *node, string s, vector<string> &res)
{
    if (node) {
        if (!s.empty())
            s += "->";

        s += to_string(node->val);

        if (!node->left && !node->right)
            res.push_back(s);
        else {
            BTPath(node->left, s, res);
            BTPath(node->right, s, res);
        }
    }
}

vector<string> binaryTreePaths(TreeNode* root)
{
    vector<string> res;
    string s = "";

    BTPath(root, s, res);

    return res;
}

//404. Sum of Left Leaves
int sumOfLeftLeaves(TreeNode* root)
{
    if (root) {
        if (root->left) {
            if (!root->left->left && !root->left->right)
                return root->left->val + sumOfLeftLeaves(root->right);
        }
        return sumOfLeftLeaves(root->left) + sumOfLeftLeaves(root->right);
    } else {
        return 0;
    }
}

//437. Path Sum III
void pathSum(TreeNode *node, int sum, int &count)
{
    if (node) {
        sum -= node->val;
        if (sum == 0) {
            count++;
        }
        pathSum(node->left, sum, count);
        pathSum(node->right, sum, count);
    }
}

int pathSum(TreeNode* root, int sum)
{
    int count = 0;

    pathSum(root, sum, count);

    if (root) {
        count += pathSum(root->left, sum) + pathSum(root->right, sum);
    }

    return count;
}

//501. Find Mode in Binary Search Tree
void findMode(TreeNode *root, unordered_map<int, int> &m)
{
    if (root) {
        m[root->val]++;
        findMode(root->left, &m);
        findMode(root->right, m);
    }
}

vector<int> findMode(TreeNode* root)
{
    int m = 0;
    unordered_map<int, int> map;
    vector<int> res;

    findMode(root, map);

    for (auto it : map) {
        if (it.second > m) {
            m = it.second;
            res.clear();
            res.push_back(it.first);
        } else if (it.second == m) {
            res.push_back(it.first);
        }
    }

    return res;
}

//538. Convert BST to Greater Tree
void convertBST(TreeNode *node, int &sum)
{
    if (node) {
        convertBST(node->right, sum);

        sum += node->val;
        node->val = sum;

        convertBST(node->left, sum);
    }
}

TreeNode* convertBST(TreeNode* root)
{
    int sum = 0;

    convertBST(root, sum);

    return root;
}

//543. Diameter of Binary Tree
int diameterOfBinaryTree(TreeNode* root)
{
    if (root) {
        int m = 0;
        int ldepth = maxDepth(root->left);
        int rdepth = maxDepth(root->right);
        m = max(m, ldepth + rdepth);

        return max(max(m, diameterOfBinaryTree(root->left)), diameterOfBinaryTree(root->right));
    } else {
        return 0;
    }
}



#endif // LEETCODEEASYTREE

