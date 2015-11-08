package com.example.app.catalog;

import java.io.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BusActivity extends AppCompatActivity {

    public String getBuses (String a, String b){
        InputStream is = null;
        String ans = "";
        try {
            is = this.getAssets().open("n1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String bus, line;
            while ((bus = reader.readLine()) != null) {
                line = reader.readLine();
                if (line.contains(a) && line.contains(b)) ans += bus + "\n";
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                TextView textView = (TextView) findViewById(R.id.textViewBuses);
                textView.setText(getBuses(editText.getText().toString(), editText2.getText().toString()));
            }
        };

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                editText.setText(null);
            }
        };

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                editText2.setText(null);
            }
        };

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(listener);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(listener1);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(listener2);
    }
}
