package com.example.testws;
import static java.lang.Thread.sleep;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;



public class Controller{
    String resp = "";
    public Controller(){

    }
    public String getAll(Context context) throws InterruptedException {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://anthophilous-baseli.000webhostapp.com/fetchAll.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        resp = response.toString();
                        System.out.println(response.toString());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        return resp;

    }
}

