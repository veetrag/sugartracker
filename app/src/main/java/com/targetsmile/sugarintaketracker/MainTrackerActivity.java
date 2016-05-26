package com.targetsmile.sugarintaketracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.apptentive.android.sdk.Apptentive;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.targetsmile.sugarintaketracker.activities.InformationGatewayActivity;

import java.util.Calendar;

public class MainTrackerActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    StringBuffer stringBuffer;
    TextView messageView;
    TextView totalSugarIntake;
    GridView gridView;
    String[][] DAILY_UNITS_CONSUMED;

    public String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);


        String dateFormatted = year+ "-"+month+"-"+day;

        return dateFormatted;
    }

    public String getCurrentDateTime()
    {
        Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int second = c.get(Calendar.SECOND);

        String timeFormatted = year+ "-"+month+"-"+day +" "+hour+":"+minute+":"+second;

        return timeFormatted;
    }

    public String getToday()
    {
        Calendar c = Calendar.getInstance();

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        String timeFormatted = year+ "-"+month+"-"+day +" 00:00:00";

        return timeFormatted;
    }




    public void addSugar(View view)
    {

        int sugarGrams = Integer.parseInt(view.getTag().toString());

        databaseHelper.insertData(getCurrentDateTime(), getCurrentDate() , sugarGrams);

        FlurryAgent.logEvent("Add Sugar - " + sugarGrams);

        showData();
    }


    public void showData()
    {
        int totalDailyCount = 0;
        int counter = 0;

        Cursor cursor = databaseHelper.getTodaysData(getToday());

        DAILY_UNITS_CONSUMED = new String[cursor.getCount()][3];


//        if(cursor.getCount() == 0)
//        {
//            return;
//        }
//        else

        {
            stringBuffer = new StringBuffer();

            while(cursor.moveToNext())
            {
                totalDailyCount = totalDailyCount + Integer.parseInt(cursor.getString(3));
                String[] parts = cursor.getString(1).split(" ");

               // stringBuffer.append( parts[1] + " " + cursor.getString(3) + "gms \n");

                DAILY_UNITS_CONSUMED[counter][0] = cursor.getString(3);
                DAILY_UNITS_CONSUMED[counter][1] = parts[1];
                DAILY_UNITS_CONSUMED[counter][2] = cursor.getString(0);

                counter++;
            }


            messageView.setText(stringBuffer);

        }

        totalSugarIntake.setText("Today's intake - " + totalDailyCount + "gms");
        gridView.setAdapter(new ImageAdapter(this, DAILY_UNITS_CONSUMED));


    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tracker);

        messageView = (TextView) findViewById(R.id.textView);
        totalSugarIntake = (TextView) findViewById(R.id.totalSugarIntake);
        gridView = (GridView) findViewById(R.id.sugarIntakeGridView);

        databaseHelper = new DatabaseHelper(this);

//        databaseHelper.deleteAllData();

        showData();

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        registerGCM();


    }

    @Override
    public void onStart()
    {
        super.onStart();

    }


    public void startInformationGateway(View view)
    {
        Intent myIntent = new Intent(MainTrackerActivity.this, InformationGatewayActivity.class);
        // myIntent.putExtra("remedyid","badbreath");
        this.startActivity(myIntent);
        FlurryAgent.logEvent("Start Info Gateway");



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.historyButton) {

            Intent myIntent = new Intent(MainTrackerActivity.this, HistoryGraph.class);
            // myIntent.putExtra("remedyid","badbreath");
            this.startActivity(myIntent);
            FlurryAgent.logEvent("Check History");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void itemClicked(final int position) {



        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.presence_offline)
                .setTitle("Confirmation")
                .setMessage("Do you want to delete this entry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        databaseHelper.deleteEntryById(DAILY_UNITS_CONSUMED[position][2]);
                        FlurryAgent.logEvent("Deleting entry - " + DAILY_UNITS_CONSUMED[position][0]);
                        showData();

                    }

                })
                .setNegativeButton("No", null)
                .show();


      //  Toast.makeText(this,DAILY_UNITS_CONSUMED[position][0],Toast.LENGTH_SHORT).show();
    }


    public void registerGCM()
    {
        GCMClientManager pushClientManager = new GCMClientManager(this, "174285893703");
        pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {

                Log.d("*** Registration id", registrationId);
                //send this registrationId to your server
                Apptentive.setPushNotificationIntegration(Apptentive.PUSH_PROVIDER_APPTENTIVE, registrationId);


            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }
        });
    }
}
