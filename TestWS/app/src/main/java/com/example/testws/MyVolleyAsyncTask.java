package com.example.testws;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class MyVolleyAsyncTask extends AsyncTask<String,String, JSONObject> {

    private Context ctx;
    private String name;
    public MyVolleyAsyncTask(Context hostContext,String name)
    {
        ctx = hostContext;
        this.name = name;
    }
    @Override
    protected JSONObject doInBackground(String... params) {

        // Method runs on a separate thread, make all the network calls you need
        Controller tester = new Controller();

        try {
            //Aqui va lo lento xd
            return tester.getAll(ctx, name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected abstract void onPostExecute(JSONObject result);
}