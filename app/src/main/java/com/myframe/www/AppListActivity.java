package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import www.wuhai.common.utils.GsonUtils;
import www.wuhai.common.utils.L;

public class AppListActivity extends AppCompatActivity {

    public static final int FILTER_ALL_APP = 0; // 所有应用程序
    public static final int FILTER_SYSTEM_APP = 1; // 系统程序
    public static final int FILTER_THIRD_APP = 2; // 第三方应用程序
    public static final int FILTER_SDCARD_APP = 3; // 安装在SDCard的应用程序
    private PackageManager pm;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AppListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

//        filterApp(FILTER_ALL_APP);
//        filterApp(FILTER_SYSTEM_APP);
//        filterApp(FILTER_THIRD_APP);
//        filterApp(FILTER_SDCARD_APP);
//        PackageUtil.getInstalledPackages(this,2);
        
        method01();
    }

    private String str = "[\"com.youba.barcode\",\"com.oppo.camera.voicebeauty\",\"io.appium.unlock\",\"com.oppo.camera.professional\",\"demo.simple.secure.yintong.com.securepayclient\",\"com.qiandaodao.push.demo\",\"com.nearme.instant.platform\",\"com.duguang.baseanimation\",\"com.monotype.android.font.HYXZYUANJOPPO0401\",\"jackpal.androidterm\",\"com.eg.android.AlipayGphone\",\"com.oppo.camera.doubleexposure\",\"com.qiandaodao.base\",\"com.bly.dkplat\",\"com.tencent.android.qqdownloader\",\"com.umeng.client\",\"com.qiandaodao.share.demo\",\"com.myframe.www.test\",\"com.tencent.mobileqq\",\"www.wuhai.shipei\",\"com.aspsine.swipetoloadlayout\",\"com.abcd\",\"com.tencent.tmgp.pubgmhd\",\"com.xywy.askxywy\",\"com.oppo.camera.filter\",\"com.example.administrator.marqueetextviewapp\",\"com.qiandaodao.jieqiandao\",\"com.kingroot.kinguser\",\"com.oppo.pcassistant\",\"com.qiandaodao.qudaikuan\",\"com.xywy.component\",\"com.thinksky.itools\",\"com.qiandaodao.qianpengyou\",\"com.myframe.www\",\"com.halo.wifikey.wifilocating\",\"com.test.banner\",\"cn.sharesdk.demo\",\"com.qiandaodao.jieqianbaodian\",\"com.tencent.mtt\",\"com.oppo.camera.raw\",\"io.appium.settings\",\"com.andromeda.androbench2\",\"com.oppo.camera.microspurmode\",\"com.daodao.qiandaodao\",\"com.speedsoftware.rootexplorer\",\"com.oppo.camera.audio\"]";

    private void method01() {
        List<String> list = GsonUtils.getInstance().parsJson2StringList(str);
        for(int x=0;x<list.size();x++){
            L.e("wuhai",list.get(x));
        }
    }

    private void filterApp(int type) {
        // 获取PackageManager对象
        pm = getPackageManager();
        // 查询已经安装的应用程序
        List<ApplicationInfo> applicationInfos = pm.
                getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        // 排序
        Collections.sort(applicationInfos,
                new ApplicationInfo.DisplayNameComparator(pm));
        switch (type) {
            case FILTER_ALL_APP:// 所有应用
                L.e("applist","所有应用-----------------------------------------");
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    getAppInfo(applicationInfo);
                }
                break;
            case FILTER_SYSTEM_APP:// 系统应用
                L.e("applist","系统应用-----------------------------------------");
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        getAppInfo(applicationInfo);
                    }
                }
                break;
            case FILTER_THIRD_APP:// 第三方应用
                L.e("applist","第三方应用-----------------------------------------");
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    // 非系统应用
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        getAppInfo(applicationInfo);
                    }
                    // 系统应用，但更新后变成不是系统应用了
                    else if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        getAppInfo(applicationInfo);
                    }
                }
                break;
            case FILTER_SDCARD_APP:// SDCard应用
                L.e("applist","SDCard应用-----------------------------------------");
                for (ApplicationInfo applicationInfo : applicationInfos) {
                    if (applicationInfo.flags == ApplicationInfo.FLAG_SYSTEM) {
                        getAppInfo(applicationInfo);
                    }
                }
            default:
                break;
            }
        }
     /**
     * 获取应用信息
     */
    private void getAppInfo(ApplicationInfo applicationInfo) {
        String appName = applicationInfo.loadLabel(pm).toString();// 应用名
        String packageName = applicationInfo.packageName;// 包名
        L.e("applist","应用名：" + appName + " 包名：" + packageName);
    }
}
