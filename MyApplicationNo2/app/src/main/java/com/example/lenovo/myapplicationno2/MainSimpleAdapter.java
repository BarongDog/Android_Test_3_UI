package com.example.lenovo.myapplicationno2;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainSimpleAdapter extends AppCompatActivity {

    private ListView listView;
    private List<Map<String,Object>> list;
    int[] img=new int[]{R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,
            R.drawable.pic5,R.drawable.pic6};
    String animal[]={"Cat","Monkey","Tiger","Lion","Elephant","Doggy"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        listView=(ListView)findViewById(R.id.lv);
        list =new ArrayList<Map<String, Object>>();

        for(int i=0;i<6;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title",animal[i]);
            map.put("img",img[i]);
            list.add(map);
        }

        String[] Item = {"title","img"};
        int[] ItemView ={R.id.item_title,R.id.item_img};

        final SimpleAdapter sAdapter=new SimpleAdapter(this,
                list,
                R.layout.relative_layout_item,
                Item,
                ItemView);
        listView.setAdapter(sAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map=list.get(position);
                String s= "";
                s+=map.get("title");
                Toast.makeText(MainSimpleAdapter.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }



}