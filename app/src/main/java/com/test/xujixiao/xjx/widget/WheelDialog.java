package com.test.xujixiao.xjx.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.tailorx.R;
import cn.tailorx.protocol.Area;
import cn.tailorx.protocol.CountyProtocol;
import cn.tailorx.protocol.ProvinceProtocol;
import cn.tailorx.protocol.TownProtocol;
import cn.tailorx.utils.Tools;
import cn.tailorx.widget.wheel_adapters.ArrayWheelAdapter;
import cn.tailorx.widget.wheel_widget.OnWheelScrollListener;
import cn.tailorx.widget.wheel_widget.WheelView;

/**
 * Created by User on 2016/8/17.
 * 轮滑选择器
 */
public class WheelDialog extends Dialog {

    private Context context;
    private WheelView mProvinceWheel;// 省份
    private WheelView mCityWheel;// 市
    private WheelView mDistrictWheel;// 县或区
    private boolean isScroll;

    private int provinceId, cityId, districtId;

    public String provinceCode;// 省份代码
    public String cityCode;// 城市代码
    public String countyCode;// 区县代码

    private String provinceName;// 省名称
    private String cityName = "";// 城市名称
    private String countyName = "";// 县区名称

    private List<TownProtocol> mTownProtocolList = null;//市集合
    private List<CountyProtocol> mCountyProtocolList = null;//县或区集合

    private ArrayList<String> mProvinceData = new ArrayList<>();// 省
    private ArrayList<String> mCityData = new ArrayList<>();// 市
    private ArrayList<String> mCountyData = new ArrayList<>();// 县或区
    private View view;
    private Area mArea;
    public SelectAddressInterface mSelectAddressInterface;

    public interface SelectAddressInterface {
        void selectAddress(String province, String city, String distrct);
    }

    //    private AddressManagePresenter mPresenter;
    public WheelDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public WheelDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    /**
     * 显示出选择地区对话框
     */
    public void init() {
        view = LayoutInflater.from(context).inflate(R.layout.wheel_dialog_layout, null);
        getWindow().setContentView(view);
        setTitle("请选择所在地区");
        mProvinceWheel = (WheelView) view.findViewById(R.id.wl_province);
        mCityWheel = (WheelView) view.findViewById(R.id.wl_city);
        mDistrictWheel = (WheelView) view.findViewById(R.id.wl_district_county);
        mProvinceWheel.setVisibleItems(5);
        mCityWheel.setVisibleItems(5);
        mDistrictWheel.setVisibleItems(5);
        mProvinceWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceData));
        mCityWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCityData));
        mDistrictWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCountyData));
        TextView confirmText = (TextView) view.findViewById(R.id.tv_confirm);
        TextView cancelText = (TextView) view.findViewById(R.id.tv_cancel);
        /*确认按钮*/
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isScroll) {
                    return;
                }
                provinceId = mProvinceWheel.getCurrentItem();
                cityId = mCityWheel.getCurrentItem();
                districtId = mDistrictWheel.getCurrentItem();
                if (mArea == null || mArea.returnList == null) {
                    return;
                }
                // 获取省code
                if (provinceCode != null) {
                    if (mArea.returnList.size() > provinceId) {
                        provinceCode = mArea.returnList.get(provinceId).code;
                    }
                }
                // 获取城市code
                if (mTownProtocolList.size() != 0 && mTownProtocolList != null) {
                    cityCode = mTownProtocolList.get(cityId).code;
                    // 获取县code
                    countyCode = mCountyProtocolList.get(districtId).code;
                }

                if (mArea.returnList.size() > provinceId) {
                    provinceName = mArea.returnList.get(provinceId).name;
                }
                if (mTownProtocolList != null && mTownProtocolList.size() != 0) {
                    if (mTownProtocolList.size() > cityId) {
                        cityName = mTownProtocolList.get(cityId).name;
                    }
                    if (mCountyProtocolList.size() > districtId) {
                        countyName = mCountyProtocolList.get(districtId).name;
                    }
                }

                // 获取省名称
                provinceName = mProvinceData.get(mProvinceWheel.getCurrentItem());
                if (mTownProtocolList.size() != 0 && mTownProtocolList != null) {
                    // 获取城市名称
                    cityName = mCityData.get(mCityWheel.getCurrentItem());
                    // 获取区县名称
                    if (mCountyData != null && mCountyData.size() > 0) {
                        countyName = mCountyData.get(mDistrictWheel.getCurrentItem());
                        String address = provinceName + cityName + countyName;
                        if (mSelectAddressInterface != null) {
                            mSelectAddressInterface.selectAddress(provinceName, cityName, countyName);
                        }
                    } else {
                        countyName = "";
                        countyCode = "";
                        Tools.toast("请选择区县!");
                        return;
                    }


                } else {
                    if (mSelectAddressInterface != null) {
                        mSelectAddressInterface.selectAddress(provinceName, "", "");
                    }
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
        // 省份 滑动监听
        mProvinceWheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                isScroll = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                isScroll = false;
                // 适配市
                int idProvince = mProvinceWheel.getCurrentItem();
                mCityData.clear();
                if (idProvince < mArea.returnList.size()) {
                    mTownProtocolList = mArea.returnList.get(idProvince).subArea;
                } else {
                    return;
                }
                for (TownProtocol townProtocol : mTownProtocolList) {
                    mCityData.add(townProtocol.name);
                }

                mCityWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCityData));
                mCityWheel.setCurrentItem(0);

                // 适配县
                if (mTownProtocolList.size() != 0 && mTownProtocolList != null) {
                    mCityWheel.setVisibility(View.VISIBLE);// 显示城市控件
                    mDistrictWheel.setVisibility(View.VISIBLE);// 显示县控件

                    int idCity = mCityWheel.getCurrentItem();

                    mCountyData.clear();// 清除县集合
                    mCountyProtocolList = mTownProtocolList.get(idCity).subArea;

                    for (CountyProtocol countyProtocol : mCountyProtocolList) {
                        mCountyData.add(countyProtocol.name);
                    }
                    mDistrictWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCountyData));
                    mDistrictWheel.setCurrentItem(0);
                } else {
                    mCityWheel.setVisibility(View.GONE);//隐藏城市控件
                    mDistrictWheel.setVisibility(View.GONE);//隐藏县控件
                }

            }

        });


        // 市 滑动监听
        mCityWheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                isScroll = true;

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                isScroll = false;
                mCountyData.clear();
                mDistrictWheel.invalidate();
                int id = mCityWheel.getCurrentItem();
                if (mTownProtocolList.get(id).subArea.size() != 0 && mTownProtocolList != null) {
                    mCountyProtocolList = mTownProtocolList.get(id).subArea;

//                    // 获取城市code
//                    code = mTownProtocolList.get(id).code;
//                    Log.i("------","---code Scrolling---->{"+code+"}"+mTownProtocolList.get(id).name);
//                    // 获取县code
//                    code = mCountyProtocolList.get(id).code;
//                    Log.i("------","---code Scrolling---->{"+code+"}"+mCountyProtocolList.get(id).name);


                    for (CountyProtocol countyProtocol : mCountyProtocolList) {
                        mCountyData.add(countyProtocol.name);
                    }

                    mDistrictWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCountyData));
                    mDistrictWheel.setCurrentItem(0);

                } else {

                }
            }
        });


        // 县或区 滑动监听
        mDistrictWheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                isScroll = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                isScroll = false;
