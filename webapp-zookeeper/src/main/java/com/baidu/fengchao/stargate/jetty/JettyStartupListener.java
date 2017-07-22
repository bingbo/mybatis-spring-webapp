/*
 * Baidu.com Inc.
 * Copyright (c) 2000-2012 All Rights Reserved.
 */
package com.baidu.fengchao.stargate.jetty;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * Jetty Startup Listener
 * 
 * Jetty startup after container started.
 * 
 */
public class JettyStartupListener implements ApplicationListener<ContextStartedEvent> {

    private JettyAware jettyServer;

    public void setJettyServer(JettyAware jettyServer) {
        this.jettyServer = jettyServer;
    }

    public void onApplicationEvent(ContextStartedEvent event) {
        jettyServer.start();
    }

}
