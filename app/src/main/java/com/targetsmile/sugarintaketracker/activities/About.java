package com.targetsmile.sugarintaketracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.apptentive.android.sdk.Apptentive;
import com.targetsmile.sugarintaketracker.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView reduceSugar = (TextView) findViewById(R.id.aboutUsTextview);
        int resourceid = getResourceId("about_us", "string", "com.targetsmile.sugarintaketracker");

        String contentToShow = getString(resourceid);
        reduceSugar.setText(Html.fromHtml(contentToShow));


    }


    public void onClickSuggestionBox(View view)

    {
        Apptentive.showMessageCenter(About.this);
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
