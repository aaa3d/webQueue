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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import lombok.Getter;

/**
 *
 * @author istorozhev
 */
public interface stockInterface {
    public void loadData();
    //public List<order> getOrders();
    public List<trade> getTrades();
    public List<orderbook> getOrderbooks();
    
}
