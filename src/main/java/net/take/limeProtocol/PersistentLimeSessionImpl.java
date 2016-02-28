package net.take.limeProtocol;

import org.limeprotocol.Command;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;
import org.limeprotocol.client.ClientChannel;

import java.util.concurrent.Future;

public class PersistentLimeSessionImpl implements PersistentLimeSession {
    public Future StartAsync() {
        return null;
    }

    public Future StopAsync() {
        return null;
    }

    public ClientChannel getClientChannel() {
        return null;
    }

    public Future<Message> ReceiveMessageAsync() {
        return null;
    }

    public Future SendMessageAsync(Message message) {
        return null;
    }

    public Future<Command> ReceiveCommandAsync() {
        return null;
    }

    public Future SendCommandAsync(Command command) {
        return null;
    }

    public Future<Notification> ReceiveNotificationAsync() {
        return null;
    }

    public Future SendNotificationAsync(Notification notification) {
        return null;
    }
}
