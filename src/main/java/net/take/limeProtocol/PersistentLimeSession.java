package net.take.limeProtocol;

import org.limeprotocol.*;
import org.limeprotocol.client.ClientChannel;

import java.util.concurrent.Future;

public interface PersistentLimeSession {

    void start() throws Exception;

    void stop();

    //Event EventHandler SessionEstablished;

    ClientChannel getClientChannel();

    Message receiveMessage();
    void sendMessage(Message message);

    Command receiveCommand();
    void sendCommand(Command command);

    Notification receiveNotification();
    void SendNotification(Notification notification);

    //Future SetResourceAsync<TResource extends Document>(LimeUri uri, TResource resource, CancellationToken cancellationToken, Func<Command, Task> unrelatedCommandHandler = null);

}
