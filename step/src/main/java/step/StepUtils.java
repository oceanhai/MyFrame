package step;

import java.text.DecimalFormat;

/**
* 步数转换工具
* 1km=1500步=15分钟=35卡  ※相当于100步=1分钟，15步=0.01km,42步=1卡(卡的转换是经产品定夺的)
* @author wuhai
* create at 2016/7/18 17:08
*/
public class StepUtils {

    /**
     * 运动距离
     * @param stepNum 步数
     * @return
     */
    public static String getDistance(int stepNum){
        if(stepNum < 0){
            return "0";
        }

        int num = stepNum/15;

        DecimalFormat df = new DecimalFormat("######0.##");
        double distance = (num * 0.01);
        return  df.format(distance);
    }

    /**
     * 消耗能量
     * @param stepNum
     * @return
     */
    public static String getCalorie(int stepNum){
        if(stepNum < 0){
            return "0";
        }

        return "" + stepNum/42;
    }

    /**
     * 百分数
     * @param stepNum 步数
     * @param maxNum  目标步数
     * @return
     */
    public static int getProgress(int stepNum, int maxNum){
        if(stepNum < 0 || maxNum < 0){
            return 0;
        }

        if(maxNum <= stepNum){
            return 100;
        }

        return (int)(((double)stepNum/maxNum)*100);
    }

    /**
     * 运动时间
     * @param stepNum
     * @return
     */
    public static int getSportMinute(int stepNum){
        return stepNum/100;
    }
}
