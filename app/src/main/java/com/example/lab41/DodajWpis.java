package com.example.lab41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DodajWpis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
    }

    public void send(View view){
        EditText control = (EditText) findViewById(R.id.editTextTextPersonName);
        String field = control.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("wpis", field);
        setResult(RESULT_OK,intent);
        finish();
    }
}