package com.espi.analyseBesoin.utilities;

public class BadgeFake {

    public BadgeFake()
    {
        EstBloque = false;
    }
    public boolean getEstBloque() {
        return EstBloque;
    }

    public boolean EstBloque;

    public void debloquer()
    {
        EstBloque = false;
    }

    public void bloquer()
    {
        EstBloque = true;
    }
}
