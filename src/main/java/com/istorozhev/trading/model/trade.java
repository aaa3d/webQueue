/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.model;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 */


@Entity
@Table(indexes = {@Index(columnList = "trade_id", name = "idx_trade_id")})
public class trade extends baseTradeModel {
    
    @Getter
    @Setter
    private String trade_id;
    
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
    
}
