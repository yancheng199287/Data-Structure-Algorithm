package com.oneinlet.structure;

/**
 * Created by WangZiHe on 19-8-28
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

/**
 * 本案例演示了数组数据结构 列举了相关crud方法
 * <p>
 * 关于数组,我们首先要知道的是,他是引用数据类型,数组是存储同一种数据类型多个元素的容器。数组既可以存储基本数据类型，也可以存储引用数据类型。数组有两种初始化方式,动态初始化和静态初始化.
 * <p>
 * 当数组被初始化之后，该数组 所占的内存空间、数组长度都是不可变的
 * 数组必须经过初始化才可使用。所谓初始化，即创建实际的数组对象，也就是在内存中为数组对象分配内存空间，并为每个数组 元素指定初始值。
 * <p>
 * 当使用一个"new"创建一个对象时，在堆中会分配一段内存空间，并返回一个引用。这一点对于数组也适用，因为在java中，数组也是对象。
 * <p>
 * 为什么数组比较快?
 * 因为CPU缓存会读入一段连续的内存，顺序存储符合连续的内存，所以顺序存储可以被缓存处理，而链接存储并不是连续的，分散在堆中，所以只能内存去处理。
 * 所以数组查询比链表要快。
 * 而数组大小固定，插入和删除都需要移动元素，链表可以动态扩充，插入删除不需要移动元素，只需要更改元素中的指针。所以链表的插入删除比数组效率高。
 **/
public class ArrayStructure {

    public static void main(String[] args) {
        ArrayStructure.oneDimensionArray();
    }

    private static void oneDimensionArray() {
        int[] ageArray = new int[]{10, 20, 30, 40, 50};
        System.out.println("数组长度：" + ageArray.length);
        System.out.println("数组根据索引下标获取值，默认从0开始，逐步递增，最大值是：" + (ageArray.length - 1));

        System.out.println("遍历数组...");
        printArray(ageArray);

        ageArray[0] = 99;
        System.out.println("改变指定下标数组的值：" + ageArray[0]);


        System.out.println("添加数据到数组...");
        //数据增加需要扩容然后复制之前的数据，删除需要进行移位操作，所以效率慢
        int newAgeArray[] = addSomeDataToArray(55, ageArray);
        printArray(newAgeArray);

        System.out.println("删除数组中的数据...");
        newAgeArray = deleteDataFromArray(3, newAgeArray);
        printArray(newAgeArray);
    }


    // 循环遍历数组
    private static void printArray(int[] targetArray) {
        for (int i = 0; i < targetArray.length; i++) {
            System.out.println("遍历数组,当前索引下标是:" + i + ",值是：" + targetArray[i]);
        }
    }


    // 添加指定的数据到数组,需要扩容更大的数组容器
    private static int[] addSomeDataToArray(int data, int[] targetArray) {
        int size = targetArray.length + 1;
        int[] newAgeArray = new int[size];
        newAgeArray[size - 1] = data;
        System.arraycopy(targetArray, 0, newAgeArray, 0, size - 1);
        return newAgeArray;
    }


    // 删除数组中指定的索引值
    private static int[] deleteDataFromArray(int index, int[] targetArray) {
        int size = targetArray.length;
        for (int i = 0; i < size; i++) {
            if (i >= index && i < size - 1) {
                targetArray[i] = targetArray[i + 1];
            }
        }
        targetArray[size - 1] = 0;
        return targetArray;
    }
}
