package com.example.lab41;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public MySQLite(Context context){
        super(context, "animalsDB",null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        String DATABASE_CREATE = "create table animals " +
                "(_id integer primary key autoincrement," +
                "gatunek text not null," +
                "kolor text not null," +
                "wielkosc real not null," +
                "opis text not null);";
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS animals");
        onCreate(database);
    }

    public void add(Animal animal){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gatunek", animal.getGatunek());
        values.put("kolor", animal.getKolor());
        values.put("wielkosc", animal.getWielkosc());
        values.put("opis", animal.getOpis());
        db.insert("animals", null, values);
        db.close();
    }

    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("animals", "_id = ?", new String[] { id });
        db.close();
    }

    public int update(Animal animal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("gatunek", animal.getGatunek());
        values.put("kolor", animal.getKolor());
        values.put("wielkosc", animal.getWielkosc());
        values.put("opis", animal.getOpis());
        int i = db.update("animals", values, "_id = ?", new String[]{String.valueOf(animal.get_id())});
        db.close();
        return i;
    }

    public Animal download(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("animals", new String[] { "_id", "gatunek", "kolor", "wielkosc", "opis" }, "_id = ?",
                        new String[] {String.valueOf(id) },
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Animal animal = new Animal(cursor.getString(1), cursor.getString(2),
                cursor.getFloat(3), cursor.getString(4));


        animal.set_id(Integer.parseInt(cursor.getString(0)));
        return animal;
    }

    public Cursor list(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from animals",null);

    }
}
