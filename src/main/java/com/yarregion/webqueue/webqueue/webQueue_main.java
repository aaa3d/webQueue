/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;

import org.eclipse.jetty.servlet.ServletHolder;


/**
 *
 * @author istorozhev
 */
public class webQueue_main {
    public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    //Connector connector = new SelectChannelConnector();
    //connector.setPort(8080);
    //server.addConnector(connector);

    WebAppContext root = new WebAppContext("src/main/webapp", "/");
    
    server.setHandler(root);

    server.start();
  }
}
