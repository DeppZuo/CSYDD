package com.csmobile.csyd.ui.adapter;

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
        View im_bg_progress = helper.getView(R.id.im_bg_progress);
        helper.setText(R.id.tv_title, bean.quotaNm);
        helper.setText(R.id.tv_topnub, bean.dayData);
        helper.setText(R.id.tv_rank, "" + bean.rank);
        helper.setText(R.id.tv_dayneed, "" + bean.monthData);
        helper.setText(R.id.tv_dayupratio, bean.historyData);
        helper.setText(R.id.tv_monthupratio, bean.goalData);
        helper.setText(R.id.tv_cpratio, "任务率 " + bean.cpRatio + "%");
        helper.setText(R.id.tv_month, bean.timeLeadRate+ "%");
        if(bean.timeLeadRate.contains("-")){
            setProgress(im_progress, StringUtils.strtodouble(bean.cpRatio),(BaseApplication.getInstance().getWith()-StringUtils.dp2px(40))/2);
            im_progress.setBackground(mContext.getDrawable(R.drawable.bg_green_pro));
        }else {
            im_progress.setBackground(mContext.getDrawable(R.drawable.bg_red_pro));
            setProgress(im_progress, 100,(BaseApplication.getInstance().getWith()-StringUtils.dp2px(40))/2);
        }

        if (bean.rank <= 10) {
            helper.setTextColor(R.id.tv_rank, mContext.getResources().getColor(R.color.color_red));
        } else {
            helper.setTextColor(R.id.tv_rank, mContext.getResources().getColor(R.color.color_green));
        }
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

    private void setProgress(View view, double proress, int with) {
        LogUtils.i("with===="+with);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = (int) (proress*with);
        view.setLayoutParams(params);

    }
}
