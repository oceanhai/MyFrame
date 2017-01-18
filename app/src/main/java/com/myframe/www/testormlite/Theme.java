package com.myframe.www.testormlite;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wuhai on 2017/01/18 16:34.
 * 描述：
 */

@DatabaseTable
public class Theme implements Serializable{
    @DatabaseField(id = true)
    public String id;
    @DatabaseField
    public String tid;
    @DatabaseField
    public String photographerId;
    @DatabaseField
    public String packageId; // 隶属套餐
    @DatabaseField
    public String status; // 后台审核状态
    @DatabaseField
    public String title; // 标题
    @DatabaseField
    public String coverId; // 封面Id
    @DatabaseField
    public String coverUrl; // 封面img
    @DatabaseField
    public String detail;  // 详情
    @DatabaseField
    public int photoCount; // 图片个数
    @DatabaseField
    public String photos; //图集
    @DatabaseField
    public String createTime; // 上传时间
    @DatabaseField
    public String recordTime; // 拍摄时间
    @DatabaseField
    public double cost; // 花费
    @DatabaseField
    public String tags; // 标签
    @DatabaseField
    public String address;// 地址
    @DatabaseField
    public String loacationCode; // 位置代码
    @DatabaseField
    public int popularCount; // 热度
    @DatabaseField(defaultValue = "0")
    public int favStatus; // 收藏状态


    // 外部对象字段
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public PackageInfo mPackage;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public Photographer photographer;
    /**
     * 这里需要注意的是：属性类型只能是ForeignCollection<T>或者Collection<T>
     * 如果需要懒加载（延迟加载）可以在@ForeignCollectionField加上参数eager=false
     * 这个属性也就说明一个部门对应着多个用户
     */
    @ForeignCollectionField(eager = true)
    public ForeignCollection<Img> imgs;
}
