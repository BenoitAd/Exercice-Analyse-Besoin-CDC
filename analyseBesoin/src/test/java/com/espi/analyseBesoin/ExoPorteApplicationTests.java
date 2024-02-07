package com.espi.analyseBesoin;

import com.espi.analyseBesoin.ControleAccess.MoteurOuverture;
import com.espi.analyseBesoin.utilities.LecteurFake;
import com.espi.analyseBesoin.utilities.PorteSpy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
		var lecteur = new LecteurFake();
		lecteur.SimulerPrésentationBadge();

		var porte = new PorteSpy();
		var moteur = new MoteurOuverture(porte);

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
		var lecteur = new LecteurFake();
		lecteur.SimulerPrésentationBadge();

		var porte = new PorteSpy();
		var moteur = new MoteurOuverture(porte);

		// ALORS cette porte ne s'ouvre pas
		assertEquals(0, porte.NombreAppelsMéthodeOuvrir);
	}

	@Test
	public void CasSansPrésentation()
	{
		// ETANT DONNE un lecteur
		// ET une porte lui étant liée
		var lecteur = new LecteurFake();
		var porte = new PorteSpy();
		var moteur = new MoteurOuverture(porte);

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
		var lecteur = new LecteurFake();
		lecteur.SimulerPrésentationBadge();

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
		var lecteur = new LecteurFake();
		var porte = new PorteSpy();
		var moteur = new MoteurOuverture(porte);

		// QUAND aucun badge n'est présenté


		// ALORS la porte ne s'ouvre pas
		assertEquals(0, porte.getNombreAppelsMéthodeOuvrir());
	}

	@Test
	void CasBadgePresenteMaisPorteNeSouvrePas() {
		// ETANT DONNE un lecteur lié à une porte
		var lecteur = new LecteurFake();
		var porte = new PorteSpy();
		var moteur = new MoteurOuverture(porte);

		// QUAND un badge est présenté
		lecteur.SimulerPrésentationBadge();

		// ALORS cette porte ne s'ouvre pas
		assertEquals(0, porte.getNombreAppelsMéthodeOuvrir());
	}

}
