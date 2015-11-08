package com.example.app.catalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GreatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great);

        Button btn = (Button)findViewById(R.id.buttonBus);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GreatActivity.this, BusActivity.class));
            }
        });

        btn = (Button)findViewById(R.id.buttonOrganization);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GreatActivity.this, CategoriesActivity.class));
            }
        });

    }
}
