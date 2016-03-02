package net.take.limeProtocol;

import org.limeprotocol.Identity;
import org.limeprotocol.Session;
import org.limeprotocol.SessionCompression;
import org.limeprotocol.SessionEncryption;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.security.Authentication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.concurrent.Future;

public class LimeSessionProviderImpl implements  LimeSessionProvider {
    public void establishSession(ClientChannel clientChannel, URI endPoint, Identity identity, Authentication authentication, PersistentLimeSessionImpl.PersistentLimeSessionListener listener) throws Exception {

        clientChannel.getTransport().open(endPoint);

        if (!clientChannel.getTransport().isConnected())
        {
            throw new Exception("Could not open connection");
        }

        clientChannel.establishSession(
                SessionCompression.NONE,
                SessionEncryption.TLS,
                identity,
                authentication,
                InetAddress.getLocalHost().getHostName(), new ClientChannel.EstablishSessionListener() {
                    @Override
                    public void onFailure(Exception exception) {
                        try {
                            finishSession(clientChannel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onReceiveSession(Session session) {
                        if (session.getState().equals(Session.SessionState.ESTABLISHED) && listener != null) {
                            try {
                                listener.sessionEstablished(true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public void finishSession(ClientChannel clientChannel) throws IOException {

        if (isSessionEstablished(clientChannel))
        {
            clientChannel.sendFinishingSession();
        }

        if (clientChannel.getTransport().isConnected())
        {
            clientChannel.getTransport().close();
        }
    }

    public boolean isSessionEstablished(ClientChannel clientChannel) {
        return clientChannel.getTransport().isConnected() && clientChannel.getState() == Session.SessionState.ESTABLISHED;
    }
}
