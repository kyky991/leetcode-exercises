package com.zing.leetcode.explore.learn;

import com.zing.structure.TreeNode;

/**
 * @author Zing
 * @date 2020-05-16
 */
public class TestTreeSolution {

    public static void main(String[] args) {
        TreeSolution.Codec codec = new TreeSolution.Codec();
        TreeNode root = codec.deserialize("5,1,5,5,5,null,5");

        TreeSolution solution = new TreeSolution();
        System.out.println(solution.countUnivalSubtrees(root));
    }

}
