package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.ILecteur;

public class LecteurFake implements ILecteur {
    private boolean _badgeDétectéAuProchainAppel;

    public boolean BadgeDetecte() {
        var réponse = _badgeDétectéAuProchainAppel;
        _badgeDétectéAuProchainAppel = false;
        return réponse;
    }

    public void SimulerPrésentationBadge()
    {
        _badgeDétectéAuProchainAppel = true;
    }

}
