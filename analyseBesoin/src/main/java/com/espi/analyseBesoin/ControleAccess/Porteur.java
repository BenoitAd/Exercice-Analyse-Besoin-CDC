package com.espi.analyseBesoin.ControleAccess;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


public class Porteur extends Observable {
    private String nom;
    private String prenom;
    private List<Badge> badges;

    public Porteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPorteurName() {
        return this.nom + " " + this.prenom;
    }
    public List<Badge> getBadges() {
        return badges != null ? badges : Collections.emptyList();
    }

    public void supprimer() {
        // Met à jour le statut du porteur (supprimé)
        this.nom = null;
        this.prenom = null;
        setChanged();
        notifyObservers();
    }




}
