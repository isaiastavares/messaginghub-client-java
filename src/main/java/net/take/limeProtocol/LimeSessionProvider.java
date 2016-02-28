package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;

public interface LimeSessionProvider {
    Future EstablishSessionAsync(ClientChannel clientChannel, URI endPoint, Identity identity, Authentication authentication);

    Future FinishSessionAsync(ClientChannel clientChannel);

    boolean IsSessionEstablished(ClientChannel clientChannel);
}
