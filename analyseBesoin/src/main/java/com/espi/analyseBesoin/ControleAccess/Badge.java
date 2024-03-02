package com.espi.analyseBesoin.ControleAccess;

import java.util.Observable;
import java.util.Observer;

public class Badge implements Observer {

    private boolean isBlocked;

    private Porteur porteur;


    public Badge() {
        this.isBlocked = false;
    }

    public void bloquer() {
        this.isBlocked = true;
    }

    public void debloquer() {
        this.isBlocked = false;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public Porteur getPorteur() {
        return this.porteur;
    }

    public void associer(String nom, String prenom) {
        if (!isBlocked && nom != null && prenom != null) {
            this.porteur = new Porteur(nom, prenom);
        }
        else if (isBlocked) {
            this.porteur = null;
        }
    }

    public void associerPorteur(Porteur porteur) {
        if (!isBlocked && porteur != null) {
            this.porteur = porteur;
            porteur.addObserver(this);
        }
        else if (isBlocked) {
            this.porteur = null;
        }
    }


    public void desassocierPorteur() {
        if (porteur != null) {
            porteur.deleteObserver(this);
            porteur = null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // Mise à jour lorsque le porteur est supprimé
        if (o instanceof Porteur) {
            desassocierPorteur();
        }
    }

}
