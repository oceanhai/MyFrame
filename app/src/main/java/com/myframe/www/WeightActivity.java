package com.myframe.www;

import android.os.Bundle;
import com.myframe.www.base.BaseActivity;

/**
 * 权重测试
 */
/**
如果a不设置height=0dp,那么当a控件高度大于权重分比占高度（或宽度）时，weight属性不起作用，设置等于0,那么weight属性什么时候都起作用。

3个textview权重比1:2:3
layout_width=“0dp ”,屏幕空间按照1:2:3的比列分配给3个textview

3个textview权重比1:2:2
layout_width=“match_parent”，,屏幕空间按照3:1:1的比列分配给3个textview
系统先给3个textview分配他们所要的宽度match_parent，也就是说每一都是填满他的父控件，这里就死屏幕的宽度
那么这时候的剩余空间=1个parent_width-3个parent_width=-2个parent_width (parent_width指的是屏幕宽度 )
那么第一个TextView的实际所占宽度应该=match_parent的宽度,即parent_width + 他所占剩余空间的权重比列1/5 * 剩余空间大小（-2 parent_width）=3/5parent_width
同理第二个TextView的实际所占宽度=parent_width + 2/5*(-2parent_width)=1/5parent_width;
第三个TextView的实际所占宽度=parent_width + 2/5*(-2parent_width)=1/5parent_width；所以就是3:1:1的比列显示了。

如果3个textview权重比1:2:3
系统先给3个textview分配他们所要的宽度match_parent，也就是说每一都是填满他的父控件，这里就死屏幕的宽度
那么这时候的剩余空间=1个parent_width-3个parent_width=-2个parent_width (parent_width指的是屏幕宽度 )
那么第一个TextView的实际所占宽度应该=fill_parent的宽度,即parent_width + 他所占剩余空间的权重比列1/6 * 剩余空间大小（-2 parent_width）=2/3parent_width
同理第二个TextView的实际所占宽度=parent_width + 2/6*(-2parent_width)=1/3parent_width;
第三个TextView的实际所占宽度=parent_width + 3/6*(-2parent_width)=0parent_width；所以就是2:1:0的比列显示了。第三个就直接没有空间了。
 */
public class WeightActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
    }
}
