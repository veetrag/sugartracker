package com.targetsmile.sugarintaketracker;

/**
 * Created by veetrag on 14/03/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[][] sugarConsumedValues;

    public ImageAdapter(Context context, String[][] sugarConsumedValues) {
        this.context = context;
        this.sugarConsumedValues = sugarConsumedValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from sugarConsumedValue.xml
            gridView = inflater.inflate(R.layout.show_unit_images, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            TextView textViewTime = (TextView) gridView.findViewById(R.id.grid_item_time);

            textView.setText(sugarConsumedValues[position][0] + " gm");
            textViewTime.setText(sugarConsumedValues[position][1]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String sugarConsumedValue = sugarConsumedValues[position][0];

            if (sugarConsumedValue.equals("4")) {
                imageView.setImageResource(R.drawable.roundshapebutton1);
            } else if (sugarConsumedValue.equals("8")) {
                imageView.setImageResource(R.drawable.roundshapebutton2);
            } else if (sugarConsumedValue.equals("12")) {
                imageView.setImageResource(R.drawable.roundshapebutton3);
            } else if (sugarConsumedValue.equals("16")){
                imageView.setImageResource(R.drawable.roundshapebutton4);
            } else {
                imageView.setImageResource(R.drawable.roundshapebutton5);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return sugarConsumedValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}