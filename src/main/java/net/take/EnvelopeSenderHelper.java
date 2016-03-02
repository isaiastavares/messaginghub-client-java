package net.take;

import net.take.receivers.EnvelopeSender;
import org.limeprotocol.Document;
import org.limeprotocol.Message;
import org.limeprotocol.Node;
import org.limeprotocol.messaging.contents.PlainText;

import javax.naming.OperationNotSupportedException;

/***
 * Extension methods for <see cref="IEnvelopeSender"/>
 */
public class EnvelopeSenderHelper
{
    public static void sendMessage(EnvelopeSender sender, String content, String to) {
        sendMessage(sender, content, Node.parse(to));
    }

    public static void sendMessage(EnvelopeSender sender, String content, Node to) {
        sendMessage(sender, CreatePlainTextContent(content), to);
    }

    public static void sendMessageAsync(EnvelopeSender sender, Document content, String to){
        sendMessage(sender, content, Node.parse(to));
    }

    public static void sendMessage(EnvelopeSender sender, Document content, Node to)
    {
        if (content == null) throw new IllegalArgumentException("content");
        Message message = new Message();
        message.setTo(to);
        message.setContent(content);

        try {
            sender.sendMessage(message);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static PlainText CreatePlainTextContent(String content) {return new PlainText(content) ;}
}