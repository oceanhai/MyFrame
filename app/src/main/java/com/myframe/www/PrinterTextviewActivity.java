package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.view.TypeTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PrinterTextviewActivity extends BaseActivity {

    @Bind(R.id.tv)
    TypeTextView tv;

    private String str = "“李七夜，那怕你是真正的帝子，也休得托大，那怕你打出了神·天灭，也打不败我龙傲天！”龙傲天这一次真的是怒了，冷冷地说道。\n" +
            "\n" +
            "    对于龙傲天的话，很多人都相视了一眼，关于第一凶人是明仁仙帝后人这种说法早就有猜测，因为当年洗颜古派一战，第一凶人竟然使用了明仁仙帝的明仁战铠，这就是让世人认为第一凶人很有可能是明仁仙帝的后人，只不过大家没有证据而己。\n" +
            "\n" +
            "    现在龙傲天再次说出这件事情，很多人都在心里面凛了一下，或者只有真正的帝子，才有可能像第一凶人这样凶悍，毕竟，这是仙帝的亲儿子！\n" +
            "\n" +
            "    对于龙傲天这样的说法，李七夜也没有反驳，只是随意地笑了一下，说道：“凭借着明仁仙帝留下的明仁刀打败你，你可能心里面不服气，也罢，我就用我的兵器来打败你。”说着收起了明仁刀。\n" +
            "\n" +
            "    当李七夜收起了明仁刀，所有人都傻眼了，第一凶人这太邈视龙傲天了吧，用自己的兵器去对抗仙帝真器，这是何等的托大。\n" +
            "\n" +
            "    “李七夜，你真以为自己已经无敌了吗？”龙傲天十荡十决，本来是沉得住气的人，但是，却被李七夜如此邈视，这的确是咽不下这口气，那怕是泥人都有三分泥性，更何况他这个天之骄子的龙傲天。";

    public static void startActivity(Context context){
        Intent intent = new Intent(context,PrinterTextviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_textview);
        ButterKnife.bind(this);

        tv.setSpecialAction(false);
        tv.start(str);
    }
}
