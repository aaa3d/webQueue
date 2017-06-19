/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.helper;

import com.istorozhev.trading.model.orderbook;
import com.istorozhev.trading.model.orderbook_details;
import com.istorozhev.trading.model.trade;
import com.istorozhev.trading.stock.stockInterface;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
        for(trade trade:stock.getTrades()){
            Criteria crit = sessionFactory.getCurrentSession().createCriteria(trade.getClass());
            crit.add(Restrictions.eq("trade_id", trade.getTrade_id()));
            if (crit.list().size()==0)
                sessionFactory.getCurrentSession().save(trade);
            //sessionFactoryAruba.getCurrentSession().save(trade);
        }
        
        for(orderbook orderbook:stock.getOrderbooks()){
            
            sessionFactory.getCurrentSession().save(orderbook);
            //sessionFactoryAruba.getCurrentSession().save(trade);
        }
    }
    
    @Transactional
    public List<trade> getTrades(){
            
        org.hibernate.Criteria criteria = null;
        
        criteria = sessionFactory.getCurrentSession().createCriteria(trade.class);
        
        
        criteria.add(Restrictions.eq("pair_name", "btc_usd"));
        criteria.add(Restrictions.eq("stock_name", "BTC-E"));
        return criteria.list();
    }
}
