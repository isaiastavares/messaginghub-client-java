package net.take;

import net.take.limeProtocol.*;
import org.limeprotocol.Command;
import org.limeprotocol.Identity;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.security.Authentication;

import java.net.URI;

public class MessagingHubClient implements MessagingHubClientInterface {

    private final Identity _identity;
    private final Authentication _authentication;
    private final URI _endpoint;
    private final PersistentLimeSessionFactory _persistentClientFactory;
    private final ClientChannelFactory _clientChannelFactory;
    private final LimeSessionProvider _limeSessionProvider;

    private final long _sendTimeout;
    private final EnvelopeListenerRegistrar _listenerRegistrar;

    private PersistentLimeSession _persistentLimeSession;
    private ChannelListener _channelListener;

    protected MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout,
                                 PersistentLimeSessionFactory persistentChannelFactory, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider,
                                 EnvelopeListenerRegistrar listenerRegistrar)
    {
        _identity = identity;
        _authentication = authentication;
        _endpoint = endPoint;
        _persistentClientFactory = persistentChannelFactory;
        _clientChannelFactory = clientChannelFactory;
        _limeSessionProvider = limeSessionProvider;
        _sendTimeout = sendTimeout;
        _listenerRegistrar = listenerRegistrar;
    }

    protected MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout, EnvelopeListenerRegistrar listenerRegistrar)
    {
        super(identity, authentication, endPoint, sendTimeout);
        _listenerRegistrar = listenerRegistrar;
    }

    public MessagingHubClient(Identity identity, Authentication authentication, URI endPoint)
    {
        super(identity, authentication, endPoint, 20);
    }

    public MessagingHubClient(Identity identity, Authentication authentication, URI endPoint, long sendTimeout)
    {
        super(identity, authentication, endPoint, sendTimeout, new PersistentLimeSessionFactoryImpl(), new ClientChannelFactoryImpl(),
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
}
