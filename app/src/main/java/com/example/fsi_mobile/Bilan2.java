package com.example.fsi_mobile;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bilan2 {
    private int idEtu;
    @SerializedName("idBil2")
    private int idBil2;

    @SerializedName("datevisiteBil2")
    private Date dateVis2;

    @SerializedName("notdossBil2")
    private float noteDoss2;

    @SerializedName("notorBil2")
    private float noteOral2;

    @SerializedName("notentBil1")
    private float noteEnt2;

    @SerializedName("moyBil2")
    private float moyenne2;

    public Bilan2( int idBil2, int idEtu, Date dateVis2, float noteDoss2, float noteOral2, float noteEnt2, float moyenne2) {
        this.idEtu = idEtu;
        this.idBil2 = idBil2;
        this.dateVis2 = dateVis2;
        this.noteDoss2 = noteDoss2;
        this.noteOral2 = noteOral2;
        this.noteEnt2 = noteEnt2;
        this.moyenne2 = moyenne2;
    }
    public Bilan2(){}

    public int getIdBil2() {
        return idBil2;
    }

    public void setIdBil2(int idBil2) {
        this.idBil2 = idBil2;
    }

    public Date getDateVis2() {
        return dateVis2;
    }

    public void setDateVis2(Date dateVis2) {
        this.dateVis2 = dateVis2;
    }

    public float getNoteDoss2() {
        return noteDoss2;
    }

    public void setNoteDoss2(float noteDoss2) {
        this.noteDoss2 = noteDoss2;
    }

    public float getNoteOral2() {
        return noteOral2;
    }

    public void setNoteOral2(float noteOral2) {
        this.noteOral2 = noteOral2;
    }

    public float getNoteEnt2() {
        return noteEnt2;
    }

    public void setNoteEnt2(float noteEnt2) {
        this.noteEnt2 = noteEnt2;
    }

    public float getMoyenne2() {
        return moyenne2;
    }

    public void setMoyenne2(float moyenne2) {
        this.moyenne2 = moyenne2;
    }

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }
}
