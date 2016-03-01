package net.take.limeProtocol;

import org.limeprotocol.client.ClientChannel;

import java.util.concurrent.Future;

public interface ClientChannelFactory {
    ClientChannel CreateClientChannelAsync(long sendTimeout);
}
