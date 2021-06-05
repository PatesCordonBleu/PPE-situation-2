package com.company;

public class BateauFret extends Bateau {
    private int poidMaxBatFret;

    public BateauFret(int id, String nom, float longueur, float largeur, int poid){
        super(id, nom, longueur, largeur);
        this.poidMaxBatFret = poid;
    }

    public int getPoidMaxBatFret(){
        return this.poidMaxBatFret;
    }
}
