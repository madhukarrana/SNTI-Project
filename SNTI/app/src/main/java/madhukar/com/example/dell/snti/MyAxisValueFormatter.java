package madhukar.com.example.dell.snti;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private String[] mFormat;

    public MyAxisValueFormatter(String[] format) {
        mFormat = format;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat[(int)value];
    }
}
