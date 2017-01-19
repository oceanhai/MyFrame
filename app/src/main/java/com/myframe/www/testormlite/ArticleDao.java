package com.myframe.www.testormlite;

import android.content.Context;
import android.text.TextUtils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by wuhai on 2017/01/19 11:25.
 * 描述：
 */

public class ArticleDao
{
    private Dao<Article, Integer> articleDaoOpe;
    private DBHelper helper;

    @SuppressWarnings("unchecked")
    public ArticleDao(Context context){
        try
        {
            helper = DBHelper.getHelper(context);
            articleDaoOpe = helper.getDao(Article.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个Article
     * @param article
     */
    public void add(Article article){
        try
        {
            articleDaoOpe.create(article);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个Article
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Article getArticleWithUser(int id){
        Article article = null;
        try
        {
            article = articleDaoOpe.queryForId(id);
            helper.getDao(User.class).refresh(article.getUser());//TODO 这是干嘛的啊

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一篇文章
     * @param id
     * @return
     */
    public Article get(int id){
        Article article = null;
        try
        {
            article = articleDaoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过UserId获取所有的文章
     * @param userId
     * @return
     */
    public List<Article> listByUserId(int userId){
        try
        {
            return articleDaoOpe.queryBuilder().where().eq("user_id", userId)
                    .query();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取user_id and title 的第一个Article对象
     * 备注
     * ※updateRaw，queryRaw 参数可直接用sql语句
     * @param user_id
     * @param title
     * @return
     */
    public Article queryForFirst(String user_id, String title){
        try {
            if(user_id == null){
                return articleDaoOpe.queryBuilder().where().eq("title",title).queryForFirst();
            }else{
                //更复杂的查询
                // select * from tb_article where ( user_id = 1 and name = 'xxx' )  or ( user_id = 2 and name = 'yyy' )  ;
//                where.or(
//                        //
//                        where.and(//
//                                where.eq("user_id", 1), where.eq("name", "xxx")),
//                        where.and(//
//                                where.eq("user_id", 2), where.eq("name", "yyy")));
                return articleDaoOpe.queryBuilder().where().eq("user_id",user_id).and().eq("title",title).queryForFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 事务操作
     */
    public void insert(){
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(), new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    //其中一个失败，事务回滚
//                    articleDaoOpe.create(XXX);
//                    articleDaoOpe.delete(XXXX)
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
