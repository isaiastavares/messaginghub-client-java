package net.take.limeProtocol;

import org.limeprotocol.Command;
import org.limeprotocol.Identity;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public class PersistentLimeSessionImpl implements PersistentLimeSession {

    private final URI endPoint;
    private final Identity identity;
    private final Authentication authentication;
    private final long sendTimeout;
    private final ClientChannelFactory clientChannelFactory;
    private final LimeSessionProvider limeSessionProvider;

    protected PersistentLimeSessionImpl(URI endPoint, Identity identity, Authentication authentication, long sendTimeout,
                                        ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider)
    {
        this.endPoint = endPoint;
        this.identity = identity;
        this.authentication = authentication;
        this.sendTimeout = sendTimeout;
        this.clientChannelFactory = clientChannelFactory;
        this.limeSessionProvider = limeSessionProvider;
    }


    public Future StartAsync() {
        return null;
    }

    public Future StopAsync() {
        return null;
    }

    public ClientChannel getClientChannel() {
        return null;
    }

    public Future<Message> ReceiveMessageAsync() {
        return null;
    }

    public Future SendMessageAsync(Message message) {
        return null;
    }

    public Future<Command> ReceiveCommandAsync() {
        return null;
    }

    public Future SendCommandAsync(Command command) {
        return null;
    }

    public Future<Notification> ReceiveNotificationAsync() {
        return null;
    }

    public Future SendNotificationAsync(Notification notification) {
        return null;
    }
}
