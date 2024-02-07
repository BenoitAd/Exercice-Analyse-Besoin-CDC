package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.ILecteur;

public class LecteurFake implements ILecteur {
    private boolean _badgeDetecteAuProchainAppel;

    public boolean BadgeDetecte() {
        var reponse = _badgeDetecteAuProchainAppel;
        _badgeDetecteAuProchainAppel = false;
        return reponse;
    }

    public void SimulerPrésentationBadge()
    {
        _badgeDetecteAuProchainAppel = true;
    }

}
