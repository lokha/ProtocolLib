package com.comphenix.protocol.injector.netty;

import com.comphenix.protocol.events.PacketEvent;

import java.util.concurrent.Callable;

public class RunnableContainer implements Runnable {

    private ChannelInjector channelInjector;
    private Runnable runnable;
    private PacketEvent event;

    public RunnableContainer(ChannelInjector channelInjector, Runnable runnable, PacketEvent event) {
        this.channelInjector = channelInjector;
        this.runnable = runnable;
        this.event = event;
    }


    @Override
    public void run() {
        channelInjector.currentEvent = event;
        runnable.run();
        channelInjector.currentEvent = null;
    }
}
