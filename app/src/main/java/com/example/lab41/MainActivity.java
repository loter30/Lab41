package com.example.lab41;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> target;
    private SimpleCursorAdapter adapter;
    private MySQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new MySQLite(this);
        setContentView(R.layout.activity_main);
        String [] colors = {"Red","Blue","Yellow","Green","Pink", "Purple","Black", "White"};
        target = new ArrayList<String>();
        target.addAll(Arrays.asList(colors));
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                database.list(),
                new String[] {"_id", "gatunek"},
                new int[] {android.R.id.text1,
                        android.R.id.text2},SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter((this.adapter));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void nowyWpis(MenuItem item){
        Intent intent = new Intent(this, DodajWpis.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            String nowy = (String)extras.get("wpis");
            target.add(nowy);
            adapter.notifyDataSetChanged();
        }

    }
}