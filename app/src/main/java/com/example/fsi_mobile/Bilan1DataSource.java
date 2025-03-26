package com.example.fsi_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bilan1DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public Bilan1DataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public String info(){return dbHelper.TABLE_COMMENTS1;}

    public void close(){dbHelper.close();}

    public Bilan1 getByIdBilan1(int id){
        Bilan1 bil1 = null;
        Cursor curseur = database.query(true,"BILAN1", new String[]{"idBil1","dateVis1","noteDoss1","noteOral1","noteEnt1","moyenne1","remarque"},
               "idBil1 = " +id,null,null,null,null,null );
        while(curseur.moveToNext()){
            bil1 = cursorToBilan1(curseur);
        }
        curseur.close();
     return bil1;
    }

    public Bilan1 getBilan1ByEtudiantId(int idEtu) {
        Bilan1 bilan1 = null;
        Cursor curseur = database.query("BILAN1",
                new String[]{"idBil1", "dateVis1", "noteDoss1", "noteOral1", "noteEnt1", "moyenne1", "remarque"},
                "idEtu = ?",
                new String[]{String.valueOf(idEtu)},
                null, null, null);

        if(curseur.moveToFirst()){
            bilan1 = cursorToBilan1(curseur);
        }
        curseur.close();
        return bilan1;
    }

    public boolean bilanExists(int idBil1) {
        Cursor cursor = database.query(
                "BILAN1",
                new String[]{"idBil1"},
                "idBil1 = ?",
                new String[]{String.valueOf(idBil1)},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public Bilan1 insertBil1(Bilan1 bilan1, int idEtu){
        if(bilanExists(bilan1.getIdBil1())) {
            return getByIdBilan1(bilan1.getIdBil1());
        }
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        values.put("idBil1", bilan1.getIdBil1());
        values.put("idEtu", idEtu);
        values.put("dateVis1", sdf.format(bilan1.getDateVis1()));
        values.put("noteDoss1", bilan1.getNoteDoss1());
        values.put("noteOral1", bilan1.getNoteOral1());
        values.put("noteEnt1", bilan1.getNoteEnt1());
        values.put("moyenne1", bilan1.getMoyenne1());
        values.put("remarque", bilan1.getRemarque());

        database.insertWithOnConflict("BILAN1", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return bilan1;
    }


    public Bilan1 cursorToBilan1(Cursor cursor){
        int idBil1 = cursor.getColumnIndexOrThrow("idBil1");
        int dateVis1 = cursor.getColumnIndexOrThrow("dateVis1");
        int noteDoss1 = cursor.getColumnIndexOrThrow("noteDoss1");
        int noteOral1 = cursor.getColumnIndexOrThrow("noteOral1");
        int noteEnt1 = cursor.getColumnIndexOrThrow("noteEnt1");
        int moyenne1 = cursor.getColumnIndexOrThrow("moyenne1");
        int remarque = cursor.getColumnIndexOrThrow("remarque");

        int id1 = cursor.getInt(idBil1);
        float noteD1 = cursor.getFloat(noteDoss1);
        String dateV1string = cursor.getString(dateVis1);
        Date dateV1 = null;
        try {
            dateV1 = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE).parse(dateV1string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float noteO1 = cursor.getFloat(noteOral1);
        float noteE1 = cursor.getFloat(noteEnt1);
        float moy1 = cursor.getFloat(moyenne1);
        String rem = cursor.getString(remarque);

        return new Bilan1(id1,dateV1, noteD1, noteO1, noteE1, moy1, rem);
    }
    public void deleteAllBilan1() {
        database.delete("BILAN1", null, null);
        database.execSQL("VACUUM"); // Optimise l'espace (facultatif mais recommandé)
    }

}
