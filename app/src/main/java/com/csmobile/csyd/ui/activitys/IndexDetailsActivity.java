package com.csmobile.csyd.ui.activitys;

import android.os.Bundle;
import android.view.View;

import com.csmobile.csyd.R;
import com.csmobile.csyd.base.BaseActivity;
import com.csmobile.csyd.present.PIndexDetails;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>文件描述：<p>
 * <p>作者：zuowenhua<p>
 * <p>创建时间：2019/4/22<p>
 */
public class IndexDetailsActivity extends BaseActivity<PIndexDetails> {
    @BindView(R.id.index_chart)
    LineChart index_chart;
    @BindView(R.id.ll_state_bar)
    View mLlStateBar;

    @Override
    public void bindEvent() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatusBarLinearLayout(mLlStateBar);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            entries.add(new Entry(i, (float) (Math.random()) * 180));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        lineDataSet.setValueTextColor(getResources().getColor(R.color.color_white));
        lineDataSet.setDrawValues(false);
        lineDataSet.setColor(getResources().getColor(R.color.color_red));
        lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.fade_red));
        lineDataSet.setDrawFilled(true);
        lineDataSet.setCircleColor(getResources().getColor(R.color.color_red));
        LineData data = new LineData(lineDataSet);


        index_chart.setData(data);
        index_chart.setTouchEnabled(false);
        index_chart.setBorderColor(R.color.color_white);
        index_chart.setDrawGridBackground(false);

        XAxis xAxis = index_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.color_white));
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(12, true);

        YAxis yAxis = index_chart.getAxisLeft();
        yAxis.setTextColor(getResources().getColor(R.color.color_white));
        yAxis.setDrawGridLines(true);
        yAxis.enableGridDashedLine(10f, 10f, 0f);

        YAxis yAxis_right = index_chart.getAxisRight();
        yAxis_right.setEnabled(false);
        yAxis_right.setDrawGridLines(false);

        Legend legend = index_chart.getLegend();
        legend.setEnabled(false);

        Description description = index_chart.getDescription();
        description.setText("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.indexdetailsactivity;
    }

    @Override
    public PIndexDetails newP() {
        return new PIndexDetails();
    }
}
