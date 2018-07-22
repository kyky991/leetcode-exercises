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
TreeNode *buildTree(vector<int>& inorder, vector<int>& postorder, int postStart, int inStart, int inEnd)
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
    root->right = buildTree(inorder, postorder, postStart - 1, index + 1, inEnd);
    root->left = buildTree(inorder, postorder, postStart - (inEnd - index) - 1, inStart, index - 1);
    return root;
}

TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder)
{
    return buildTree(inorder, postorder, postorder.size() - 1, 0, inorder.size() - 1);
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

#endif // LEETCODE150_H
