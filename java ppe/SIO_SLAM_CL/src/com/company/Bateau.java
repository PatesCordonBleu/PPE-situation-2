package com.company;

public class Bateau {

    protected int idBat;
    protected String nomBat;
    protected float longueurBat;
    protected float largeurBat;

    public Bateau(String nom){
        this.nomBat = nom;
    }

    public Bateau(int id, String nom, float longueur, float largeur){
        this.largeurBat = largeur;
        this.longueurBat = longueur;
        this.nomBat = nom;
        this.idBat = id;
    }

    public String toString(){
        String sBat = "";
        sBat += "Nom du bateau : "+this.nomBat+"\n";
        sBat += "Longueur : "+this.longueurBat+"\n";
        sBat += "Largeur : "+this.largeurBat+"\n";

        return sBat;
    }

    public String getNomBat(){
        return this.nomBat;
    }


}
