package com.csmobile.csyd.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseFragment;
import com.csmobile.csyd.model.bean.BeanFndicator;
import com.csmobile.csyd.model.response.Fndicator_Res;
import com.csmobile.csyd.present.PFndicator;
import com.csmobile.csyd.ui.activitys.MainActivity;
import com.csmobile.csyd.ui.adapter.Adapter_Fndicator;
import com.csmobile.csyd.utils.ToastUtils;
import com.csmobile.csyd.views.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/2<p>
 */
public class Fragment_Fndicator extends BaseFragment<PFndicator> {
    @BindView(R.id.processplan_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.processplan_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.processplan_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private Adapter_Fndicator adapter_fndicator;
    private MainActivity mainActivity;
    public static Fragment_Fndicator newInstance(String type) {
        Fragment_Fndicator fragment = new Fragment_Fndicator();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        adapter_fndicator = new Adapter_Fndicator(R.layout.item_fndicator);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerview.setAdapter(adapter_fndicator);
        List<BeanFndicator> listdata = new ArrayList<BeanFndicator>();

        recyclerview.setPage(10, 30);//必须设置setPage，否则上拉加载更多会无效
        adapter_fndicator.setNewData(listdata);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore(int page) {
                ToastUtils.showToast("加载更多");
            }
        });
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ToastUtils.showToast("刷新");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //execute the task
                        swiperefreshlayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        getP().getData("13973198515");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fndicator;
    }

    @Override
    public PFndicator newP() {
        return new PFndicator();

    }

    public void showToast(String msg) {
        getvDelegate().dismissLoading();
        getvDelegate().toastShort(msg);
    }

    public void setData(Fndicator_Res fndicatorRes) {
        adapter_fndicator.setNewData(fndicatorRes.list);
        mainActivity.setTopdata(fndicatorRes);
    }
}
