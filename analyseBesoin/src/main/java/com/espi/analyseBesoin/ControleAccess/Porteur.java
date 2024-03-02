package com.espi.analyseBesoin.ControleAccess;
import java.util.Observable;


public class Porteur extends Observable {
    private String nom;
    private String prenom;

    public Porteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPorteurName() {
        return this.nom + " " + this.prenom;
    }

    public void supprimer() {
        // Met à jour le statut du porteur (supprimé)
        this.nom = null;
        this.prenom = null;
        setChanged();
        notifyObservers();
    }




}
