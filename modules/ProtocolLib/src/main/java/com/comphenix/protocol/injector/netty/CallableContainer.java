package com.comphenix.protocol.injector.netty;

import com.comphenix.protocol.events.PacketEvent;

import java.util.concurrent.Callable;

public class CallableContainer<T> implements Callable<T> {

    private ChannelInjector channelInjector;
    private Callable<T> callable;
    private PacketEvent event;

    public CallableContainer(ChannelInjector channelInjector, Callable<T> callable, PacketEvent event) {
        this.channelInjector = channelInjector;
        this.callable = callable;
        this.event = event;
    }

    @Override
    public T call() throws Exception {
        T result;

        // This field must only be updated in the pipeline thread
        channelInjector.currentEvent = event;
        result = callable.call();
        channelInjector.currentEvent = null;
        return result;
    }
}
