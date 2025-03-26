package com.example.fsi_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EtudiantDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public EtudiantDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public String info(){return dbHelper.TABLE_COMMENTS;}


    public void close(){dbHelper.close();}

    public Etudiant getByIdEtu(int id){
        Etudiant unEtu = null;
        Cursor curseur = database.query(true, "ETUDIANTS", new String[]{"idEtu","nomEtu","preEtu","login","mdp","mailEtu","classe","specialite","adrEtu","nomEnt","adrEnt","nomMai","preMai","telMai","mailMai","nomTut","preTut"},
               "idEtu = " + id, null,null,null,null,null);
        while(curseur.moveToNext()){
            unEtu = cursorToEtudiant(curseur);
        }
        curseur.close();
        return unEtu;
    }


    public boolean etudiantExists(int idEtu) {
        Cursor cursor = database.query(
                "ETUDIANTS",
                new String[]{"idEtu"},
                "idEtu = ?",
                new String[]{String.valueOf(idEtu)},
                null, null, null);

        boolean exists = cursor.moveToFirst();
        cursor.close();

        return exists;
    }

    public Etudiant insertEtu(Etudiant etudiant) {
        if (etudiantExists(etudiant.getIdEtu())) {
            return getByIdEtu(etudiant.getIdEtu());
        }

        ContentValues values = new ContentValues();

        values.put("idEtu", etudiant.getIdEtu());
        values.put("nomEtu", etudiant.getNomEtu());
        values.put("preEtu", etudiant.getPreEtu());
        values.put("login", etudiant.getLogin());
        values.put("mdp", etudiant.getMdp());
        values.put("mailEtu", etudiant.getMailEtu());
        values.put("classe", etudiant.getClasse());
        values.put("specialite", etudiant.getSpecialite());
        values.put("adrEtu", etudiant.getAdrEtu());
        values.put("nomEnt", etudiant.getNomEnt());
        values.put("adrEnt", etudiant.getAdrEnt());
        values.put("nomMai", etudiant.getNomMai());
        values.put("preMai", etudiant.getPreMai());
        values.put("telMai", etudiant.getTelMai());
        values.put("mailMai", etudiant.getMailMai());
        values.put("nomTut", etudiant.getNomTut());
        values.put("preTut", etudiant.getPreTut());

        // insertion avec ID explicite
        database.insertWithOnConflict("ETUDIANTS", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return etudiant;
    }


    public Etudiant cursorToEtudiant(Cursor cursor){
        int idEtu = cursor.getColumnIndexOrThrow("idEtu");
        int nomEtu = cursor.getColumnIndexOrThrow("nomEtu");
        int preEtu = cursor.getColumnIndexOrThrow("preEtu");
        int login = cursor.getColumnIndexOrThrow("login");
        int mdp = cursor.getColumnIndexOrThrow("mdp");
        int mailEtu = cursor.getColumnIndexOrThrow("mailEtu");
        int classe = cursor.getColumnIndexOrThrow("classe");
        int specialite = cursor.getColumnIndexOrThrow("specialite");
        int adrEtu = cursor.getColumnIndexOrThrow("adrEtu");
        int nomEnt = cursor.getColumnIndexOrThrow("nomEnt");
        int adrEnt = cursor.getColumnIndexOrThrow("adrEnt");
        int nomMai = cursor.getColumnIndexOrThrow("nomMai");
        int preMai = cursor.getColumnIndexOrThrow("preMai");
        int telMai = cursor.getColumnIndexOrThrow("telMai");
        int mailMai = cursor.getColumnIndexOrThrow("mailMai");
        int nomTut = cursor.getColumnIndexOrThrow("nomTut");
        int preTut = cursor.getColumnIndexOrThrow("preTut");

        int id = cursor.getInt(idEtu);
        String nomEt = cursor.getString(nomEtu);
        String preEt = cursor.getString(preEtu);
        String loginn = cursor.getString(login);
        String mdpp = cursor.getString(mdp);
        String mailEt = cursor.getString(mailEtu);
        String classeEt = cursor.getString(classe);
        String specialiteEt = cursor.getString(specialite);
        String adrEt = cursor.getString(adrEtu);
        String nomE = cursor.getString(nomEnt);
        String adrE = cursor.getString(adrEnt);
        String nomM = cursor.getString(nomMai);
        String preM = cursor.getString(preMai);
        int telM = cursor.getInt(telMai);
        String mailM = cursor.getString(mailMai);
        String nomT = cursor.getString(nomTut);
        String preT = cursor.getString(preTut);

        return new Etudiant(id, nomEt, preEt, loginn, mdpp, mailEt, classeEt, specialiteEt, adrEt, nomE, adrE, nomM, preM, telM, mailM, nomT, preT);
    }
    public void deleteAllEtudiants() {
        database.delete("ETUDIANTS", null, null);
    }
}
