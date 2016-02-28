package net.take;

import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.Reason;

public class MessageExtensions {

    public static Notification toReceivedNotification(Message message) {
        return toNotification(message, Notification.Event.RECEIVED);
    }

    public static Notification toConsumedNotification(Message message) {
        return toNotification(message, Notification.Event.CONSUMED);
    }
    public static Notification toFailedNotification(Message message, Reason reason)
    {
        Notification notification = toNotification(message, Notification.Event.FAILED);
        notification.setReason(reason);
        return notification;
    }

    public static Notification toNotification(Message message, Notification.Event event)
    {
        Notification notification = new Notification();
        notification.setId(message.getId());
        notification.setEvent(event);
        notification.setTo(message.getFrom());
        return notification;
    }
}
