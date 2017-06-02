/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.model;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 * 
 */
@Entity
public class ticker extends baseTradeModel{
    @Getter
    @Setter
    private double buy_price;
    
    @Getter
    @Setter
    private double sell_price;
    
    @Getter
    @Setter
    private double last_trade_price;
    
    @Getter
    @Setter
    private double high;
    
    @Getter
    @Setter
    private double low;
    
    @Getter
    @Setter
    private double avg;
    
    @Getter
    @Setter
    private double vol;
    
    @Getter
    @Setter
    private double vol_curr;
    
    @Getter
    @Setter
    private int updated_timestamp;
    
    @Getter
    @Setter
    private double summ;
}
