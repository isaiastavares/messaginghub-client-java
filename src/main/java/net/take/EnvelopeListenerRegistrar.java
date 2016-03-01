package net.take;

import net.take.receivers.*;
import org.limeprotocol.Envelope;
import org.limeprotocol.Message;
import org.limeprotocol.Notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EnvelopeListenerRegistrar implements EnvelopeListener {

    private final Collection<ReceiverFactoryPredicate<Message>> messageReceivers;
    private final Collection<ReceiverFactoryPredicate<Notification>> notificationReceivers;

    private static final Collection<MessageReceiver> DefaultMessageReceivers = new ArrayList<>();
    private static final Collection<NotificationReceiver> DefaultNotificationReceivers = new ArrayList<>();

    public EnvelopeListenerRegistrar() {
        messageReceivers = new ArrayList<ReceiverFactoryPredicate<Message>>();
        notificationReceivers = new ArrayList<ReceiverFactoryPredicate<Notification>>();

        DefaultMessageReceivers.add(new UnsupportedMessageReceiver());
        DefaultNotificationReceivers.add(new BlackholeNotificationReceiver());
    }


    public void addMessageReceiver(Supplier<MessageReceiver> receiverFactory, Predicate<Message> predicate) {

        //TODO: Try extract all addReceivers to a unique method
        if (receiverFactory == null) throw new IllegalArgumentException("receiverFactory");
        if (predicate == null) throw new IllegalArgumentException("predicate");

        //Java gambis (Workaround)
        Supplier<EnvelopeReceiver<Message>> enveloperFactoryWrapper = receiverFactory::get;

        messageReceivers.add(new ReceiverFactoryPredicate<Message>(enveloperFactoryWrapper, predicate));
    }

    public void addNotificationReceiver(Supplier<NotificationReceiver> receiverFactory, Predicate<Notification> predicate) {
        //TODO: Try extract all addReceivers to a unique method
        if (receiverFactory == null) throw new IllegalArgumentException("receiverFactory");
        if (predicate == null) throw new IllegalArgumentException("predicate");

        //Java gambis (Workaround)
        Supplier<EnvelopeReceiver<Notification>> enveloperFactoryWrapper = receiverFactory::get;

        notificationReceivers.add(new ReceiverFactoryPredicate<Notification>(enveloperFactoryWrapper, predicate));
    }

    //TODO: Try extract all getReceiversFor to a unique method
    public Collection<EnvelopeReceiver<Message>> getMessageReceivers() {
        List<EnvelopeReceiver<Message>> resultList = new ArrayList<EnvelopeReceiver<Message>>();

        for (ReceiverFactoryPredicate<Message> rFP : messageReceivers) {
            resultList.add(rFP.getReceiverFactory().get());
        }
        return CollectionHelper.Coalesce(resultList, DefaultMessageReceivers);
    }

    //TODO: Try extract all getReceiversFor to a unique method
    public Collection<EnvelopeReceiver<Notification>> getNotificationReceivers() {
        List<EnvelopeReceiver<Notification>> resultList = new ArrayList<EnvelopeReceiver<Notification>>();

        for (ReceiverFactoryPredicate<Notification> rFP : notificationReceivers) {
            resultList.add(rFP.getReceiverFactory().get());
        }
        return CollectionHelper.Coalesce(resultList, DefaultNotificationReceivers);
    }

    //TODO: Try use something like this
    private Collection<EnvelopeReceiver<Envelope>> getReceiversFor(Collection<ReceiverFactoryPredicate<Envelope>> envelopeReceivers, Envelope envelope) {

        List<EnvelopeReceiver<Envelope>> resultList = new ArrayList<EnvelopeReceiver<Envelope>>();

        for (ReceiverFactoryPredicate<Envelope> rFP : envelopeReceivers) {

            if (rFP.getPredicate().test(envelope)) {
                resultList.add(rFP.getReceiverFactory().get());
            }
        }
        return resultList;
    }

    class ReceiverFactoryPredicate<T extends Envelope> {
        private final Predicate<T> predicate;
        private final Supplier<EnvelopeReceiver<T>> receiverFactory;

        ReceiverFactoryPredicate(Supplier<EnvelopeReceiver<T>> receiverFactory, Predicate<T> predicate) {
            if (receiverFactory == null) throw new IllegalArgumentException("receiverFactory");
            if (predicate == null) throw new IllegalArgumentException("predicate");

            this.receiverFactory = receiverFactory;
            this.predicate = predicate;
        }

        public Supplier<EnvelopeReceiver<T>> getReceiverFactory() {
            return receiverFactory;
        }

        public Predicate<T> getPredicate() {
            return predicate;
        }
    }
}
