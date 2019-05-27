package com.example.trivia;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HighscoreRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    Context context;
    private Callback returnactivity;


    @Override
    public void onErrorResponse(VolleyError error) {
        returnactivity.gotHighscoreError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        // Loop through JSON list.
        try {
            ArrayList<TriviaPlayer> list = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {
                String name = response.getJSONObject(i).getString("name");
                String score = response.getJSONObject(i).getString("score");
                String date = response.getJSONObject(i).getString("date");
                String id = response.getJSONObject(i).getString("id");

                // Put objects into list.
                TriviaPlayer triviaPlayer = new TriviaPlayer(Integer.valueOf(score), name, date, Integer.valueOf(id));
                list.add(triviaPlayer);
            }
            returnactivity.gotHighscore(list);

        } catch (JSONException e) {
            e.printStackTrace();
            returnactivity.gotHighscoreError(e.getMessage());
        }

    }

    public interface Callback {
        void gotHighscore(ArrayList<TriviaPlayer> list);
        void gotHighscoreError(String message);
    }

    public HighscoreRequest(Context c) {
        context = c;
    }


    public void getHighscore(Callback activity) {
        // Find the items at the given URL.

        String UrlString = "https://ide50-robner.legacy.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                UrlString,
                this, this);
                queue.add(jsonObjectRequest);
        returnactivity = activity;
    }


    // Posting player details to a server.
    public void postHighscore(final TriviaPlayer triviaPlayer) {
        String UrlString = "https://ide50-robner.legacy.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, UrlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                }, this) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", triviaPlayer.getName());
                params.put("score", String.valueOf(triviaPlayer.getScore()));
                params.put("date", triviaPlayer.getDate());
                return params;
            }
        };
        queue.add(postRequest);
    }
}

//        }
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("error", "");
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parameters = new HashMap<String, String>();
//                parameters.put("name", triviaPlayer.getName());
//                parameters.put("score", String.valueOf(triviaPlayer.getScore()));
//                parameters.put("date", triviaPlayer.getDate());
//                return parameters;
//            }
////        };
//    }
//
//}