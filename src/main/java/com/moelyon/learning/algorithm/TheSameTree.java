package com.moelyon.learning.algorithm;

/**
 * @author moelyon
 * https://leetcode-cn.com/problems/same-tree/
 */
public class TheSameTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Solution {

        private boolean isSame;

        public boolean isSameTree(TreeNode p, TreeNode q) {
            isSame = true;
            dfs(p, q);

            return isSame;
            // StringBuilder sb = new StringBuilder();
            // firstOrder(p,sb);
            // String str1 = sb.toString();
            // sb.setLength(0);
            // firstOrder(q, sb);
            // String str2 = sb.toString();
            // if(!str1.equals(str2)){
            //     return false;
            // }
            // sb.setLength(0);
            // middleOrder(p,sb);
            // str1 = sb.toString();
            // sb.setLength(0);
            // middleOrder(q,sb);
            // str2 = sb.toString();

            // return str1.equals(str2);
        }

        private void firstOrder(TreeNode node,StringBuilder sb){
            if(node == null){
                sb.append("null");
                return;
            }
            sb.append(node.val);
            firstOrder(node.left, sb);
            firstOrder(node.right, sb);
        }

        private void middleOrder(TreeNode node,StringBuilder sb){
            if(node ==null){
                sb.append("null");
                return ;
            }

            middleOrder(node.left, sb);
            sb.append(node.val);
            middleOrder(node.right, sb);
        }

        private void dfs(TreeNode node1,TreeNode node2){
            if(node1 == node2 && node1 == null){
                return; // null;
            }
            if(!isSame || (node1 ==null || node2 ==null )){
                isSame = false;
                return;
            }
            if(node1.val != node2.val){
                isSame = false;
                return;
            }

            dfs(node1.left, node2.left);
            if(isSame){
                dfs(node1.right, node2.right);
            }
        }
    }

}
