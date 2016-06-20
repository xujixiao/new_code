package com.example.xujixiao.myapplication.common.adapter.base;

public interface TAdapterDelegate {

	public int getViewTypeCount();

    /*获取指定位置的viewholder对象*/
	public Class<? extends TViewHolder> viewHolderAtPosition(int position);

	public boolean enabled(int position);
}