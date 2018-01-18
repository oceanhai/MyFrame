package com.example.lctupdatedata;


/**
 * Created by wangC on 2017/2/6.
 */

public class Member{

    private String  member_id;//id，主键
    private String phone_num;//电话
    private String phone_last_four_num;//电话
    private String member_name; //用户名称
    private int  is_solar_calendar; //是否阳历
    private String member_birthd; //会员生日
    private int member_score; //会员积分
    private int member_allscore   ; //会员总积分
    private int sex; //会员性别
    private int member_state;//0:未赊账 1：赊账过
    private double total_member_credit;//会员赊账总金额
    private double member_credit; //会员赊账金额
    private String shop_id; //添加时间
    private String create_time;//添加时间
    private String last_modified;//最后修改时间

    //v1.4.2追加
    private String member_name_abbreviation;//会员名称首字母缩写
    private String member_card;//会员卡

    //v1.4.5追加
    private double member_balance;//余额
    private double member_balance_total;//总余额记录

    //v1.5.1追加
    private int is_poor;//是否平困户     2否 1 是       0是没有选
    private int is_has_car;//是否有车  2没有  1有   0是没有选

    //连锁版本
    private long chain_id;//连锁id
    private int user_type;//0:店主；1：雇员
    private long user_id;//店主、雇员键值id
    private String user_name;//店主、雇员name

    //1.5.3 临时标志member的同步类型     1:加，2：减，3：set,4:model
    private int syncData_type;
    //1.5.3 临时标志是跟新哪个字段  目前只有积分和 余额
    private String column;
    //1.5.3 临时标志是跟新哪个字段 的值
    private double  syncValue;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPhone_last_four_num() {
        return phone_last_four_num;
    }

    public void setPhone_last_four_num(String phone_last_four_num) {
        this.phone_last_four_num = phone_last_four_num;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getIs_solar_calendar() {
        return is_solar_calendar;
    }

    public void setIs_solar_calendar(int is_solar_calendar) {
        this.is_solar_calendar = is_solar_calendar;
    }

    public String getMember_birthd() {
        return member_birthd;
    }

    public void setMember_birthd(String member_birthd) {
        this.member_birthd = member_birthd;
    }

    public int getMember_score() {
        return member_score;
    }

    public void setMember_score(int member_score) {
        this.member_score = member_score;
    }

    public int getMember_allscore() {
        return member_allscore;
    }

    public void setMember_allscore(int member_allscore) {
        this.member_allscore = member_allscore;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getMember_state() {
        return member_state;
    }

    public void setMember_state(int member_state) {
        this.member_state = member_state;
    }

    public double getTotal_member_credit() {
        return total_member_credit;
    }

    public void setTotal_member_credit(double total_member_credit) {
        this.total_member_credit = total_member_credit;
    }

    public double getMember_credit() {
        return member_credit;
    }

    public void setMember_credit(double member_credit) {
        this.member_credit = member_credit;
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

    public String getMember_name_abbreviation() {
        return member_name_abbreviation;
    }

    public void setMember_name_abbreviation(String member_name_abbreviation) {
        this.member_name_abbreviation = member_name_abbreviation;
    }

    public String getMember_card() {
        return member_card;
    }

    public void setMember_card(String member_card) {
        this.member_card = member_card;
    }

    public double getMember_balance() {
        return member_balance;
    }

    public void setMember_balance(double member_balance) {
        this.member_balance = member_balance;
    }

    public double getMember_balance_total() {
        return member_balance_total;
    }

    public void setMember_balance_total(double member_balance_total) {
        this.member_balance_total = member_balance_total;
    }

    public int getIs_poor() {
        return is_poor;
    }

    public void setIs_poor(int is_poor) {
        this.is_poor = is_poor;
    }

    public int getIs_has_car() {
        return is_has_car;
    }

    public void setIs_has_car(int is_has_car) {
        this.is_has_car = is_has_car;
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

    public int getSyncData_type() {
        return syncData_type;
    }

    public void setSyncData_type(int syncData_type) {
        this.syncData_type = syncData_type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public double getSyncValue() {
        return syncValue;
    }

    public void setSyncValue(double syncValue) {
        this.syncValue = syncValue;
    }
}
