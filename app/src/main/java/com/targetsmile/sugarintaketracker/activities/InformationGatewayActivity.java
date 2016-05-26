package com.targetsmile.sugarintaketracker.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.targetsmile.sugarintaketracker.HistoryGraph;
import com.targetsmile.sugarintaketracker.MainTrackerActivity;
import com.targetsmile.sugarintaketracker.R;

import java.util.ArrayList;

public class InformationGatewayActivity extends AppCompatActivity {

    ListView startListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_gateway);


        startListView = (ListView) findViewById(R.id.startListView);
        ArrayList<String> itemsToClickToStart = new ArrayList<String>();

        itemsToClickToStart.add("Track Sugar Consumption");

        itemsToClickToStart.add("Sugar Calculations");
        itemsToClickToStart.add("Reduce Sugar");
        itemsToClickToStart.add("History");
        itemsToClickToStart.add("About / Suggestions");


        ArrayList<String> itemDescriptionToClick = new ArrayList<String>();

        itemDescriptionToClick.add("Track added sugar in your food");
        itemDescriptionToClick.add("How much added sugar does your food have");
        itemDescriptionToClick.add("What does sugar do to your body");
        itemDescriptionToClick.add("Check your sugar consumption history");
        itemDescriptionToClick.add("Suggest us features you like to see in app");

        ArrayList<Integer> thumbnails = new ArrayList<Integer>();
        thumbnails.add(R.drawable.icon_apple);
        thumbnails.add(R.drawable.icon_lemon);
        thumbnails.add(R.drawable.icon_kiwi);
        thumbnails.add(R.drawable.icon_strawberry);
        thumbnails.add(R.drawable.icon_pear);

        startListView.setAdapter(new ListViewAdapterWithImage(this, itemsToClickToStart, itemDescriptionToClick, thumbnails));

        startListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                 @Override
                                                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                     switch (position) {
                                                         case 0:
                                                             onClickTrackSugar();
                                                             break;
                                                         case 1:
                                                             onClickHowMuchSugar();
                                                             break;
                                                         case 2:
                                                             onClickWhyStopSugar();
                                                             break;
                                                         case 3:
                                                             onClickHistory();
                                                             break;
                                                         case 4:
                                                             onClickAbout();
                                                             break;

                                                     }

                                                 }
                                             }


        );
    }


    public void onClickAbout()
    {
        Intent myIntent = new Intent(InformationGatewayActivity.this, About.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("About us - Sugar App");

    }

    public void onClickHistory()
    {
        Intent myIntent = new Intent(InformationGatewayActivity.this, HistoryGraph.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("Check History");

    }

    public void onClickTrackSugar()
    {
        Intent myIntent = new Intent(InformationGatewayActivity.this, MainTrackerActivity.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("Track Sugar");

    }

    public void onClickHowMuchSugar()
    {
        Intent myIntent = new Intent(InformationGatewayActivity.this, SugarMeasurements.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("Sugar Measurements");

    }

    public void onClickWhyStopSugar()
    {
        Intent myIntent = new Intent(InformationGatewayActivity.this, HowToReduce.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("Why Stop Sugar");
    }

    public void launchMarket(View view) {

        // Log.d("Trying to go to PlayStore", "...");

        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);



        try {
            startActivity(myAppLinkToMarket);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
        FlurryAgent.logEvent("Start Activity - Rate Us");
    }


}
