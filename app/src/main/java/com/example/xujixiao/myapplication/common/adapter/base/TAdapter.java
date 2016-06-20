package com.example.xujixiao.myapplication.common.adapter.base;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用adapter可以包含多种类型的viewholder
 *
 * @param <T>
 */
public class TAdapter<T> extends BaseAdapter implements IViewReclaimer {

    protected final Context context;

    private final List<T> items;

    private final TAdapterDelegate delegate;

    private final LayoutInflater inflater;

    private final Map<Class<?>, Integer> viewTypes;

    private Object tag;

    private boolean mutable;

    private Set<IScrollStateListener> listeners;

    public TAdapter(Context context, List<T> items, TAdapterDelegate delegate) {
        this.context = context;
        this.items = items;
        this.delegate = delegate;
        this.inflater = LayoutInflater.from(context);
        this.viewTypes = new HashMap<Class<?>, Integer>(getViewTypeCount());
        this.listeners = new HashSet<IScrollStateListener>();
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    public T getItem(int position) {
        return position < getCount() ? items.get(position) : null;
    }

    public long getItemId(int position) {
        return position;
    }

    public boolean isEnabled(int position) {
        return delegate.enabled(position);
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, true);
    }

    public View getView(final int position, View convertView, ViewGroup parent, boolean needRefresh) {
        if (convertView == null) {
            convertView = viewAtPosition(position);
        }
        TViewHolder holder = (TViewHolder) convertView.getTag();
        holder.setPosition(position);
        if (needRefresh) {
            try {
                holder.refresh(getItem(position));
            } catch (RuntimeException e) {
                Log.e("TAdapter", "refresh viewholder error. " + e.getMessage());
            }
        }
        if (holder instanceof IScrollStateListener) {
            listeners.add(holder);
        }
        return convertView;
    }

    /**
     * 获取当前的adapter有几种类型的viewholder
     */
    @Override
    public int getViewTypeCount() {
        return delegate.getViewTypeCount();
    }

    /*获取类型item对应位置的view是哪种类型的holder*/
    @Override
    public int getItemViewType(int position) {
        if (getViewTypeCount() == 1) {
            return 0;
        }
        /*如果类型已经存在*/
        Class<?> clazz = delegate.viewHolderAtPosition(position);
        /*根据viewholder的类型，获取map集合记录的int 类型*/
        if (viewTypes.containsKey(clazz)) {
            return viewTypes.get(clazz);
        } else {
            /*如果类型没有保存，*/
            int type = viewTypes.size();
            if (type < getViewTypeCount()) {
                viewTypes.put(clazz, type);
                return type;
            }
            return 0;
        }
    }

    @Override
    public void reclaimView(View view) {
        /*view 回收*/
        if (view == null) {
            return;
        }

        TViewHolder holder = (TViewHolder) view.getTag();
        if (holder != null) {
            holder.reclaim();
            listeners.remove(holder);
        }
    }

    public void onMutable(boolean mutable) {
        boolean becomeImmutable = this.mutable && !mutable;
        this.mutable = mutable;
        if (becomeImmutable) {
            for (IScrollStateListener listener : listeners) {
                listener.onImmutable();
            }
            listeners.clear();
        }
    }

    public boolean isMutable() {
        return mutable;
    }

    /**
     * 获取制定位置的对应view
     *
     * @param position
     * @return
     */
    public View viewAtPosition(int position) {
        TViewHolder holder = null;
        View view = null;
        try {
            Class<?> viewHolder = delegate.viewHolderAtPosition(position);
            holder = (TViewHolder) viewHolder.newInstance();
            holder.setAdapter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view = holder.getView(inflater);
        view.setTag(holder);
        holder.setContext(view.getContext());
        return view;
    }

    /**
     * 设置tag标记方便其他特殊用处
     *
     * @return
     */
    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
