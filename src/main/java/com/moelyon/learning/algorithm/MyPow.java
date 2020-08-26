package com.moelyon.learning.algorithm;

public class MyPow {

    static class Solution {
        public double myPow(double x, int n) {
//            return helper(x,n);
            return  helper2(x, n);
        }

        private double helper(double x, int n){
            if(n ==0){
                return 1;
            }
//            System.out.println("n = " + n);
            double temp = helper(x,n>>1);
            temp = temp*temp;
            if((n & 1) == 1){
                temp *= x;
            }
            return temp;
        }

        private double helper2(double x, int n){
            double ans =1;

            double temp=x;
            while (n>0){
                if((n&1) ==1){
                    ans *= temp;
                }
                temp *= temp;
                n = n>>1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        var res = solution.myPow(2,10);
        System.out.println("res = " + res);
    }
}
