package com.example.fsi_mobile;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bilan1 {
    @SerializedName("idBil1")
    private int idBil1;

    @SerializedName("datevisiteBil1")
    private Date dateVis1;

    @SerializedName("notdossBil1")
    private float noteDoss1;

    @SerializedName("notorBil1")
    private float noteOral1;

    @SerializedName("notentBil1")
    private float noteEnt1;

    @SerializedName("moyBil1")
    private float moyenne1;

    @SerializedName("remarqueBil1")
    private String remarque;

    public Bilan1(int idBil1, Date dateVis1, float noteDoss1, float noteOral1, float noteEnt1, float moyenne1, String remarque) {
        this.idBil1 = idBil1;
        this.dateVis1 = dateVis1;
        this.noteDoss1 = noteDoss1;
        this.noteOral1 = noteOral1;
        this.noteEnt1 = noteEnt1;
        this.moyenne1 = moyenne1;
        this.remarque = remarque;
    }

    public Bilan1(){}

    public int getIdBil1() {
        return idBil1;
    }

    public void setIdBil1(int idBil1) {
        this.idBil1 = idBil1;
    }

    public Date getDateVis1() {
        return dateVis1;
    }

    public void setDateVis1(Date dateVis1) {
        this.dateVis1 = dateVis1;
    }

    public float getNoteDoss1() {
        return noteDoss1;
    }

    public void setNoteDoss1(float noteDoss1) {
        this.noteDoss1 = noteDoss1;
    }

    public float getNoteOral1() {
        return noteOral1;
    }

    public void setNoteOral1(float noteOral1) {
        this.noteOral1 = noteOral1;
    }

    public float getNoteEnt1() {
        return noteEnt1;
    }

    public void setNoteEnt1(float noteEnt1) {
        this.noteEnt1 = noteEnt1;
    }

    public float getMoyenne1() {
        return moyenne1;
    }

    public void setMoyenne1(float moyenne1) {
        this.moyenne1 = moyenne1;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
