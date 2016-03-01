package net.take.limeProtocol;

import org.limeprotocol.*;
import org.limeprotocol.client.ClientChannel;

import java.util.concurrent.Future;

public interface PersistentLimeSession {

    void start() throws Exception;

    void stop();

    //Event EventHandler SessionEstablished;

    ClientChannel getClientChannel();

    Future<Message> ReceiveMessageAsync();
    Future SendMessageAsync(Message message);

    Future<Command> ReceiveCommandAsync();
    Future SendCommandAsync(Command command);

    Future<Notification> ReceiveNotificationAsync();
    Future SendNotificationAsync(Notification notification);

    //Future SetResourceAsync<TResource extends Document>(LimeUri uri, TResource resource, CancellationToken cancellationToken, Func<Command, Task> unrelatedCommandHandler = null);

}
