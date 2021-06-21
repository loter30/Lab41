package com.example.lab41;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {

    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") != null) {
                Animal zwierz = (Animal) extras.getSerializable("element");
                EditText kolor = (EditText) findViewById(R.id.editKolor);
                EditText wielkosc = (EditText) findViewById(R.id.editWielkosc);
                EditText opis = (EditText) findViewById(R.id.editOpis);
                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());
                this.modyfi_id=zwierz.get_id();
            }
        }catch(Exception ex) {
            this.modyfi_id=0;
        }
    }


    public void send(View view){
        EditText kolor = (EditText) findViewById(R.id.editKolor);
        EditText wielkosc = (EditText) findViewById(R.id.editWielkosc);
        EditText opis = (EditText) findViewById(R.id.editOpis);
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);

        Animal animal = new Animal(gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString());
        animal.set_id(this.modyfi_id);
        Intent intent = new Intent();
        intent.putExtra("nowy", animal);
        setResult(RESULT_OK,intent);
        finish();
    }

}