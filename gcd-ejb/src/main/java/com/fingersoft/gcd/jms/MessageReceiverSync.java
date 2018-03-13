package com.fingersoft.gcd.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Reads numbers from the head of the JMS queue.
 * 
 * @author KevinWang
 *
 */
@Stateless
public class MessageReceiverSync {
	@Inject
	private JMSContext context;

	@Resource(mappedName = "java:jboss/exported/jms/queue/gcd")
	private Queue queue;

	/**
	 * Reads two numbers from the head of the JMS queue.
	 * 
	 * @return An array with two numbers read from the queue. 
	 *         If the queue does not have numbers, a zero length array is returned. 
	 */
	public int[] receive() {
		JMSConsumer consumer = context.createConsumer(queue);
		
		Integer i1 = consumer.receiveBodyNoWait(Integer.class);
		Integer i2 = consumer.receiveBodyNoWait(Integer.class);

		if (i1 == null || i2 == null) {
			return new int[0];
		} else {
			return new int[] { i1, i2 };
		}
	}
}
