package com.example.testws;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Controller{
    String resp = "";
    String TAG = "ALERT";
    public Controller(){

    }
    public JSONObject getAll(Context context, String name) throws InterruptedException, TimeoutException, ExecutionException {


        String url = "https://anthophilous-baseli.000webhostapp.com/fetch.php/?name="+name;



        JSONObject response = null;
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url,null,future,future);
        requestQueue.add(request);


        try {
            response = future.get(5, TimeUnit.SECONDS); // Blocks for at most 5 seconds.
        } catch (InterruptedException e) {
            Log.d(TAG,"interrupted");
        } catch (ExecutionException e) {
            Log.d(TAG,"execution");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if(response!=null)
            Log.d(TAG,response.toString());

        return response;

    }
}

