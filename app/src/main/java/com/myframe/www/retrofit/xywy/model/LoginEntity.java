package com.myframe.www.retrofit.xywy.model;

/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/14 16:41
 *
 * 描述：登录数据体
 */
public class LoginEntity {
    private String code;
    private String msg;
    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String userid;
        private String username;
        private String insert_data;
        private String userphone;
        private String useremail;
        private String usersource;
        private String type;
        private String devid;
        private String lasttime;
        private String nickname;
        private String realname;
        private String birthday;
        private String sex;
        private String age;
        private String idcard;
        private String weight;
        private String height;
        private String blood_type;
        private String blood_pressure;
        private String photo;
        private int points;
        private String first_login_time;

        //V4.1.4新加字段，主要用到token
        private String is_auth_qq;
        private String is_auth_sina;
        private String is_auth_weixin;
        private String is_auth_alipay;
        private String token;
        private String expire;
        private String auth_phone_status;

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIs_auth_alipay() {
            return is_auth_alipay;
        }

        public void setIs_auth_alipay(String is_auth_alipay) {
            this.is_auth_alipay = is_auth_alipay;
        }

        public String getIs_auth_weixin() {
            return is_auth_weixin;
        }

        public void setIs_auth_weixin(String is_auth_weixin) {
            this.is_auth_weixin = is_auth_weixin;
        }

        public String getIs_auth_sina() {
            return is_auth_sina;
        }

        public void setIs_auth_sina(String is_auth_sina) {
            this.is_auth_sina = is_auth_sina;
        }

        public String getIs_auth_qq() {
            return is_auth_qq;
        }

        public void setIs_auth_qq(String is_auth_qq) {
            this.is_auth_qq = is_auth_qq;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setInsert_data(String insert_data) {
            this.insert_data = insert_data;
        }

        public void setUserphone(String userphone) {
            this.userphone = userphone;
        }

        public void setUseremail(String useremail) {
            this.useremail = useremail;
        }

        public void setUsersource(String usersource) {
            this.usersource = usersource;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDevid(String devid) {
            this.devid = devid;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setBlood_type(String blood_type) {
            this.blood_type = blood_type;
        }

        public void setBlood_pressure(String blood_pressure) {
            this.blood_pressure = blood_pressure;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }


        public void setFirst_login_time(String first_login_time) {
            this.first_login_time = first_login_time;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getUserid() {
            return userid;
        }

        public String getUsername() {
            return username;
        }

        public String getInsert_data() {
            return insert_data;
        }

        public String getUserphone() {
            return userphone;
        }

        public String getUseremail() {
            return useremail;
        }

        public String getUsersource() {
            return usersource;
        }

        public String getType() {
            return type;
        }

        public String getDevid() {
            return devid;
        }

        public String getLasttime() {
            return lasttime;
        }

        public String getNickname() {
            return nickname;
        }

        public String getRealname() {
            return realname;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getSex() {
            return sex;
        }

        public String getAge() {
            return age;
        }

        public String getIdcard() {
            return idcard;
        }

        public String getWeight() {
            return weight;
        }

        public String getHeight() {
            return height;
        }

        public String getBlood_type() {
            return blood_type;
        }

        public String getBlood_pressure() {
            return blood_pressure;
        }

        public String getPhoto() {
            return photo;
        }


        public String getFirst_login_time() {
            return first_login_time;
        }

        public String getAuth_phone_status() {
            return auth_phone_status;
        }

        public void setAuth_phone_status(final String auth_phone_status) {
            this.auth_phone_status = auth_phone_status;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "userid='" + userid + '\'' +
                    ", username='" + username + '\'' +
                    ", insert_data='" + insert_data + '\'' +
                    ", userphone='" + userphone + '\'' +
                    ", useremail='" + useremail + '\'' +
                    ", usersource='" + usersource + '\'' +
                    ", type='" + type + '\'' +
                    ", devid='" + devid + '\'' +
                    ", lasttime='" + lasttime + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", realname='" + realname + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age='" + age + '\'' +
                    ", idcard='" + idcard + '\'' +
                    ", weight='" + weight + '\'' +
                    ", height='" + height + '\'' +
                    ", blood_type='" + blood_type + '\'' +
                    ", blood_pressure='" + blood_pressure + '\'' +
                    ", photo='" + photo + '\'' +
                    ", points=" + points +
                    ", first_login_time='" + first_login_time + '\'' +
                    ", is_auth_qq='" + is_auth_qq + '\'' +
                    ", is_auth_sina='" + is_auth_sina + '\'' +
                    ", is_auth_weixin='" + is_auth_weixin + '\'' +
                    ", is_auth_alipay='" + is_auth_alipay + '\'' +
                    ", token='" + token + '\'' +
                    ", expire='" + expire + '\'' +
                    ", auth_phone_status='" + auth_phone_status + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
