package net.infobosccoma.JocDeTrons;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.ArrayList;
/**
 * Classe que implementa la interface de gestió de contactes
 * 
 * @author Marc
 *
 */
public class GestorContactes implements ContactListener {
	//<editor-fold desc="Atributs">
	// de moment, no utilitzat
	private ArrayList<Body> bodyDestroyList;
	//</editor-fold>

	//<editor-fold desc="Constructors">
	public GestorContactes() {
		
	}
	
	public GestorContactes(ArrayList<Body> bodyDestroyList) {
		this.bodyDestroyList = bodyDestroyList;
	}
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.getBody().getUserData() == null
				|| fixtureB.getBody().getUserData() == null) {
			return;
		}

		if (fixtureA.getBody().getUserData().equals("personatge")
				&& fixtureB.getBody().getUserData().equals("primerObjecte")
				|| fixtureA.getBody().getUserData().equals("primerObjecte")
				&& fixtureB.getBody().getUserData().equals("personatge")) {
			/*
			 * Afegir cos a destruir
			 * 
			 * if(!fixtureA.getBody().getUserData().equals("personatge")) {
				bodyDestroyList.add(fixtureA.getBody());
			} else {
				bodyDestroyList.add(fixtureB.getBody());
			}*/
		}
	}
	//</editor-fold>

	//<editor-fold desc="Mètodes implementats de la interface ContactListener">
	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
	//</editor-fold>

}
