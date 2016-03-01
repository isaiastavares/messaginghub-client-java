package net.take.receivers;

import org.limeprotocol.Envelope;

import java.util.concurrent.Future;

/***
 * Receive envelopes from Messaging Hub
 * @param <T> where T extends Envelope
 */
public interface EnvelopeReceiver<T extends Envelope> {

    /***
     * Handle received envelopes
     * @param envelopeType
     */
    void receive(T envelopeType);
}
