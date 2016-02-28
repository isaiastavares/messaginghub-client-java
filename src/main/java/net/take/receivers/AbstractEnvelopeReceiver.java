package net.take.receivers;

/***
 * Base envelope receiver
 * Obs.: Senders are automatically injected by MessagingHubClient
 */
public abstract class AbstractEnvelopeReceiver {

    private EnvelopeSender envelopeSender;

    public EnvelopeSender getEnvelopeSender() {
        return envelopeSender;
    }

    protected void setEnvelopeSender(EnvelopeSender envelopeSender) {
        this.envelopeSender = envelopeSender;
    }
}
