package net.take.receivers;

import net.take.MessageHelper;
import org.limeprotocol.Message;
import org.limeprotocol.Reason;
import org.limeprotocol.ReasonCodes;

import java.util.concurrent.Future;

public class UnsupportedMessageReceiver extends AbstractMessageReceiver {

    @Override
    public void receive(Message message) {
        Reason reason = new Reason(
                ReasonCodes.MESSAGE_UNSUPPORTED_CONTENT_TYPE,
                message.getType().toString() + " messages are not supported");

        getEnvelopeSender().sendNotification(
                MessageHelper.toFailedNotification(message, reason));
    }


}
