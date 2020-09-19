package com.moelyon.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

public class combinationSum {
    static class Solution {

        private List<List<Integer>> ans = new ArrayList<>();
        private List<Integer> temp = new ArrayList<>();

        public List<List<Integer>> combinationSum3(int k, int n) {

            dfs(k, n, 9);
            return ans;
        }

        private void dfs(int k,int sum, int v){
            if (temp.size() + (v) < k || temp.size() > k) {
                return;
            }
            if(temp.size() == k && sum ==0 ){
                ans.add(new ArrayList<>(temp));
                return;
            }

            if(v==0){
                return;
            }
            if(sum>=v){
                temp.add(v);
                dfs(k, sum-v, v-1);
                temp.remove(temp.size()-1);
            }
            dfs(k, sum, v-1);

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr ={2,5,2,1,2};
        var ans = solution.combinationSum3(3, 7);

        System.out.println(ans);
    }
}
