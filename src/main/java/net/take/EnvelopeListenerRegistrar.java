package net.take;

import net.take.receivers.*;
import org.limeprotocol.Envelope;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EnvelopeListenerRegistrar implements EnvelopeListener {

    private final Collection<ReceiverFactoryPredicate<Message>> _messageReceivers;
    private final Collection<ReceiverFactoryPredicate<Notification>> _notificationReceivers;

    private static final MessageReceiver[] DefaultMessageReceivers = new MessageReceiver[] { new UnsupportedMessageReceiver() };
    private static final NotificationReceiver[] DefaultNotificationReceivers = new NotificationReceiver[] { new BlackholeNotificationReceiver() };

    public EnvelopeListenerRegistrar()
    {
        _messageReceivers = new ArrayList<ReceiverFactoryPredicate<Message>>();
        _notificationReceivers = new ArrayList<ReceiverFactoryPredicate<Notification>>();
    }


    public void addMessageReceiver(Supplier<MessageReceiver> receiverFactory, Predicate<Message> predicate) {
        //addEnvelopeReceiver(_messageReceivers, receiverFactory, predicate);
    }

    public void addNotificationReceiver(Supplier<NotificationReceiver> receiverFactory, Predicate<Notification> predicate) {
        //addEnvelopeReceiver(_notificationReceivers, receiverFactory, predicate);
    }

    public Collection<EnvelopeReceiver<Envelope>> getReceiversFor(Type envelope)
    {
        if (envelope == null) throw new IllegalArgumentException("envelope");

        if (envelope instanceof Message)
        {
            //return (Collection<EnvelopeReceiver<Envelope>>) getReceiversFor(_messageReceivers, (Message) envelope).Coalesce(DefaultMessageReceivers);
        }

        if (envelope instanceof Notification)
        {
            //return (Collection<EnvelopeReceiver<Envelope>>) getReceiversFor(_notificationReceivers, (Notification) envelope).Coalesce(DefaultNotificationReceivers);
        }

        return new ArrayList<EnvelopeReceiver<Envelope>>();
    }

    private void addEnvelopeReceiver(Collection<ReceiverFactoryPredicate<Envelope>> envelopeReceivers,
    Supplier<EnvelopeReceiver> receiverFactory, Predicate predicate)
    {
        if (receiverFactory == null) throw new IllegalArgumentException("receiverFactory");
        if (predicate == null) throw new IllegalArgumentException("predicate");

        ReceiverFactoryPredicate predicateReceiverFactory = new ReceiverFactoryPredicate(receiverFactory, predicate);
        envelopeReceivers.add(predicateReceiverFactory);
    }

    Collection<EnvelopeReceiver<Envelope>> getReceiversFor(Collection<ReceiverFactoryPredicate<Envelope>> envelopeReceivers, Envelope envelope){

        List<EnvelopeReceiver<Envelope>> resultList = new ArrayList<EnvelopeReceiver<Envelope>>();

        for (ReceiverFactoryPredicate<Envelope> rFP : envelopeReceivers ){

            if(rFP.getPredicate().test(envelope)){
                resultList.add(rFP.getReceiverFactory().get());
            }
        }
        return resultList;
    }

    class ReceiverFactoryPredicate<T extends Envelope>
    {
        private final Predicate<T> predicate;
        private final Supplier<EnvelopeReceiver<T>> receiverFactory;

        ReceiverFactoryPredicate(Supplier<EnvelopeReceiver<T>> receiverFactory, Predicate<T> predicate)
        {
            if (receiverFactory == null) throw new IllegalArgumentException("receiverFactory");
            if (predicate == null) throw new IllegalArgumentException("predicate");

            this.receiverFactory = receiverFactory;
            this.predicate = predicate;
        }

        public Supplier<EnvelopeReceiver<T>> getReceiverFactory() { return receiverFactory; }

        public Predicate<T> getPredicate() { return predicate; }
    }
}
