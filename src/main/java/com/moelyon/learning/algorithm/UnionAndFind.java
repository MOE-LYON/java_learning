package com.moelyon.learning.algorithm;

import java.util.*;

/**
 * @author moelyon
 *
 * https://leetcode-cn.com/problems/accounts-merge/
 */

public class UnionAndFind {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.accountsMerge();
    }
}

class DFU{
    private final List<Integer> arr;
    private int size;
    public DFU(int init){
        arr = new ArrayList<>(init);
        size = init;
        for(int i=0; i<init; i++){
            arr.add(i);
        }
    }

    public void increase(int num){
        for(int i=0; i<num; ++i){
            arr.add(size++);
        }
    }

    public int find(int x){
        int root = x;
        while(root != arr.get(root)){
            root = arr.get(root);
        }

        return root;
    }

    public void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);

        arr.set(xRoot, yRoot);
    }

    public int getSize(){
        return size;
    }
}
class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DFU dfu = new DFU(30);
        int identity =1;
        Map<String,Integer> email_to_id = new HashMap<>();
        Map<String,String> email_to_name = new HashMap<>();

        for(List<String> line : accounts){
            String name = line.get(0);

            for(int i=1; i<line.size(); ++i){
                String email = line.get(i);
                email_to_name.put(email, name);

                if(!email_to_id.containsKey(email)){
                    email_to_id.put(email, identity++);
                }
                if(dfu.getSize() <identity+5){
                    dfu.increase(10);
                }
                dfu.union(email_to_id.get(line.get(1)), email_to_id.get(email));
            }
        }

        Map<Integer,List<String>>  ans = new  HashMap<>();

        for ( String email : email_to_id.keySet()) {
            ans.computeIfAbsent(dfu.find(email_to_id.get(email)),x-> new ArrayList<>()).add(email);
        }

        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, email_to_name.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }
}

/*
 * 总结一下  email to Id 这个操作 相当于 一个Hash 映射操作 又和数据库的主键有异曲同工之处
 * email to name 这个是为了记录名字，用空间换取时间的操作，只需要O （1） 即可从邮箱映射到 account
 *使用自动扩容的数组 代替传统的预先分配一个很大的数据 这里使用ArrayList 而不是LinkedList 是因为这个只会扩容 不会减少 而且经常随机访问
 *
 * 遍历输入的列表 name = list[0] emails = list[1:]
 *
 * 查看 email 是否获取到了 Id 若没获取Id 则申请一个  若空间不足 自动扩容
 * 合并个人下的所有邮箱
 *
 *剩下就是遍历整个 email-id 这个集合
 * for( Map.Entry<String,Integer> entry : emailToId)
 * 根据Id 获取所在集合 并加入其中
 *
 * 最后 从 Email-Name 中获取Name 并填充到ans 中
 */
