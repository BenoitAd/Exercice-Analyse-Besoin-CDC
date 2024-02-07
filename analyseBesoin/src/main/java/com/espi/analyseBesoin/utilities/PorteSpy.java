package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.IPorte;

public class PorteSpy implements IPorte {
    public int getNombreAppelsMethodeOuvrir() {
        return NombreAppelsMethodeOuvrir;
    }

    public void setNombreAppelsMethodeOuvrir(int nombreAppelsMethodeOuvrir) {
        NombreAppelsMethodeOuvrir = nombreAppelsMethodeOuvrir;
    }

    public int NombreAppelsMethodeOuvrir;



    public void Ouvrir()
    {
        NombreAppelsMethodeOuvrir++;
    }
}
