/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;


import java.io.PrintStream;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;

import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.RolloverFileOutputStream;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;


/**
 *
 * @author istorozhev
 */
public class webQueue_main {
    public static void main(String[] args) throws Exception {
    
        

        RolloverFileOutputStream os = new RolloverFileOutputStream("yyyy_mm_dd_jcglogging.log", true);
        //We are creating a print stream based on our RolloverFileOutputStream
        PrintStream logStream = new PrintStream(os);
        //We are redirecting system out and system error to our print stream.
        System.setOut(logStream);
        System.setErr(logStream);  

        
        Server server = new Server(8080);
    
    
    
        Log.getRootLogger().info("JCG Embedded Jetty logging started.", new Object[]{});

    

    //Connector connector = new SelectChannelConnector();
    //connector.setPort(8080);
    //server.addConnector(connector);

    WebAppContext root = new WebAppContext("src/main/webapp", "/");
    
    server.setHandler(root);

    server.start();
  }
}
