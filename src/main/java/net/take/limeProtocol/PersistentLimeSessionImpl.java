package net.take.limeProtocol;

import org.limeprotocol.*;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.security.Authentication;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.function.Function;

public class PersistentLimeSessionImpl implements PersistentLimeSession {

    private final URI endPoint;
    private final Identity identity;
    private final Authentication authentication;
    private final long sendTimeout;
    private final ClientChannelFactory clientChannelFactory;
    private final LimeSessionProvider limeSessionProvider;
    private final PersistentLimeSessionListener listener;

    private ClientChannel clientChannel = null;
    private WatchSession watchSessionRunnable = null;
    private Thread watchSessionThread = null;

    private long reconnectDelay = 2000;

    protected PersistentLimeSessionImpl(URI endPoint, Identity identity, Authentication authentication, long sendTimeout,
                                        ClientChannelFactory clientChannelFactory, LimeSessionProvider limeSessionProvider,
                                        PersistentLimeSessionListener listener) {
        this.endPoint = endPoint;
        this.identity = identity;
        this.authentication = authentication;
        this.sendTimeout = sendTimeout;
        this.clientChannelFactory = clientChannelFactory;
        this.limeSessionProvider = limeSessionProvider;
        this.listener = listener;
    }


    private boolean isWatchingSession() {
        return watchSessionRunnable != null && !watchSessionRunnable.isStopping();
    }

    public synchronized void start() throws IllegalStateException {
        establishSession();

        if (!isSessionEstablished()) {
            throw new IllegalStateException("Could not establish session");
        }

        watchSessionRunnable = new WatchSession();
        watchSessionThread = new Thread(watchSessionRunnable);
        watchSessionThread.start();
    }

    public synchronized void stop() {

        if (isWatchingSession()) {
            watchSessionRunnable.stop();
            if (watchSessionThread != null && watchSessionThread.isAlive()) {
                watchSessionThread.interrupt();
            }
            watchSessionRunnable = null;
            watchSessionThread = null;
        }

        endSession();

    }

    private void endSession()
    {
        limeSessionProvider.finishSession(clientChannel);
        clientChannel = null;
    }

    public ClientChannel getClientChannel() {
        return null;
    }

    @Override
    public Message receiveMessage() {
        return null;
    }

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public Command receiveCommand() {
        return null;
    }

    @Override
    public void sendCommand(Command command) {

    }

    @Override
    public Notification receiveNotification() {
        return null;
    }

    @Override
    public void sendNotification(Notification notification) {

    }

    @Override
    public void setResource(LimeUri uri, Document resource, Function<Command, Void> unrelatedCommandHandler) {

    }

    private boolean isSessionEstablished() {
        return limeSessionProvider.IsSessionEstablished(clientChannel);
    }

    private synchronized void establishSession() {
        clientChannel = null;

        clientChannel = clientChannelFactory.createClientChannel();

        limeSessionProvider.establishSession(clientChannel, endPoint, identity, authentication);

        if (isSessionEstablished() && listener != null) {
            listener.sessionEstablished(true);
        }
    }

    /***
     * Listener for PersistentLimeSession
     */
    public interface PersistentLimeSessionListener {
        void sessionEstablished(boolean isEstablished);
    }

    class WatchSession implements Runnable {

        private boolean isStopping = false;

        public WatchSession() {
        }

        public void run() {

            if (isSessionEstablished()) {
                return;
            }

            while (!isSessionEstablished()) {
                try {
                    establishSession();
                } catch (Exception e) {
                }

                if (isSessionEstablished()) {
                    break;
                }

                try {
                    Thread.sleep(reconnectDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean isStopping() {
            return this.isStopping;
        }

        public void stop() {
            this.isStopping = true;
        }
    }
}