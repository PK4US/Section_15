package com.MyApp_3;

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
                double currency = Double.parseDouble (((EditText) findViewById(R.id.et)).getText().toString());

                new WeatherTask(currency).execute();
            }
        });
    }

    private class WeatherTask extends AsyncTask<Void, Void, String> {

        private double currency;

        public WeatherTask(double currency) {
            this.currency = currency;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String content = getContent("https://www.cbr-xml-daily.ru/daily_json.js");
            return content;
        }

        protected void onPostExecute(String content) {
            try {
                JSONObject json = new JSONObject(content);
                JSONObject jsonValute = json.getJSONObject("Valute");
                JSONObject jsonUSD = jsonValute.getJSONObject("USD");
                double temp = currency/jsonUSD.getDouble("Value") ;

                tv.setText(temp+"$");
                System.out.println(jsonValute);
                System.out.println(jsonUSD);
                System.out.println(jsonUSD.getDouble("Value"));
                System.out.println(jsonUSD.getDouble("Value")*currency);
                //System.out.println("____________________________"+temp);
                //System.out.println(temp*currency);
                System.out.println(currency);
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
                } //tem.out.println(content);
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}