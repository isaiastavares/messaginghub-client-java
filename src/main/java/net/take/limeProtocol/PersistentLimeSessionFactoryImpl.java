package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public class PersistentLimeSessionFactoryImpl implements PersistentLimeSessionFactory {
    @Override
    public PersistentLimeSession createPersistentClientChannel(URI endpoint, Identity identity, Authentication authentication, ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider, PersistentLimeSessionImpl.PersistentLimeSessionListener listener) {
        PersistentLimeSession persistentClientChannel = new PersistentLimeSessionImpl(endpoint, identity, authentication, clientChannelFactory, limeSessionProvider, listener);

        return persistentClientChannel;
    }
}
