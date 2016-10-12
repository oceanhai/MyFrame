package com.example;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by wuhai on 2016/10/11.
 */
public class GetSetLocale {
    public static void main(String[] args) {
        // 得到并且打印出缺省本地国家代码设置。
        Locale l = Locale.getDefault();
        System.out.println("Today's Locale is " + l);
        // 根据命令行参数设置本地缺省国家代码
        switch (args.length) {
            case 0:
                Locale.setDefault(Locale.TRADITIONAL_CHINESE );
                break;
            case 1:
                throw new IllegalArgumentException();
            case 2:
                Locale.setDefault(new Locale(args[0], args[1]));
                break;
            default:
                System.out.println("Usage: SetLocale [language [country]]");
        }
        // 根据设定的缺省国家代码设置日期和数字显示的格式
        DateFormat df = DateFormat.getInstance();
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println("Locale set to " +
                Locale.getDefault().getDisplayCountry() +
                Locale.getDefault().getDisplayLanguage());
        System.out.println(df.format(new Date()));
        System.out.println(nf.format(123.4567));

        Locale locale = getDefaultLocaleFromSystemProperties();
        System.out.println("Locale set to " +
                locale.getDisplayCountry() +
                locale.getDisplayLanguage());
    }

    public static Locale getDefaultLocaleFromSystemProperties() {
        final String languageTag = System.getProperty("user.locale", "");
        System.out.println("languageTag:" + languageTag);

        final Locale defaultLocale;
        if (!languageTag.isEmpty()) {
            defaultLocale = Locale.forLanguageTag(languageTag);
        } else {
            String language = System.getProperty("user.language", "en");
            String region = System.getProperty("user.region", "US");
            String variant = System.getProperty("user.variant", "");
            defaultLocale = new Locale(language, region, variant);
        }
        System.out.println("defaultLocale:" + defaultLocale);
        return defaultLocale;
    }
}
