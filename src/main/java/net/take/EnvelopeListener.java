package net.take;

import net.take.receivers.MessageReceiver;
import net.take.receivers.NotificationReceiver;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import java.util.function.*;

public interface EnvelopeListener {

    /***
     * Add a message receiver listener to handle received messages.
     * @param receiverFactory A supplier used to build the notification listener</param>
     * @param predicate The message predicate used as a filter of messages received by listener. When not informed, only receives messages which no 'typed' receiver is registered.
     */
    void addMessageReceiver(Supplier<MessageReceiver> receiverFactory, Predicate<Message> predicate);

    /***
     * Add a notification receiver listener to handle received notifications.
     * @param receiverFactory A supplier used to build the notification listener
     * @param predicate EventType used as a filter of notification received by listener.
     */
    void addNotificationReceiver(Supplier<NotificationReceiver> receiverFactory, Predicate<Notification> predicate);
}
