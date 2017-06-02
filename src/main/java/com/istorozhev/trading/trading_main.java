/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading;

import com.istorozhev.trading.helper.databaseHelper;
import com.istorozhev.trading.stock.stockImplExmo;
import com.istorozhev.trading.stock.stockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;

/**
 *
 * @author istorozhev
 */
@Controller
public class trading_main {
    
    @Autowired databaseHelper database;
    
     public static void main(String[] args) throws Exception {
        //ApplicationContext context = new ClassPathXmlApplicationContext(String[] {"applicationContext.xml", "dispatcher-servlet.xml"});
        ApplicationContext context;
        context = new FileSystemXmlApplicationContext(new String [] {"src\\main\\webapp\\WEB-INF\\applicationContext.xml","src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml"});
        databaseHelper database = (databaseHelper) context.getBean("databaseHelper");
        stockInterface exmo = new stockImplExmo();
        exmo.loadData();
        database.saveStockDataToDatabase(exmo);
     }
}
