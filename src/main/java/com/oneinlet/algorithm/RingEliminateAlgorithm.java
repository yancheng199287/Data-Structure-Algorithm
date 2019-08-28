package com.oneinlet.algorithm;

/**
 * Created by WangZiHe on 19-8-28
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

/**
 * 本案例演示了环形淘汰算法
 * 经常会有这样一个问题，一个容器只能装N个数据，但是会有不断新来的数据，容器不够装就需要淘汰旧的数据
 * 采取环形淘汰算法 简单高效
 *
 * 原理：维护一个头部指针，尾部指针，根据容器长度和填充的数据，根据取余，
 **/
public class RingEliminateAlgorithm {

    private int[] container = new int[11];
    private int length = container.length;

    //头部指针
    private int head = 0;
    //尾部指针
    private int tail = length - 1;

    public static void main(String[] args) {
        //模拟数据
        int[] data = new int[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121};

        RingEliminateAlgorithm ringEliminateAlgorithm = new RingEliminateAlgorithm();

        //改成模拟数据的长度，测试不同情况
        int dataLength = data.length - 7;
        for (int i = 0; i < dataLength; i++) {
            ringEliminateAlgorithm.fillContainer(data[i]);
        }
        ringEliminateAlgorithm.getRingContainer();
    }


    //核心算法，源源不断的将模拟数据填充到不可变长的容器中，会自动排序数据
    private void fillContainer(int element) {
        container[tail] = element;
        //+1相当于后移位，取余长度，就可以循环移位
        tail = (tail + 1) % length;
        //如果尾部已经绕一圈追上头部，说明已经填满，head需要向后移位
        if (head == tail) {
            head++;
            //如果头部指针到尾，需要重置为0，圆形循环
            if (head == length - 1) {
                head = 0;
            }
        }
    }

    private void getRingContainer() {
        System.out.println("head指针索引:" + head + ", tail尾部指针索引:" + tail);
        //如果头部比尾部大，说明，尾部已经绕了一圈
        if (head > tail) {
            for (int i = head; i < length; i++) {
                System.out.println("当前索引:" + i + ",取值：" + container[i]);
            }
            System.out.println("环绕一圈...");
            for (int i = 0; i <= tail; i++) {
                System.out.println("当前索引:" + i + ",取值：" + container[i]);
            }
        } else {
            for (int i = head; i <= tail; i++) {
                System.out.println("当前索引:" + i + ",取值：" + container[i]);
            }
        }
    }

}
