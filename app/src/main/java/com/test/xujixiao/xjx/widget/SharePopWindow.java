package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import cn.tailorx.R;
import cn.tailorx.apdpter.base.CommonAdapter;

/**
 * Created by xujixiao on 2016/7/27.13:48
 * 邮箱：1107313740@qq.com
 */
public class SharePopWindow extends PopupWindow {
    private GridView mGridView;
    private ShareAdapter mShareAdapter;
    private List<SharePlatform> mList = new ArrayList<>();
    private Context mContext;
    private ShareOperate mShareOperate;
    private ShareItem mShareItem;

    public SharePopWindow(Context context, ShareItem shareItem, ShareOperate shareOperate) {
        super(context);
        this.mContext = context;
        this.mShareItem = shareItem;
        this.mShareOperate = shareOperate;
        init();
    }


    public interface ShareOperate {
        void sharePlatform(SharePlatform sharePlatform, ShareItem shareItem);

        void windowDismiss();
    }

    public void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.share_layout_activity, null);
        mGridView = (GridView) view.findViewById(R.id.gv_share_platform);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mShareAdapter = new ShareAdapter(mContext, mList);
        mGridView.setAdapter(mShareAdapter);
        mShareAdapter.notifyDataSetChanged();
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(null);
        setTouchable(true);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mShareOperate != null) {
                    mShareOperate.windowDismiss();
                }
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharePlatform sharePlatform = mList.get(i);
                if (sharePlatform != null) {
                    if (mShareOperate != null) {
                        mShareOperate.sharePlatform(sharePlatform, mShareItem);
                    }
                }
            }
        });
    }

    public class ShareAdapter extends CommonAdapter<SharePlatform> {
        public ShareAdapter(Context context, List<SharePlatform> mDatas) {
            super(context, mDatas);
        }

        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = mInflater.inflate(R.layout.share_popwindow_layout_item, null);
                holder.mTextView = (TextView) convertView.findViewById(R.id.umeng_socialize_shareboard_pltform_name);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.umeng_socialize_shareboard_image);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            SharePlatform shareItem = getItem(i);
            if (shareItem != null) {
                holder.mTextView.setText(shareItem.namePlatform);
                holder.mImageView.setImageResource(shareItem.imgViewId);
            }
            final int k = i;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharePlatform sharePlatform = mList.get(k);
                    if (sharePlatform != null) {
                        if (mShareOperate != null) {
                            mShareOperate.sharePlatform(sharePlatform, mShareItem);
                        }
                    }
                }
            });
            return convertView;
        }
    }

    public class Holder {
        public TextView mTextView;
        public ImageView mImageView;
    }

    public static class SharePlatform {
        public int imgViewId;
        public String namePlatform;
        public SHARE_MEDIA mSHARE_media;

        public SharePlatform(int imgViewId, String namePlatform, SHARE_MEDIA SHARE_media) {
            this.imgViewId = imgViewId;
            this.namePlatform = namePlatform;
//            mSHARE_media = SHARE_media;
        }

        @Override
        public String toString() {
            return "SharePlatfrom{" +
                    "imgViewId=" + imgViewId +
                    ", namePlatform='" + namePlatform + '\'' +
                    ", mSHARE_media=" + mSHARE_media +
                    '}';
        }
    }

    public static class ShareItem {
        public String title;
        public String content;
        public String url;
        public int imgId;
        public String imgUrlId;


        @Override
        public String toString() {
            return "ShareItem{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    ", imgId=" + imgId +
                    '}';
        }

        public ShareItem(String title, String content, String url, int imgId) {
            this.title = title;
            this.content = content;
            this.url = url;
            this.imgId = imgId;
        }

        public ShareItem(String title, String content, String url, String urlImg) {
            this.title = title;
            this.content = content;
            this.url = url;
            this.imgUrlId = urlImg;
        }
    }

}
