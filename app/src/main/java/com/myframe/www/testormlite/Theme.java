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
//    @DatabaseField(id = true)//标示为主键，可以为String
    @DatabaseField(generatedId = true)//自增主键，必须为int等数据类型
    public int id;
    @DatabaseField
    public String title; // 标题
    @DatabaseField
    public String detail;  // 详情
    @DatabaseField
    public double cost; // 花费
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
//    @ForeignCollectionField(eager = true)
//    public ForeignCollection<Img> imgs;


    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", cost=" + cost +
                ", favStatus=" + favStatus +
                ", mPackage=" + mPackage +
                ", photographer=" + photographer +
                '}';
    }
}
