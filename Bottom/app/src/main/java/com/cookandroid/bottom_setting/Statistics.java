package com.cookandroid.bottom_setting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.statistics, container, false);

        // 그래프 종류 설정
        BarChart barchart = (BarChart) view.findViewById(R.id.chart);

        // 그래프에 넣을 점 설정
        // 후에는 데이터베이스에서 받아온 정보를 이용하여 추가할 예정
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1));
        entries.add(new BarEntry(1, 3));
        entries.add(new BarEntry(2, 2));
        entries.add(new BarEntry(3, 1));
        entries.add(new BarEntry(4, 4));
        entries.add(new BarEntry(5, 0));
        entries.add(new BarEntry(6, 2));

        // 세로축 설정
        BarDataSet dataset = new BarDataSet(entries, "달성 목표 수");
        dataset.setAxisDependency(YAxis.AxisDependency.RIGHT);

        // 차트에 데이터 입력
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataset);
        barchart.setData(data);
        barchart.setDrawGridBackground(false);

        // 애니메이션 설정
        // 설정시 그래프가 한번에 나타나는 것이 아니라 창 이동시 그래프의 선이 제자리 찾아가면서 나타남
        barchart.animateY(1500);
        barchart.invalidate();

        return view;
    }
}