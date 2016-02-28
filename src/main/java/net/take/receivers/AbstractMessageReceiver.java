package net.take.receivers;

import org.limeprotocol.Message;

import java.util.concurrent.Future;

/***
 * Base message receiver
 */
public abstract class AbstractMessageReceiver extends AbstractEnvelopeReceiver implements MessageReceiver {

    public abstract Future ReceiveAsync(Message message);
}
