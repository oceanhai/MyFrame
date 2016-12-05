package com.myframe.www.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.myframe.www.R;
import com.myframe.www.widget.navigationmenu1.Fragment2;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import www.wuhai.common.utils.ToastUtils;

public class NavigationViewActivity extends AppCompatActivity {

    @Bind(R.id.frame)
    FrameLayout frame;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private FragmentTransaction fragmentTransaction;
    private Fragment2 currentFragment;
    private Fragment2 inboxFragment;
    private Fragment2 staredFragment;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NavigationViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        ButterKnife.bind(this);

        // Initializing Toolbar and setting it as the actionbar
        //TODO toolbar 这东西也不是必须的，可以自己定义titlebar而不需要吧，一样能最左侧滑打开抽屉（下面的toolbar置null就行）
        setSupportActionBar(toolbar);

        init();

        //导航 头部布局
        initHeaderView();

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            //TODO 这里完全可以只作为一个个人中心页，点击跳转到各自业务页面；而主页面的activity里可以放下导航来控制
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if(item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);
                }

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //curr hide
                if(currentFragment != null){
                    getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
                }

                fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //Check to see which item was being clicked and perform appropriate action
                switch (item.getItemId()){
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inbox:
                        ToastUtils.showShort(getApplicationContext(), "Inbox Selected");
//                        fragmentTransaction.replace(R.id.frame, Fragment2.newInstace("Inbox"));
                        if(inboxFragment == null){
                            inboxFragment = Fragment2.newInstace("Inbox");
                            fragmentTransaction.add(R.id.frame, inboxFragment, "1");
                        }else{
                            fragmentTransaction.show(inboxFragment);
                        }
                        currentFragment = inboxFragment;
                        break;

                    // For rest of the options we just show a toast on click
                    case R.id.starred:
                        Toast.makeText(getApplicationContext(), "Stared Selected", Toast.LENGTH_SHORT).show();
//                        fragmentTransaction.replace(R.id.frame, Fragment2.newInstace("Stared"));
                        if(staredFragment == null){
                            staredFragment = Fragment2.newInstace("Stared");
                            fragmentTransaction.add(R.id.frame, staredFragment, "2");
                        }else{
                            fragmentTransaction.show(staredFragment);
                        }
                        currentFragment = staredFragment;
                        break;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(),"Send Selected",Toast.LENGTH_SHORT).show();
                        return true;//TODO 注意 追加的时候这里都要变成 break
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(),"Drafts Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(),"All Mail Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Trash Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;
                }

                fragmentTransaction.commit();
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void init() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
    }

    /**
     * 导航头部事件 操作
     */
    private void initHeaderView() {
        View view = navigationView.getHeaderView(0);
        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(getApplicationContext(), "头像被点击了");
            }
        });
    }
}
