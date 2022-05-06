package com.ncr.tx.apitoolkit.training;

import java.util.SortedMap;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;

public class ConsumerAction {
	public static final String PARAMETER_BROKER_URL = "brokerUrl";
	public static final String PARAMETER_SUBSCRIPTION_MODEL = "subscriptionModel";
	public static final String PARAMETER_NAME = "name";
	public static final String PARAMETER_TIMEOUT = "timeout";
	public static final String PARAMETER_REPLY_MESSAGE = "replyText";
	public static final String OUTPUT_NAME = "consumerActionResult";
	public static final String[] INVOKE_REQUIRED_PARAMETERS = {PARAMETER_BROKER_URL, PARAMETER_SUBSCRIPTION_MODEL, PARAMETER_NAME, PARAMETER_TIMEOUT, PARAMETER_REPLY_MESSAGE};

	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		Logger logger = (Logger) parameters.get("*Logger");
		logger.info("Inside action");
		String brokerUrl = parameters.get(PARAMETER_BROKER_URL).toString();
		String subscriptionModel = parameters.get(PARAMETER_SUBSCRIPTION_MODEL).toString();
		String name = parameters.get(PARAMETER_NAME).toString();
		String replyText = parameters.get(PARAMETER_REPLY_MESSAGE).toString();
		Integer timeout = Integer.parseInt(parameters.get(PARAMETER_TIMEOUT).toString());
		boolean isQueueRequest = false;

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		try {
			// Connect to ActiveMQ message broker
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Start a session for message receiving
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Receive message from the specified topic/queue
			Destination destination;
			if (subscriptionModel.equalsIgnoreCase("Topic")) {
				destination = session.createTopic(name);
			} else if (subscriptionModel.equalsIgnoreCase("Queue")) {
				isQueueRequest = true;
				destination = session.createQueue(name);
			} else {
				fields.put(OUTPUT_NAME, "Error: subscription model must be either topic or queue");
				connection.close();
				return 0;
			}

			MessageConsumer consumer = session.createConsumer(destination);

			// Consumer waits for message until timeout expires
			TextMessage message = (TextMessage) consumer.receive(timeout);
			if (message != null) {
				// Specify output fields
				fields.put(OUTPUT_NAME, message.getText());

				// For queue request: Get reply destination from request message
				if (isQueueRequest) {
					Destination replyDestination = message.getJMSReplyTo();
					// Send a reply to the reply destination
					TextMessage replyMessage = session.createTextMessage(replyText);
					replyMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					MessageProducer replyProducer = session.createProducer(replyDestination);
					replyProducer.send(replyMessage);
				}
			} else {
				fields.put(OUTPUT_NAME, "Timeout: no message received");
			}

			connection.close();
		} catch (JMSException e) {
			logger.error("Exception occurred while trying to receive message from topic:{}", e.getMessage());
		}
		return 0;
	}
}
