package com.example.lenovo.myapplicationno2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainSimpleAdapter.class);
                startActivity(intent);
            }
        });

        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainAlertDialog.class);
                startActivity(intent);
            }
        });

        Button b3 = findViewById(R.id.b3);
        b3.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainXml.class);
                startActivity(intent);
            }
        });

        Button b4 = findViewById(R.id.b4);
        b4.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActionmode.class);
                startActivity(intent);
            }
        });


    }


}
