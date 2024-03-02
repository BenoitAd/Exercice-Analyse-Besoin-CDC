package com.espi.analyseBesoin;

import com.espi.analyseBesoin.ControleAccess.Badge;
import com.espi.analyseBesoin.ControleAccess.IPorte;
import com.espi.analyseBesoin.ControleAccess.MoteurOuverture;
import com.espi.analyseBesoin.ControleAccess.Porteur;
import com.espi.analyseBesoin.utilities.LecteurFake;
import com.espi.analyseBesoin.utilities.PorteSpy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        Porteur porteur = new Porteur("nomPorteur", "prenomPorteur");
        Badge badge = new Badge();
        // QUAND on associe un porteur à un badge
        badge.associer(porteur);
        // ALORS ce badge est associé a ce porteur
        assertEquals(porteur.getPorteurName(), badge.getPorteur().getPorteurName());
    }

    @Test
    public void estAttribuePuisDesattribueAUnBadge() {
        // ETANT donné un Badge ainsi qu'un Porteur
        Porteur porteur = new Porteur("nomPorteur", "prenomPorteur");
        Badge badge = new Badge();
        // QUAND on associe un porteur à un badge et puis je le désassocie
        badge.associer(porteur);
        badge.desassocier();
        // ALORS ce badge est associé a aucun porteur
        assertNull(badge.getPorteur());
    }

    @Test
    public void desassociationSansAssociationNeChangePasLeBadge() {
        // ETANT donné un Badge
        Badge badge = new Badge();

        // QUAND on tente de désassocier un porteur sans avoir été préalablement associé
        badge.desassocier();

        // ALORS le porteur associé au badge reste null
        assertNull(badge.getPorteur());
    }

    @Test
    public void tentativeAssociationAvecPorteurInvalideNeChangePasLeBadge() {
        // ETANT donné un Badge ainsi qu'un Porteur
        Porteur porteurValide = new Porteur("nomPorteur", "prenomPorteur");
        Porteur porteurInvalide = null;  // Porteur invalide

        Badge badge = new Badge();

        // QUAND on associe un badge à un porteur valide
        badge.associer(porteurValide);

        // ET qu'on tente d'associer ce même badge a un porteur invalide
        badge.associer(porteurInvalide);

        // ALORS le porteur associé au badge reste inchangé
        assertEquals(porteurValide.getPorteurName(), badge.getPorteur().getPorteurName());
    }

    @Test
    public void desassocierBadgeDoitRemettrePorteurAVide() {
        // Étant donné un Badge associé à un porteur
        Badge badge = new Badge();
        Porteur porteur = new Porteur("nomPorteur", "prenomPorteur");
        badge.associer(porteur);
        // Quand on désassocie le badge
        badge.desassocier();

        // Alors le porteur associé au badge est null
        assertNull(badge.getPorteur());
    }

    @Test
    public void badgeBloqueNeChangePasAvecNouveauPorteur() {
        // ETANT DONNE un badge bloqué
        Badge badge = new Badge();
        badge.bloquer();

        // QUAND on tente d'associer un nouveau porteur au badge bloqué
        badge.associer("nomPorteur", "prenomPorteur");
        // ALORS le badge doit rester bloqué
        assertTrue(badge.isBlocked());
        // ET le porteur associé au badge doit rester null
        assertNull(badge.getPorteur());
    }
    @Test
    public void badgeBloqueAvecporteurdebloque()
    {
        //ETANT donné un badge bloqué et qui est associé au porteur
        Badge badge = new Badge();
        badge.bloquer();
        badge.associer("nomPorteur", "prenomPorteur");
        //QUAND on débloque
        badge.debloquer();
        //Alors le badge n'est plus bloqué
        assertEquals(false, badge.isBlocked());
    }

    @Test
    public void badgeBloqueDesassocier()
    {
        //ETANT donné un badge associé au porteur
        Badge badge = new Badge();
        badge.associer("nomPorteur", "prenomPorteur");
        //QUAND le badge est bloqué
        badge.bloquer();
        //Alors le badge est desassocié au porteur
        badge.desassocier();
        assertNull(badge.getPorteur());
    }
    @Test
    public void badgeDesassocierBloque()
    {
        //Etant donné un badge desassocier au porteur
        Badge badge = new Badge();
        badge.desassocier();
        //QUAND le badge n'est pas bloqué
        badge.isBlocked();
        //ALORS le badge doit etre bloquer
        badge.bloquer();
        assertEquals(true, badge.isBlocked());

    }

    @Test
    public void badgeAssocierPorteurNomPrenom()
    {
        //Etant donné un badge associé au porteur
        Badge badge = new Badge();
        Porteur porteur = new Porteur("Nom", "Prenom");
        //Quand le badge est associé au porteur
        badge.associer(porteur);
        //Alors le badge est associé au porteur
        assertEquals( porteur.getPorteurName() , badge.getPorteur().getPorteurName());
    }

    @Test
    public void porteurAssoicieADeuxBadge()
    {
        //Etant donné un porteur
        Porteur porteur = new Porteur("Nom", "Prenom");
        //Quand le porteur est associé à deux badges
        Badge badge1 = new Badge();
        Badge badge2 = new Badge();
        badge1.associer(porteur);
        badge2.associer(porteur);
        //Alors le porteur est associé à deux badges
        assertEquals( porteur.getPorteurName() , badge1.getPorteur().getPorteurName());
        assertEquals( porteur.getPorteurName() , badge2.getPorteur().getPorteurName());
    }

    @Test
    public void supprimerPorteurAssocieAUnBadge()
    {
        //Etant donné un porteur associé à un badge
        Porteur porteur = new Porteur("Nom", "Prenom");
        Badge badge = new Badge();
        badge.associer(porteur);
        //Quand le porteur est supprimé
        porteur = null;
        //Alors le badge est desassocié
        assertNull(badge.getPorteur());
    }

}
