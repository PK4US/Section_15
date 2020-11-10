package com.Lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView wv = findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadData("<html><head></head><body><h1>Заголовок</h1></body></html>", "text/html", "UTF-8");
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = ((EditText) findViewById(R.id.link)).getText().toString();
                wv.loadUrl(link);
            }
        });
    }
}