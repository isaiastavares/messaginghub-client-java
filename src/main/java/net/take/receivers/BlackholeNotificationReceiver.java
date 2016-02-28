package net.take.receivers;

import org.limeprotocol.Notification;

import java.util.concurrent.Future;

public class BlackholeNotificationReceiver extends AbstractNotificationReceiver {

    @Override
    public Future ReceiveAsync(Notification notification) {
        return null;
    }
}
