package com.MyApp_2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s1 = "{\"firstname\":\"Имя1\", \"age\":20 }";
        String s2 = "{\"firstname\":\"Имя2\", \"age\":25 }";

        try {
            JSONObject jsonObject1 = new JSONObject(s1);
            JSONObject jsonObject2 = new JSONObject(s2);
            JSONObject jsonObject3 = new JSONObject();

            jsonObject3.put("sss1",jsonObject1);
            jsonObject3.put("sss2",jsonObject2);

            try {
                FileOutputStream out = openFileOutput(FILE_NAME,MODE_PRIVATE);
                out.write(jsonObject3.toString().getBytes());
                out.close();
                showToast("В файл добавленны данные");
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Ошибка при записи файла");
            }

            try {
                FileInputStream in = openFileInput(FILE_NAME);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                in.close();
                String s = new String(buffer);
                jsonObject3 = new JSONObject(s);
                ((TextView)findViewById(R.id.tvReadJSONFromFile)).setText(s);

                JSONObject jsons1 = jsonObject3.getJSONObject("sss1");
                JSONObject jsons2 = jsonObject3.getJSONObject("sss2");

//                jsons1.put("firstname",jsons1.getString("age"));
//                jsons2.put("firstname",jsons2.getString("age"));
                System.out.println(jsons1.getString("firstname")+", "+jsons1.getString("age")+"; "+jsons2.getString("firstname")+", "+jsons2.getString("age"));
            } catch (Exception e) {
                e.printStackTrace();
                showToast("Ошибка при чтении файла!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_SHORT).show(); }
}