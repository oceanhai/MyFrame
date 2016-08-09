package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.moreinfo.CollapsibleTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowMoreInfoActivity extends BaseActivity {

    String s = "　　一个从小便靠捡破烂为生的少年，饱受着别人的白眼和嘲讽，但是很少有人知道，他其实是一个令人无法想象的天才！\r\n　　——他无意间获得一台电脑，于是计算机行业的基本格局被改变，整个黑客界因他而翻起了惊天巨浪！\r\n　　——他画了两幅画，名为《蒙娜丽莎姐妹的微笑》，世界上的名画鉴定机构居然全都认定这两幅画出于达芬奇之手！\r\n　　——他创作了三张歌曲专辑，一经发布便被誉为“不可超越的经典神作”！他也被全世界的狂热歌迷称为“魔音大帝”！\r\n　　——他不尽为人知，但他构建的阴影帝国却牢牢把持着全世界的脉络！\r\n　　——他就是风笑天，一个被所有人称之为“神”的男人！\r\n　　风笑天淡然道：“别跟我比智商，不然你会无地自容的！\"\r\n　　（未语浅笑恳求大家订阅、点击、收藏、推荐，本故事发生在平行空间，希望大家不要对号入座。）";
    String s2 = "　　一个从小便靠捡破烂为生的少年，";
    String s3 = "　　一个从小便靠捡破烂为生的少年，饱受着别人的白眼和嘲讽，但是很少有人知道";// ，他其实是一个令人无法想象的天才！";// ——他无意间获得一台电脑，于是计算机行业的基本格局被改变，整个黑客界因他而翻起了惊天巨浪！\r\n　　——他画了两幅画，名为《蒙娜丽莎姐妹的微笑》，世界上的名画鉴定机构居然全都认定这两幅画出于达芬奇之手！\r\n　　——他创作了三张歌曲专辑，一经发布便被誉为“不可超越的经典神作”！他也被全世界的狂热歌迷称为“魔音大帝”！\r\n　　——他不尽为人知，但他构建的阴影帝国却牢牢把持着全世界的脉络！\r\n　　——他就是风笑天，一个被所有人称之为“神”的男人！\r\n　　风笑天淡然道：“别跟我比智商，不然你会无地自容的！\"\r\n　　（未语浅笑恳求大家订阅、点击、收藏、推荐，本故事发生在平行空间，希望大家不要对号入座。）";
    String s4 = "　　一个从小便靠捡破烂为生的少年，饱受着别人的白眼和嘲讽，但是很少有人知道，他其实是一个令人无法想象的天才！";// ——他无意间获得一台电脑，于是计算机行业的基本格局被改变，整个黑客界因他而翻起了惊天巨浪！\r\n　　——他画了两幅画，名为《蒙娜丽莎姐妹的微笑》，世界上的名画鉴定机构居然全都认定这两幅画出于达芬奇之手！\r\n　　——他创作了三张歌曲专辑，一经发布便被誉为“不可超越的经典神作”！他也被全世界的狂热歌迷称为“魔音大帝”！\r\n　　——他不尽为人知，但他构建的阴影帝国却牢牢把持着全世界的脉络！\r\n　　——他就是风笑天，一个被所有人称之为“神”的男人！\r\n　　风笑天淡然道：“别跟我比智商，不然你会无地自容的！\"\r\n　　（未语浅笑恳求大家订阅、点击、收藏、推荐，本故事发生在平行空间，希望大家不要对号入座。）";
    @Bind(R.id.desc_collapse_tv)
    CollapsibleTextView descCollapseTv;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ShowMoreInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_info);
        ButterKnife.bind(this);

        descCollapseTv.setDesc(s, TextView.BufferType.NORMAL);
    }
}
