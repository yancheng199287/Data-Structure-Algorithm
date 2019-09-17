package com.oneinlet.algorithm;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by WangZiHe on 19-9-11
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

/**
 * 本算法演示了 按月分割周，不足一周按一周算
 **/
public class SpiltMonthByWeek {

    public static void main(String[] args) {
        spiltMonthByWeek();
    }

    private static void spiltMonthByWeek() {
        List<LocalDate[]> weekList = new ArrayList<>();
        LocalDate localDate = LocalDate.now().withMonth(1);
        boolean leapYear = localDate.isLeapYear();//判断是否闰年
        Month month = localDate.getMonth();//获取当前月份
        int length = month.length(leapYear);//根据是否的闰年获取当前月份的最大天数
        int times = length / 7 + 2;// 循环次数，最大6次，每个月至少4周，加上首尾两个不完整的周
        int day = 1;    //当前递增天数变量
        int last = times - 1;//最后一次循环变量
        int lastDayEnd = length + 7;//最大day的天数
        for (int i = 0; i < times; i++) {
            if (day == 1) {//如果当前是第一天，防止当前是周日
                LocalDate dayLocalDate = localDate.withDayOfMonth(day);
                DayOfWeek dayOfWeek = dayLocalDate.getDayOfWeek();
                if (DayOfWeek.SUNDAY == dayOfWeek) {
                    LocalDate[] week = new LocalDate[2];
                    week[0] = dayLocalDate;
                    week[1] = dayLocalDate;
                    weekList.add(week);
                } else {//如果不是周日，那么重新计算开始结束周期，有可能是完整的一周七天，有可能是半截
                    LocalDate dayLocalDate1 = localDate.withDayOfMonth(day);
                    int value = dayOfWeek.getValue();
                    day += (7 - value);
                    LocalDate dayLocalDate2 = localDate.withDayOfMonth(day);
                    LocalDate[] week = new LocalDate[2];
                    week[0] = dayLocalDate1;
                    week[1] = dayLocalDate2;
                    weekList.add(week);
                }
            } else {
                if (last == i) {//如果最后一次循环，即是最后一周
                    int intervalDay = day - length;
                    LocalDate dayLocalDate3 = localDate.withDayOfMonth(day - intervalDay);
                    LocalDate[] week = new LocalDate[2];
                    week[0] = dayLocalDate3;
                    week[1] = localDate.withDayOfMonth(length);
                    weekList.add(week);
                } else {
                    LocalDate dayLocalDate3 = localDate.withDayOfMonth(day - 6);
                    LocalDate[] week = new LocalDate[2];
                    week[0] = dayLocalDate3;
                    week[1] = dayLocalDate3.withDayOfMonth(day);
                    weekList.add(week);
                }
            }
            day += 7;//每次处理一个周，则递增7天

            //如果当前大于当前月的总天数并且不超过最大总天数，我们认为是最后两次，注意last已经在开始的时候减去1，因为这里的day需要重新计算天数的起始位置
            //如果遇到28天，倒数第二周的周日是24，则本次day就是32，实际只剩下最后两次
            if (day > length && day <= lastDayEnd) {
                i = last - 1;
                //如果遇到月份30天，上次的周日是23，那么这次的周日就是30.则是循环最后一次
            } else if (day == length) {
                i = last;
            }
        }
        int i = 1;
        for (LocalDate[] mlocalDate : weekList) {
            LocalDate start = mlocalDate[0];
            LocalDate end = mlocalDate[1];
            StringBuilder sb = new StringBuilder("当前第").append(i).append("周：")
                    .append("开始时间:").append(start).append("-----").append("结束时间:").append(end);
            System.out.println(sb.toString());
            i++;
        }
    }
}
