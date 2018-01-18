package com.example.lctupdatedata;


/**
 * Created by wuhai on 2017/02/21 17:02.
 * 描述：会员积分表  记录会员积分增减记录
 */

public class MemberScore{

    private String member_credit_id;//uuid
    private String member_id;//会员信息 外键
    private Member member;//会员
    private int update_type;//修改类型，1.添加，2.手动减少, 3.退货, 4.手动增加
    private int update_num;//变化数量，减少为负数
    private String order_id;//订单记录 外键
    private String return_goods_order_id;//退货订单id
    private String shop_id;//商店id，用户表id 外键
    private String create_time;//添加时间
    private String last_modified;//最后修改时间

    //连锁版本
    private long chain_id;//连锁id
    private int user_type;//0:店主；1：雇员
    private long user_id;//店主、雇员键值id
    private String user_name;//店主、雇员name

    public String getMember_credit_id() {
        return member_credit_id;
    }

    public void setMember_credit_id(String member_credit_id) {
        this.member_credit_id = member_credit_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(int update_type) {
        this.update_type = update_type;
    }

    public int getUpdate_num() {
        return update_num;
    }

    public void setUpdate_num(int update_num) {
        this.update_num = update_num;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReturn_goods_order_id() {
        return return_goods_order_id;
    }

    public void setReturn_goods_order_id(String return_goods_order_id) {
        this.return_goods_order_id = return_goods_order_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public long getChain_id() {
        return chain_id;
    }

    public void setChain_id(long chain_id) {
        this.chain_id = chain_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
