package com.csmobile.csyd.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
//        helper.setText(R.id.tv_title, bean.staffNm);
        View ll_month_day = helper.getView(R.id.ll_month_day);
        View ll_up_down = helper.getView(R.id.ll_up_down);
//        if (bean.type_up == 0) {
//            ll_month_day.setVisibility(View.VISIBLE);
//            ll_up_down.setVisibility(View.INVISIBLE);
//        } else {
//            ll_month_day.setVisibility(View.INVISIBLE);
//            ll_up_down.setVisibility(View.VISIBLE);
//        }
//        if (bean.type_red == 0) {
//            helper.getView(R.id.tv_down).setVisibility(View.VISIBLE);
//            helper.getView(R.id.tv_up).setVisibility(View.INVISIBLE);
//            helper.setImageResource(R.id.im_updown_flag, R.mipmap.icon_arrow_down);
//        } else {
//            helper.getView(R.id.tv_down).setVisibility(View.INVISIBLE);
//            helper.getView(R.id.tv_up).setVisibility(View.VISIBLE);
//            helper.setImageResource(R.id.im_updown_flag, R.mipmap.icon_arrow_up);
//        }
    }
}
