/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.stock;


import com.istorozhev.trading.model.order;
import com.istorozhev.trading.model.trade;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author istorozhev
 */
public class stockImplExmo implements stockInterface {

    
    public void loadTrades(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
           
            
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://btc-e.nz/api/3/trades/btc_usd")
                    .header("accept", "application/json")
//                    .queryString("apiKey", "123")
//                    .field("parameter", "value")
//                    .field("foo", "bar")
                    .asJson();
            
            JSONArray stock_trades = jsonResponse.getBody().getObject().getJSONArray("btc_usd");
           /* try {
                Unirest.shutdown();
            } catch (IOException ex) {
                Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
                
            
            //fantastic!!! new object has array data from old object
            trades.clear();
            for( Object o : stock_trades){
                
                trade trade = new trade();
                
                JSONObject stock_trade = (JSONObject)o;
                trade.setStock_name("BTC-E");
                trade.setPrice(stock_trade.getDouble("price"));
                trade.setAmount(stock_trade.getDouble("amount"));
                trade.setSumm(trade.getPrice()*trade.getAmount());
                
                trade.setTrade_id(String.valueOf(stock_trade.getLong("tid")));
                trade.setPair_name("btc_usd");
                trade.setOperation(stock_trade.getString("type"));
                Calendar cal = Calendar.getInstance();
                cal.set(1970, Calendar.JANUARY, 1, 0, 0,  0);
                cal.add(Calendar.SECOND, stock_trade.getInt("timestamp"));
                
                trade.setCreateDate(cal);
                
                trades.add(trade);
//trade.get("amount")
            
            }
            
            System.out.append(jsonResponse.getRawBody().toString());
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void loadOrders(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
           
            
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://btc-e.nz/api/3/depth/btc_usd")
                    .header("accept", "application/json")
//                    .queryString("apiKey", "123")
//                    .field("parameter", "value")
//                    .field("foo", "bar")
                    .asJson();
            
            JSONObject operations = jsonResponse.getBody().getObject().getJSONObject("btc_usd");
            JSONArray bids = operations.getJSONArray("bids");
            JSONArray asks = operations.getJSONArray("asks");
            
            //one time for all
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -3);
            
            
            System.out.println("CALENDAR="+cal.toString());
            
            
           
                
            
            
            //fantastic!!! new object has array data from old object
            orders.clear();
            for( Object o : bids){
                
                order order = new order();
                JSONArray stock_order = (JSONArray)o; 
                order.setStock_name("BTC-E");
                order.setPrice(Double.valueOf(stock_order.get(0).toString()));
                order.setAmount(Double.valueOf(stock_order.get(1).toString()));
                order.setSumm(order.getPrice()*order.getAmount());
                order.setOperation("bid");
                order.setPair_name("btc_usd");
                
                order.setCreateDate(cal);
                orders.add(order);
            }
            
            for( Object o : asks){
                
                order order = new order();
                JSONArray stock_order = (JSONArray)o; 
                order.setStock_name("BTC-E");
                order.setPrice(Double.valueOf(stock_order.get(0).toString()));
                order.setAmount(Double.valueOf(stock_order.get(1).toString()));
                
                order.setSumm(order.getPrice()*order.getAmount());
                order.setOperation("ask");
                order.setPair_name("btc_usd");
                
                order.setCreateDate(cal);
                orders.add(order);
            }
            
            System.out.append(jsonResponse.getRawBody().toString());
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void loadData() {
        loadTrades();
        loadOrders();
    }

    
}
