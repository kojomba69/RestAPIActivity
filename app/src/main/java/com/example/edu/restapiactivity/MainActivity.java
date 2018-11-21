package com.example.edu.restapiactivity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        OpenWeatherAPITask task=new OpenWeatherAPITask();
        try {
            weather=task.execute("London").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextView textView=findViewById(R.id.textView);
        textView.setText(weather);

    }


}

class OpenWeatherAPITask extends AsyncTask<String,Void,String>{


    String weather1;
    @Override
    public String doInBackground(String... params) {
        String id=params[0];
        String urlString="http://api.openweathermap.org/data/2.5/weather"+"?q="+id+"&appid=ca85c720a2e86228bb411fec6fa59321";
        try {
            URL url=new URL(urlString);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            InputStream in=urlConnection.getInputStream();
            byte[] buffer = new byte[1000];
            in.read(buffer);
            weather1 = new String(buffer);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather1;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
