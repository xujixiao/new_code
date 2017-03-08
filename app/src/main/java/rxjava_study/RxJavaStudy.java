package rxjava_study;

import com.apkfuns.logutils.LogUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by xujixiao on 2017/2/21.09:51
 * 邮箱：ji-xiao.xu@utsoft.cn
 */

public class RxJavaStudy {
    public void test(String[] args) {
        /*rxjava的观察者*/
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtils.d("completed");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("error");

            }

            @Override
            public void onNext(String s) {
                LogUtils.d(s);
            }
        };
        /*subscriber和observer是相同的*/
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

}
