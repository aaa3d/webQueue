/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;

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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

/**
 *
 * @author istorozhev
 */
@Entity
public class context {
    
    @Id
    @Column(columnDefinition = "integer")
    @Getter
    @Setter
    private int  id;
    
    
    @Getter
    @Setter
    private String Name;
    
    
    
    @Getter
    @Setter
    @ManyToOne(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "tail_id", insertable = true, columnDefinition = "integer", foreignKey = @ForeignKey(name = "FK_CONTEXT_TAIL_TALON"))            
    private talon tailTalon;
}
