package com.example.trivia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectAdapter extends ArrayAdapter {
    ArrayList<String> list;
    public SelectAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        list = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Create view for list.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.button, parent, false);
        }

        TextView button = convertView.findViewById(R.id.textViewButton);
        // Set value of button through the list.
        button.setText(list.get(position));
        return convertView;
    }
}