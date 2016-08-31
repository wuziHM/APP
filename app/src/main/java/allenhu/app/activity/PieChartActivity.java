package allenhu.app.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ParagraphStyle;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import allenhu.app.R;
import allenhu.app.base.BaseActivity;
import allenhu.app.util.LogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity implements OnChartValueSelectedListener {

    @Bind(R.id.pie_chart)
    PieChart pieChart;

    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        initView();
    }

    private void initView() {
//
        setData(4, 100);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDescription("这是个什么鬼");
        pieChart.setDescriptionColor(Color.parseColor("#00ff00"));
//        pieChart.setExtraTopOffset(20f);
//        pieChart.setDescriptionPosition(pieChart.getCenterOfView().x, 10);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
//        //开始加载的时候的圆环的动画 开始角度
        pieChart.setRotationAngle(0);
        //每一部分的文字
        pieChart.setDrawEntryLabels(false);

        //加载出来的时候的动画
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

//        mSeekBarX.setOnSeekBarChangeListener(this);
//        mSeekBarY.setOnSeekBarChangeListener(this);
        Legend l = pieChart.getLegend();
//        l.setEnabled(false);
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l.setForm(Legend.LegendForm.LINE);
//        l.setXEntrySpace(5f);
//        l.setYEntrySpace(0f);
//        l.setXOffset(0f);


    }


    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        //设置每一块的边距
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f); // 选中态多出的长度


        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //每一部分上的百分比数量
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        LogUtil.e(
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

}
