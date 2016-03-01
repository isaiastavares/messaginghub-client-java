package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.security.Authentication;

import java.net.URI;

public interface PersistentLimeSessionFactory {
    PersistentLimeSession createPersistentClientChannel(URI endpoint, long sendTimeout, Identity identity, Authentication authentication, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider, PersistentLimeSessionImpl.PersistentLimeSessionListener listener);
}
