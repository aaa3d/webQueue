/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarregion.webqueue.webqueue;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author istorozhev
 */
@Controller

@RequestMapping(value = "/admin")
public class admin {
    
    
    @Autowired	private SessionFactory sessionFactory;
    
    
    @Transactional
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView  admin_view(){
        
        
        ModelAndView mav = new ModelAndView("admin") ;
        org.hibernate.Criteria criteria = null;
        
        criteria = sessionFactory.getCurrentSession().createCriteria(queue.class);
        List<queue> list = null;
        
        
    	//Загрузка объектов из БД
        if (criteria!=null){
            list = criteria.list();
            mav.addObject("queueList", list);  
            return mav;
            
            
        }
        
        //вернуть окно терминала
    return  mav;
    }
    
    
    @Transactional
    @RequestMapping(value = "/onButtonClick/{buttonTag}",method = RequestMethod.GET)
    public ModelAndView onButtonClick(@PathVariable("buttonTag") String buttonTag){
        //операции по обработке нажатия кнопки
        System.out.println("admin/onButtonClick: "+buttonTag);
        
        if (buttonTag.compareTo("add_queue")==0){
            queue q = new queue();
            
            sessionFactory.getCurrentSession().save(q);
            
        }
        return admin_view();
    }
    
}
