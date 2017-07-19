package com.example.lctusb;

/**
 * Created by wuhai on 2017/03/24 14:38.
 * 描述：金额
 */

public class Test03 {

    public static void main(String[] args){
//        method01(8.85, 5.0);
//        method01(6, 5.99);
//        method01(6, 5.99);
//        method02(13.41, 0);

//        method03(2.235*Double.valueOf(45));//100.57
//        method03(2.235*43);//96.10
//        method03(100.575);//100.58
//        method03(96.105);//96.11
//        method03(100.57499999999999);//100.57
//        method03(96.10499999999999);//96.10

//        String str = "";
//        System.out.println("str="+str);
//        System.out.println("str.length()="+str.length());
//        if (null != str && str.length() > 0){
//            System.out.println("里面");
//        }

//        System.out.println(1 == 1.0);


//        double num = 10;
//        double num1 = 10;//10
//        double num2 = 10;//店家优惠
//        String str = "满20.0减3.0";
        double num = 10;
        double num1 = 10;//10
        double num2 = 0;//店家优惠
        String str = "";
        method04(num,num1,num2,str);
//        double money = Double.valueOf(str.substring(str.indexOf("减")+1));
//        System.out.println(money);


    }

    public static void method04(double num, double num1, double num2, String str){
        double returnGoodsAmountBeforeCalculation = num;
        if(returnGoodsAmountBeforeCalculation > 0){
            double order_payable = num1;
            double order_customer_discount = num2;
            String order_full_cut_tip = str;
            double order_full_cut_amount = 0;
            if(order_full_cut_tip != null && order_full_cut_tip.length()>0){
                order_full_cut_amount = Double.valueOf(order_full_cut_tip.substring(order_full_cut_tip.indexOf("减")+1));
            }
            if(order_customer_discount>0 || order_full_cut_amount>0){//店家优惠 或 满减
                double totalBase = order_payable + order_customer_discount + order_full_cut_amount;//总基数
                double discountBase = order_customer_discount + order_full_cut_amount;//计算回收优惠金额 基数
                double returnDiscount = (returnGoodsAmountBeforeCalculation/totalBase) * discountBase;//应返还的优惠金额
                double returnGoodsAmountAfterCalculation = returnGoodsAmountBeforeCalculation - returnDiscount;//最终应退金额
                String resutlt = StringUtils.getDecimalFormat(null,returnGoodsAmountAfterCalculation);
                System.out.println("结果：" + resutlt);
            }else{
                System.out.println("结果：" + returnGoodsAmountBeforeCalculation);
            }
        }else{
            System.out.println("结果：0.00");
        }
    }

    public static void method01(double num1, double num2){
        System.out.println(num1 - num2);
        System.out.println(StringUtils.getDecimalFormat(null,num1 - num2));
    }

    public static void method02(double num1, double num2){
        System.out.println(ArithUtils.formatDouble2(num1-num2));
    }

    public static void method03(double num1){
        System.out.println("入参:"+num1);
        System.out.println(StringUtils.getDecimalFormat(null,num1));
    }
}
