package com.espi.analyseBesoin.ControleAccess;

public class Badge {

    private boolean isBlocked;

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
}
