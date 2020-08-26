package com.moelyon.learning.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickSort {

    public static void quickSort(int[] arr){
        quicksort(arr,0,arr.length-1);
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

    private static final int cutOff = 100;
    public static void quicksort(int[] arr, int start, int end){

        if(start>=end){
            return;
        }

        if(end-start<=cutOff){
            insertSort(arr,start,end+1);
            return;
        }
        int pivot = partition(arr, start, end);

        quicksort(arr, start, pivot-1);
        quicksort(arr, pivot+1, end);

    }

    public static void insertSort(int[] arr,int start, int end){
        for(int i=start+1 ;i<end; ++i){
            int temp = arr[i];

            int p;
            for( p= i; p>start && arr[p-1]> temp; --p){
                arr[p] = arr[p-1];
            }
            arr[p] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] ints = new Random().ints().limit(200).toArray();
        int[] ints = {5,1,1,2,0,0};
        quickSort(ints);

        System.out.println(Arrays.toString(ints));
        boolean isvalid = true;
        for(int i=0; i<ints.length-1; ++i){
            if(ints[i]>ints[i+1]){
                isvalid = false;
                break;
            }
        }

        System.out.println(isvalid);
    }

}