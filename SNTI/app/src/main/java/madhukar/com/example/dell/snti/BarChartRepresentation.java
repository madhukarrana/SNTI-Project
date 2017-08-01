package madhukar.com.example.dell.snti;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;


public class BarChartRepresentation extends Fragment implements OnChartValueSelectedListener,AdapterView.OnItemSelectedListener{

    private Spinner order;
    private String currentOrder;
    private BarChart mChart;

    String[] yearDataXaxis= {"2017"};
    String[] branchDataXaxis =new String[ChartRepresentation.branchData.size()];
    String[] instituteDataXaxis =new String[ChartRepresentation.instituteData.size()];
    String[] guideDataXaxis =new String[ChartRepresentation.guideDataSet.size()];

    public BarChartRepresentation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_bar_chart_representation, container, false);

        //year data xaxis
/*        int i=0;
        for (Map.Entry m : ChartRepresentation.yearData.entrySet())
        {
            yearDataXaxis[i]=m.getKey().toString();
            i++;
        }
        i=0;
        for (Map.Entry m : ChartRepresentation.branchData.entrySet())
        {
            branchDataXaxis[i]=m.getKey().toString();
            i++;
        }
       i=0;
        for (Map.Entry m : ChartRepresentation.instituteData.entrySet())
        {
            instituteDataXaxis[i]=m.getKey().toString();
            i++;
        }
        i=0;
        for (Map.Entry m : ChartRepresentation.guideDataSet.entrySet())
        {
            guideDataXaxis[i]=m.getKey().toString();
            i++;
        }

   */
        order=(Spinner)getActivity().findViewById(R.id.chartOrder);
        currentOrder=order.getSelectedItem().toString();

        order.setOnItemSelectedListener(this);

        mChart=(BarChart)v.findViewById(R.id.barChartRepresentation);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
         Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        if(currentOrder.equals("YEAR"))
        {
            setYearData();
        }
        else if(currentOrder.equals("BRANCH"))
        {
            setBranchData();
        }
        else if(currentOrder.equals("INSTITUTE"))
        {
            setInstituteData();
        }
        else if(currentOrder.equals("GUIDE")){
            setGuideDataSet();
        }



        return v;
    }

    private void setYearData()
    {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        int i=0;
        for (Map.Entry m : ChartRepresentation.yearData.entrySet())
        {
            entries.add(new BarEntry(i,(int)m.getValue()));
            i++;
        }

        BarDataSet set = new BarDataSet(entries, "YEAR DATA");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        set.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set);



        BarData data = new BarData(dataSets);
           data.setValueTextSize(10f);
           // data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);
            mChart.setData(data);
            mChart.setFitBars(true); // make the x-axis fit exactly all bars
            mChart.invalidate();
/*
        IAxisValueFormatter formatter = new MyAxisValueFormatter(yearDataXaxis);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

 */
        }


    private void setBranchData()
    {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        int i=0;
        for (Map.Entry m : ChartRepresentation.branchData.entrySet())
        {
            entries.add(new BarEntry(i,(int)m.getValue()));
            i++;
        }

        BarDataSet set = new BarDataSet(entries, "BRANCH DATA");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        set.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);
        mChart.setData(data);
        mChart.setFitBars(true); // make the x-axis fit exactly all bars
        mChart.invalidate();

    /*    IAxisValueFormatter formatter = new MyAxisValueFormatter(branchDataXaxis);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        */

    }
    private void setInstituteData()
    {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        int i=0;
        for (Map.Entry m : ChartRepresentation.instituteData.entrySet())
        {
            entries.add(new BarEntry(i,(int)m.getValue()));
            i++;
        }

        BarDataSet set = new BarDataSet(entries, "INSTITUTE DATA");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        set.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);
        mChart.setData(data);
        mChart.setFitBars(true); // make the x-axis fit exactly all bars
        mChart.invalidate();

    /*    IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return instituteDataXaxis[(int) value];
            }
        };

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    */

    }
    private void setGuideDataSet()
    {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        int i=0;
        for (Map.Entry m : ChartRepresentation.guideDataSet.entrySet())
        {
            entries.add(new BarEntry(i,(int)m.getValue()));
            i++;
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        set.setColors(colors);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);
        mChart.setData(data);
        mChart.setFitBars(true); // make the x-axis fit exactly all bars
        mChart.invalidate();

     /*   IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return guideDataXaxis[(int) value];
            }
        };

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position)
        {
            case 0:
                setYearData();
                break;
            case 1:
                setBranchData();
                break;
            case 2:
                setInstituteData();
                break;
            case 3:
                setGuideDataSet();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
