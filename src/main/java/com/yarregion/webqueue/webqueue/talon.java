/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;



/**
 *
 * @author istorozhev
 */
@Entity
public class talon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer")
    @Getter
    private int  id;

    @Getter
    @Setter
    private String name;
    
    @Column(columnDefinition = "integer")
    @Getter
    @Setter
    private int  number;
    
    @Getter
    private Calendar date;
    
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_id", insertable = true, columnDefinition = "integer")
    private talon nextTalon;
    
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "queue_id", insertable = true, columnDefinition = "integer")
    private queue queue;
    
    
    
    public talon(){
        System.out.print((String.format("new talon def constructor: id={0}, number={1} ", this.id, this.number)));
        
        
    }
    
    
    public talon(talon parent){
        
        date =Calendar.getInstance();
        
        if (parent != null){
            number = parent.getNumber()+1;
            parent.setNextTalon(this);
        }
        
        
    }

}
