package com.targetsmile.sugarintaketracker;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HistoryGraph extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    ArrayList<String> xAxis = new ArrayList<>();
    ArrayList<IBarDataSet> dataSets = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);

        setContentView(R.layout.activity_history_graph);
        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(xAxis, getDataSet());
        chart.setData(data);
        chart.setDescription("Weekly Tracker");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private List<IBarDataSet> getDataSet() {


        Cursor cursor = databaseHelper.getHistoricData();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();


        int counter = 0;
        if(cursor.getCount() == 0)
        {

        }
        else {

            while(cursor.moveToNext())
            {
                xAxis.add(cursor.getString(0));
                BarEntry v1e1 = new BarEntry(Float.parseFloat(cursor.getString(1)), counter); // Jan
                valueSet1.add(v1e1);

                counter++;
            }

        }


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Daily Sugar Intake");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }





}