//                int districtId = districtWheel.getCurrentItem();
//                 获取区县code
//                if(mCountyProtocolList!=null&&mCountyProtocolList.size()!=0){
//                    code = mCountyProtocolList.get(districtId).code;
//                    Log.i("------","---code Scrolling---->{"+code+"}"+mCountyProtocolList.get(districtId).name);
//                }
            }
        });

    }

    // 加载市及县数据
    public void addData(Area mArea) {
        this.mArea = mArea;
        //去掉最后三个数据 香港、澳门、台湾
        for (int i = 0; i < mArea.returnList.size() - 3; i++) {
            ProvinceProtocol data = mArea.returnList.get(i);
            mProvinceData.add(data.name);//添加 省 集合
        }
        if (mProvinceWheel != null && mProvinceData != null && context != null) {
            mProvinceWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceData));
            mProvinceWheel.setCurrentItem(0);//设置默认显示第三个
//                    addData();//加载 市 、县数据
        }
        //加载 市
        mCityData.clear();
        // mCityData.addAll();
        mTownProtocolList = mArea.returnList.get(0).subArea;

        cityCode = mTownProtocolList.get(0).code;
//        Log.i("------","---code 0---->{"+code+"}"+cityProtocolList.get(0).name);

        for (TownProtocol townProtocol : mTownProtocolList) {
            mCityData.add(townProtocol.name);
        }
        mCityWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCityData));
        mCityWheel.setCurrentItem(0);

        //加载 县
        mCountyData.clear();
        // mCountyData.addAll();
        mCountyProtocolList = mTownProtocolList.get(0).subArea;

        countyCode = mCountyProtocolList.get(0).code;
//        Log.i("------","---code 0---->{"+code+"}"+countyProtocolList.get(0).name);

        for (CountyProtocol countyProtocol : mCountyProtocolList) {
            mCountyData.add(countyProtocol.name);
        }
        mDistrictWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, mCountyData));
        mDistrictWheel.setCurrentItem(0);
    }

}
