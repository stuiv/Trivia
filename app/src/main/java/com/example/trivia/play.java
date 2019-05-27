package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class play extends AppCompatActivity implements TriviaRequest.Callback{

    int questionNr = 0;
    TriviaQuestion question;
    TriviaPlayer player = new TriviaPlayer(0, "", "", 0);
    ArrayList<TriviaQuestion> questions;

    private int difficultly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        String difficulty =  (String) intent.getSerializableExtra("difficulty");

        TriviaRequest score = new TriviaRequest(this);
        score.getTrivia(this, difficulty);

        TextView progress_score =  findViewById(R.id.progress_score);
        progress_score.setText("Your current Score is: 0");

        switch ((String) intent.getSerializableExtra("difficulty")) {
            default:
                difficultly = 1;
                break;
            case"medium":
                difficultly = 2;
                break;
            case"hard":
                difficultly = 3;
                break;
        }
    }
    @Override
    public void gotTrivia(ArrayList<TriviaQuestion> items) {
        questions = items;
        nextQuestion(0);
    }
    @Override
    public void gotTriviaError(String message) {
        Toast.makeText(this, "Trivia Error", Toast.LENGTH_LONG).show();

    }
    public void nextQuestion(int qNr) {
        question = questions.get(qNr);
        TextView textView = findViewById(R.id.textViewQuestion);
        textView.setText(question.getQuestion());

        ListView listView = findViewById(R.id.listViewAnswers);
        listView.setAdapter(new SelectAdapter(this, R.layout.button, question.getAll_answers()));

        listView.setOnItemClickListener(new Click_listener());
    }

    private class Click_listener implements AdapterView.OnItemClickListener {
        public ProgressBar progressBar;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

            String item = (String) parent.getItemAtPosition(pos);


            if (item.equals(question.getCorrect_answer())) {
                int score = player.getScore();
                score += 10 * difficultly;
                player.setScore(score);
            }

            String progress_score_string = "Your current score is: " + player.getScore();
            TextView progress_score = findViewById(R.id.progress_score);
            progress_score.setText(progress_score_string);

            questionNr += 1;

            progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(progressBar.getProgress() + 1);

            if (questionNr == 10) {
                Intent intent = new Intent(getApplicationContext(), name.class);
                intent.putExtra("player", player);
                startActivity(intent);
            } else {
                nextQuestion(questionNr);
            }
        }
    }
}
