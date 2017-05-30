/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author istorozhev
 */
@Controller

@RequestMapping(value = "/operator")
public class operator {
    
    @Autowired	private SessionFactory sessionFactory;
    
    @Transactional
    @RequestMapping(value = "/view/{queue_id}",method = RequestMethod.GET)
    public ModelAndView  operator_view_queue(@PathVariable("queue_id") int queue_id){
        ModelAndView mav = new ModelAndView("operator") ;
        
        queue q = (queue) sessionFactory.getCurrentSession().get(queue.class, queue_id);
        if (q != null){
            mav.addObject(q);
        }
    return  mav;
    }
    
    @Transactional
    @RequestMapping(value = "/onButtonClick/{queue_id}/{buttonTag}",method = RequestMethod.GET)
    public ModelAndView onButtonClick(@PathVariable("buttonTag") String buttonTag, @PathVariable("queue_id") int queue_id){
        //операции по обработке нажатия кнопки
        System.out.println("onButtonClick: "+buttonTag);

        queue q = (queue) sessionFactory.getCurrentSession().get(queue.class, buttonTag);
        if (q != null){
            appContext.callNextTalon(q); //в базу записывает сам, т.к. Transactional ?
        }
        
        return operator_view_queue(queue_id);
    }
    
    
    
}
