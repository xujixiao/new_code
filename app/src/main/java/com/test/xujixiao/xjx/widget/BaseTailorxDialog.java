package com.test.xujixiao.xjx.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.tailorx.R;
import cn.tailorx.utils.UIUtils;

/**
 * 通用Dialog
 */
public class BaseTailorxDialog extends AlertDialog {


    protected BaseTailorxDialog(Context context) {
        super(context);
    }


    public static class ChooseBuilder {
        private List<String> mList;
        private Builder mBuilder;
        private TextView mTitleText;
        private ListView mChooseList;
        private AlertDialog mDialog;
        private BaseAdapter mAdapter;
        private int choosePosition = -1;

        public ChooseBuilder(final Context context) {


            mBuilder = new Builder(context);
            View view = View.inflate(context, R.layout.base_tailorx_dialog_layout, null);
            mBuilder.setView(view);

            mTitleText = (TextView) view.findViewById(R.id.tv_hint);
            mChooseList = (ListView) view.findViewById(R.id.lv_choose);

            mList = new ArrayList<>();
            mAdapter = new BaseAdapter() {

                @Override
                public int getCount() {
                    return mList.size();
                }

                @Override
                public Object getItem(int position) {
                    return mList.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ViewHolder viewHolder;
                    if (null == convertView) {
                        convertView = View.inflate(context, R.layout.base_tailorx_choose_item, null);
                        viewHolder = new ViewHolder();
                        viewHolder.mChooseText = (TextView) convertView.findViewById(R.id.tv_choose);
                        viewHolder.mChooseImage = (ImageView) convertView.findViewById(R.id.iv_choose);
                        convertView.setTag(viewHolder);
                    } else {
                        viewHolder = (ViewHolder) convertView.getTag();
                    }
                    viewHolder.mChooseText.setText(mList.get(position));
//                    viewHolder.mChooseImage.setVisibility(choosePosition==position?View.VISIBLE:View.INVISIBLE);
                    viewHolder.mChooseImage.setImageResource(choosePosition == position ? R.mipmap.single_topic : R.mipmap.single_topic_default);
                    return convertView;
                }

                class ViewHolder {
                    public TextView mChooseText;
                    public ImageView mChooseImage;
                }
            };

            mChooseList.setAdapter(mAdapter);
        }

        public ChooseBuilder setData(String[] strings) {
            mList.clear();
            for (String string : strings) {
                mList.add(string);
            }
            return this;
        }

        public ChooseBuilder setData(List<String> list) {
            this.mList.clear();
            this.mList.addAll(list);
            return this;
        }

        public ChooseBuilder setTitle(String title) {
            if (null != mTitleText) {
                mTitleText.setText(title);
            }
            return this;
        }

        public ChooseBuilder setChoosePosition(int position) {
            choosePosition = position;
            return this;
        }

        public ChooseBuilder setMaxHeight(int h) {
            try {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mChooseList.getLayoutParams();
                layoutParams.height = UIUtils.dip2px(h);
                mChooseList.setLayoutParams(layoutParams);
            } catch (Exception e) {
            }
            return this;
        }

        public ChooseBuilder setOnItemClickListener(final AdapterView.OnItemClickListener l) {
            if (null != mChooseList) {
                mChooseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        choosePosition = position;
                        choosePosition = position;
                        mAdapter.notifyDataSetChanged();
                        l.onItemClick(parent, view, position, id);
                        if (null != mDialog && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    }
                });
            }
            return this;
        }

        public AlertDialog create() {
            mDialog = mBuilder.create();
            return mDialog;
        }
    }
}
