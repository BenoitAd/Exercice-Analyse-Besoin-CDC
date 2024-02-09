package com.espi.analyseBesoin.utilities;

import com.espi.analyseBesoin.ControleAccess.Badge;
import com.espi.analyseBesoin.ControleAccess.ILecteur;

public class LecteurFake implements ILecteur {
    private boolean badgeDetecteAuProchainAppel;

    public boolean BadgeDetecte() {
        var reponse = badgeDetecteAuProchainAppel;
        badgeDetecteAuProchainAppel = false;
        return reponse;
    }

    public void SimulerPresentationBadge() {
        badgeDetecteAuProchainAppel = true;
    }

    public LecteurFake(Badge badge) {

    }

}
