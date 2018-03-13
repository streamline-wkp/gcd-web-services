package com.fingersoft.gcd.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.fingersoft.gcd.model.GcdResult;

/**
 * EJB bean for saving computed GCDs to the database.
 * 
 * @author KevinWang
 *
 */
@Stateless
public class GcdResultRegistration {
	@Inject
	private Logger logger;

	@Inject
	private EntityManager em;

	@Inject
	private Event<GcdResult> gcdEventSrc;

	/**
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void register(GcdResult result) throws Exception {
		logger.info("Registering GCD: " + result.toString());
		
		em.persist(result);
		gcdEventSrc.fire(result);
	}
}
