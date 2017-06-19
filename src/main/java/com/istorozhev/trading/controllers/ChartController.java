/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.controllers;


import com.istorozhev.trading.helper.databaseHelper;
import com.istorozhev.trading.model.trade;
import com.istorozhev.trading.stock.stockImplBtce;
import com.istorozhev.trading.stock.stockImplExmo;
import com.istorozhev.trading.stock.stockInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author istorozhev
 */
@Controller

@RequestMapping(value = "/chart")
public class ChartController {
    
    private class jsonResponse{
        public String date;
        public String value;
        public jsonResponse(Calendar _date, Double _value){
            date = String.format("UTC(%d,%d,%d,%d,%d,%d)", _date.get(Calendar.YEAR),_date.get(Calendar.MONTH), _date.get(Calendar.DAY_OF_MONTH), _date.get(Calendar.HOUR_OF_DAY), _date.get(Calendar.MINUTE), _date.get(Calendar.SECOND));
            value = _value.toString();
        }
        
    }
    
    @Autowired databaseHelper database;
    
    @RequestMapping(value = "/getJson", method = RequestMethod.GET, headers="Accept=*/*",  produces="application/json;; charset=UTF-8")
    @ResponseBody
    public Map<String,String> getJson(){
        Map<String,String> resultArray = new HashMap<String,String>();
        
        for(trade t: database.getTrades()){
            
            Calendar _date = t.getCreateDate();
            Double _value = t.getPrice();
            
            resultArray.put(String.format("UTC(%d,%d,%d,%d,%d,%d)", _date.get(Calendar.YEAR),_date.get(Calendar.MONTH), _date.get(Calendar.DAY_OF_MONTH), _date.get(Calendar.HOUR_OF_DAY), _date.get(Calendar.MINUTE), _date.get(Calendar.SECOND)), _value.toString());
                    
            
        }
        
        return resultArray;
        
        
    }
    
    
}
