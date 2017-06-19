/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading;

import com.istorozhev.trading.helper.databaseHelper;
import com.istorozhev.trading.stock.stockImplBtce;
import com.istorozhev.trading.stock.stockImplExmo;
import com.istorozhev.trading.stock.stockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 *
 * @author igor
 */

/**/


@Controller
public class tradingWorker {
    
    @Autowired databaseHelper database;
    @Scheduled(initialDelay = 2000000, fixedDelay=60000)
    public void doSomething() {
        stockInterface btce = new stockImplBtce("BTC-E");
        
        btce.loadData();
        
        stockInterface exmo = new stockImplExmo("EXMO");
        exmo.loadData();
        
        
        database.saveStockDataToDatabase(btce);
        database.saveStockDataToDatabase(exmo);
        
    }
}
