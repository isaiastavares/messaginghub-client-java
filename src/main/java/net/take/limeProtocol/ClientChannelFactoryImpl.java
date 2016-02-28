package net.take.limeProtocol;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.client.ClientChannelImpl;
import org.limeprotocol.network.TraceWriter;
import org.limeprotocol.network.tcp.TcpTransport;
import org.limeprotocol.serialization.JacksonEnvelopeSerializer;

import java.util.concurrent.Future;

public class ClientChannelFactoryImpl implements ClientChannelFactory {
    public Future<ClientChannel> CreateClientChannelAsync(long sendTimeout) {

        TcpTransport transport = new TcpTransport(new JacksonEnvelopeSerializer());
        ClientChannelImpl clientChannel = new ClientChannelImpl(transport, true, true, true);
        return null;
        //return clientChannel;
    }
}
