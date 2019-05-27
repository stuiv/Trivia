package com.example.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Select_difficulty_Activity extends AppCompatActivity {
    ArrayList<String> level_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_);

        Collections.addAll(level_list,"easy","medium","hard");

        ListView listView = findViewById(R.id.Difficulty_list);
        listView.setAdapter(new SelectAdapter(this, R.layout.button, level_list));
        listView.setOnItemClickListener(new Clicklistener());
    }
    private class Clicklistener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

            Intent intent = new Intent(getApplicationContext(), play.class);
            intent.putExtra("difficulty", (String) parent.getItemAtPosition(pos));
            startActivity(intent);
        }

    }
}
