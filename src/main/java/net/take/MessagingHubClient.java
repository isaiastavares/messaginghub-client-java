package net.take;

import net.take.limeProtocol.*;
import org.limeprotocol.*;
import org.limeprotocol.messaging.resources.Presence;
import org.limeprotocol.messaging.resources.UriTemplates;
import org.limeprotocol.security.Authentication;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.net.URI;

/***
 * Default implementation for <see cref="MessagingHubClient" /> class.
 */
public class MessagingHubClient implements MessagingHubClientInterface, PersistentLimeSessionImpl.PersistentLimeSessionListener {

    private final Identity identity;
    private final Authentication authentication;
    private final URI endpoint;
    private final PersistentLimeSessionFactory persistentClientFactory;
    private final ClientChannelFactory clientChannelFactory;
    private final LimeSessionProvider limeSessionProvider;

    private final EnvelopeListenerRegistrar listenerRegistrar;
    private boolean started;

    private PersistentLimeSession persistentLimeSession;
    //private ChannelListener _channelListener;

    protected MessagingHubClient(Identity identity, Authentication authentication, URI endPoint,
                                 PersistentLimeSessionFactory persistentChannelFactory,
                                 ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider,
                                 EnvelopeListenerRegistrar listenerRegistrar)
    {
        this.identity = identity;
        this.authentication = authentication;
        endpoint = endPoint;
        persistentClientFactory = persistentChannelFactory;
        this.clientChannelFactory = clientChannelFactory;
        this.limeSessionProvider = limeSessionProvider;
        this.listenerRegistrar = listenerRegistrar;
    }

    protected MessagingHubClient(Identity identity, Authentication authentication,
                                 URI endPoint, EnvelopeListenerRegistrar listenerRegistrar)
    {
        this(identity, authentication, endPoint);
    }

    public MessagingHubClient(Identity identity, Authentication authentication, URI endPoint)
    {
        this(identity, authentication, endPoint, new PersistentLimeSessionFactoryImpl(), new ClientChannelFactoryImpl(),
                new LimeSessionProviderImpl(), new EnvelopeListenerRegistrar());
    }

    public void sendCommand(Command command) throws IllegalStateException, IOException {
        if (!isStarted())
            throw new IllegalStateException("Client must be started before to proceed with this operation");

            persistentLimeSession.getClientChannel().sendCommand(command);
    }

    public void sendMessage(Message message) throws IllegalStateException {
        if (!isStarted())
            throw new IllegalStateException("Client must be started before to proceed with this operation");

        persistentLimeSession.sendMessage(message);
    }

    public void sendNotification(Notification notification) throws IllegalStateException {
        if (!isStarted())
            throw new IllegalStateException("Client must be started before to proceed with this operation!");

        persistentLimeSession.sendNotification(notification);
    }

    public Message receiveMessage() {
        return null;
    }

    public Notification receiveNotification() {
        return null;
    }

    @Override
    public synchronized void start() {
        if (isStarted()) throw new IllegalStateException("The client is already started");

        instantiateClientChannel();
        try {
            persistentLimeSession.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startEnvelopeListeners();

        setStarted(true);
    }

    private void startEnvelopeListeners(){

    }

    @Override
    public synchronized void stop() {
        if (isStarted()) throw new IllegalStateException("The client is not started");

        //_channelListener.Stop();
        //_channelListener.Dispose();

        persistentLimeSession.stop();
        setStarted(false);
    }

    @Override
    public void sessionEstablished(boolean isEstablished) {
        setPresence();
    }

    private void instantiateClientChannel()
    {
        persistentLimeSession = persistentClientFactory.createPersistentClientChannel(endpoint, identity,
                authentication, clientChannelFactory, limeSessionProvider, this);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }


    private void setPresence()
    {
        Presence p = new Presence();
        p.setStatus(Presence.PresenceStatus.AVAILABLE);
        p.setRoutingRule(Presence.RoutingRule.IDENTITY);

        persistentLimeSession.setResource(LimeUri.parse(UriTemplates.PRESENCE), p, null);
    }
}
