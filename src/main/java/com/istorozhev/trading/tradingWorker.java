/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading;

import com.istorozhev.trading.helper.databaseHelper;
import com.istorozhev.trading.stock.stockImplBtce;
import com.istorozhev.trading.stock.stockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 *
 * @author igor
 */
@Controller
public class tradingWorker {
    
    @Autowired databaseHelper database;
    @Scheduled(initialDelay = 20, fixedDelay=30000)
    public void doSomething() {
        stockInterface btce = new stockImplBtce();
        btce.loadData();
        database.saveStockDataToDatabase(btce);
    }
}
