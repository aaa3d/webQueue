/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.helper;

import com.istorozhev.trading.model.order;
import com.istorozhev.trading.model.trade;
import com.istorozhev.trading.stock.stockInterface;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author istorozhev
 */
@Controller
public class databaseHelper {
    @Autowired	private SessionFactory sessionFactory;
    
    
    @Transactional
    public void saveStockDataToDatabase(stockInterface stock){
        
        //save only new trades
        for(trade trade:stock.trades){
            Criteria crit = sessionFactory.getCurrentSession().createCriteria(trade.getClass());
            crit.add(Restrictions.eq("trade_id", trade.getTrade_id()));
            if (crit.list().size()==0)
                sessionFactory.getCurrentSession().save(trade);
            //sessionFactoryAruba.getCurrentSession().save(trade);
        }
        
        //save all orders for this time
        for(order order:stock.orders){
                sessionFactory.getCurrentSession().save(order);
            //sessionFactoryAruba.getCurrentSession().save(trade);
        }
        
        
    }
}
