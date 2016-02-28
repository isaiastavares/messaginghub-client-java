package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public interface PersistentLimeSessionFactory {
    Future<PersistentLimeSession> CreatePersistentClientChannelAsync(URI endpoint, long sendTimeout, Identity identity, Authentication authentication, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider);
}
