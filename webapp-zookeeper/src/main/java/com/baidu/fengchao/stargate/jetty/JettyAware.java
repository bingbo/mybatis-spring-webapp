/*
 * Baidu.com Inc.
 * Copyright (c) 2000-2012 All Rights Reserved.
 */
package com.baidu.fengchao.stargate.jetty;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * To embed a Jetty server, the following steps are typical:
 * 
 * 1. Create the server 
 * 2. Add/Configure Connectors 
 * 3. Add/Configure Handlers 
 * 4. Add/Configure Servlets/Webapps to Handlers 
 * 5. start the server 
 * 6. wait (join the server to prevent main exiting).
 * 
 * @author sunkai
 * 
 * @version $Id: JettyAware.java 2012-12-10 ����02:23:11 sunkai Exp $
 */
public class JettyAware implements ApplicationContextAware {

    Logger logger = Logger.getLogger(JettyAware.class);

    /**
     * Jetty Server
     */
    private Server server;

    /**
     * Jetty Port
     */
    private Integer port;

    /**
     * Spring application context.
     */
    private ApplicationContext applicationContext;

    /**
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    /**
     * Confgure server && start server.
     * 
     * @author sunkai
     * @date 2012-12-13
     * @throws Exception void 
     */
    public void init() throws Exception {
        port = ConfigUtils.getJettyPort();
        server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        server.addConnector(connector);

        logger.info("Init connector finished with port = " + port + "..");

        RpcServletHandler handler = new RpcServletHandler();
        handler.setApplicationContext(applicationContext);

        handler.init();
        server.setHandler(handler);

        logger.info("Init RpcServletHandler finished with handler = " + handler);

    }

    public void start() {
        try {
            if (server == null) {
                this.init();
            }
            server.start();
            logger.info("Jetty started success. port: " + port);
        } catch (Exception e) {
            logger.info("Start jetty failed! ", e);
            throw new RuntimeException("Start jetty failed! ", e);
        }

    }

}
