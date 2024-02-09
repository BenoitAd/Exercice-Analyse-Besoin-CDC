package com.espi.analyseBesoin;

import com.espi.analyseBesoin.ControleAccess.Badge;
import com.espi.analyseBesoin.ControleAccess.IPorte;
import com.espi.analyseBesoin.ControleAccess.MoteurOuverture;
import com.espi.analyseBesoin.utilities.LecteurFake;
import com.espi.analyseBesoin.utilities.PorteSpy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExoPorteApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void casNominal() {
        // ETANT DONNE un lecteur ayant détecté un badge
        // ET une porte lui étant liée
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND le moteur d'ouverture interroge ce lecteur
        moteur.Interroger(lecteur);

        // ALORS cette porte s'ouvre
        assertEquals(1, porte.getNombreAppelsMéthodeOuvrir());
    }

    @Test
    void CasSansInterrogation() {
        // ETANT DONNE un lecteur ayant détecté un badge
        // ET une porte lui étant liée
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        // QUAND aucun badge n'est présenté
        var porte = new PorteSpy();
        ArrayList<IPorte> portes = new ArrayList<>();
        var moteur = new MoteurOuverture(porte);

        // ALORS cette porte ne s'ouvre pas
        assertEquals(0, porte.NombreAppelsMéthodeOuvrir);
    }

    @Test
    public void CasSansPrésentation() {
        // ETANT DONNE un lecteur
        // ET une porte lui étant liée
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND le moteur d'ouverture interroge ce lecteur
        moteur.Interroger(lecteur);

        // ALORS cette porte ne s'ouvre pas
        assertEquals(0, porte.NombreAppelsMéthodeOuvrir);
    }

    @Test
    public void CasPrésentationPuisRien() {
        // ETANT DONNE un lecteur ayant détecté un badge
        // ET une porte lui étant liée
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND le moteur d'ouverture interroge ce lecteur deux fois
        moteur.Interroger(lecteur);
        moteur.Interroger(lecteur);

        // ALORS cette porte s'ouvre une fois
        assertEquals(1, porte.NombreAppelsMéthodeOuvrir);
    }

    @Test
    void CasAucunBadgePresente() {
        // ETANT DONNE un lecteur lié à une porte
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND aucun badge n'est présenté


        // ALORS la porte ne s'ouvre pas
        assertEquals(0, porte.getNombreAppelsMéthodeOuvrir());
    }

    @Test
    public void CasLecteurLieeADeuxPortes() {
        // ETANT DONNE un lecteur ayant détecté un badge
        // ET deux portes lui étant liée
        Badge badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        var porte1 = new PorteSpy();
        var porte2 = new PorteSpy();
        IPorte[] portes = {porte1, porte2};
        var moteur1 = new MoteurOuverture(portes);

        // QUAND le moteur d'ouverture interroge ce lecteur
        moteur1.Interroger(lecteur);

        // ALORS les deux portes s'ouvrent
        assertEquals(1, porte1.getNombreAppelsMéthodeOuvrir());
        assertEquals(1, porte2.getNombreAppelsMéthodeOuvrir());
    }

    @Test
    public void CasBadgeBloqueDebloque() {
        // ETANT DONNE un lecteur ayant détecté un badge
        // ET une porte lui étant liée
        var badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND un badge est bloqué puis débloqué est présenté
        badge.bloquer();
        badge.debloquer();
        moteur.Interroger(lecteur);

        // ALORS cette porte s'ouvre
        assertEquals(1, porte.getNombreAppelsMéthodeOuvrir());
    }


    @Test
    public void CasBadgeBloque() {
        // ETANT DONNE un lecteur liée a une porte
        var badge = new Badge();
        var lecteur = new LecteurFake(badge);
        lecteur.SimulerPresentationBadge();

        var porte = new PorteSpy();
        var moteur = new MoteurOuverture(porte);

        // QUAND un badge est bloqué est présenté
        // ET que ce lecteur est interrogé
        badge.bloquer();
        moteur.Interroger(lecteur);

        // ALORS cette porte ne s'ouvre pas
        assertEquals(1, porte.getNombreAppelsMéthodeOuvrir());
    }

    @Test
    public void estAttribueAUnBadge() {
        // ETANT donné un Badge ainsi qu'un Porteur
        String porteur = "porteur";
        Badge badge = new Badge();
        // QUAND on associe un porteur à un badge
        badge.associer(porteur);
        // ALORS ce badge est associé a ce porteur
        assertEquals(porteur, badge.getPorteur());
    }

    @Test
    public void estAttribuePuisDesattribueAUnBadge() {
        // ETANT donné un Badge ainsi qu'un Porteur
        String porteur = "porteur";
        Badge badge = new Badge();
        // QUAND on associe un porteur à un badge et puis je le désassocie
        badge.associer(porteur);
        badge.desassocier();
        // ALORS ce badge est associé a ce porteur
        assertEquals("", badge.getPorteur());
    }

    @Test
    public void desassociationSansAssociationNeChangePasLeBadge() {
        // ETANT donné un Badge
        Badge badge = new Badge();

        // QUAND on tente de désassocier un porteur sans avoir été préalablement associé
        badge.desassocier();

        // ALORS le porteur associé au badge reste une chaîne vide
        assertEquals("", badge.getPorteur());
    }

    @Test
    public void tentativeAssociationAvecPorteurInvalideNeChangePasLeBadge() {
        // ETANT donné un Badge ainsi qu'un Porteur
        String porteurValide = "porteurValide";
        String porteurInvalide = null;  // Porteur invalide

        Badge badge = new Badge();

        // QUAND on associe un porteur valide à un badge
        badge.associer(porteurValide);

        // ET qu'on tente d'associer un porteur invalide au badge
        badge.associer(porteurInvalide);

        // ALORS le porteur associé au badge reste inchangé
        assertEquals(porteurValide, badge.getPorteur());
    }




}
