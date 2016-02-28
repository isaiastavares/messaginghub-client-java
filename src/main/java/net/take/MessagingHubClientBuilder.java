package net.take;

import net.take.receivers.MessageReceiver;
import org.limeprotocol.Identity;
import org.limeprotocol.MediaType;
import org.limeprotocol.security.Authentication;
import org.limeprotocol.security.KeyAuthentication;
import org.limeprotocol.security.PlainAuthentication;

import java.net.URI;
import java.net.URISyntaxException;

public class MessagingHubClientBuilder {

    public static final String DEFAULT_DOMAIN = "msging.net";

    private final MessagingHubSenderBuilder _senderBuilder;

    private String _login;
    private String _password;
    private String _accessKey;
    private long _sendTimeout;
    private String _domain;
    private String _hostName;

    private Identity _identity;
    private URI _endPoint;

    private MessagingHubClient MessagingHubClient;

    protected MessagingHubClient getMessagingHubClient() {
        return MessagingHubClient;
    }

    private void setMessagingHubClient(MessagingHubClient messagingHubClient) {
        MessagingHubClient = messagingHubClient;
    }

    public MessagingHubClientBuilder() throws URISyntaxException {
        _hostName = DEFAULT_DOMAIN;
        _domain = DEFAULT_DOMAIN;
        _sendTimeout = 20;
        //_senderBuilder = new MessagingHubSenderBuilder(this);

        _identity = Identity.parse(_login + "@" + _domain);
        _endPoint = new URI("net.tcp://" + _hostName + ":55321");
    }

    public MessagingHubClientBuilder UsingAccount(String login, String password) {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException("login");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("password");

        _login = login;
        _password = password;

        return this;
    }

    public MessagingHubClientBuilder UsingAccessKey(String login, String accessKey) {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException("login");
        if (accessKey == null || login.isEmpty()) throw new IllegalArgumentException("accessKey");

        _login = login;
        _accessKey = accessKey;

        return this;
    }

    public MessagingHubClientBuilder UsingHostName(String hostName) {
        if (hostName == null || hostName.isEmpty()) throw new IllegalArgumentException("hostName");

        _hostName = hostName;
        return this;
    }

    public MessagingHubClientBuilder UsingDomain(String domain) {
        if (domain == null || domain.isEmpty()) throw new IllegalArgumentException("domain");

        _domain = domain;
        return this;
    }

    public MessagingHubClientBuilder WithSendTimeout(long timeout) {
        _sendTimeout = timeout;
        return this;
    }

    public MessagingHubSenderBuilder AddMessageReceiver(MessageReceiver messageReceiver, MediaType forMimeType) {
        _senderBuilder.AddMessageReceiver(messageReceiver, forMimeType);
        return _senderBuilder;
    }

    public MessagingHubSenderBuilder AddMessageReceiver(Func<IMessageReceiver> receiverFactory, MediaType forMimeType=null) {
        _senderBuilder.AddMessageReceiver(receiverFactory, forMimeType);
        return _senderBuilder;
    }

    public MessagingHubSenderBuilder AddNotificationReceiver(INotificationReceiver notificationReceiver, Event?forEventType=null) {
        _senderBuilder.AddNotificationReceiver(notificationReceiver, forEventType);
        return _senderBuilder;
    }

    public MessagingHubSenderBuilder AddNotificationReceiver(Func<INotificationReceiver> receiverFactory, Event?forEventType=null) {
        _senderBuilder.AddNotificationReceiver(receiverFactory, forEventType);
        return _senderBuilder;
    }

    public IMessagingHubClient Build() {
        MessagingHubClient = new MessagingHubClient(_identity, GetAuthenticationScheme(), _endPoint, _sendTimeout, _senderBuilder.EnvelopeRegistrar);
        return MessagingHubClient;
    }

    private MessagingHubSenderBuilder AsMessagingSenderBuilder;

    protected MessagingHubSenderBuilder getAsMessagingSenderBuilder() {
        return AsMessagingSenderBuilder;
    }

    private Authentication GetAuthenticationScheme() {
        Authentication result = null;

        if (_password != null) {
            PlainAuthentication plainAuthentication = new PlainAuthentication();
            plainAuthentication.setToBase64Password(_password);
            result = plainAuthentication;
        }

        if (_accessKey != null) {
            KeyAuthentication keyAuthentication = new KeyAuthentication();
            keyAuthentication.setKey(_accessKey);
            result = keyAuthentication;
        }

        if (result == null)
            throw new UnsupportedOperationException("A password or accessKey should be defined. Please use the 'UsingAccount' or 'UsingAccessKey' methods for that.");

        return result;
    }
}