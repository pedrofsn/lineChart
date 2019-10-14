package br.ufg.labtime.app.linechart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainTest extends AppCompatActivity {

    final int colorBackgroundChart = Color.parseColor("#121212");
    final int colorBabyBlueEyes = Color.parseColor("#0068ff");
    final int colorGridLines = Color.parseColor("#CC1A1A1A");
    final int colorAlmostWhite = Color.parseColor("#707070");
    final boolean showDrawGridLines = true;

    public LineChart lineChart;
//    protected Typeface tfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//          FULLSCREEN
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"); FIXME INSERIR A FONTE AQUI

        setContentView(R.layout.activity_main);
        setTitle("LineChartActivity1");

        //referencia
        lineChart = findViewById(R.id.chart1);
        //background
        lineChart.setBackgroundColor(colorBackgroundChart);

        // disable description text
        lineChart.getDescription().setEnabled(false);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        // FIXME -> dataset da API
        List<MyDataItem> items = new ArrayList<>();
        items.add(new MyDataItem("01/07", 6));
        items.add(new MyDataItem("10/07", 8));
        items.add(new MyDataItem("15/07", 10));
        items.add(new MyDataItem("20/07", 12));
        MyData myData = new MyData(items);


        //eixo X
        eixoX(lineChart.getXAxis(), myData);

        //eixo Y
        eixoY(lineChart.getAxisRight(), 12f);


        LineDataSet dataSet = entries(myData);

        //degrade
        drawFilled(dataSet);

        // Setting Data
        LineData data = new LineData(dataSet);
        lineChart.setData(data);

        // Remove labels
        lineChart.getLegend().setEnabled(false);

        //refresh
        lineChart.invalidate();
    }

    private void drawFilled(LineDataSet dataSet) {

        dataSet.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
            dataSet.setFillDrawable(drawable);
        } else {
            dataSet.setFillColor(colorBackgroundChart);
        }
    }

    private void eixoX(XAxis xAxis, MyData myData) {

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight); FIXME DESCOMENTE PARA FONTE
        xAxis.setTextSize(10f);
        xAxis.setTextColor(colorAlmostWhite);
        xAxis.setDrawAxisLine(true);

        //FIXME SE FALSE OCULTA LINHAS HORIZONTAIS
        xAxis.setDrawGridLines(showDrawGridLines);
        xAxis.setGranularityEnabled(true);
        xAxis.setGridColor(colorGridLines);
        xAxis.setGranularity(1f); // one hour

        final ArrayList<String> entries = new ArrayList<>();
        for (int x = 0; x < myData.getGrafico().size(); x++) {
            entries.add(myData.getGrafico().get(x).getData());
        }

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return entries.get((int) value);
            }
        };
        xAxis.setValueFormatter(formatter);
    }

    private void eixoY(YAxis yAxisRight, float maxValue) {

        yAxisRight.setDrawGridLines(showDrawGridLines);

        yAxisRight.setGridColor(colorGridLines);
        yAxisRight.setEnabled(false);

        // FIXME EIXO Y
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setTypeface(tfLight); FIXME DESCOMENTE PARA FONTE
        yAxisLeft.setTextColor(colorAlmostWhite);

        //FIXME SE FALSE OCULTA LINHAS VERTICAIS
        yAxisLeft.setDrawGridLines(showDrawGridLines);
        yAxisLeft.setGridColor(colorGridLines);
        yAxisLeft.setGranularityEnabled(true);

        //FIXME VALORES MAX E MIN DO EIXO Y
        yAxisLeft.setAxisMinimum(1f);
        yAxisLeft.setAxisMaximum(maxValue);
        yAxisLeft.setYOffset(-9f);
    }

    private LineDataSet entries(MyData myData) {

        ArrayList<Entry> entries = new ArrayList<>();
        for (int x = 0; x < myData.getGrafico().size(); x++) {
            entries.add(new Entry(x, myData.getGrafico().get(x).getValor()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(colorBabyBlueEyes);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setValueTextColor(colorAlmostWhite);
        dataSet.setLineWidth(1.5f);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setFillAlpha(65);
//        dataSet.setFillColor(ColorTemplate.getHoloBlue());
//        dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setDrawCircleHole(false);

        return dataSet;
    }

}
