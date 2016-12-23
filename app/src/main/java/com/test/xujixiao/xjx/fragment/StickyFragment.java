package com.test.xujixiao.xjx.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;

import butterknife.BindView;
import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by xujixiao on 2016/11/23.15:30
 * 邮箱：1107313740@qq.com
 */

public class StickyFragment extends BaseFragment {
    @BindView(R.id.sticky_listview)
    ExpandableStickyListHeadersListView mStickyListview;

    public static StickyFragment newInstance() {

        Bundle args = new Bundle();

        StickyFragment fragment = new StickyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sticky_listview;
    }

    @Override
    public void initData() {
        mStickyListview.setAdapter(new MyAdapter(getActivity()));
        mStickyListview.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if (mStickyListview.isHeaderCollapsed(headerId)) {
                    mStickyListview.expand(headerId);
                } else {
                    mStickyListview.collapse(headerId);
                }
            }
        });
    }

    public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private String[] countries = new String[]{"徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1", "徐继晓1"};
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return countries.length;
        }

        @Override
        public Object getItem(int position) {
            return countries[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.test_list_item_layout, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(countries[position]);
            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.main_adapter_item, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            //set header text as first char in name
            String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
            holder.text.setText(headerText);
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            //return the first character of the country as ID because this is what headers are based upon
            return countries[position].subSequence(0, 1).charAt(0);
        }

        class HeaderViewHolder {
            TextView text;
        }

        class ViewHolder {
            TextView text;
        }

    }
}
