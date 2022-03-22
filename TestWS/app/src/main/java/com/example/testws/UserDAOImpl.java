package com.example.testws;

import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserDAOImpl implements DAO {
    private final String BASE_URL ="https://anthophilous-baseli.000webhostapp.com/";
    private Gson gson = null;
    private Retrofit retrofit = null;
    private void obtener(){
        if(gson == null){
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

    }
    @Override
    public Object create(Object object) {
        obtener();
        User usuario = (User) object;
        Call<User> call = retrofit.create(UserDAORF.class).create(0, usuario.getName(), usuario.getEmail());
        final User[] u = {null};
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    u[0] = response.body();
                    System.out.println("Rsp "+u[0].toString());
                }else{
                    Log.e("Error","Unsuccessful response");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                System.out.println(call.request().toString());
                System.out.println(call.request().body());
                System.out.println("Error "+ t.getMessage());
                System.out.println(t.getCause().toString());
                System.out.println(t.getLocalizedMessage());
                t.printStackTrace();
            }
        });
        while(u[0]==null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tic tac");
        }

        return u[0];
        }


    @Override
    public Object update(Object object) {
        obtener();
        User usuario = (User) object;
        Call<User> call = retrofit.create(UserDAORF.class).update(usuario.getName(), usuario.getEmail());
        final User[] u = {null};
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    u[0] = response.body();
                    System.out.println("Rsp "+u[0].toString());
                }else{
                    Log.e("Error",response.raw().toString());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                u[0] = new User();
                System.out.println("No lo encontré");
            }
        });

        while(u[0]==null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tic tac");
        }

        return u[0];
    }

    @Override
    public Object get(Object key) {
        obtener();
        Call<User> call = retrofit.create(UserDAORF.class).get((String)key);
        final User[] u = {null};
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                u[0] = response.body();
                System.out.println(u[0].getName());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e("Error", t.getMessage());
                t.printStackTrace();
            }
        });

        while(u[0]==null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tic tac");
        }

        return u[0];


    }

    @Override
    public List<Object> getAll() {
        return null;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }
}
