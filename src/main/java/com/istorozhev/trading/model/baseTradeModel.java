/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author istorozhev
 */
@MappedSuperclass
public class baseTradeModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer")
    @Getter
    private int  id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    @Getter
    @Setter
    private Calendar createDate;
    
    @Getter
    @Setter
    private String stock_name;
    
    @Getter
    @Setter
    private String pair_name;
    
    
    
}
