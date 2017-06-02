/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.stock;

import com.istorozhev.trading.model.order;
import com.istorozhev.trading.model.ticker;
import com.istorozhev.trading.model.trade;
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
    public List<order> orders = new java.util.ArrayList<order>();
    List<trade> trades = new java.util.ArrayList<trade>();
    ticker ticker = new ticker();
    
    
    
    public void loadData();
}
