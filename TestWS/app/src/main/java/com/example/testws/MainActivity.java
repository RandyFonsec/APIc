package com.example.testws;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    EditText eT, eM;
    TextView tv, idT;
    Button btn, btn2, btn3;
    UserDAOImpl uDAO = new UserDAOImpl();
    static RequestQ rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rq = RequestQ.getInstance(this);
        eT = findViewById(R.id.name);
        eM = findViewById(R.id.email);
        tv = findViewById(R.id.texto);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        idT=findViewById(R.id.tvID);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargar();
            }
        });
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });


    }

    public void agregar() {

        User u = new User(eT.getText().toString(), eM.getText().toString());
        new AsyncTask<String, String, User>() {
            @Override
            protected User doInBackground(String... strings) {

                return (User) uDAO.create(u);
            }
            @Override
            protected void onPostExecute(User result) {
                if(result != null){
                    Toast.makeText(getApplicationContext(),"Agregado",Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"No agregado",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            protected void onPreExecute(){
                Toast.makeText(getApplicationContext(),"Inciando agregado",Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

    public void cargar(){
        String a = eT.getText().toString();

        new AsyncTask<String, String, User>() {
            @Override
            protected User doInBackground(String... strings) {
                Log.d("HERE ", " 1 ");
                User u = (User) uDAO.get(a);
                if(u != null)
                    Log.d("HERE ", u.getName());
                return u;
            }
            @Override
            protected void onPostExecute(User result) {
                if(result != null) {
                    //idT.setText(result.getId());
                    //eM.setText(result.getEmail());
                    cambiarTexto(result.toString());
                }
                else{
                    cambiarTexto("No hay resultados\n.");
                }

            }
            @Override
            protected void onPreExecute(){
                Toast.makeText(getApplicationContext(),"Iniciando consulta",Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }


    public void actualizar(){
        User user = new User(eT.getText().toString(), eM.getText().toString());
        new AsyncTask<String, String, User>() {
            @Override
            protected User doInBackground(String... strings) {
                Log.d("HERE ", " 1 ");
                User u = (User) uDAO.update(user);
                if(u != null)
                    Log.d("HERE ", u.getName());
                return u;
            }
            @Override
            protected void onPostExecute(User result) {
                if(result != null) {
                    cambiarTexto(result.toString());
                }
                else{
                    cambiarTexto("No hay resultados\n.");
                }

            }
            @Override
            protected void onPreExecute(){
                Toast.makeText(getApplicationContext(),"Iniciando actualización",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public void cambiarTexto(String text){
        tv.setText(text);
    }

}