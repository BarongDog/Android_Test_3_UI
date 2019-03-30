package com.example.lenovo.myapplicationno2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainXml extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menutest_layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView textView = findViewById(R.id.text1);
        switch (item.getItemId()){
            case R.id.item2:
                textView.setTextSize(10);
                return true;
            case R.id.item3:
                textView.setTextSize(16);
                return true;
            case R.id.item4:
                textView.setTextSize(20);
                return true;
            case R.id.item5:
                Toast.makeText(this,"selected" + item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            case R.id.item7:
                textView.setTextColor(Color.RED);
                return true;
            case R.id.item8:
                textView.setTextColor(Color.BLACK);
                return true;
            default :
                return super.onOptionsItemSelected(item);

        }
    }
}
