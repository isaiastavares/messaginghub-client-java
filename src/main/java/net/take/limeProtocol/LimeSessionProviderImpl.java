package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public class LimeSessionProviderImpl implements  LimeSessionProvider {
    public void establishSession(ClientChannel clientChannel, URI endPoint, Identity identity, Authentication authentication) {
    }

    public void finishSession(ClientChannel clientChannel) {

    }

    public boolean IsSessionEstablished(ClientChannel clientChannel) {
        return false;
    }
}
