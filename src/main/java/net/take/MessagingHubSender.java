package net.take;

import net.take.receivers.EnvelopeSender;

import java.io.IOException;

public interface MessagingHubSender extends EnvelopeSender {

    void start();

    void stop() throws IOException;
}
