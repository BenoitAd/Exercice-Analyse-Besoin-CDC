package com.espi.analyseBesoin.ControleAccess;

import com.espi.analyseBesoin.utilities.PorteSpy;

import java.util.ArrayList;

public class MoteurOuverture implements IPorte, ILecteur {
    private ArrayList<PorteSpy> porte ;

    public MoteurOuverture(ArrayList<PorteSpy> porte)
    {
        this.porte = porte;
    }

    public void Interroger(ILecteur lecteur)
    {
        if(lecteur.BadgeDetecte()) {
            this.porte.forEach(PorteSpy::Ouvrir);
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
