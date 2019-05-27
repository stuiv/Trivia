package com.example.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class TriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    private Callback returnactivity;
    public TriviaRequest(Context c) {
        context = c;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        returnactivity.gotTriviaError(error.getMessage());

    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList<TriviaQuestion> list = new ArrayList<>();
        try {

            JSONArray JSONlist = response.getJSONArray("results");
            for (int i = 0; i < JSONlist.length(); i++) {

                String correct_answer = JSONlist.getJSONObject(i).getString("correct_answer");
                correct_answer = URLDecoder.decode(correct_answer, "UTF-8");

                String question= JSONlist.getJSONObject(i).getString("question");
                question = URLDecoder.decode(question, "UTF-8");

                JSONArray JSONlist2 = JSONlist.getJSONObject(i).getJSONArray("incorrect_answers");
                ArrayList<String> incorrect_answers = new ArrayList<>();

                for (int j = 0; j < JSONlist2.length(); j++) {
                    String incorrect_answer = JSONlist2.getString(j);
                    incorrect_answer = URLDecoder.decode(incorrect_answer, "UTF-8");
                    incorrect_answers.add(incorrect_answer);
                }
                list.add(new TriviaQuestion(correct_answer, question, incorrect_answers));
            }
            returnactivity.gotTrivia(list);

        } catch (JSONException e) {
            returnactivity.gotTriviaError(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            returnactivity.gotTriviaError(e.getMessage());
        }
    }

    public interface Callback {
        void gotTrivia(ArrayList<TriviaQuestion> list);
        void gotTriviaError(String message);
    }

    void getTrivia(Callback activity, String difficulty) {
        String UrlString = context.getString(R.string.Url_string_request) + difficulty + "&type=multiple&encode=url3986";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(UrlString, null, this, this);
        queue.add(jsonObjectRequest);
        returnactivity = activity;
    }

}
