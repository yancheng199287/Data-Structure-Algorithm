package com.oneinlet.algorithm;

/**
 * Created by WangZiHe on 19-9-19
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */
public class InsertRepeatStr {

    // 使用场景
    // 某字符串太长，需要对指定的长度进行换行，插入<br>标签
    public static void main(String[] args) {
        String str = "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        StringBuilder sb = new StringBuilder(str);
        int len = str.length();//计算字符串的长度
        int times = len / 20;//每隔20分隔，计算多少次
        for (int i = 1; i <= times; i++) {
            int index = i * 20; //算出第i个出现的位置
            index=index+(i-1)*4;//因为之前插入了<br>字符 四个  要加上
            sb.insert(index, "<br>");
        }
        System.out.println(sb.toString());
    }
}
