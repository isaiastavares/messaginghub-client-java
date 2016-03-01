package net.take;

import net.take.receivers.MessageReceiver;
import net.take.receivers.NotificationReceiver;
import org.limeprotocol.MediaType;
import org.limeprotocol.Notification;

import java.util.function.Supplier;

public class EnvelopeListenerHelper {

    /***
     * Add a message receiver listener to handle received messages.
     *
     * @param envelopeListener Listener
     * @param messageReceiver  receiver
     * @param forMimeType      MediaType used as a filter of messages received by listener. When not informed, only receives messages which no 'typed' receiver is registered
     */
    public static void addMessageReceiver(EnvelopeListener envelopeListener, MessageReceiver messageReceiver,
                                          MediaType forMimeType) {
        addMessageReceiver(envelopeListener, () -> messageReceiver, forMimeType);
    }


    /***
     * Add a message receiver listener to handle received messages.
     *
     * @param envelopeListener
     * @param receiverFactory  A function used to build the notification listener
     * @param forMimeType      MediaType used as a filter of messages received by listener. When not informed, only receives messages which no 'typed' receiver is registered
     */
    public static void addMessageReceiver(EnvelopeListener envelopeListener,
                                          Supplier<MessageReceiver> receiverFactory, MediaType forMimeType) {
        if (forMimeType == null ||
                forMimeType.equals(MediaTypes.getAny())) {
            envelopeListener.addMessageReceiver(receiverFactory, (m) -> true);
        } else {
            envelopeListener.addMessageReceiver(receiverFactory, (m) -> m.getType().equals(forMimeType));
        }
    }

    /***
     * Add a notification receiver listener to handle received notifications.
     *
     * @param envelopeListener
     * @param notificationReceiver receiver
     * @param forEventType         EventType used as a filter of notification received by listener
     */
    public static void addNotificationReceiver(EnvelopeListener envelopeListener,
                                               NotificationReceiver notificationReceiver, Notification.Event forEventType) {
        addNotificationReceiver(envelopeListener, () -> notificationReceiver, forEventType);
    }

    /***
     * Add a notification receiver listener to handle received notifications.
     *
     * @param envelopeListener
     * @param receiverFactory  A function used to build the notification listener
     * @param forEventType     EventType used as a filter of notification received by listener
     */
    public static void addNotificationReceiver(EnvelopeListener envelopeListener,
                                               Supplier<NotificationReceiver> receiverFactory, Notification.Event forEventType) {
        if (forEventType == null) {
            envelopeListener.addNotificationReceiver(receiverFactory, n -> true);
        } else {
            envelopeListener.addNotificationReceiver(receiverFactory, n -> n.getEvent().equals(forEventType));
        }
    }
}
