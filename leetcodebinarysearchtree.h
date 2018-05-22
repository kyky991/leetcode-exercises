#ifndef LEETCODEBINARYSEARCHTREE
#define LEETCODEBINARYSEARCHTREE

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

//530. Minimum Absolute Difference in BST
int minDiff(TreeNode *node, int val)
{
    if (node) {
        int left = INT_MAX;
        int right = INT_MAX;
        if (node->left)
            left = abs(node->left->val - val);
        if (node->right)
            right = abs(node->right->val - val);
        int m = min(left, right);
        return min(min(m, minDiff(node->left, val)), minDiff(node->right, val));
    } else {
        return INT_MAX;
    }
}

int getMinimumDifference(TreeNode* root)
{
    if (root) {
        int m = minDiff(root, root->val);
        return min(min(m, getMinimumDifference(root->left)), getMinimumDifference(root->right));
    } else {
        return INT_MAX;
    }
}

//783. Minimum Distance Between BST Nodes
int minDiffInBST(TreeNode* root)
{
    if (root) {
        int m = minDiff(root, root->val);
        return min(min(m, minDiffInBST(root->left)), minDiffInBST(root->right));
    } else {
        return INT_MAX;
    }
}




#endif // LEETCODEBINARYSEARCHTREE

