package com.espi.analyseBesoin.ControleAccess;

import java.util.Arrays;

public class MoteurOuverture implements IPorte, ILecteur {
    private IPorte[] portes;

    public MoteurOuverture(IPorte... portes) {
        this.portes = portes;
    }

    public void Interroger(ILecteur lecteur) {
        if (lecteur.BadgeDetecte()) {
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
