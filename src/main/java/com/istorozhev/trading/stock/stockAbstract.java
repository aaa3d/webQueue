/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.stock;

import com.istorozhev.trading.model.orderbook_details;
import com.istorozhev.trading.model.orderbook;
import com.istorozhev.trading.model.ticker;
import com.istorozhev.trading.model.trade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author istorozhev
 */
public abstract class stockAbstract implements stockInterface{
    protected List<orderbook_details> orders = new java.util.ArrayList<orderbook_details>();
    protected List<trade> trades = new java.util.ArrayList<trade>();
    protected ticker ticker = new ticker();
    
    
    protected List<orderbook> orderbooks = new ArrayList<orderbook>();
    
    protected List<String> stockPairs = new ArrayList<String>();
    protected Calendar serverTime = Calendar.getInstance();;
    protected String stock_name;
    
    
    public List<orderbook> getOrderbooks(){
        return orderbooks;
    }
    
    
    /*
    public List<order> getOrders(){
        return orders;
    }
    */
    public List<trade> getTrades(){
        return trades;
    }
}
