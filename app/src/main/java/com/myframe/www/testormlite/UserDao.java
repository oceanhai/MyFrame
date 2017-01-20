package com.myframe.www.testormlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import www.wuhai.common.utils.L;
import www.wuhai.common.utils.ToastUtils;

/**
 * Created by wuhai on 2017/01/19 11:39.
 * 描述：
 */

public class UserDao {
    private Context context;
    private int result;
    private Dao<User, String> userDaoOpe;//<对象，主键的类型（string或int）>
    private DBHelper helper;

    public UserDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DBHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     * 题外话：
     * 1>create 返回1：成功，0：失败
     * 2>create 对于已存在的用户（主键），增加不了（0：失败）
     * 3>createOrUpdate 应该是对已存在用户进行update，不存在用户直接创建（未测试）
     * @param user
     */
    public void add(User user)
    {
        try
        {
            User existUser = get(user.getId());
            if(existUser!=null){
                ToastUtils.showShort(context,"用户已存在");
                return;
            }
            result = userDaoOpe.create(user);
            L.e(OrmliteActivity.TAG,"add success result="+result);
        } catch (SQLException e)
        {
            e.printStackTrace();
            L.e(OrmliteActivity.TAG,"add fail result="+result+",Message="+e.getMessage());
        }

    }

    /**
     * 增加 user集合
     * @param datas
     */
    public void addAll(List<User> datas){
        try {
            userDaoOpe.create(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(String id)
    {
        try
        {
            return userDaoOpe.queryForId(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 获取user表，等于name名的第一个user对象
     * @param name
     * @return
     */
    public User queryForFirstByName(String name){
        try {
            return userDaoOpe.queryBuilder().where().eq("name",name).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User queryForFirstById(String id){
        try {
            return userDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
