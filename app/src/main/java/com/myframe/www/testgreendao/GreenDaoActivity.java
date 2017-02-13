package com.myframe.www.testgreendao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myframe.www.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.add)
    Button add;
    @Bind(R.id.get_by_id)
    Button getById;
    @Bind(R.id.insert)
    Button insert;
    @Bind(R.id.updata)
    Button updata;
    @Bind(R.id.query)
    Button query;
    @Bind(R.id.id_tv)
    EditText idTv;
    @Bind(R.id.name_tv)
    EditText nameTv;
    @Bind(R.id.age_tv)
    EditText ageTv;
    @Bind(R.id.isboy_tv)
    EditText isboyTv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GreenDaoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);

        add.setOnClickListener(this);
        getById.setOnClickListener(this);
        insert.setOnClickListener(this);
        updata.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    private void getuserById() {
        User user = getUserDao().load(1l);
        Log.i("tag", "结果：" + user.getId() + "," + user.getName() + "," + user.getAge() + "," + user.getIsBoy() + ";");


    }

    private void insertdata() {
        //插入数据
        User insertData = new User(null, "插入数据", 24, false);
        getUserDao().insert(insertData);
    }

    private void updatadata() {
        //更改数据
        List<User> userss = getUserDao().loadAll();
        User user = new User(userss.get(0).getId(), "更改后的数据用户", 22, true);
        getUserDao().update(user);

    }

    private void querydata() {
        //查询数据详细
        List<User> users = getUserDao().loadAll();
        Log.i("tag", "当前数量：" + users.size());
        for (int i = 0; i < users.size(); i++) {
            Log.i("tag", "结果：" + users.get(i).getId() + "," + users.get(i).getName() + "," + users.get(i).getAge() + "," + users.get(i).getIsBoy() + ";");
        }

    }

    private void querydataBy() {////查询条件
        Query<User> nQuery = getUserDao().queryBuilder()
//                .where(UserDao.Properties.Name.eq("user1"))//.where(UserDao.Properties.Id.notEq(999))
                .orderAsc(UserDao.Properties.Age)//.limit(5)//orderDesc
                .build();
        List<User> users = nQuery.list();
        Log.i("tag", "当前数量：" + users.size());
        for (int i = 0; i < users.size(); i++) {
            Log.i("tag", "结果：" + users.get(i).getId() + "," + users.get(i).getName() + "," + users.get(i).getAge() + "," + users.get(i).getIsBoy() + ";");
        }

//        QueryBuilder qb = userDao.queryBuilder();
//        qb.where(Properties.FirstName.eq("Joe"),
//                qb.or(Properties.YearOfBirth.gt(1970),
//                        qb.and(Properties.YearOfBirth.eq(1970), Properties.MonthOfBirth.ge(10))));
//        List youngJoes = qb.list();
    }


    /**
     * 根据查询条件,返回数据列表
     *
     * @param where  条件
     * @param params 参数
     * @return 数据列表
     */
    public List<User> queryN(String where, String... params) {
        return getUserDao().queryRaw(where, params);
    }

    /**
     * 根据用户信息,插件或修改信息
     *
     * @param user 用户信息
     * @return 插件或修改的用户id
     */
    public long saveN(User user) {
        return getUserDao().insertOrReplace(user);
    }

    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public void saveNLists(final List<User> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        getUserDao().getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    User user = list.get(i);
                    getUserDao().insertOrReplace(user);
                }
            }
        });

    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote() {
        getUserDao().deleteAll();
    }

    /**
     * 根据用户类,删除信息
     *
     * @param user 用户信息类
     */
    public void deleteNote(User user) {
        getUserDao().delete(user);
    }

    private UserDao getUserDao() {
        return GreenDaoManager.getInstance().getSession().getUserDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                User user = new User(null, nameTv.getText().toString(), Integer.valueOf(ageTv.getText().toString()), false);
                saveN(user);
                break;
            case R.id.get_by_id:
                break;
            case R.id.insert:
                break;
            case R.id.updata:
                break;
            case R.id.query:
                break;
        }
    }
}
