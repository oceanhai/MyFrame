package com.myframe.www.testormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wuhai on 2017/01/18 16:49.
 * 描述：
 */

@DatabaseTable
public class Img implements Serializable{

    @DatabaseField(id = true)
    public String id;
}
