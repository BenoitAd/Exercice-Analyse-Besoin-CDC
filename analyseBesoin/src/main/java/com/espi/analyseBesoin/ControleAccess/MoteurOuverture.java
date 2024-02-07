package com.espi.analyseBesoin.ControleAccess;

public class MoteurOuverture implements IPorte, ILecteur {
    private IPorte porte ;

    public MoteurOuverture(IPorte porte)
    {
        this.porte = porte;
    }

    public void Interroger(ILecteur lecteur)
    {
        if(lecteur.BadgeDetecte())
            this.porte.Ouvrir();
    }

    @Override
    public boolean BadgeDetecte() {
        return true;
    }

    @Override
    public void Ouvrir() {

    }
}
