package com.moelyon.learning.leetcode;



public class SumOfLeftLeaves {
    static class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            dfs(root);
            return  sum;
        }

        private int sum = 0;
        private void dfs(TreeNode node){

            if(node == null){
                return;
            }

            if(node.left != null && node.left.left == null && node.left.right == null){
                sum += node.left.val;
            }

            dfs(node.left);
            dfs(node.right);

        }
    }

    public static void main(String[] args) {

    }
}
