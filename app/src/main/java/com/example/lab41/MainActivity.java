package com.example.lab41;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                database.list(),
                new String[] {"_id", "gatunek"},
                new int[] {android.R.id.text1,
                        android.R.id.text2},SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        );

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter((this.adapter));
        listView.setOnItemClickListener(new
                AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                        TextView name = (TextView) view.findViewById(android.R.id.text1);
                        Animal zwierz = database.download(Integer.parseInt(name.getText().toString()));
                        Intent intencja = new Intent(getApplicationContext(), DodajWpis.class);
                        intencja.putExtra("element", zwierz);
                        startActivityForResult(intencja, 2);
                    }
        });
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
            Animal nowy = (Animal) extras.getSerializable("nowy");
            database.add(nowy);
            adapter.changeCursor(database.list());
            adapter.notifyDataSetChanged();
        }
        if (requestCode == 2 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Animal nowy = (Animal) extras.getSerializable("nowy");
            database.update(nowy);
            adapter.changeCursor(database.list());
            adapter.notifyDataSetChanged();
        }

    }
}