package com.espi.analyseBesoin;

import com.espi.analyseBesoin.ControleAccess.MoteurOuverture;
import com.espi.analyseBesoin.utilities.BadgeFake;
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
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		var porte = new PorteSpy();
		ArrayList <PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte);
		var moteur = new MoteurOuverture(portes);

		// QUAND le moteur d'ouverture interroge ce lecteur
		moteur.Interroger(lecteur);

		// ALORS cette porte s'ouvre
		assertEquals(1, porte.getNombreAppelsMéthodeOuvrir());
	}

	@Test
	void CasSansInterrogation()
	{
		// ETANT DONNE un lecteur ayant détecté un badge
		// ET une porte lui étant liée
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		// QUAND aucun badge n'est présenté
		var porte = new PorteSpy();
		ArrayList <PorteSpy> portes = new ArrayList<PorteSpy>();
		var moteur = new MoteurOuverture(portes);

		// ALORS cette porte ne s'ouvre pas
		assertEquals(0, porte.NombreAppelsMéthodeOuvrir);
	}

	@Test
	public void CasSansPrésentation()
	{
		// ETANT DONNE un lecteur
		// ET une porte lui étant liée
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		var porte = new PorteSpy();
		ArrayList <PorteSpy> portes = new ArrayList<PorteSpy>();
		var moteur = new MoteurOuverture(portes);

		// QUAND le moteur d'ouverture interroge ce lecteur
		moteur.Interroger(lecteur);

		// ALORS cette porte ne s'ouvre pas
		assertEquals(0, porte.NombreAppelsMéthodeOuvrir);
	}

	@Test
	public void CasPrésentationPuisRien()
	{
		// ETANT DONNE un lecteur ayant détecté un badge
		// ET une porte lui étant liée
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		var porte = new PorteSpy();
		ArrayList <PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte);
		var moteur = new MoteurOuverture(portes);

		// QUAND le moteur d'ouverture interroge ce lecteur deux fois
		moteur.Interroger(lecteur);
		moteur.Interroger(lecteur);

		// ALORS cette porte s'ouvre une fois
		assertEquals(1, porte.NombreAppelsMéthodeOuvrir);
	}

	@Test
	void CasAucunBadgePresente() {
		// ETANT DONNE un lecteur lié à une porte
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		var porte = new PorteSpy();
		ArrayList <PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte);
		var moteur = new MoteurOuverture(portes);

		// QUAND aucun badge n'est présenté


		// ALORS la porte ne s'ouvre pas
		assertEquals(0, porte.getNombreAppelsMéthodeOuvrir());
	}
	@Test
	public void CasLecteurLieeADeuxPortes() {
		// ETANT DONNE un lecteur ayant détecté un badge
		// ET deux portes lui étant liée
		BadgeFake badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		var porte1 = new PorteSpy();
		var porte2 = new PorteSpy();
		ArrayList<PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte1);
		portes.add(porte2);
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
		var badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		var porte = new PorteSpy();
		ArrayList<PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte);
		var moteur = new MoteurOuverture(portes);

		// QUAND un badge est bloqué puis débloqué est présenté
		lecteur.getBadge().bloquer();
		lecteur.getBadge().debloquer();
		moteur.Interroger(lecteur);

		// ALORS cette porte s'ouvre
		assertEquals(1, porte.getNombreAppelsMéthodeOuvrir());

	}


	@Test
	public void CasBadgeBloque() {
		// ETANT DONNE un lecteur liée a une porte
		var badge = new BadgeFake();
		var lecteur = new LecteurFake(badge);
		lecteur.SimulerPresentationBadge();

		var porte = new PorteSpy();
		ArrayList<PorteSpy> portes = new ArrayList<PorteSpy>();
		portes.add(porte);
		var moteur = new MoteurOuverture(portes);

		// QUAND un badge est bloqué est présenté
		// ET que ce lecteur est interrogé
		lecteur.getBadge().bloquer();
		moteur.Interroger(lecteur);

		// ALORS cette porte ne s'ouvre pas
		assertEquals(0, porte.getNombreAppelsMéthodeOuvrir());
	}



}
