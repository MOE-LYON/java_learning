package com.moelyon.learning.algorithm;

import java.util.Arrays;
import java.util.Random;

public  class MergeSort {

    private static void mergeSort(int[] arr){

        if(arr == null || arr.length<=1){
            return;
        }

        int[] temp = new int[arr.length];

        _mergeSort(arr,temp,0,arr.length-1);
    }

    private static void merge(int[] arr, int[] temp,int left,int right, int end){

        int leftEnd = right-1;
        int totalNum = end-left+1;

        int tmp = left;
        while (left<= leftEnd && right<=end){

            if(arr[left]>arr[right]){
                temp[tmp++]= arr[right++];
            }else{
                temp[tmp++] = arr[left++];
            }
        }

        while (left<= leftEnd){
            temp[tmp++] = arr[left++];
        }
        while (right<= end){
            temp[tmp++] = arr[right++];
        }
        for(int i=0; i<totalNum; ++i, end--){
            arr[end]= temp[end];
        }
    }

    private static void _mergeSort(int[]arr, int[] temp, int left, int end ){

        if(left>= end){
            return;
        }
        int center = left + (end-left)/2;
        _mergeSort(arr, temp, left, center);
        _mergeSort(arr, temp, center+1, end);

        merge(arr,temp,left,center+1,end);
    }

    public static void main(String[] args) {
        Random random = new Random();

        var ins = random.ints().limit(200).toArray();

        mergeSort(ins);
        System.out.println(Arrays.toString(ins));
        boolean isvalid = true;
        for(int i=0; i<ins.length-1; ++i){
            if(ins[i]>ins[i+1]){
                isvalid = false;
                break;
            }
        }

        System.out.println(isvalid);
    }
}
