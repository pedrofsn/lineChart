
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

//          FULLSCREEN
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setTitle("LineChartActivity1");

//        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"); FIXME INSERIR A FONTE AQUI

        int colorBackgroundChart = Color.parseColor("#121212");
        int colorBabyBlueEyes = Color.parseColor("#0068ff");
        int colorGridLines = Color.parseColor("#CC1A1A1A");
        int colorAlmostWhite = Color.parseColor("#707070");
        boolean showDrawGridLines = true;

        lineChart = findViewById(R.id.chart1);
        lineChart.setBackgroundColor(colorBackgroundChart);

        // disable description text
        lineChart.getDescription().setEnabled(false);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);

        //FIXME VALORES DA LINHA PRINCIPAL DO GRÁFICO (EIXO X, EIXO Y)
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 3));
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 5));
        entries.add(new Entry(3, 7));
        entries.add(new Entry(4, 8));

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

        //FIXME DESCOMENTE PARA INSERIR O DEGRADE ABAIXO DA LINHA
        dataSet.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
            dataSet.setFillDrawable(drawable);
        } else {
            dataSet.setFillColor(colorBackgroundChart);
        }

        // FIXME EIXO X
        XAxis xAxis = lineChart.getXAxis();
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

        //FIXME VALORES DO EIXO X
        YAxis rightAxis = lineChart.getAxisRight();
        final String[] days = new String[]{"07/05", "14/05", "30/05", "03/06", "12/06", "19/08"};

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return days[(int) value];
            }
        };
        xAxis.setValueFormatter(formatter);

        //right side of y axis
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setDrawGridLines(showDrawGridLines);
        yAxisRight.setGridColor(colorGridLines);
        yAxisRight.setEnabled(false);

        // FIXME EIXO Y
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
//        leftAxis.setTypeface(tfLight); FIXME DESCOMENTE PARA FONTE
        yAxisLeft.setTextColor(colorAlmostWhite);

        //FIXME SE FALSE OCULTA LINHAS VERTICAIS
        yAxisLeft.setDrawGridLines(showDrawGridLines);
        yAxisLeft.setGridColor(colorGridLines);
        yAxisLeft.setGranularityEnabled(true);

        //FIXME VALORES MAX E MIN DO EIXO Y
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(15f);
        yAxisLeft.setYOffset(-9f);

        // Setting Data
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.animateX(1000);

        // Remove labels
        lineChart.getLegend().setEnabled(false);

        //refresh
        lineChart.invalidate();
    }

}
