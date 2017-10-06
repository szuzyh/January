 package com.example.a909811.january;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a909811.january.model.JanuartAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

 public class MainActivity extends AppCompatActivity {

     private ListView listView;
     private JanuartAdapter myAdapter;
     private ArrayList<HashMap<String,Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.januaryList);
        data = getData();
        myAdapter = new JanuartAdapter(data,this);
        listView.setAdapter(myAdapter);
    }

     private ArrayList<HashMap<String,Object>> getData() {
         return new ArrayList<HashMap<String,Object>>();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         menu.add("add");
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getTitle().toString()){
             case "add":
                 //listView add one
                 HashMap<String,Object> tmp = new HashMap<>();
                 tmp.put("message","heiheihei");
                 tmp.put("date","103");
                 data.add(tmp);
                 myAdapter.notifyDataSetChanged();
                 break;
         }

         return super.onOptionsItemSelected(item);
     }
 }
