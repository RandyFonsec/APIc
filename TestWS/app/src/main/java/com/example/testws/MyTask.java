package com.example.testws;



import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public abstract class MyTask extends AsyncTask<String, Integer, String> {

    // Runs in UI before background thread is called
    @Override
    protected abstract void onPreExecute();

    // This is run in a background thread
    @Override
    protected abstract String doInBackground(String... params);

    // This is called from background thread but runs in UI
    @Override
    protected abstract void onProgressUpdate(Integer... values) ;

    // This runs in UI when background thread finishes
    @Override
    protected abstract void onPostExecute(String result);
}