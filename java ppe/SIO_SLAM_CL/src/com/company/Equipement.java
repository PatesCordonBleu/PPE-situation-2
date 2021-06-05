package com.company;

public class Equipement {

    private int idEquip;

    private String libEquip;

    public Equipement(int idEquip, String libEquip){
        this.idEquip = idEquip;
        this.libEquip = libEquip;
    }

    public String getLibEquip(){
        return libEquip;
    }
}
