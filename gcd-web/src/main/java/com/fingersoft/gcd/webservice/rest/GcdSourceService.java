package com.fingersoft.gcd.webservice.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fingersoft.gcd.data.GcdSourceRepository;
import com.fingersoft.gcd.jms.MessageSender;
import com.fingersoft.gcd.model.GcdSource;
import com.fingersoft.gcd.service.GcdSourceRegistration;

/**
 * Restful web service that exposes two methods.
 *  
 * @author KevinWang
 *
 */
@Path("/source")
@RequestScoped
public class GcdSourceService {
	@Inject
    private Logger logger;
	
	@Inject
	private GcdSourceRepository repository;
	
	@Inject
	private GcdSourceRegistration registration;
	
	@Inject
	private MessageSender sender;

	/**
	 * Pushes two numbers to the JMS queue and database.
	 * 
	 * @param i1 	number 1 to be pushed. 
	 * @param i2	number 2 to be pushed.
	 * 
	 * @return Status of the request. 
	 *         "OK" with code 200 if successfully processed.
	 *          Error message with code 400 if any exception occurred.
	 */
	@POST
	@Path("/push")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response push(@FormParam("i1") int i1, @FormParam("i2") int i2) {

		Response.ResponseBuilder builder = null;
		
		try {
			GcdSource source = new GcdSource(i1, i2);
			
			sender.send(source);
			registration.register(source);
			
			builder = Response.ok().entity("OK");
		} catch (Exception e) {
			logger.log(Level.WARNING, "Unknown error occured.", e);
			builder = Response.status(Response.Status.BAD_REQUEST).entity("Unknown error occured.");
		}
		
		return builder.build();
	}

	/**
	 * Returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure.
	 * 
	 * @return List of the numbers ever added.
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> list() {
		List<GcdSource> sources = repository.findAll();

		List<Integer> numbers = new ArrayList<>(sources.size() * 2);
		for (GcdSource source : sources) {
			numbers.add(source.getNumber1());
			numbers.add(source.getNumber2());
		}

		return numbers;
	}

}
