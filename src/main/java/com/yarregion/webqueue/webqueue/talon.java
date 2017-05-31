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
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;





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
    @ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "next_id", insertable = true, columnDefinition = "integer", foreignKey = @ForeignKey(name = "FK_TALON_NEXT_TALON"))
    private talon nextTalon;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", insertable = true, columnDefinition = "integer", foreignKey = @ForeignKey(name = "FK_TALON_QUEUE"))
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
