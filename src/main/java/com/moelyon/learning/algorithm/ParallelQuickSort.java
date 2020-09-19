package com.moelyon.learning.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.FutureTask;

public class ParallelQuickSort {

    private static class QuickSort extends ForkJoinTask<Object>{

        private final int[] arr;
        private final int left;
        private final int right;

        public QuickSort(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        public Object getRawResult() {
            return null;
        }

        @Override
        protected void setRawResult(Object value) {

        }

        private static final int CUT_OFF = 100;
        @Override
        protected boolean exec() {

            if(left>= right){
                return true;
            }
            if(right-left<=CUT_OFF){
                InsertSort.insertSort(arr,left,right+1);
                return true;
            }

            int pivot =partition(arr,left,right);

            ForkJoinTask<Object> task1 = new QuickSort(arr,left,pivot-1);
            ForkJoinTask<Object> task2 = new QuickSort(arr,pivot+1,right);

            task1.fork();
            task2.fork();
            task1.join();
            task2.join();

            return true;
        }

        private static int partition(int[] arr, int left, int right){
            int temp = arr[left];
            int i = left;
            while (left<right){

                while(right>left && arr[right]>=temp){
                    right--;
                }
                while(left<right && arr[left]<=temp){
                    left++;
                }
                if(left<right){
                    swap(arr,left,right);
                }
            }
            arr[i] = arr[left];
            arr[left] = temp;

            return left;
        }
        private static void  swap(int[] arr,int i,int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j]=temp;
        }

    }

    private static final long numOfArray = 500000;
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int[] ints = new Random().ints().limit(numOfArray).toArray();

        long current = System.currentTimeMillis();
        ForkJoinTask<Object> task = new QuickSort(ints,0,ints.length-1);
        pool.execute(task);
        task.join();

        System.out.println(" fork join cost time is " + (System.currentTimeMillis()-current) +" ms");
        boolean isvalid = true;
        for(int i=0; i<ints.length-1; ++i){
            if(ints[i]>ints[i+1]){
                isvalid = false;
                break;
            }
        }

        System.out.println(isvalid);
        pool.shutdown();

        ints = new Random().ints().limit(numOfArray).toArray();
        current = System.currentTimeMillis();
//        com.moelyon.learning.algorithm.QuickSort.quickSort(ints);
        Arrays.sort(ints);
        System.out.println(" one thread cost time is " + (System.currentTimeMillis()-current) +" ms");

        for(int i=0; i<ints.length-1; ++i){
            if(ints[i]>ints[i+1]){
                isvalid = false;
                break;
            }
        }
        System.out.println(isvalid);
    }
}
