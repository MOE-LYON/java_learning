package com.moelyon.learning.algorithm;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {

    public static void insertSort(int[] arr){

        insertSort(arr,0,arr.length);

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
        Random random = new Random();
        int[] ins = random.ints().limit(20000).toArray();

        insertSort(ins);

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
