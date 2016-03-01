package net.take;

import net.take.receivers.EnvelopeSender;
import net.take.receivers.MessageReceiver;
import net.take.receivers.NotificationReceiver;
import org.limeprotocol.MediaType;
import org.limeprotocol.Notification;

public class MessagingHubSenderBuilder {
    private final MessagingHubClientBuilder clientBuilder;

    protected EnvelopeSender getEnvelopeSender() {
        return clientBuilder.getMessagingHubClientInterface();
    }

    private EnvelopeListenerRegistrar envelopeRegistrar;

    protected EnvelopeListenerRegistrar getEnvelopeRegistrar() {
        return envelopeRegistrar;
    }

    public MessagingHubSenderBuilder(MessagingHubClientBuilder clientBuilder) {
        this.clientBuilder = clientBuilder;
        EnvelopeRegistrar = new EnvelopeListenerRegistrar();
    }

    public MessagingHubSenderBuilder AddMessageReceiver(MessageReceiver messageReceiver, MediaType forMimeType) {
        EnvelopeRegistrar.AddMessageReceiver(messageReceiver, forMimeType);
        return this;
    }

    public MessagingHubSenderBuilder AddMessageReceiver(Func<MessageReceiver> receiverFactory, MediaType forMimeType) {
        EnvelopeRegistrar.AddMessageReceiver(receiverFactory, forMimeType);
        return this;
    }

    public MessagingHubSenderBuilder AddNotificationReceiver(NotificationReceiver notificationReceiver, Notification.Event forEventType) {
        EnvelopeRegistrar.AddNotificationReceiver(notificationReceiver, forEventType);
        return this;
    }

    public MessagingHubSenderBuilder AddNotificationReceiver(Func<NotificationReceiver> receiverFactory, Notification.Event forEventType) {
        EnvelopeRegistrar.AddNotificationReceiver(receiverFactory, forEventType);
        return this;
    }

    public MessagingHubSender build() {
        return clientBuilder.build();
    }
}
