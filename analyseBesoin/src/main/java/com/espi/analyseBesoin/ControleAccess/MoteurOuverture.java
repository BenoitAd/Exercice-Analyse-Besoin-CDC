package com.espi.analyseBesoin.ControleAccess;

import java.util.Arrays;

public class MoteurOuverture implements IPorte, ILecteur {
    private IPorte[] portes;
    private boolean badgeDetecte;

    public MoteurOuverture(IPorte... portes) {

        this.portes = portes;
        this.badgeDetecte = false;
    }

    public void Interroger(ILecteur lecteur) {
        if (lecteur.BadgeDetecte()) {
            badgeDetecte = true;
            for (IPorte porte : portes) {
                porte.Ouvrir();
            }
        }
    }

    @Override
    public boolean BadgeDetecte() {
        return true;
    }

    @Override
    public void Ouvrir() {

    }

}
