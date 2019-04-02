package com.csmobile.csyd.ui.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.base.XFragmentAdapter;
import com.csmobile.csyd.present.PMainActivity;
import com.csmobile.csyd.ui.fragments.Fragment_Fndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<PMainActivity> {
    @BindView(R.id.ll_state_bar)
    View mLlStateBar;
    @BindView(R.id.processplan_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.processplan_viewPager)
    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"所有指标", "本月重要指标"};
    XFragmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public PMainActivity newP() {
        return new PMainActivity();
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatusBarLinearLayout(mLlStateBar);
        fragmentList.clear();
        fragmentList.add(Fragment_Fndicator.newInstance(""));
        fragmentList.add(Fragment_Fndicator.newInstance(""));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }
}
