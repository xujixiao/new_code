package com.test.xujixiao.xjx.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.utouu.android.commons.constants.DataConstant;

import cn.tailorx.LoginModuleActivity;
import cn.tailorx.R;


public class LoginInvalidDialog extends AlertDialog.Builder {

    public LoginInvalidDialog(Context arg0) {
        super(arg0, R.style.style_dialog_title_center);

        setTitle("身份令牌过期");
        setCancelable(false);

        setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                okClick();
            }
        });
    }

    public void okClick() {
        if (null != getContext()) {
//			PreferenceUtils.remove(getContext(), UBeautyPreference.KEY_BASIC_TGT);
//			PreferenceUtils.setPrefString(getContext(), UBeautyPreference.KEY_BASIC_ST, UBeautyPreference.KEY_BASIC_ST_RESET);
//			PreferenceUtils.remove(getContext(),UBeautyPreference.KEY_BASIC_USER_MONEY);
            DataConstant.clearST();
            DataConstant.clearTGT(getContext());
            getContext().startActivity(new Intent(getContext(), LoginModuleActivity.class));
        }
    }
}
