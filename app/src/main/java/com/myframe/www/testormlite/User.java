package com.myframe.www.testormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by wuhai on 2017/01/19 11:19.
 * 描述：
 */

//tableName 可以设定table名;不适用默认用类名做table表名
@DatabaseTable(tableName = "tb_user")
public class User
{
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField
    private int age;
    @DatabaseField
    private int height;

    @ForeignCollectionField
    private Collection<Article> articles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", name=" + name
                + "]";
    }

}
