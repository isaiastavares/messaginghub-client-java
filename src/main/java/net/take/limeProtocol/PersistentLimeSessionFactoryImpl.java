package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public class PersistentLimeSessionFactoryImpl implements PersistentLimeSessionFactory {
    public PersistentLimeSession createPersistentClientChannel(URI endpoint, long sendTimeout, Identity identity, Authentication authentication, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider, PersistentLimeSessionImpl.PersistentLimeSessionListener listener) {

        PersistentLimeSession persistentClientChannel = new PersistentLimeSessionImpl(endpoint, identity, authentication, sendTimeout, clientChannelFactory, limeSessionProvider, listener);

        return persistentClientChannel;
    }
}
