package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
    }
    public void playClicked(View view) {
        startActivity(new Intent(this, Select_difficulty_Activity.class));
    }

    public void highscoreClicked(View view) {
        startActivity(new Intent(this, HighscoreActivity.class));
    }
}
