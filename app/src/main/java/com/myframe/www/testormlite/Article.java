package com.myframe.www.testormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wuhai on 2017/01/19 11:22.
 * 描述：
 */

@DatabaseTable(tableName = "tb_article")
public class Article
{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Article [id=" + id + ", title=" + title + ", user=" + user
                + "]";
    }

}
