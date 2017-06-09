/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;





/**
 *
 * @author istorozhev
 */
@Controller
public class appContext  {
   
    @Autowired	private SessionFactory sessionFactory;
    
    public context getContext(){
        context c = (context) sessionFactory.getCurrentSession().get(context.class, 1500);
        if (c == null){
            c = new context();
            c.setId(1500);
            sessionFactory.getCurrentSession().save(c);
        }
        return c;
    }
    
    
    public void makeNewTalon( queue queue){
        talon t = new talon();
        t.setQueue(queue);
        
        if (getContext().getTailTalon() != null){
            t.setNumber(getContext().getTailTalon().getNumber()+1);
            getContext().setTailTalon(t);
        }
        else{
            t.setNumber(1);
        }
        getContext().setTailTalon(t);
        
        
        if (queue.getTail()!=null){
            queue.getTail().setNextTalon(t);
            
        }
        queue.setTail(t);
        
        
        if (queue.getHead()==null)
            queue.setHead((t));
        
        
        updateQueueInfo(queue);
        
    }
    
    public void callNextTalon(queue queue){
        if (queue.getHead()==null)
            return;
        
        queue.setHead(queue.getHead().getNextTalon());
        if (queue.getHead()==null)
            queue.setTail(null);
        updateQueueInfo(queue);
    }
    
    private void updateQueueInfo(queue queue){
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
