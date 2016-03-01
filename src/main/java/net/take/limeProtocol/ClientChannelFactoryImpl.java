package net.take.limeProtocol;

import org.limeprotocol.client.ClientChannel;
import org.limeprotocol.client.ClientChannelImpl;
import org.limeprotocol.network.tcp.TcpTransport;
import org.limeprotocol.serialization.JacksonEnvelopeSerializer;

public class ClientChannelFactoryImpl implements ClientChannelFactory {
    public ClientChannel createClientChannel(long sendTimeout) {

        TcpTransport transport = new TcpTransport(new JacksonEnvelopeSerializer());
        ClientChannelImpl clientChannel = new ClientChannelImpl(transport, true, true, true);
        return null;
        //return clientChannel;
    }
}
