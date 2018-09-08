package com.felhr.serialportexample;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostRequest {

    PostRequest(final Context context, final int speed, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONArray tmp = new JSONArray();
        JSONObject post = null;

        try {
            post = new JSONObject();
            post.put("speed", speed);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, post, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, String.valueOf(speed), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(postRequest);
    }
}
