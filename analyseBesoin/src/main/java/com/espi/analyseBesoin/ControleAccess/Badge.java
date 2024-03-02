package com.espi.analyseBesoin.ControleAccess;

public class Badge {

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

    public void associer(Porteur porteur) {
        if (!isBlocked && porteur != null) {
            this.porteur = porteur;
        }
        else if (isBlocked) {
            this.porteur = null;
        }
    }


    public void desassocier() {
        this.porteur = null;
    }

}
