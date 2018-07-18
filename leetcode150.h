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


#endif // LEETCODE150_H
