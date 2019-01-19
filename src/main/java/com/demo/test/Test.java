package com.demo.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
	
	//7的倍数换行
	/*public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
		for (int i = 0; i < a.length; i++) {
			if(i%7 == 0 && i != 0) {
				System.out.println();
			}
			System.out.print(a[i]+" ");
		}
	}*/
	
	//找出两个数的和为指定数
	/*public static void main(String[] args) {
		int[] a = {-12,8,18,-7,4,-8,-26,11,12};
		//方法1：HashSet
		Set<Integer> set = new HashSet<Integer>(a.length);
		for (int i : a) {
			int b = 0 - i;
			if(!set.contains(i)) {
				set.add(b);
			}else {
				System.out.println("方法1---和为0的两个数："+i+"和"+b);
			}
		}
		//方法2：排序
		Arrays.sort(a);
		int i = 0;    //最小数
		int j = a.length-1;   //最大数
		while (i < j) {
			if(a[i]+a[j] > 0) {
				j--;
			}else if(a[i]+a[j] < 0) {
				i++;
			}else {
				System.out.println("方法2---和为0的两个数："+a[i]+"和"+a[j]);
				i++;
				j--;
			}
		}
	}*/

}
