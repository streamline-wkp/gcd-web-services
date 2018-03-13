package com.fingersoft.gcd.webservice.soap.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebService;

import com.fingersoft.gcd.data.GcdResultRepository;
import com.fingersoft.gcd.jms.MessageReceiverSync;
import com.fingersoft.gcd.model.GcdResult;
import com.fingersoft.gcd.service.GcdResultRegistration;
import com.fingersoft.gcd.webservice.soap.GcdService;

@WebService(endpointInterface = "com.fingersoft.gcd.webservice.soap.GcdService", serviceName = "GcdWS")
public class GcdServiceImpl implements GcdService {
	@Inject
	private Logger logger;

	@Inject
	private GcdResultRepository repository;

	@Inject
	private GcdResultRegistration registration;

	@Inject
	private MessageReceiverSync receiver;

	@Override
	public int gcd() throws Exception {
		int gcd = 0;
		try {
			int[] numbers = receiver.receive();

			if (numbers.length == 2) {
				gcd = BigInteger.valueOf(numbers[0]).gcd(BigInteger.valueOf(numbers[1])).intValue();

				registration.register(new GcdResult(gcd));
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "Unknown error occured.", e);
			throw e;
		}

		return gcd;
	}

	@Override
	public List<Integer> gcdList() {
		List<GcdResult> results = repository.findAll();

		List<Integer> gcdList = new ArrayList<>(results.size());
		for (GcdResult result : results) {
			gcdList.add(result.getGcd());
		}

		return gcdList;
	}

	@Override
	public int gcdSum() {
		return repository.sumAll();
	}
}
