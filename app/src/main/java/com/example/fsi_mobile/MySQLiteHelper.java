package com.example.fsi_mobile;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS ="ETUDIANTS";
    public static final String TABLE_COMMENTS1 = "BILAN1";
    public static final String TABLE_COMMENTS2 = "BILAN2";
    public static final String DATABASE_NAME = "FSI_Mobile.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_COMMENTS + "(idEtu integer primary key autoincrement, " + "nomEtu text, preEtu text, login text UNIQUE, mdp text, mailEtu text, telEtu integer, classe text, specialite text, adrEtu text, nomEnt text, adrEnt text, nomMai text, preMai text, telMai integer, mailMai text, nomTut text, preTut text);";
    private static final String DATABASE_CREATE1 = "CREATE TABLE IF NOT EXISTS " + TABLE_COMMENTS1  + "(idBil1 integer primary key autoincrement, "
                                                                                                    + "idEtu INTEGER UNIQUE, "
                                                                                                    + "dateVis1 Date, noteDoss1 float, noteOral1 float, noteEnt1 float, moyenne1 float, remarque text,"
                                                                                                    + "FOREIGN KEY(idEtu) REFERENCES " + TABLE_COMMENTS + "(idEtu) ON DELETE CASCADE);";
    private static final String DATABASE_CREATE2 = "CREATE TABLE IF NOT EXISTS " + TABLE_COMMENTS2  + "(idBil2 integer primary key autoincrement, "
                                                                                                    + "idEtu INTEGER UNIQUE, " + "dateVis2 Date, noteDoss2 float, noteOral2 float, noteEnt2 float, moyenne2 float,"
                                                                                                    + "FOREIGN KEY(idEtu) REFERENCES " + TABLE_COMMENTS + "(idEtu) ON DELETE CASCADE);";
    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE1);
        database.execSQL(DATABASE_CREATE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS2);
        onCreate(db);
    }
    }
