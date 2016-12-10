用的时候，只需将当前目录下面的aar 文件 放在lib中，并配置gradle依赖就可以了


1  初始化函数stepmanager.init（Context,userid）,   传id就区分id，不传就不区分，登陆成功也需要调用一下这个函数，主要是为了设置id，如果不区分id就算了
    ※初始化建议最好在aplication处，或在主界面开始时候就要初始化（未初始化前，实现计步器界面跳转DB取不到context）
    ※不能传Null，传固定all字符串，作为记录手机步数
2  StepDetector.addlistener ,添加步数变化的listener,   ondestroy需要remove
3  获得需要上传的数据的list   StepDBManager.getIns().getDataShouldUpload();
4  设置数据已经上传的接口   StepDBManager.getIns().updateDateBaseAfterUpload(List list）
5  假如区分用户，需要调用StepCountManager.getIns().clearStepCount();
6  启动计步页面的activity    StepCountActivity.startActivity(Activity activity )
7  StepCountManager.getIns().dateChanged()发生改变的时候，注意要清内存数据，不然有可能你调用StepCountManager.getIns().getStepCount()时候由于取的是内存数据而不是数据库取得数据
   寻医问药切回首页每次是要请求一次数据的，成功后是要StepCountManager.getIns().getStepCount()展示步数，所以StepCountActivity onResume 日期变更要清下内存，
   不然StepCountActivity销毁时候会写一次DB，内存有值的话会把内存值写入DB造成错误
8  manifest  需要配置如下2部分:

   a  :     <uses-permission android:name="android.permission.WAKE_LOCK" />




   b  :
         <activity
                android:name="step.StepCountActivity"
                android:screenOrientation="portrait"/>


        <service android:name="step.StepService$GrayInnerService"
                 android:enabled="true"
        />
        <service android:name="step.StepService"
        >
            <intent-filter>
                <action android:name="com.service.platform.StepService"/>

                <category android:name="android.intent.category.default"/>
            </intent-filter>
        </service>


        <!--计步相关的广播-->
        <receiver
                android:name="step.receiver.DateChangedReceiver"
                android:enabled="true"
                android:exported="false">
            <intent-filter>
                <action android:name="android.intent.action.DATE_CHANGED"/>
            </intent-filter>
        </receiver>
        <receiver android:exported="false"
                  android:name="step.receiver.SystemReceiver"> 
            <intent-filter> 
                <action android:name="android.intent.action.BOOT_COMPLETED"/> 
                <action android:name="android.intent.action.ACTION_POWER_CONNECTED"/> 
                <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED"/> 
                <action android:name="android.intent.action.BATTERY_LOW"/> 
                <action android:name="android.intent.action.BATTERY_CHANGED"/> 
                <action android:name="android.intent.action.BATTERY_OKAY"/> 
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/> 
                <action android:name="android.net.wifi.WIFI_STATE_CHANGED"/> 
            </intent-filter>

            <intent-filter>
                <action android:name="cn.jpush.android.intent.REGISTRATION"/>
                <!-- Required  用户注册SDK的intent -->
                <action android:name="cn.jpush.android.intent.UNREGISTRATION"/>
                <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED"/>
                <!-- Required  用户接收SDK消息的intent -->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED"/>
                <!-- Required  用户接收SDK通知栏信息的intent -->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED"/>
                <!-- Required  用户打开自定义通知栏的intent -->
                <action android:name="cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK"/>
                <!-- Optional 用户接受Rich Push Javascript 回调函数的intent -->
                <action android:name="cn.jpush.android.intent.CONNECTION"/>
            </intent-filter>

            <intent-filter>
                <action android:name="android.intent.action.USER_PRESENT"/>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>
            <!-- Optional -->
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_ADDED"/>
                <action android:name="android.intent.action.PACKAGE_REMOVED"/>

                <data android:scheme="package"/>
            </intent-filter>
             
        </receiver>

        <receiver android:exported="false"
                  android:name="step.receiver.TimerReceiver"> 
            <intent-filter>
                 <action android:name="me.chunyu.pedometer.midnight_alarm_filter"/> 
                <action android:name="me.chunyu.pedometer.service_alive_alarm_filter"/> 
                <action android:name="android.intent.action.DATE_CHANGED"/> 
                <action android:name="android.intent.action.TIME_SET"/> 
                <action android:name="android.intent.action.TIMEZONE_CHANGED"/> 
            </intent-filter>
             
        </receiver>

        <receiver android:name="step.receiver.AutoStarter"/>
        <!--计步相关的广播   end-->