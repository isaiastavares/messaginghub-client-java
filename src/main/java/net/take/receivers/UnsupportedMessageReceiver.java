package net.take.receivers;

import net.take.MessageExtensions;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.Reason;
import org.limeprotocol.ReasonCodes;

import java.util.concurrent.Future;

public class UnsupportedMessageReceiver extends AbstractMessageReceiver {

    @Override
    public Future ReceiveAsync(Message message) {
        Reason reason = new Reason(
                ReasonCodes.MESSAGE_UNSUPPORTED_CONTENT_TYPE,
                message.getType().toString() + " messages are not supported");

        return getEnvelopeSender().SendNotificationAsync(
                MessageExtensions.toFailedNotification(message, reason));
    }


}
