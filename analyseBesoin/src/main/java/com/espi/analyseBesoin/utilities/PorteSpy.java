package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.IPorte;

public class PorteSpy implements IPorte {
    public int getNombreAppelsMéthodeOuvrir() {
        return NombreAppelsMéthodeOuvrir;
    }

    public int NombreAppelsMéthodeOuvrir;

    public void Ouvrir()
    {
        NombreAppelsMéthodeOuvrir++;
    }
}
