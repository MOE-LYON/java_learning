package com.moelyon.learning.algorithm;

public class MySqrt {
    static class Solution {
        public int mySqrt(int x) {

            int left =1 , right = x;
            int ans=0;
            while(left<= right){

                int mid =  left +(right-left)/2;

                if(mid == x/mid){
                    return mid;
                }else if(mid> x/mid){
                    right = mid-1;
                }else{
                    left = mid+1;
                    ans = mid;
                }
            }

            return ans;
        }

        public double mySqrtPlus(int x, int n){

            double left =0, right =x;
            double ans =left;
            double accuracy = Math.pow(10, -n);
            while(Math.abs(ans*ans - x) > accuracy){
                double mid = left + (right-left)/2;
                if(Math.abs(x - x/mid) <accuracy){
                    return mid;
                }else if(mid >x/mid){
                    right = mid;
                }else{
                    left = mid;
                    ans = mid;
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        var solution = new Solution();

        var ans = solution.mySqrt(233);
        System.out.println("ans = " + ans);
    }
}
