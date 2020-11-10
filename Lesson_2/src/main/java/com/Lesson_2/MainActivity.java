package com.Lesson_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = "{\"firstname\":\"Дмитрий\", \"surname\":\"Смирнов\", \"age\":25, \"married\":true, \"brothers\":[\"Алексей\", \"Владимир\", \"Александр\"], \"prof\":{\"title\":\"programmer\", \"exp\":5}}";

        try {
            JSONObject json = new JSONObject(s);
            System.out.println(json.getString("firstname"));
            System.out.println(json.getString("surname"));
            System.out.println(json.getInt("age"));
            System.out.println(json.getBoolean("married"));
            JSONArray jsonArray = json.getJSONArray("brothers");
            for (int i = 0; i < jsonArray.length(); i++)
                System.out.println(jsonArray.get(i));
            JSONObject jsonProf = json.getJSONObject("prof");
            System.out.println(jsonProf.getString("title"));
            System.out.println(jsonProf.getInt("exp"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = new JSONObject();
            json.put("firstname", "Алексей");
            json.put("surname", "Иванов");
            json.put("age", 30);
            System.out.println(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}