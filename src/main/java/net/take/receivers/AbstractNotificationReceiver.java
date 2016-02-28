package net.take.receivers;

import org.limeprotocol.Notification;

import java.util.concurrent.Future;

/***
 * Base notification receiver
 */
public abstract class AbstractNotificationReceiver extends AbstractEnvelopeReceiver implements NotificationReceiver {

    public abstract Future ReceiveAsync(Notification notification);
}
