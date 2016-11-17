package com.test.xujixiao.xjx;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xujixiao on 2016/11/17.13:34
 * 邮箱：1107313740@qq.com
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfig();
    }

    private void initRealmConfig() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().
                name("test_realm").
                schemaVersion(1).
                encryptionKey(new byte[64]).
                deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
