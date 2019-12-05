package com.oneinlet.sort;

/**
 * Created by WangZiHe on 19-8-28
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

import java.util.Arrays;

/**
 * 本案例演示了冒泡排序
 **/
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort.sort();
    }

    private static void sort() {

        //从大到小的数据，你可以借此模拟最坏情况 int[] container = new int[]{101, 100, 92, 88, 79, 78, 74, 69, 67, 55, 45, 35, 31, 19, 12, 6, 0};

        //从小到大的数据，你可以借此模拟最好情况  int[] container = new int[]{0, 6, 12, 19, 31, 35, 45, 55, 67, 69, 74, 78, 79, 88, 92, 100, 101};

        // 混序数据
        int[] container = new int[]{79, 35, 69, 45, 12, 55, 19, 74, 6, 0, 78, 31, 67, 88, 101, 92, 100};
        System.out.println("排序前：" + Arrays.toString(container));

        int length = container.length;
        //外层循环控制一定要进行几趟交换，我们可以自己设置n个数，发现规律总坏情况下共需要进行n-1趟
        //什么是最坏情况下? 意思是，如果是按照从小到大排序一组从大到小的数据，那么就是最坏的情况，两辆交换比较，需要交换 n-1次
        int count = length - 1;
        for (int i = 0; i < count; i++) {
            //引入一个标识符，如果本趟根本不存在交换的数据，意味着，整个排序已经排序完毕，后面不需要再次排序
            //大家注意，由于给定的排序数据是不确定的，我们既要保证最坏的情况排序次数，也要保证比较好或者最好的情况下的数据，避免做无用功，提高性能
            boolean isSwap = false;
            //内循环控制每趟交换多少次 ，每次循环一次则减少一次交换位置，因为每次循环，会把最大的放后面，第一次是最大的在最后位置，第二次是第二大的放倒数第二的位置
            //这里 i  就是上几次次已经排序好的较大的数据，本次就不需要额外再去比较，因为后面排序过后总是比较前面还没排序好的要大
            // 所以减i，避免多次比较，提高性能，如果你不减去也可以得到正确的结果，但是会做无用功
            for (int j = 0; j < count - i; j++) {
                // 如果你想把顺序改成从大到小的顺序，仅仅需要改变if语句中的大于号，即大于号是从小到大排序，小于号是从大到小的排序
                if (container[j] > container[j + 1]) {
                    // 如果我们想将两瓶的水互换，则必须借助一个容器，才能完成互换工作
                    int temp = container[j + 1];
                    container[j + 1] = container[j];
                    container[j] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
            System.out.println("第几趟" + i + "排序后：" + Arrays.toString(container));
        }
        System.out.println("排序后：" + Arrays.toString(container));
    }

}
