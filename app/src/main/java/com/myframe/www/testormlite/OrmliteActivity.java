package com.myframe.www.testormlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myframe.www.R;
import com.myframe.www.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;
import www.wuhai.common.utils.ToastUtils;

public class OrmliteActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "lct_db";

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.show_info)
    TextView showInfo;
    @Bind(R.id.btn03)
    Button btn03;
    @Bind(R.id.btn04)
    Button btn04;
    @Bind(R.id.btn05)
    Button btn05;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.article_title)
    EditText articleTitle;
    @Bind(R.id.btn06)
    Button btn06;
    @Bind(R.id.user_id)
    EditText userId;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OrmliteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn01://add user
                addUser();
                break;
            case R.id.btn02://add article
                addArticle();
                break;
            case R.id.btn03://delete
                break;
            case R.id.btn04://update
                break;
            case R.id.btn05://query
                query();
                break;
            case R.id.btn06://gson解析
                String json = CommonUtils.getFromAssets("user.json", this);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<User>>() {
                }.getType();
                List<User> datas = gson.fromJson(json, type);
                L.e(TAG, "addALL:" + datas);
                new UserDao(this).addAll(datas);
                break;
        }
    }

    private User addUser() {
        String id = userId.getText().toString();
        String name = userName.getText().toString();
        if (TextUtils.isEmpty(id)) {
            ToastUtils.showShort(this, "id 不能为空");
            return null;
        }
        User u = new User();
        u.setId(id);
        u.setName(name);
        new UserDao(this).add(u);
        return u;
    }

    private void addArticle() {
        String title = articleTitle.getText().toString();
        String id = userId.getText().toString();
//        String name = userName.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtils.showShort(this, "title 文章名不能为空");
            return;
        }
//        if (TextUtils.isEmpty(name)) {
//            ToastUtils.showShort(this, "name 不能为空");
//            return;
//        }
//        User user = new UserDao(this).queryForFirst(name);
         if (TextUtils.isEmpty(id)) {
            ToastUtils.showShort(this, "id 不能为空");
            return;
        }
        User user = new UserDao(this).queryForFirstById(id);
        if (user == null) {
            user = addUser();
        }
        Article article = new Article();
        article.setTitle(title);
        article.setUser(user);
        new ArticleDao(this).add(article);
    }

    private void query() {
        String title = articleTitle.getText().toString();
        String name = userName.getText().toString();
        User user = null;
        Article article = null;
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(title)) {
            //满足 name和title的信息
            user = new UserDao(this).queryForFirstByName(name);
            L.e(TAG, "search first user:" + user);

            if (user != null && !TextUtils.isEmpty(title)) {
                article = new ArticleDao(this).queryForFirst(user.getId(), title);
            } else if (!TextUtils.isEmpty(title)) {
                article = new ArticleDao(this).queryForFirst(null, title);
            }
            L.e(TAG, "search first article:" + article);
        } else {
            ToastUtils.showShort(this, "检索信息不能为空");
        }
    }


    private void showUser() {
        Article article1 = new ArticleDao(this).get(1);
        L.e(TAG, "article1：" + article1);
        Article article2 = new ArticleDao(this).getArticleWithUser(1);
        L.e(TAG, "article2：" + article1);
        List<Article> articles = new ArticleDao(this).listByUserId(1);
        L.e(TAG, "articles：" + articles);

        User user = new UserDao(this).get(1);
        L.e(TAG, "user：" + user);
        if (user.getArticles() != null) {
//            Collection<Article> articles2 = user.getArticles();

            for (Article article : user.getArticles()) {
                L.e(TAG, "article" + article.toString());
            }
        }
    }

    private void showTheme() {
        Theme theme = new ThemeDao(this).queryForId(1);
        showInfo.setText(theme.toString());
    }

    private void addTheme() {

        Theme theme = new Theme();
        theme.id = 1;
        theme.title = "主题";
        theme.detail = "主题详情";

        PackageInfo packageInfo = new PackageInfo();
        packageInfo.id = 1;
        packageInfo.pid = "10000";

        theme.mPackage = packageInfo;

        new ThemeDao(this).add(theme);
    }
}
