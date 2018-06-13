package com.example.qdd;

import java.math.BigDecimal;

/**
 * Created by wuhai on 2018/5/18.
 */

public class TestV3012 {

    public static void main(String[] args){
        System.out.println(""+new BigDecimal("1.11").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.2").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.3").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.4").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.5").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.6").setScale(0,BigDecimal.ROUND_UP));
        System.out.println(""+new BigDecimal("1.9").setScale(0,BigDecimal.ROUND_UP));
    }
}
