package com.MyApp_1;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button blink1 = findViewById(R.id.blink1);
        Button blink2 = findViewById(R.id.blink2);
        Button blink3 = findViewById(R.id.blink3);

        blink1.setOnClickListener(this);
        blink2.setOnClickListener(this);
        blink3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blink1: {
                WebView wv = findViewById(R.id.wv);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl("https://www.instagram.com/");
                break;
            }
            case R.id.blink2: {
                WebView wv = findViewById(R.id.wv);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl("https://music.youtube.com/");
                break;
            }
            case R.id.blink3: {
                WebView wv = findViewById(R.id.wv);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl("http://srs.MyRusakov.ru");
                break;
            }
        }
    }
}