package com.targetsmile.sugarintaketracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.targetsmile.sugarintaketracker.R;

public class HowToReduce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_reduce);

        TextView reduceSugar = (TextView) findViewById(R.id.reduceSugarTextView);
        int resourceid = getResourceId("reduce_sugar_tips", "string", "com.targetsmile.sugarintaketracker");

        String contentToShow = getString(resourceid);
        reduceSugar.setText(Html.fromHtml(contentToShow));
    }


    public int getResourceId(String pVariableName, String pResourcename, String pPackageName) {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
