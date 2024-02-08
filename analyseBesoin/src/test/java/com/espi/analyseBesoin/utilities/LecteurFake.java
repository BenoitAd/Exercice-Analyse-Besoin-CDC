package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.Badge;
import com.espi.analyseBesoin.ControleAccess.ILecteur;

public class LecteurFake implements ILecteur {
    private boolean _badgeDétectéAuProchainAppel;


    public boolean BadgeDetecte() {
        var réponse = _badgeDétectéAuProchainAppel;
        _badgeDétectéAuProchainAppel = false;
        return réponse;
    }
    boolean _badgeDetecteAuProchainAppel;

    public void SimulerPresentationBadge() {
        _badgeDétectéAuProchainAppel = true;
    }

    public LecteurFake(Badge badge) {

    }

}
