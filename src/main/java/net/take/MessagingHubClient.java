package net.take;

/***
 * Allow a client application to connect, send an receive messages, commands and notifications in the Messaging Hub.
 * <a src="http://messaginghub.io" />
 */
public interface MessagingHubClient extends MessagingHubSender, MessagingHubEnvelopeReceiver {
}
