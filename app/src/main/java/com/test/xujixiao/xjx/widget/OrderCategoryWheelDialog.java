package com.test.xujixiao.xjx.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.tailorx.R;
import cn.tailorx.protocol.OrderCategory;
import cn.tailorx.utils.Tools;
import cn.tailorx.widget.wheel_adapters.ArrayWheelAdapter;
import cn.tailorx.widget.wheel_widget.OnWheelScrollListener;
import cn.tailorx.widget.wheel_widget.WheelView;

/**
 * Created by User on 2016/8/17.
 * 轮滑选择器
 */
public class OrderCategoryWheelDialog extends Dialog {

    private Context context;
    private WheelView mFirstCategoryWheel;// 省份
    private WheelView mSecondCategoryWheel;// 市
    private boolean isScroll;

    private int firstCategoryId, secondCategoryId;

    public String provinceCode;// 省份代码
    public String cityCode;// 城市代码

    private String firstCategoryName;// 省名称
    private String secondCategoryName = "";// 城市名称

    private List<OrderCategory.ChildCategoryListEntity> mChildCategoryListEntities = null;//市集合

    private ArrayList<String> mFirstCategoryList = new ArrayList<>();// 省
    private ArrayList<String> mScondeCategoryList = new ArrayList<>();// 市
    private View view;
    private List<OrderCategory> mOrderCategories;
    public SelectInterface mSelectInterface;
    private TextView mTvPrice;

    public interface SelectInterface {
        void selectCategory(String province, String city, int id);
    }

