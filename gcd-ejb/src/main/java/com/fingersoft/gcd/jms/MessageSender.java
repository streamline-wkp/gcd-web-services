package com.fingersoft.gcd.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.fingersoft.gcd.model.GcdSource;

/**
 * Pushes numbers to the JMS queue.
 * 
 * @author KevinWang
 *
 */
@Stateless
public class MessageSender {
	@Inject
	private JMSContext context;

	@Resource(mappedName = "java:jboss/exported/jms/queue/gcd")
	private Queue queue;
	
	/**
	 * Pushes two numbers to the JMS queue.
	 * 
	 * @param source	Object that contains two numbers for GCD calculation.
	 */
	//@TransactionAttribute(TransactionAttributeType.NEVER)
	public void send(GcdSource source) {
		context.createProducer().send(queue, source.getNumber1());
		context.createProducer().send(queue, source.getNumber2());
	}
}
