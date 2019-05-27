package com.example.trivia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter {
    ArrayList<TriviaPlayer> list;

    public HighscoreAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        list = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Create the view for list.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_highscore, parent, false);
        }

        TextView dateview = convertView.findViewById(R.id.textViewHighDate);
        TextView nameview = convertView.findViewById(R.id.textViewHighName);
        TextView scoreview = convertView.findViewById(R.id.textViewHighScore);

        // Set values of the view through the list.
        String name = list.get(position).getName();
        int score = list.get(position).getScore();
        String date = list.get(position).getDate();
        int id = list.get(position).getId();


//        int id = list.get(position).getId();
//        TextView dateview = convertView.findViewById(R.id.textViewHighDate);
//        TextView nameview = convertView.findViewById(R.id.textViewHighName);
//        TextView scoreview = convertView.findViewById(R.id.textViewHighScore);
//
//        dateview.setText("Date: " + list.get(position).getDate());
//        nameview.setText(position + "1" + " " + list.get(position).getName());
//        scoreview.setText("Score: " + list.get(position).getScore());
        int rank = position + 1;
        dateview.setText("Date: " + date);
        nameview.setText(rank + " " + name);
        scoreview.setText("Score: " + Integer.toString(score));

        return convertView;
    }
}