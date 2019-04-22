package com.csmobile.csyd.ui.adapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseApplication;
import com.csmobile.csyd.model.bean.BeanFndicator;
import com.csmobile.csyd.ui.activitys.IndexDetailsActivity;
import com.csmobile.csyd.utils.LogUtils;
import com.csmobile.csyd.utils.StringUtils;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class Adapter_Fndicator extends BaseQuickAdapter<BeanFndicator, BaseViewHolder> {
    public Adapter_Fndicator(int layoutResId) {
        super(layoutResId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, BeanFndicator bean) {
//        helper.setText(R.id.tv_title, bean.staffNm);
        View ll_month_day = helper.getView(R.id.ll_month_day);
        View ll_up_down = helper.getView(R.id.ll_up_down);
        View im_progress = helper.getView(R.id.im_progress);
        View tv_month = helper.getView(R.id.tv_month);
        View ll_paraent = helper.getView(R.id.ll_paraent);
        helper.setText(R.id.tv_title, bean.quotaNm);
        helper.setText(R.id.tv_topnub, bean.dayData);
        helper.setText(R.id.tv_rank, "" + bean.rank);
        helper.setText(R.id.tv_dayneed, "" + bean.monthData);
        helper.setText(R.id.tv_dayupratio, bean.historyData);
        helper.setText(R.id.tv_monthupratio, bean.goalData);
        helper.setText(R.id.tv_cpratio, StringUtils.getTwoPoint(bean.cpRatio * 100) + "%");
        helper.setText(R.id.tv_month, StringUtils.getTwoPoint(bean.timeLeadRate * 100) + "%");
        if (bean.timeLeadRate <= 0) {
            setProgress(im_progress, bean.cpRatio, (BaseApplication.getInstance().getWith() - StringUtils.dp2px(40)) / 2);
            im_progress.setBackground(mContext.getDrawable(R.drawable.bg_green_pro));
            helper.setTextColor(R.id.tv_month, mContext.getResources().getColor(R.color.color_green));
            helper.setTextColor(R.id.tv_cpratio, mContext.getResources().getColor(R.color.color_green));
            helper.setTextColor(R.id.tv_topnub, mContext.getResources().getColor(R.color.color_green));
        } else {
            im_progress.setBackground(mContext.getDrawable(R.drawable.bg_red_pro));
            setProgress(im_progress, bean.cpRatio, (BaseApplication.getInstance().getWith() - StringUtils.dp2px(40)) / 2);
            helper.setTextColor(R.id.tv_month, mContext.getResources().getColor(R.color.color_red));
            helper.setTextColor(R.id.tv_cpratio, mContext.getResources().getColor(R.color.color_red));
            helper.setTextColor(R.id.tv_topnub, mContext.getResources().getColor(R.color.color_red));
        }

        if (bean.rank <= 10) {
            helper.setTextColor(R.id.tv_rank, mContext.getResources().getColor(R.color.color_white));
        } else {
            helper.setTextColor(R.id.tv_rank, mContext.getResources().getColor(R.color.color_white));
        }
        ll_paraent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, IndexDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void setProgress(View view, double proress, int with) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = (int) (proress * with);
        view.setLayoutParams(params);

    }
}
