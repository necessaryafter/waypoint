package me.necessaryafter.waypoint.impl.util;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@RequiredArgsConstructor
public class NamedThreadFactory implements ThreadFactory {

    private final String baseName;
    private final AtomicInteger threadNum = new AtomicInteger();
    private boolean daemon;

    @Override
    public synchronized Thread newThread(Runnable r) {
        Thread thread = Executors.defaultThreadFactory().newThread(r);

        thread.setName(this.baseName + " #" + this.threadNum.getAndIncrement());

        thread.setDaemon(this.daemon);

        return thread;
    }
}