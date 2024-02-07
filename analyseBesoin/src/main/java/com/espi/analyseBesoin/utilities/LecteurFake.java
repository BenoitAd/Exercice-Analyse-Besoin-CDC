package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.ILecteur;

public class LecteurFake implements ILecteur {

    public LecteurFake(BadgeFake badge) {
        this.badge = badge;
    }

    public BadgeFake getBadge() {
        return badge;
    }

    private boolean _badgeDétectéAuProchainAppel;
    private BadgeFake badge;

    public boolean BadgeDetecte() {
        if (badge.getEstBloque()) {
            return false;
        }
        var réponse = _badgeDétectéAuProchainAppel;
        _badgeDétectéAuProchainAppel = false;
        return réponse;
    }

    public void SimulerPrésentationBadge()
    {
        _badgeDétectéAuProchainAppel = true;
    }

}
