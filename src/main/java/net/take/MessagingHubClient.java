package net.take;

import net.take.limeProtocol.*;
import org.limeprotocol.Command;
import org.limeprotocol.Identity;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.security.Authentication;

import java.net.URI;

public class MessagingHubClient implements MessagingHubClientInterface {

    private final Identity identity;
    private final Authentication authentication;
    private final URI endpoint;
    private final PersistentLimeSessionFactory persistentClientFactory;
    private final ClientChannelFactory clientChannelFactory;
    private final LimeSessionProvider limeSessionProvider;

    private final long sendTimeout;
    private final EnvelopeListenerRegistrar listenerRegistrar;

    private PersistentLimeSession persistentLimeSession;
    //private ChannelListener _channelListener;

    protected MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout,
                                 PersistentLimeSessionFactory persistentChannelFactory, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider,
                                 EnvelopeListenerRegistrar listenerRegistrar)
    {
        this.identity = identity;
        this.authentication = authentication;
        endpoint = endPoint;
        persistentClientFactory = persistentChannelFactory;
        this.clientChannelFactory = clientChannelFactory;
        this.limeSessionProvider = limeSessionProvider;
        this.sendTimeout = sendTimeout;
        this.listenerRegistrar = listenerRegistrar;
    }

    protected MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout, EnvelopeListenerRegistrar listenerRegistrar)
    {
        this(identity, authentication, endPoint, sendTimeout);
    }

    public MessagingHubClient(Identity identity, Authentication authentication, URI endPoint)
    {
        this(identity, authentication, endPoint, 20);
    }

    public MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout)
    {
        this(identity, authentication, endPoint, sendTimeout, new PersistentLimeSessionFactoryImpl(), new ClientChannelFactoryImpl(),
                new LimeSessionProviderImpl(), new EnvelopeListenerRegistrar());
    }

    public Command sendCommand(Command command) {
        return null;
    }

    public void sendMessage(Message message) {

    }

    public void sendNotification(Notification notification) {

    }

    public Message receiveMessage() {
        return null;
    }

    public Notification receiveNotification() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
