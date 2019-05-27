package com.example.trivia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class name extends AppCompatActivity implements HighscoreRequest.Callback {
    TriviaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent intent = getIntent();
        player = (TriviaPlayer) intent.getSerializableExtra("player");

        TextView finalscore = findViewById(R.id.textViewScore);
        String scorestring = "Your score was: " + player.getScore();
        finalscore.setText(scorestring);
    }

    public void playClicked(View view) {
        startActivity(new Intent(this, Select_difficulty_Activity.class));
    }

    public void shareClicked(View view) {
        EditText editName = findViewById(R.id.editTextName);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        player.setDate(formattedDate);
        player.setName(editName.getText().toString());
        HighscoreRequest score = new HighscoreRequest(this);
        score.postHighscore(player);
        score.getHighscore(this);

        // Only start showing the highscores when your score has been posted.
        Button button = findViewById(R.id.buttonHighscore);
        button.setClickable(false);
//        EditText editName = findViewById(R.id.editTextName);
//
//        @SuppressLint("SimpleDateFormat") String formattedDate = new SimpleDateFormat("dd-MMM-yyyy")
//                .format(Calendar.getInstance().getTime());
//
//        player.setDate(formattedDate);
//        player.setName(editName.getText().toString());
//
//        HighscoreRequest final_score = new HighscoreRequest(this);
//        final_score.postHighscore(player);
//        final_score.getHighscore(this);
//
//        //Show scores when score is send
//        Button button = findViewById(R.id.buttonHighscore);
//        button.setClickable(false);

//        startActivity(new Intent(this, HighscoreActivity.class));
    }

    @Override
    public void gotHighscore(ArrayList<TriviaPlayer> list) {
        startActivity(new Intent(this, HighscoreActivity.class));
    }

    @Override
    public void gotHighscoreError(String message) {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }
}
