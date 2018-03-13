package com.fingersoft.gcd.webservice.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * SOAP web service interface.
 * 
 * @author KevinWang
 *
 */
@WebService
public interface GcdService {
	/**
	 * returns the GCD (greatest common divisor) of the two integers at the head of the queue. 
	 * These two elements will subsequently be discarded from the queue 
	 * and the head replaced by the next two in line.
	 * 
	 * The computed GCD is added to a database for future reference. 
	 * 
	 * @return The greatest common divisor. 0 if there is no numbers in the queue.
	 * @throws Exception
	 */
	@WebMethod
	int gcd() throws Exception;

	/**
	 * Returns a list of all the computed GCDs from a database.
	 *  
	 * @return A list of all the computed GCDs.
	 */
	@WebMethod
	List<Integer> gcdList();

	/**
	 * Returns the sum of all computed GCDs from a database.
	 * 
	 * @return The sum of all computed GCDs.
	 */
	@WebMethod
	int gcdSum();
}
