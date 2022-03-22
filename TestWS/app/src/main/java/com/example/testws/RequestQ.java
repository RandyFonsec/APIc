package com.example.testws;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQ {
    private static RequestQ instance;
    private static Context ctx;
    private RequestQueue requestQueue;
    private RequestQ(Context context){
        ctx = context;
        this.requestQueue = getRequestQueue();

    }
    public static synchronized RequestQ getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQ(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue(){
        if(this.requestQueue == null){
            this.requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return this.requestQueue;
    }
}
