package com.espi.analyseBesoin.ControleAccess;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


public class Porteur extends Observable {
    private String nom;
    private String prenom;
    private List<Badge> badges = new ArrayList<>();
    private boolean isBlocked;

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
    public void addBadge(Badge badge) {
        if (badges == null) {
            badges = new ArrayList<>();
        }
        badges.add(badge);
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public void assignBadge(Badge badge) {

    }


    public void blockAllBadges() {

    }


}
