package net.take.receivers;

import org.limeprotocol.Command;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import java.util.concurrent.Future;

public interface EnvelopeSender {

    /***
     * Send a command through the Messaging Hub
     * @param command Command to be sent
     * @return A future representing the sending operation. When completed, it will contain the command response
     */
    Future<Command> SendCommandAsync(Command command);

    /***
     * Send a message through the Messaging Hub
     * @param message Message to be sent
     * @return A future representing the sending operation
     */
    Future SendMessageAsync(Message message);

    /***
     * Send a notification through the Messaging Hub
     * @param notification Notification to be sent
     * @return A future representing the sending operation
     */
    Future SendNotificationAsync(Notification notification);

}
