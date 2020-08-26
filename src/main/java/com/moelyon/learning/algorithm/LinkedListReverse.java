package com.moelyon.learning.algorithm;


class TreeNode {
    public int value;
    public TreeNode next;

    public TreeNode(final int val) {
        this.value = val;
        this.next = null;
    }
}

public class LinkedListReverse {
    
    private static TreeNode BuildList(){
        TreeNode head = new TreeNode(0);

        TreeNode cur = head;

       for(int i=1; i<233; i++){
           TreeNode node = new TreeNode(i);
           cur.next = node;
           cur = cur.next; 
       }
       return head.next;
    }


    private static String printList(TreeNode root){
        StringBuilder sb = new StringBuilder();

        TreeNode cur = root;
        while(cur != null){
            sb.append(cur.value+" ");
            cur = cur.next;
        }

        return sb.toString();
    }

    private static TreeNode reverseByStack(TreeNode root,TreeNode pre){
        TreeNode ans = root;
        if(root.next != null){
            ans =  reverseByStack(root.next,root);
        }
        root.next = pre;
        return ans;

    }

    private static TreeNode reverseList(TreeNode root){
        TreeNode cur=null,prev = root;
        while(prev != null){
            TreeNode t = prev.next;
            prev.next = cur;
            cur = prev;
            prev = t;
        }

        return cur;
    }

    public static void main(String[] args) {
        TreeNode root = BuildList();
        var str = printList(root);
        System.out.println(str);

        System.out.println("after reverse");
        TreeNode node = reverseByStack(root,null);
        System.out.println(printList(node));

    }
}