package com.felhr.serialportexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewSpeedListener extends BroadcastReceiver {

    int speed=0;

    @Override
    public void onReceive(final Context context, Intent intent) {

        JSONArray data_arr = new JSONArray();
        final String url = "http://34.220.143.60:8000/api/speed/";
        RequestQueue queue = Volley.newRequestQueue(context);

        if (true) {
            final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, data_arr,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Toast.makeText(context, String.valueOf(response.length()), Toast.LENGTH_SHORT).show();
                                JSONObject tmp = response.getJSONObject(response.length() - 1);
                                speed = tmp.getInt("speed");
                                Toast.makeText(context, speed , Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(getRequest);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
