package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.test.xujixiao.xjx.widget.recycleritem.DividerGridItemDecoration;
import com.test.xujixiao.xjx.widget.recycleritem.DividerItemDecoration;


/**
 * Created by Administrator on 2016/7/14.
 */
public class RecyclerUtils {
    /**
     * 默认水平无分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void linearDefault(Context context, RecyclerView xRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void linearDefaultSplitline(Context context, RecyclerView xRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));
    }

    /**
     * 横向
     *
     * @param context
     * @param xRecyclerView
     */
    public static void HorizontalDefault(Context context, RecyclerView xRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        xRecyclerView.setLayoutManager(layoutManager);

    }

    /**
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void HorizontalSplitline(Context context, RecyclerView xRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));
    }

    /**
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void linearRefresh(Context context, XRecyclerView xRecyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));

    }

    /**
     * 垂直
     * 默认无分割线
     *
     * @param context
     * @param xRecyclerView
     * @param spanCount     设置垂直列数
     */
    public static void GridDefaultVertical(Context context, RecyclerView xRecyclerView, int spanCount) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 垂直
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     * @param spanCount     设置垂直列数
     * @param isSplitline   分割线
     */
    public static void GridDefaultVertical(Context context, RecyclerView xRecyclerView, int spanCount, boolean isSplitline) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        if (isSplitline) {
            xRecyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        }
    }

    /**
     * 水平
     *
     * @param context
     * @param xRecyclerView
     * @param spanCount     设置垂直列数
     */
    public static void GridDefaultHorizontal(Context context, RecyclerView xRecyclerView, int spanCount) {

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.HORIZONTAL);
        xRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 水平
     *
     * @param context
     * @param xRecyclerView
     * @param spanCount     设置垂直列数
     * @param isSplitline   分割线
     */
    public static void GridDefaultHorizontal(Context context, RecyclerView xRecyclerView, int spanCount, boolean isSplitline) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.HORIZONTAL);
        xRecyclerView.setLayoutManager(layoutManager);
        if (isSplitline) {
            xRecyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        }
    }

    /**
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void GridRefresh(Context context, int spanCount, XRecyclerView xRecyclerView) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.HORIZONTAL);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));

    }

    /**
     * 分割线
     *
     * @param context
     * @param xRecyclerView
     */
    public static void GridRefreshAndLoad(Context context, int spanCount, XRecyclerView xRecyclerView) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.HORIZONTAL);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));

    }


//    /**
//     * GridItem分割线
//     *
//     * @param context
//     * @param xRecyclerView
//     * @param splitline     分割数量
//     * @param isSplitline   是否分割线
//     */
//    public static void GridItem(Context context, RecyclerView xRecyclerView, int splitline, boolean isSplitline) {
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(splitline,
//                StaggeredGridLayoutManager.VERTICAL);
//        xRecyclerView.setLayoutManager(layoutManager);
//        if (isSplitline) {
//            xRecyclerView.addItemDecoration(new DividerGridItemDecoration(context));
//        }
//    }
//
//    /**
//     * @param context
//     * @param xRecyclerView
//     * @param splitline     分割数量
//     * @param vertical      横向 纵向
//     * @param isSplitline   是否分割线
//     */
//    public static void GridItem(Context context, RecyclerView xRecyclerView, int splitline, boolean vertical, boolean isSplitline) {
//        StaggeredGridLayoutManager layoutManager;
//        if (vertical) {
//            layoutManager = new StaggeredGridLayoutManager(splitline,
//                    StaggeredGridLayoutManager.VERTICAL);
//        } else {
//            layoutManager = new StaggeredGridLayoutManager(splitline,
//                    StaggeredGridLayoutManager.HORIZONTAL);
//        }
//        xRecyclerView.setLayoutManager(layoutManager);
//        if (isSplitline) {
//            xRecyclerView.addItemDecoration(new DividerGridItemDecoration(context));
//        }
//    }
//
//    /**
//     * 无分割线
//     *
//     * @param context
//     * @param xRecyclerView
//     */
//    public static void linearItem(Context context, RecyclerView xRecyclerView) {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        xRecyclerView.setLayoutManager(layoutManager);
//    }
//
//    /**
//     * 分割线
//     *
//     * @param context
//     * @param xRecyclerView
//     */
//    public static void linearItem(Context context, RecyclerView xRecyclerView, boolean isSplitline) {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        xRecyclerView.setLayoutManager(layoutManager);
//        if (isSplitline) {
//            xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));
//        }
//    }
//
//
//    /**
//     * 横向和纵向分割线
//     *
//     * @param context
//     * @param xRecyclerView
//     * @param vertical
//     */
//    public static void linearItem(Context context, RecyclerView xRecyclerView, boolean isSplitline, boolean vertical) {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        if (vertical && isSplitline) {
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 0));
//        } else {
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            xRecyclerView.addItemDecoration(new DividerItemDecoration(context, 1));
//
//        }
//
//        xRecyclerView.setLayoutManager(layoutManager);
//    }

}
