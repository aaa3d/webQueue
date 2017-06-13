/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.model;

import com.yarregion.webqueue.webqueue.talon;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 */
@Entity
public class orderbook extends baseTradeModel{
    @Getter
    @Setter
    private Double ask_quantity;
    
    @Getter
    @Setter
    private Double ask_amount;
    
    @Getter
    @Setter
    private Double ask_top;
    
    
    @Getter
    @Setter
    private Double bid_quantity;
    
    @Getter
    @Setter
    private Double bid_amount;
    
    @Getter
    @Setter
    private Double bid_top;
    
    @Getter
    @Setter
    @OneToMany(mappedBy="orderbook", cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @Column(name="orderbook_id", nullable = false)
    private List<orderbook_details> orderbook_details;
    
    
    
}
