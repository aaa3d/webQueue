/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.model;

import com.yarregion.webqueue.webqueue.talon;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 * 
 */
@Entity
@Table(indexes = {@Index(columnList = "orderbook_id", name = "idx_orderbook_id")})
public class orderbook_details extends baseTradeModel{
    @Getter
    @Setter
    private String operation;
    
    @Getter
    @Setter
    private double price;
    
    @Getter
    @Setter
    private double amount;
    
    @Getter
    @Setter
    private double summ;
    
    @Getter
    @Setter
    @ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "orderbook_id", nullable = false, updatable = false, insertable = true, columnDefinition = "integer")
    private orderbook orderbook;
    
    
    public orderbook_details(orderbook parent){
        orderbook = parent;
    }
    
}
