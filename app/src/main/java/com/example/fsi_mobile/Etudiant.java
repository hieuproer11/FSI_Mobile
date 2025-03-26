package com.example.fsi_mobile;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Etudiant {
    private int idEtu;
    private String nomEtu;
    private String preEtu;
    private String login;
    private String mdp;
    private String mailEtu;
    private String classe;

    private String specialite;
    private String adrEtu;
    private String nomEnt;
    private String adrEnt;
    private String nomMai;
    private String preMai;
    private int telMai;
    private String mailMai;
    private String nomTut;
    private String preTut;

    public Etudiant(int idEtu, String nomEtu, String preEtu, String login, String mdp, String mailEtu, String classe, String specialite, String adrEtu, String nomEnt, String adrEnt, String nomMai, String preMai, int telMai, String mailMai, String nomTut, String preTut) {
        this.idEtu = idEtu;
        this.nomEtu = nomEtu;
        this.preEtu = preEtu;
        this.login = login;
        this.mdp = mdp;
        this.mailEtu = mailEtu;
        this.classe = classe;
        this.specialite = specialite;
        this.adrEtu = adrEtu;
        this.nomEnt = nomEnt;
        this.adrEnt = adrEnt;
        this.nomMai = nomMai;
        this.preMai = preMai;
        this.telMai = telMai;
        this.mailMai = mailMai;
        this.nomTut = nomTut;
        this.preTut = preTut;
    }
    public Etudiant(){}

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }

    public String getNomEtu() {
        return nomEtu;
    }

    public void setNomEtu(String nomEtu) {
        this.nomEtu = nomEtu;
    }

    public String getPreEtu() {
        return preEtu;
    }

    public void setPreEtu(String preEtu) {
        this.preEtu = preEtu;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMailEtu() {
        return mailEtu;
    }

    public void setMailEtu(String mailEtu) {
        this.mailEtu = mailEtu;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getAdrEtu() {
        return adrEtu;
    }

    public void setAdrEtu(String adrEtu) {
        this.adrEtu = adrEtu;
    }

    public String getNomEnt() {
        return nomEnt;
    }

    public void setNomEnt(String nomEnt) {
        this.nomEnt = nomEnt;
    }

    public String getAdrEnt() {
        return adrEnt;
    }

    public void setAdrEnt(String adrEnt) {
        this.adrEnt = adrEnt;
    }

    public String getNomMai() {
        return nomMai;
    }

    public void setNomMai(String nomMai) {
        this.nomMai = nomMai;
    }

    public String getPreMai() {
        return preMai;
    }

    public void setPreMai(String preMai) {
        this.preMai = preMai;
    }

    public int getTelMai() {
        return telMai;
    }

    public void setTelMai(int telMai) {
        this.telMai = telMai;
    }

    public String getMailMai() {
        return mailMai;
    }

    public void setMailMai(String mailMai) {
        this.mailMai = mailMai;
    }

    public String getPreTut() {
        return preTut;
    }

    public void setPreTut(String preTut) {
        this.preTut = preTut;
    }

    public String getNomTut() {
        return nomTut;
    }

    public void setNomTut(String nomTut) {
        this.nomTut = nomTut;
    }


}
