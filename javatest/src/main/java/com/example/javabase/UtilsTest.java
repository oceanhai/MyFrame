package com.example.javabase;

import com.example.Person;

/**
 * Created by wuhai on 2017/12/09 14:25.
 * 描述：
 */

public class UtilsTest {

    public static void main(String[] args){
        method04();
    }

    public static void method04(){
        Person person = new Person();
        person.setName("wuhai");
        System.out.println(GsonUtils.getInstance().toJson(person));

        Person person1 = new Person();
        person1.setAge(18);
        System.out.println(GsonUtils.getInstance().toJson(person1));

        Person person2 = new Person();
        person2.setAge(18);
        person2.setName("");
        System.out.println(GsonUtils.getInstance().toJson(person2));
    }

    public static void method03(){
        String str1 = "192.168.9.150";
        String str2 = "192.168.9.256";
        String str3 = "192%168.9.256";
        String str4 = "092.168.9.256";
        String str5 = "292.168.9.256";
        String str6 = "192.168.9.150.1";
        String str7 = "100.168.9.150";
        String str8 = "242.168.9.150";
        String str9 = "192.168.9.955";
        System.out.println(IpAddressUtils.isIP(str1));
        System.out.println(IpAddressUtils.isIP(str2));
        System.out.println(IpAddressUtils.isIP(str3));
        System.out.println(IpAddressUtils.isIP(str4));
        System.out.println(IpAddressUtils.isIP(str5));
        System.out.println(IpAddressUtils.isIP(str6));
        System.out.println(IpAddressUtils.isIP(str7));
        System.out.println(IpAddressUtils.isIP(str8));
        System.out.println(IpAddressUtils.isIP(str9));
    }

    public static void method01(){
        String str = "桃100";
        String sbc = SbcAndDbcUtils.ToSBC(str);
        System.out.println(sbc);
    }

    public static void method02(){
        String str = "０";
        String sbc1 = ChineseAreaCode.toAreaCode(str);
        String sbc2 = ChineseAreaCode.toAreaCode2(str);
        String sbc3 = ChineseAreaCode.getQuwei(str);
        System.out.println(sbc1);
        System.out.println(sbc2);
        System.out.println(sbc3);
    }
}
