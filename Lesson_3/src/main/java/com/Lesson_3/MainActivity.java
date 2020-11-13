package com.Lesson_3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = ((EditText) findViewById(R.id.et)).getText().toString();

                new WeatherTask(city).execute();
            }
        });
    }

    private class WeatherTask extends AsyncTask<Void, Void, String> {

        private String city;
        private String key = "f44bb70f42ee63814c0d9bfa7f39ca84";

        public WeatherTask(String city) {
            this.city = city;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String content = getContent("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key+ "&units=metric");
            return content;
        }

        protected void onPostExecute(String content) {
            try {
                JSONObject json = new JSONObject(content);
                double temp = json.getJSONObject("main").getDouble("temp");
                tv.setText("Температура: " + temp + " \u2103");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String getContent(String path) {
            try {
                URL url = new URL(path);
                HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(20000);
                c.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String content = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content += line + "\n";
                } System.out.println(content);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}