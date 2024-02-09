package com.espi.analyseBesoin.ControleAccess;

public class Badge {

    private boolean isBlocked;

    private String porteur;


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

    public String getPorteur() {
        return this.porteur;
    }

    public void associer(String nouveauPorteur) {
        if(nouveauPorteur != null) {
            this.porteur = nouveauPorteur;
        }
        this.porteur = porteur;
    }

    public void desassocier() {
        this.porteur = "";
    }

}
