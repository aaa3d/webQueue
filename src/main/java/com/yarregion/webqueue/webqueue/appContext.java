/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;



/**
 *
 * @author istorozhev
 */

public class appContext  {
   

    @Getter
    @Setter
    private static String Name;
    
    
    
    @Getter
    @Setter
    static talon tailTalon;
    
    
    
    
    @Getter
    @Setter
    private static Set<queue> queue_set = new HashSet<queue>();
    
    
    
    
    public static void makeNewTalon( queue queue){
        talon t = new talon();
        t.setQueue(queue);
        
        if (getTailTalon() != null){
            t.setNumber(getTailTalon().getNumber()+1);
            setTailTalon(t);
        }
        else{
            t.setNumber(1);
        }
        setTailTalon(t);
        
        
        if (queue.getTail()!=null){
            queue.getTail().setNextTalon(t);
            
        }
        queue.setTail(t);
        
        
        if (queue.getHead()==null)
            queue.setHead((t));
        
        
        updateQueueInfo(queue);
        
    }
    
    public static void callNextTalon(queue queue){
        if (queue.getHead()==null)
            return;
        
        queue.setHead(queue.getHead().getNextTalon());
        updateQueueInfo(queue);
    }
    
    private static void updateQueueInfo(queue queue){
        String queueInfo = "";
        talon t = queue.getHead();
        while (t != null){
            if (queueInfo.length()!=0)
                queueInfo += " ";
            queueInfo += t.getNumber();
            t = t.getNextTalon();
        }
        
        queue.setQueueInfo(queueInfo);
    }
    

}
