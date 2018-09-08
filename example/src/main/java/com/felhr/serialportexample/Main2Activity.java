package com.felhr.serialportexample;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends Activity {
    EditText et_speed;
    Button bt_send;
    int speed;
    JSONArray data_arr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_speed= (EditText) findViewById(R.id.editText1);
        bt_send= (Button) findViewById(R.id.buttonSend);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://34.220.143.60:8000/api/speed/";


        data_arr= new JSONArray();
        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, data_arr,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Toast.makeText(getApplicationContext(),String.valueOf(response.length()), Toast.LENGTH_SHORT).show();
                            JSONObject tmp = response.getJSONObject(response.length()-1);
                            speed = tmp.getInt("speed");
                            et_speed.setText(String.valueOf(speed));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(getRequest);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_speed.getText().toString().equals("")) {
                    String data = et_speed.getText().toString();
                    speed = Integer.parseInt(data);

                    if(Integer.parseInt(data)<0 || Integer.parseInt(data)>255)
                        Toast.makeText(getApplicationContext(),"속도 범위를 벗어났습니다 (0~255)",Toast.LENGTH_SHORT).show();

                    else{
                        PostRequest postRequest = new PostRequest(getApplicationContext(), speed, url);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"속도를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
