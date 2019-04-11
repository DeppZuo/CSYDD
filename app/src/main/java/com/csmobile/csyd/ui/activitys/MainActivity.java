package com.csmobile.csyd.ui.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.base.XFragmentAdapter;
import com.csmobile.csyd.model.response.CheckVersionResults;
import com.csmobile.csyd.model.response.Fndicator_Res;
import com.csmobile.csyd.present.PMainActivity;
import com.csmobile.csyd.ui.fragments.Fragment_Fndicator;
import com.csmobile.csyd.utils.StringUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<PMainActivity> {
    @BindView(R.id.ll_state_bar)
    View mLlStateBar;
    @BindView(R.id.processplan_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.processplan_viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_gridnm)
    TextView tv_gridnm;
    @BindView(R.id.tv_chlnum)
    TextView tv_chlnum;
    @BindView(R.id.tv_statnum)
    TextView tv_statnum;
    @BindView(R.id.tv_statusernum)
    TextView tv_statusernum;
    @BindView(R.id.tv_groupnum)
    TextView tv_groupnum;
    @BindView(R.id.tv_grpusernum)
    TextView tv_grpusernum;
    @BindView(R.id.tv_todayin)
    TextView tv_todayin;
    @BindView(R.id.tv_commnum)
    TextView tv_commnum;
    @BindView(R.id.tv_monthin)
    TextView tv_monthin;
    @BindView(R.id.tv_lastmonthin)
    TextView tv_lastmonthin;
    @BindView(R.id.tv_monthshare)
    TextView tv_monthshare;
    @BindView(R.id.tv_lastmonthshare)
    TextView tv_lastmonthshare;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"劳动竞赛", "本月重要"};
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
        fragmentList.add(Fragment_Fndicator.newInstance("ALL"));
        fragmentList.add(Fragment_Fndicator.newInstance("MONTH"));
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

    private UIData crateUIData(CheckVersionResults results) {
        UIData uiData = UIData.create();
        uiData.setTitle("版本更新");
        uiData.setDownloadUrl(results.downloadUrl);
        String centent = results.appDesc;
        if (!StringUtils.isNull(centent)) {
            centent = centent.replace("\\n", "\n");
        }
        uiData.setContent(centent);
        return uiData;
    }

    private RxPermissions rxPermissions;

    @SuppressLint("CheckResult")
    public void update(final CheckVersionResults results) {
        getvDelegate().dismissLoading();
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(this);
        }
        rxPermissions.requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户允许权限
                    DownloadBuilder builder = AllenVersionChecker
                            .getInstance()
                            .downloadOnly(crateUIData(results))
                            .setShowNotification(false);
                    if ("1".equals(results.updateType)) {//强制更新
                        builder.setForceUpdateListener(new ForceUpdateListener() {
                            @Override
                            public void onShouldForceUpdate() {
                            }
                        });
                    }
                    builder.setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel() {
//                                        showToast("取消");
                        }
                    });
                    builder.executeMission(MainActivity.this);
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了权限申请
                    update(results);
                } else {
                    /// 用户拒绝，并且选择不再提示
                    // 可以引导用户进入权限设置界面开启权限
                    showToast("设备信息获取失败,请开启设备信息权限");
                    gotoSetting();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        });

    }

    public void setTopdata(Fndicator_Res fndicatorRes) {
/*
            设置头部数据
             */
        tv_chlnum.setText(fndicatorRes.chlNum);
        tv_gridnm.setText(fndicatorRes.gridNm);
        tv_groupnum.setText(fndicatorRes.grpNum);
        tv_grpusernum.setText(fndicatorRes.grpUserNum);
        tv_lastmonthshare.setText(fndicatorRes.lastMonthShare);
        tv_lastmonthin.setText(fndicatorRes.lastMonthIn);
        tv_monthin.setText(fndicatorRes.monthIn);
        tv_monthshare.setText(fndicatorRes.monthShare);
        tv_statnum.setText(fndicatorRes.statNum);
        tv_todayin.setText(fndicatorRes.todayIn);
        tv_name.setText(fndicatorRes.staffNm);
        tv_commnum.setText(fndicatorRes.commNum);
        tv_statusernum.setText(fndicatorRes.statUserNum);
        tv_time.setText(StringUtils.formatDate4(fndicatorRes.today));
               /*
            设置头部数据
             */
    }
}
