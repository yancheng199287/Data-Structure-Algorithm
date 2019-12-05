package com.oneinlet.algorithm;

/**
 * Created by WangZiHe on 19-9-11
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

import java.util.Arrays;

/**
 * 本案例演示著名的约瑟夫算法问题
 * N个人围成一圈，从第一个人开始报数，第M个将被杀掉，依次进行，最后只剩下一个，其余人将被杀掉
 */
public class Josephus {

    // 这里有55个人名，你可以自由截取你想要的数量，来玩一场自杀游戏
    private static final String[] names = new String[]{"卷卷", "花生", "米西", "萌萌", "瓜皮", "楼楼", "啵哩", "花菇", "花卷", "鹿子", "河河", "豆芽", "饭粒", "团子", "麻花", "甘蔗", "馒头", "糯米", "米饭", "饭团", "狐狸", "粟粟", "西西", "童童", "小暖", "臭臭", "仔仔", "大勇", "鲁鲁", "麦子", "小豆", "土豆", "小七",
            "小挺", "聪聪", "蛋蛋", "虎儿", "毛豆", "秋秋", "小哲", "元宝", "小文", "盼盼", "裴裴", "念念", "勇勇", "球球", "小俊", "小奇", "小五", "哲哲", "迪迪", "虾饺", "毛毛", "小博"};


    public static void main(String[] args) {
        String[] children = Arrays.copyOf(names, 41);
        Josephus josephus = new Josephus();
        Node head = new Node(children[0]);
        for (int i = 1; i < children.length; i++) {
            josephus.addChild(head, children[i]);
        }
        josephus.traverse(head);







    }

    public static class Node {
        private String child;
        private Node next;

        public Node(String child) {
            this.child = child;
        }
    }

    private void addChild(Node head, String child) {
        //初始化要加入的节点
        Node newNode = new Node(child);

        //临时节点
        Node temp = head;

        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;
    }

    /**
     * 遍历链表
     *
     * @param head 头节点
     */
    public void traverse(Node head) {

        //临时节点，从首节点开始
        Node temp = head.next;

        while (temp != null) {
            System.out.println("这个孩子的名字：" + temp.child);
            //继续下一个
            temp = temp.next;
        }
    }

}

