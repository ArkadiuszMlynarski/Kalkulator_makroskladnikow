package com.example.msi.myapplication;

public class dish {

    private String nazwa;
    private String bialko;
    private String weglowodany;
    private String tluszcze;
    private String kcal;

    public dish(String nazwa, String bialko, String weglowodany, String tluszcze, String kcal) {
        this.nazwa = nazwa;
        this.bialko = bialko;
        this.weglowodany = weglowodany;
        this.tluszcze = tluszcze;
        this.kcal = kcal;
    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getBialko() {
        return bialko;
    }

    public void setBialko(String bialko) {
        this.bialko = bialko;
    }

    public String getWeglowodany() {
        return weglowodany;
    }

    public void setWeglowodany(String weglowodany) {
        this.weglowodany = weglowodany;
    }

    public String getTluszcze() {
        return tluszcze;
    }

    public void setTluszcze(String tluszcze) {
        this.tluszcze = tluszcze;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }
}
