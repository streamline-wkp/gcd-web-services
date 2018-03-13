package com.fingersoft.gcd.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.fingersoft.gcd.model.GcdSource;

@Stateless
public class GcdSourceRegistration {
	@Inject
	private Logger logger;

	@Inject
	private EntityManager em;

	@Inject
	private Event<GcdSource> sourceEventSrc;

	public void register(GcdSource source) throws Exception {
		logger.info("Registering GCD source: " + source.toString());
		
		em.persist(source);
		sourceEventSrc.fire(source);
	}
}
