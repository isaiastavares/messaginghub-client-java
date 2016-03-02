package net.take.limeProtocol;

import org.limeprotocol.*;
import org.limeprotocol.client.ClientChannel;

import java.util.concurrent.Future;
import java.util.function.Function;

public interface PersistentLimeSession {

    void start() throws Exception;

    void stop();

    ClientChannel getClientChannel();

    Message receiveMessage();
    void sendMessage(Message message);

    Command receiveCommand();
    void sendCommand(Command command);

    Notification receiveNotification();
    void sendNotification(Notification notification);

    void setResource(LimeUri uri, Document resource, Function<Command, Void> unrelatedCommandHandler);
}
