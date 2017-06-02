/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.helper;

import com.istorozhev.trading.model.trade;
import com.istorozhev.trading.stock.stockInterface;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author istorozhev
 */
@Controller
public class databaseHelper {
    @Autowired	private SessionFactory sessionFactoryAruba;
    
    
    @Transactional
    public void saveStockDataToDatabase(stockInterface stock){
        for(trade trade:stock.trades){
            
            sessionFactoryAruba.openSession().save(trade);
            //sessionFactoryAruba.getCurrentSession().save(trade);
        }
        
        
    }
}
