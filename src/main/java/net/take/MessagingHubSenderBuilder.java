package net.take;

import net.take.receivers.EnvelopeSender;
import net.take.receivers.MessageReceiver;
import net.take.receivers.NotificationReceiver;
import org.limeprotocol.MediaType;
import org.limeprotocol.Notification;

import java.util.function.Supplier;

public class MessagingHubSenderBuilder {

    private final MessagingHubClientBuilder clientBuilder;
    private final EnvelopeListenerRegistrar envelopeRegistrar;

    public MessagingHubSenderBuilder(MessagingHubClientBuilder clientBuilder) {
        this.clientBuilder = clientBuilder;
        this.envelopeRegistrar = new EnvelopeListenerRegistrar();
    }

    public MessagingHubSenderBuilder addMessageReceiver(MessageReceiver messageReceiver, MediaType forMimeType) {
        EnvelopeListenerHelper.addMessageReceiver(getEnvelopeRegistrar(), messageReceiver, forMimeType);
        return this;
    }

    public MessagingHubSenderBuilder addMessageReceiver(Supplier<MessageReceiver> receiverFactory, MediaType forMimeType) {
        EnvelopeListenerHelper.addMessageReceiver(getEnvelopeRegistrar(), receiverFactory, forMimeType);
        return this;
    }

    public MessagingHubSenderBuilder addNotificationReceiver(NotificationReceiver notificationReceiver, Notification.Event forEventType) {
        EnvelopeListenerHelper.addNotificationReceiver(getEnvelopeRegistrar(), notificationReceiver, forEventType);
        return this;
    }

    public MessagingHubSenderBuilder addNotificationReceiver(Supplier<NotificationReceiver> receiverFactory, Notification.Event forEventType) {
        EnvelopeListenerHelper.addNotificationReceiver(getEnvelopeRegistrar(), receiverFactory, forEventType);
        return this;
    }

    protected EnvelopeSender getEnvelopeSender() {
        return clientBuilder.getMessagingHubClientInterface();
    }

    protected EnvelopeListenerRegistrar getEnvelopeRegistrar() {
        return envelopeRegistrar;
    }


    public MessagingHubSender build() {
        return clientBuilder.build();
    }
}
