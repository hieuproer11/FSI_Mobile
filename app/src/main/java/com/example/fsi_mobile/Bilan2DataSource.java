package com.example.fsi_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bilan2DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public Bilan2DataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public String info(){return dbHelper.TABLE_COMMENTS1;}

    public void close(){dbHelper.close();}


    public Bilan2 getByIdBilan2(int id){
        Bilan2 bil2 = null;
        Cursor curseur = database.query(true, "BILAN2",
                new String[]{"idBil2", "idEtu", "dateVis2", "noteDoss2", "noteOral2", "noteEnt2", "moyenne2"},
                "idBil2 = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(curseur.moveToFirst()){
            bil2 = cursorToBilan2(curseur);
        }
        curseur.close();
        return bil2;
    }


    public boolean bilanExists(int idBil2) {
        Cursor cursor = database.query(
                "BILAN2",
                new String[]{"idBil2"},
                "idBil2 = ?",
                new String[]{String.valueOf(idBil2)},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public Bilan2 getBilan2ByEtudiantId(int idEtu) {
        Bilan2 bil2 = null;
        Cursor cursor = database.query(
                "BILAN2",
                new String[]{"idBil2", "idEtu", "dateVis2", "noteDoss2", "noteOral2", "noteEnt2", "moyenne2"},
                "idEtu = ?",
                new String[]{String.valueOf(idEtu)},
                null, null, null
        );

        if(cursor.moveToFirst()) {
            bil2 = cursorToBilan2(cursor);
        }
        cursor.close();
        return bil2;
    }

    public Bilan2 insertBil2(Bilan2 bilan2, int idEtu){
        ContentValues values = new ContentValues();
        if(bilanExists(bilan2.getIdBil2())) {
            return getByIdBilan2(bilan2.getIdBil2());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);

        values.put("idBil2", bilan2.getIdBil2());
        values.put("idEtu", idEtu); // Lien avec l'étudiant
        values.put("dateVis2", sdf.format(bilan2.getDateVis2()));
        values.put("noteDoss2", bilan2.getNoteDoss2());
        values.put("noteOral2", bilan2.getNoteOral2());
        values.put("noteEnt2", bilan2.getNoteEnt2());
        values.put("moyenne2", bilan2.getMoyenne2());

        database.insertWithOnConflict("BILAN2", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return bilan2;
    }

    public Bilan2 cursorToBilan2(Cursor cursor){
        int idBil2 = cursor.getColumnIndexOrThrow("idBil2");
        int idEtu = cursor.getColumnIndexOrThrow("idEtu");
        int dateVis2 = cursor.getColumnIndexOrThrow("dateVis2");
        int noteDoss2 = cursor.getColumnIndexOrThrow("noteDoss2");
        int noteOral2 = cursor.getColumnIndexOrThrow("noteOral2");
        int noteEnt2 = cursor.getColumnIndexOrThrow("noteEnt2");
        int moyenne2 = cursor.getColumnIndexOrThrow("moyenne2");

        int id2 = cursor.getInt(idBil2);
        int idEtuVal = cursor.getInt(idEtu);
        float noteD2 = cursor.getFloat(noteDoss2);
        String dateV2string = cursor.getString(dateVis2);
        Date dateV2 = null;
        try {
            dateV2 = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE).parse(dateV2string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float noteO2 = cursor.getFloat(noteOral2);
        float noteE2 = cursor.getFloat(noteEnt2);
        float moy2 = cursor.getFloat(moyenne2);

        Bilan2 bilan2 = new Bilan2(id2,idEtuVal, dateV2, noteD2, noteO2, noteE2, moy2);

        return bilan2;
    }

    public void deleteAllBilan2() {
        database.delete("BILAN2", null, null);
    }
}
