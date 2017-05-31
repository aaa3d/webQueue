/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;


import java.io.Serializable;
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
import org.hibernate.annotations.Type;



/**
 *
 * @author istorozhev
 * Очередь. 
 * в ней есть список талонов по порядкку поступления
 * 
 */

@Entity
public class queue implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer")
    @Getter
    private int  id;

    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String prefix;
    
    @Getter
    @Setter
    private String queueInfo;    
    
    @Getter
    @Setter
    @ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id", insertable = true, columnDefinition = "integer", foreignKey = @ForeignKey(name = "FK_QUEUE_HEAD_TALON"))
    private talon head;
    
    @Getter
    @Setter
    @ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @OnDelete(action=CASCADE)
    @JoinColumn(name = "tail_id", insertable = true, columnDefinition = "integer", foreignKey = @ForeignKey(name = "FK_QUEUE_TAIL_TALON"))            
    private talon tail;
    
    
   

    
    

    
}
