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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
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
    private String Name;
    
    @Getter
    private String queueInfo;    
    
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id", insertable = true, columnDefinition = "integer")
    talon head;
    
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tail_id", insertable = true, columnDefinition = "integer")            
    talon tail;

    
    
    public boolean addNewTalon(){

        //определить максимальный номер в очереди 
        
        talon newTalon = new talon(this.getTail());  //увеличит номер, прсвоит tail next talon

        if (this.getHead()==null)
            this.setHead(newTalon);

        this.setTail(newTalon);
        
        fillQueueInfo();
        return true;
    }    
    
    
    private void fillQueueInfo(){
        queueInfo = "";
        talon t = this.head;
        while (t != null){
            if (queueInfo.length()!=0)
                queueInfo += " ";
            queueInfo += t.getNumber();
            t = t.getNextTalon();
        }
    }

    
}
