package com.myframe.www.retrofit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.myframe.www.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import www.wuhai.common.utils.L;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "rxjava";
    @Bind(R.id.iv01)
    ImageView iv01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        method08();
    }

    /**
     * 加载资源图片   异步
     */
    private void method09() {
        final int drawableRes = R.drawable.f0;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxJavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                iv01.setImageDrawable(drawable);
            }
        });
    }

    /**
     * Scheduler 调度器
     */
    private void method08(){
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        L.e(TAG,"int="+integer);
                    }
                });
    }

    /**
     * 加载资源图片
     */
    private void method07() {
        final int drawableRes = R.drawable.f0;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxJavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Drawable drawable) {
                iv01.setImageDrawable(drawable);
            }
        });
    }

    //打印字符串
    private void method06() {
        String[] names = {"路飞", "索隆", "娜美"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                L.e(TAG, "name:" + s);
            }
        });
    }

    private void method05() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                L.e(TAG, "onNextAction onNext:" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                L.e(TAG, "onErrorAction onError");
            }
        };
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                L.e(TAG, "onCompletedAction onCompleted");
            }
        };

        Observable observable = Observable.just("hello", "hi", "gameover");

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
//        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    private void method04() {
        Observable observable = Observable.just("hello", "hi", "gameover");

        String[] words = {"hello", "hi", "gameover"};
        Observable observable2 = Observable.from(words);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                L.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                L.e(TAG, "onNext:" + s);
            }
        };

        observable.subscribe(observer);
    }

    private void method03() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onNext("aliluya");
                subscriber.onCompleted();
            }
        });
    }

    private void method02() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                L.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                L.e(TAG, "onNext:" + s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                L.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                L.e(TAG, "onNext:" + s);
            }
        };

    }

//    private void method01(){
//        Observable.from(folders).flatMap(new Func1<File, Observable<File>>() {
//            @Override
//            public Observable<File> call(File file) {
//                return Observable.from(file.listFiles());
//            }
//        }).filter(new Func1<File, Boolean>() {
//            @Override
//            public Boolean call(File file) {
//                return file.getName().endsWith(".png");
//            }
//        }).map(new Func1<File, Bitmap>() {
//            @Override
//            public Bitmap call(File file) {
//                return getBitmapFromFile(file);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageCollectorView.addImage(bitmap);
//                    }
//                });
//    }
}
