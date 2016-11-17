package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.protocol.RealmTestProtocol;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by xujixiao on 2016/11/17.13:29
 * 邮箱：1107313740@qq.com
 */

public class RealmFragment extends BaseFragment {
    @BindView(R.id.tv_config)
    TextView mTvConfig;
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv_update)
    TextView mTvUpdate;

    public static RealmFragment newInstance() {

        Bundle args = new Bundle();

        RealmFragment fragment = new RealmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_realm;
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.tv_config)
    public void config() {
    }

    @OnClick(R.id.tv_add)
    public void add() {
        mRealm.beginTransaction();
        RealmTestProtocol protocol = mRealm.createObject(RealmTestProtocol.class);
        protocol.name = "xujxiao";
        protocol.age = 90;
        mRealm.commitTransaction();
        LogUtils.d("保存成功");

    }

    @OnClick(R.id.tv_search)
    public void search() {
        /*相关操作可查询网页*/
    }

    /**
     * 数据库异步操作
     */
    public void runAsyncTask() {
        RealmAsyncTask realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }

}
