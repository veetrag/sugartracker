package com.targetsmile.sugarintaketracker.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.targetsmile.sugarintaketracker.R;

import java.util.ArrayList;

/**
 * Created by veetrag on 24/02/16.
 */

class ListViewAdapterWithImage extends BaseAdapter {

    private LayoutInflater layoutInflater;
    ArrayList<String> mainHeading;
    ArrayList<String> subHeading;
    ArrayList<Integer> thumbnailImage;


    public ListViewAdapterWithImage(Context context, ArrayList<String> mainHeading, ArrayList<String> subHeading, ArrayList<Integer> thumbnailImage) {
// TODO Auto-generated constructor stub
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mainHeading = mainHeading;
        this.subHeading = subHeading;
        this.thumbnailImage = thumbnailImage;
    }

    @Override
    public int getCount() {
// Set the count value to the total number of items in the Array
        return mainHeading.size();
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

// Inflate the item layout and set the views
        View listItem = convertView;
        int pos = position;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.list_items, null);
        }

// Initialize the views in the layout
        ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
        TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
        TextView tvDesc = (TextView) listItem.findViewById(R.id.desc);



// Set the views in the layout
        iv.setBackgroundResource(thumbnailImage.get(pos));
        tvTitle.setText(mainHeading.get(pos));
        tvDesc.setText(subHeading.get(pos));

        return listItem;
    }

}

