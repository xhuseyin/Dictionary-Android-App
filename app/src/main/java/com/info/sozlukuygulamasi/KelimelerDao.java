package com.info.sozlukuygulamasi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by kasimadalan on 1.05.2018.
 */

public class KelimelerDao {

    public ArrayList<Kelimeler> tumKelimeler(Veritabani vt){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM kelimeler",null);
        while (c.moveToNext()){
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    ,c.getString(c.getColumnIndex("ingilizce"))
                    ,c.getString(c.getColumnIndex("turkce")));
            kelimelerArrayList.add(k);
        }

        return kelimelerArrayList;

    }

    public ArrayList<Kelimeler> kelimeAra(Veritabani vt,String aramaKelime){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce like '%"+aramaKelime+"%'",null);
        while (c.moveToNext()){
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    ,c.getString(c.getColumnIndex("ingilizce"))
                    ,c.getString(c.getColumnIndex("turkce")));
            kelimelerArrayList.add(k);
        }

        return kelimelerArrayList;

    }

    public void kelimeEkle(Veritabani vt, String ingilizce, String turkce){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("ingilizce",ingilizce);
        degerler.put("turkce",turkce);

        db.insertOrThrow("kelimeler",null,degerler);
        db.close();
    }

    public void kelimeSil(Veritabani vt,int kelimeId){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("kelimeler","kelime_id=?",new String[]{String.valueOf(kelimeId)});
        db.close();
    }

    public void kelimeGuncelle(Veritabani vt, int kelime_id, String ingilizce, String turkce){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("ingilizce",ingilizce);
        degerler.put("turkce",turkce);

        db.update("kelimeler",degerler,"kelime_id=?",new String[]{String.valueOf(kelime_id)});
        db.close();

    }

}
