package net.take;

import net.take.receivers.MessageReceiver;
import net.take.receivers.NotificationReceiver;
import org.limeprotocol.Identity;
import org.limeprotocol.MediaType;
import org.limeprotocol.Notification;
import org.limeprotocol.security.Authentication;
import org.limeprotocol.security.KeyAuthentication;
import org.limeprotocol.security.PlainAuthentication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Supplier;

public class MessagingHubClientBuilder {

    public static final String DEFAULT_DOMAIN = "msging.net";

    private final MessagingHubSenderBuilder senderBuilder;

    private String login;
    private String password;
    private String accessKey;
    private long sendTimeout;
    private String domain;
    private String hostName;

    private Identity identity;
    private URI endPoint;

    private MessagingHubClientInterface messagingHubClientInterface;

    protected MessagingHubClientInterface getMessagingHubClientInterface() {
        return messagingHubClientInterface;
    }

    public MessagingHubClientBuilder() throws URISyntaxException {
        hostName = DEFAULT_DOMAIN;
        domain = DEFAULT_DOMAIN;
        sendTimeout = 20;
        senderBuilder = new MessagingHubSenderBuilder(this);

        identity = Identity.parse(login + "@" + domain);
        endPoint = new URI("net.tcp://" + hostName + ":55321");
    }

    public MessagingHubClientBuilder usingAccount(String login, String password) {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException("login");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("password");

        this.login = login;
        this.password = password;

        return this;
    }

    public MessagingHubClientBuilder usingAccessKey(String login, String accessKey) {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException("login");
        if (accessKey == null || login.isEmpty()) throw new IllegalArgumentException("accessKey");

        this.login = login;
        this.accessKey = accessKey;

        return this;
    }

    public MessagingHubClientBuilder usingHostName(String hostName) {
        if (hostName == null || hostName.isEmpty()) throw new IllegalArgumentException("hostName");

        this.hostName = hostName;
        return this;
    }

    public MessagingHubClientBuilder usingDomain(String domain) {
        if (domain == null || domain.isEmpty()) throw new IllegalArgumentException("domain");

        this.domain = domain;
        return this;
    }

    public MessagingHubClientBuilder withSendTimeout(long timeout) {
        sendTimeout = timeout;
        return this;
    }

    public MessagingHubSenderBuilder addMessageReceiver(MessageReceiver messageReceiver, MediaType forMimeType) {
        senderBuilder.addMessageReceiver(messageReceiver, forMimeType);
        return senderBuilder;
    }

    public MessagingHubSenderBuilder addMessageReceiver(Supplier<MessageReceiver> receiverFactory, MediaType forMimeType) {
        senderBuilder.addMessageReceiver(receiverFactory, forMimeType);
        return senderBuilder;
    }

    public MessagingHubSenderBuilder addNotificationReceiver(NotificationReceiver notificationReceiver, Notification.Event forEventType) {
        senderBuilder.addNotificationReceiver(notificationReceiver, forEventType);
        return senderBuilder;
    }

    public MessagingHubSenderBuilder addNotificationReceiver(Supplier<NotificationReceiver> receiverFactory, Notification.Event forEventType) {
        senderBuilder.addNotificationReceiver(receiverFactory, forEventType);
        return senderBuilder;
    }

    public MessagingHubClientInterface build() {
        messagingHubClientInterface = new MessagingHubClient(identity, GetAuthenticationScheme(), endPoint, senderBuilder.getEnvelopeRegistrar());
        return messagingHubClientInterface;
    }

    protected MessagingHubSenderBuilder getMessagingSenderBuilder() {
        return senderBuilder;
    }

    private Authentication GetAuthenticationScheme() {
        Authentication result = null;

        if (password != null) {
            PlainAuthentication plainAuthentication = new PlainAuthentication();
            plainAuthentication.setToBase64Password(password);
            result = plainAuthentication;
        }

        if (accessKey != null) {
            KeyAuthentication keyAuthentication = new KeyAuthentication();
            keyAuthentication.setKey(accessKey);
            result = keyAuthentication;
        }

        if (result == null)
            throw new UnsupportedOperationException("A password or accessKey should be defined. Please use the 'UsingAccount' or 'UsingAccessKey' methods for that.");

        return result;
    }
}