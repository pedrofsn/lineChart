
package br.ufg.labtime.app.linechart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

/**
 * Example of a heavily customized {@link LineChart} with limit lines, custom line shapes, etc.
 *
 * @version 3.1.0
 * @since 1.7.4
 */
public class MainActivity extends AppCompatActivity {

    public LineChart lineChart;
//    protected Typeface tfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setTitle("LineChartActivity1");

//        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"); FIXME INSERIR A FONTE AQUI

        lineChart = findViewById(R.id.chart1);
        lineChart.setBackgroundColor(Color.DKGRAY); //FIXME COR DO GRÁFICO
        // disable description text
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setTextColor(Color.WHITE);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        //Values X and Y
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 3));
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 5));
        entries.add(new Entry(3, 7));
        entries.add(new Entry(4, 8));

        LineDataSet dataSet = new LineDataSet(entries, "Name lineChart");
        dataSet.setColor(Color.BLUE);
//        dataSet.setValueTextColor(Color.WHITE);
//        dataSet.setMode(LineDataSet.Mode.LINEAR);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//        dataSet.setColor(ColorTemplate.getHoloBlue());
        dataSet.setValueTextColor(ColorTemplate.getHoloBlue());
        dataSet.setLineWidth(1.5f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setFillAlpha(65);
        dataSet.setFillColor(ColorTemplate.getHoloBlue());
        dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setDrawCircleHole(false);

        //FIXME descomente para inserir o degrade abaixo da linha

        dataSet.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
            dataSet.setFillDrawable(drawable);
        }
        else {
            dataSet.setFillColor(Color.BLACK);
        }
        dataSet.setDrawCircles(false);


        //****
        // X axis
        XAxis xAxis = lineChart.getXAxis();
        // position to bottom. Default is top
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
//        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight); FIXME INSERIR A FONTE AQUI
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
//        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour

        //x axis value
        final String[] days = new String[]{"07/05", "14/05", "30/05", "03/06", "12/06", "19/08"};

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return days[(int) value];
            }
        };
        xAxis.setValueFormatter(formatter);

        //***
        //right side of y axis
        YAxis yAxisRight = lineChart.getAxisRight();
//        yAxisRight.setDrawGridLines(true);
        yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        leftAxis.setTypeface(tfLight); FIXME INSERIR A FONTE AQUI
        yAxisLeft.setTextColor(ColorTemplate.getHoloBlue());
        yAxisLeft.setDrawGridLines(true);
        yAxisLeft.setGranularityEnabled(true);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(15f);
        yAxisLeft.setYOffset(-9f);
        yAxisLeft.setTextColor(Color.rgb(255, 192, 56));

        // Setting Data
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.animateX(1000);
        //refresh
        lineChart.invalidate();
    }

}