    public OrderCategoryWheelDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public OrderCategoryWheelDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    /**
     * 显示出选择地区对话框
     */
    public void init() {
        view = LayoutInflater.from(context).inflate(R.layout.order_category_wheel_dialog_layout, null);
        getWindow().setContentView(view);
        mFirstCategoryWheel = (WheelView) view.findViewById(R.id.wl_first_category);
        mSecondCategoryWheel = (WheelView) view.findViewById(R.id.wl_second_category);
        mFirstCategoryWheel.setDrawShadows(true);
        mSecondCategoryWheel.setDrawShadows(true);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mFirstCategoryWheel.setVisibleItems(5);
        mSecondCategoryWheel.setVisibleItems(5);
        mFirstCategoryWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mFirstCategoryList));
        mSecondCategoryWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mScondeCategoryList));
        TextView confirmText = (TextView) view.findViewById(R.id.tv_confirm);
        TextView cancelText = (TextView) view.findViewById(R.id.tv_cancel);
        /*确认按钮*/
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isScroll) {
                    return;
                }
                firstCategoryId = mFirstCategoryWheel.getCurrentItem();
                secondCategoryId = mSecondCategoryWheel.getCurrentItem();
                if (mOrderCategories == null || mOrderCategories.size() == 0) {
                    return;
                }
                // 获取省code
                if (mOrderCategories.size() > firstCategoryId) {
                    provinceCode = String.valueOf(mOrderCategories.get(firstCategoryId).id);
                }
                // 获取城市code
                if (mChildCategoryListEntities.size() != 0 && mChildCategoryListEntities != null) {
                    cityCode = String.valueOf(mChildCategoryListEntities.get(secondCategoryId).id);
                    // 获取县code
                }

                if (mOrderCategories.size() > firstCategoryId) {
                    firstCategoryName = mOrderCategories.get(firstCategoryId).name;
                }
                if (mChildCategoryListEntities != null && mChildCategoryListEntities.size() != 0) {
                    if (mChildCategoryListEntities.size() > secondCategoryId) {
                        secondCategoryName = mChildCategoryListEntities.get(secondCategoryId).name;
                    }
                }

                // 获取省名称
                firstCategoryName = mFirstCategoryList.get(mFirstCategoryWheel.getCurrentItem());
                if (mChildCategoryListEntities.size() != 0 && mChildCategoryListEntities != null) {
                    // 获取城市名称
                    secondCategoryName = mScondeCategoryList.get(mSecondCategoryWheel.getCurrentItem());
                    if (mSelectInterface != null) {
                        mSelectInterface.selectCategory(firstCategoryName, secondCategoryName, mOrderCategories.get(firstCategoryId)
                                .childCategoryList.get(secondCategoryId).id);
                    }
                } else {
                    Tools.toast("请选择分类");
                }

                dismiss();
            }
        });
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isScroll) {
                    return;
                }
                dismiss();
            }
        });
        /**
         * 一级分类滑动
         */
        mFirstCategoryWheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                isScroll = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                isScroll = false;
                // 适配市
                int idProvince = mFirstCategoryWheel.getCurrentItem();
                mScondeCategoryList.clear();
                /*获取子分类数据源*/
                if (idProvince < mOrderCategories.size()) {
                    mChildCategoryListEntities = mOrderCategories.get(idProvince).childCategoryList;
                } else {
                    return;
                }
                /*构建子分类名称list集合*/
                for (OrderCategory.ChildCategoryListEntity townProtocol : mChildCategoryListEntities) {
                    mScondeCategoryList.add(townProtocol.name);
                }

                mSecondCategoryWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mScondeCategoryList));
                mSecondCategoryWheel.setCurrentItem(0);
                String minPrice = mChildCategoryListEntities.get(0).minPrice;
                String maxPrice = mChildCategoryListEntities.get(0).maxPrice;
                if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
                    mTvPrice.setText(Html.fromHtml(String.format("价格区间：<font color='#ff3366'>¥%s-¥%s</font>", minPrice, maxPrice)));
                } else {
                    mTvPrice.setText("未查询到价格区间");
                }

                // 适配县
                if (mChildCategoryListEntities.size() != 0 && mChildCategoryListEntities != null) {
                    mSecondCategoryWheel.setVisibility(View.VISIBLE);// 显示城市控件
                } else {
                    mSecondCategoryWheel.setVisibility(View.GONE);//隐藏城市控件
                }

            }

        });
        /**
         * 二级分类滑动
         */
        mSecondCategoryWheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                isScroll = true;

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                isScroll = false;
                int parentId = mFirstCategoryWheel.getCurrentItem();
                int childId = mSecondCategoryWheel.getCurrentItem();
                OrderCategory.ChildCategoryListEntity entity = null;
                if (mOrderCategories.size() < parentId && mOrderCategories.get(parentId).childCategoryList.size() < childId) {
                    entity = mOrderCategories.get(parentId).childCategoryList.get(childId);
                }
                if (entity != null) {
                    if (!TextUtils.isEmpty(entity.minPrice) && !TextUtils.isEmpty(entity.maxPrice)) {
                        mTvPrice.setText(Html.fromHtml(String.format("价格区间：<font color='#ff3366'>¥%s-¥%s</font>", entity.minPrice, entity.maxPrice)));
                    } else {
                        mTvPrice.setText("未查询到价格区间");
                    }
                }
            }
        });


    }

    // 加载市及县数据
    public void addData(List<OrderCategory> orderCategories) {
        this.mOrderCategories = orderCategories;
        //去掉最后三个数据 香港、澳门、台湾
        for (int i = 0; i < orderCategories.size(); i++) {
            OrderCategory data = orderCategories.get(i);
            mFirstCategoryList.add(data.name);//添加 省 集合
        }
        if (mFirstCategoryWheel != null && mFirstCategoryList != null && context != null) {
            mFirstCategoryWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mFirstCategoryList));
            mFirstCategoryWheel.setCurrentItem(0);//设置默认显示第三个
//                    addData();//加载 市 、县数据
        }
        //加载 市
        mScondeCategoryList.clear();
        // mScondeCategoryList.addAll();
        mChildCategoryListEntities = orderCategories.get(0).childCategoryList;
        cityCode = String.valueOf(mChildCategoryListEntities.get(0).id);
//        Log.i("------","---code 0---->{"+code+"}"+citrotocolList.get(0).name);

        for (OrderCategory.ChildCategoryListEntity townProtocol : mChildCategoryListEntities) {
            mScondeCategoryList.add(townProtocol.name);
        }
        mSecondCategoryWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mScondeCategoryList));
        mSecondCategoryWheel.setCurrentItem(0);
        if (orderCategories.size() > 0 && orderCategories.get(0).childCategoryList.size() > 0) {
            String minPrice = orderCategories.get(0).childCategoryList.get(0).minPrice;
            String maxPrice = orderCategories.get(0).childCategoryList.get(0).maxPrice;
            if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
                mTvPrice.setText(Html.fromHtml(String.format("价格区间：<font color='#ff3366'>¥%s-¥%s</font>", minPrice, maxPrice)));
            } else {
                mTvPrice.setText("未查询到价格区间");
            }
        }
    }

}
