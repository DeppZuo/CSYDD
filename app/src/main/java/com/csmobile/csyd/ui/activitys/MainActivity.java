package com.csmobile.csyd.ui.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.base.XFragmentAdapter;
import com.csmobile.csyd.model.response.CheckVersionResults;
import com.csmobile.csyd.present.PMainActivity;
import com.csmobile.csyd.ui.fragments.Fragment_Fndicator;
import com.csmobile.csyd.utils.StringUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import rx.functions.Action1;

public class MainActivity extends BaseActivity<PMainActivity> {
    @BindView(R.id.ll_state_bar)
    View mLlStateBar;
    @BindView(R.id.processplan_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.processplan_viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_chlnum)
    TextView tv_chlnum;//渠道数字
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
}
