package net.take.receivers;

import org.limeprotocol.Command;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.concurrent.Future;

public interface EnvelopeSender {

    /***
     * Send a command through the Messaging Hub
     * @param command Command to be sent
     */
    void sendCommand(Command command) throws OperationNotSupportedException, IOException;

    /***
     * Send a message through the Messaging Hub
     * @param message Message to be sent
     */
    void sendMessage(Message message) throws OperationNotSupportedException;

    /***
     * Send a notification through the Messaging Hub
     * @param notification Notification to be sent
     */
    void sendNotification(Notification notification) throws OperationNotSupportedException;

}
