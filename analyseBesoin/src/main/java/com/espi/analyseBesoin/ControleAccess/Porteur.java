package com.espi.analyseBesoin.ControleAccess;

public class Porteur {
    private String nom;
    private String prenom;

    public Porteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPorteurName() {
        return this.nom + " " + this.prenom;
    }




}
