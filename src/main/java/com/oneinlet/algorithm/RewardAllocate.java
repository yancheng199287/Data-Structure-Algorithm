package com.oneinlet.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by WangZiHe on 19-12-4
 * QQ/WeChat:648830605
 * QQ-Group:368512253
 * Blog:www.520code.net
 * Github:https://github.com/yancheng199287
 */

/**
 * 本案例 演示  资金分配问题，通过对随机数分区，分离出权重高的区间和权重低的区间，然后根据每个随机数求和，并算出随机数在求和中的百分占比，
 * 将该百分占比最终引用到 固定金额中，从而算出这个人的实际赏金
 * 保证权重高的区间单个金额一定比权重低的区间的单个金额要高
 * 不受哪边区间数量多影响，如果想保证区间内波动小，需要调整区间内的随机数区间尽量小
 * 如果想保证权重高和权重低的区间波动小，可以调整两个区间的值更趋向于相等状态
 */
public class RewardAllocate {


    public static void main(String[] args) {
        RewardAllocate rewardAllocate = new RewardAllocate();

        BigDecimal totalReward = BigDecimal.valueOf(100.00);

        // 模拟获取用户

        // 权重较高的优秀用户
        List<UserProportion> excellentUser = rewardAllocate.getExcellentUser();

        // 权重一般的平凡用户
        List<UserProportion> mediocreUser = rewardAllocate.getMediocreUserUser();


        List<UserProportion> allUser = new ArrayList<>();
        allUser.addAll(excellentUser);
        allUser.addAll(mediocreUser);

        List<UserProportion> result = rewardAllocate.getUserReward(allUser, totalReward);


        System.out.println("本次总赏金金额：" + totalReward);

        BigDecimal totalProportionReward = BigDecimal.ZERO;

        BigDecimal totalPercent = BigDecimal.ZERO;
        for (UserProportion userProportion : result) {
            totalProportionReward = totalProportionReward.add(userProportion.reward);
            totalPercent=totalPercent.add(userProportion.percent);
            System.out.println(userProportion);
        }
        System.out.println("总分配的金额是:" + totalProportionReward+",总百分比："+totalPercent);

    }


    // 获取用户随机数
    private List<UserProportion> getUserReward(List<UserProportion> userProportionList, BigDecimal totalReward) {
        // 分配随机数，计算总的随机数和值
        int sum = 0;
        for (UserProportion userProportion : userProportionList) {
            if (userProportion.userType == UserType.EXCELLENT) {
                userProportion.random = getMaxRandomInterval();
            } else if (userProportion.userType == UserType.MEDIOCRE) {
                userProportion.random = getMinRandomInterval();
            }
            sum += userProportion.random;
        }

        // 根据用户的随机数 算出在总的随机数值的占比，并根据占比计算在总赏金的具体分配金额
        int len = userProportionList.size();
        BigDecimal totalProportionReward = BigDecimal.ZERO;
        for (int i = 0; i < len ; i++) {
            UserProportion userProportion = userProportionList.get(i);
            userProportion.percent = BigDecimal.valueOf(userProportion.random).divide(BigDecimal.valueOf(sum), 2, BigDecimal.ROUND_HALF_UP);

            //最后一个防止因精度问题，带来的百分比不够精确，可以兜底，直接用总赏金减去已经分配的赏金，保证金额完美不留缝隙的分配完
            if (i == len - 1) {
                userProportion.reward = totalReward.subtract(totalProportionReward);
            } else {
                userProportion.reward = totalReward.multiply(userProportion.percent);
            }
            totalProportionReward = totalProportionReward.add(userProportion.reward);
        }


        return userProportionList;
    }


    private List<UserProportion> getExcellentUser() {
        List<UserProportion> excellentUser = new ArrayList<>(2);
        excellentUser.add(new UserProportion("张飞", UserType.EXCELLENT));
        excellentUser.add(new UserProportion("关羽", UserType.EXCELLENT));
        return excellentUser;
    }

    private List<UserProportion> getMediocreUserUser() {
        List<UserProportion> mediocreUser = new ArrayList<>(2);
        mediocreUser.add(new UserProportion("吕布", UserType.MEDIOCRE));
        mediocreUser.add(new UserProportion("曹仁", UserType.MEDIOCRE));
        mediocreUser.add(new UserProportion("魏延", UserType.MEDIOCRE));
        return mediocreUser;
    }


    private int getMinRandomInterval() {
        return ThreadLocalRandom.current().nextInt(1, 5);
    }

    private int getMaxRandomInterval() {
        return ThreadLocalRandom.current().nextInt(6, 10);
    }


    private void testRandow() {
        RewardAllocate rewardAllocate = new RewardAllocate();
        List<String> excellentUser = new ArrayList<>(2);
        for (int i = 0; i < 10; i++) {
            int min = rewardAllocate.getMinRandomInterval();
            int max = rewardAllocate.getMaxRandomInterval();
            System.out.println("-----------------" + i);
            System.out.println("min:" + min + ", max:" + max);
        }
    }

    enum UserType {
        EXCELLENT,
        MEDIOCRE
    }

    class UserProportion {
        public String name;
        public UserType userType;
        public Integer random;
        public BigDecimal percent;
        public BigDecimal reward;

        public UserProportion() {
        }

        public UserProportion(String name, UserType userType) {
            this.name = name;
            this.userType = userType;
        }

        @Override
        public String toString() {
            return "UserProportion{" +
                    "name='" + name + '\'' +
                    ", userType=" + userType +
                    ", random=" + random +
                    ", percent=" + percent +
                    ", reward=" + reward +
                    '}';
        }
    }


}
