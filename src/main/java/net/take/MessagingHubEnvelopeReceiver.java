package net.take;

import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import java.util.concurrent.Future;

public interface MessagingHubEnvelopeReceiver {

    Message receiveMessage();

    Notification receiveNotification();
}
