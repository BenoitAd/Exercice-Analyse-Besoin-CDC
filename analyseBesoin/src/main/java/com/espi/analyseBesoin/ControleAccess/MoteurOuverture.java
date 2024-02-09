package com.espi.analyseBesoin.ControleAccess;

import java.util.Arrays;

public class MoteurOuverture implements IPorte, ILecteur {
    private IPorte[] portes;
    private Badge[] badgesAssocies = new Badge[0];

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

    public boolean possede(Badge badge) {

        return Arrays.stream(this.badgesAssocies).filter(b -> b.equals(badge)).count() == 1;
    }

    public void associer(Badge badge) {
        Badge[] badgesMiseAJour = new Badge[this.badgesAssocies.length + 1];
        System.arraycopy(this.badgesAssocies, 0, badgesMiseAJour, 0, this.badgesAssocies.length);
        badgesMiseAJour[this.badgesAssocies.length] = badge;
        this.badgesAssocies = badgesMiseAJour;
    }
}
