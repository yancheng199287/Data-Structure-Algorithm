package com.oneinlet.sort;

/**
 * Created by WangZiHe on 19-8-30
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

import java.util.Arrays;

/**
 * 本案例演示了选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] ageArray = new int[]{15, 28, 36, 24, 96, 55, 61, 43, 5, 74, 88, 11};

        System.out.println("排序前：" + Arrays.toString(ageArray));
        selectionSort(ageArray);
        System.out.println("排序后：" + Arrays.toString(ageArray));
    }

    private static void selectionSort(int[] arr) {
        int min, temp, len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            min = i;//未排序序列中最小数据数组下标
            for (int j = i + 1; j < len; j++) { //在未排序元素中继续寻找最小元素，并保存其下标
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = arr[min]; //将最小元素放到已排序序列的末尾
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
