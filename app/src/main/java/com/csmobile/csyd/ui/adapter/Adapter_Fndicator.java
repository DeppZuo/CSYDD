package com.csmobile.csyd.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csmobile.csyd.R;
import com.csmobile.csyd.model.bean.BeanFndicator;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class Adapter_Fndicator extends BaseQuickAdapter<BeanFndicator, BaseViewHolder> {
    public Adapter_Fndicator(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BeanFndicator bean) {
        helper.setText(R.id.tv_title, bean.title);
    }
}
