package com.example.trivia;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighscoreActivity extends AppCompatActivity implements HighscoreRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();

        HighscoreRequest score = new HighscoreRequest(this);
        score.getHighscore(this);
    }

    @Override
    public void gotHighscore(ArrayList<TriviaPlayer> list) {
        Collections.sort(list, new Comparator<TriviaPlayer>() {
            public int compare(TriviaPlayer c1, TriviaPlayer c2) {
              return Integer.compare(c2.getScore(), c1.getScore());
            }
        });
        HighscoreAdapter adapter = new HighscoreAdapter(this, R.layout.highscore, list);
        ListView listView = findViewById(R.id.listViewHighscores);
        listView.setAdapter(adapter);

    }

    @Override
    public void gotHighscoreError(String message) {
        Toast.makeText(this, "HighScore Error", Toast.LENGTH_LONG).show();
    }
}


