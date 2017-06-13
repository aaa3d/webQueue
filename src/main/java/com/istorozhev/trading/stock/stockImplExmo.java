/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istorozhev.trading.stock;


import com.istorozhev.trading.model.orderbook;
import com.istorozhev.trading.model.orderbook_details;
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
public class stockImplExmo extends stockAbstract implements stockInterface{

   
    
    public stockImplExmo(String _stock_name){
        stock_name = _stock_name;
    }
    
    public void loadServerInfo(){
        //http://api.exmo.me/v1/ticker
        /*
        {"BTC_USD":{"buy_price":"2723.120012","sell_price":"2723.130011","last_trade":"2723.130011","high":"2748","low":"2644.5","avg":"2704.31958987","vol":"94.52161761","vol_curr":"254695.89531784","updated":1496991793},"BTC_EUR":{"buy_price":"2495.58900001","sell_price":"2499.99999","last_trade":"2500","high":"2527","low":"2424.677724","avg":"2478.78212333","vol":"62.23086942","vol_curr":"154046.74640422","updated":1496991834},"BTC_RUB":
}
        */
        stockPairs.clear();
        
        HttpResponse<JsonNode> jsonResponse;
        try {
            jsonResponse = Unirest.get("http://api.exmo.me/v1/ticker")
                    .header("accept", "application/json")
                    .asJson();
        
            
            for(String pair_name : jsonResponse.getBody().getObject().keySet()){
                stockPairs.add(pair_name);
                serverTime.set(1970, Calendar.JANUARY, 1, 0, 0,  0);
                serverTime.add(Calendar.SECOND, jsonResponse.getBody().getObject().getJSONObject(pair_name).getInt("updated"));
                
            }
        
        
        
        } catch (Exception ex) {
            Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadTrades(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
           
            String pairs = "";
            for(String pair_name: stockPairs){
                pairs = pairs+pair_name+",";
            }
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.exmo.me/v1/trades/?pair="+pairs)
                    .header("accept", "application/json")
                    .asJson();
            
            for(String pair_name: stockPairs){
                JSONArray stock_trades = jsonResponse.getBody().getObject().getJSONArray(pair_name);
                
                for( Object o : stock_trades){

                    trade trade = new trade();

                    JSONObject stock_trade = (JSONObject)o;
                    trade.setStock_name(stock_name);
                    trade.setPrice(stock_trade.getDouble("price"));
                    trade.setAmount(stock_trade.getDouble("quantity"));
                    trade.setSumm(trade.getPrice()*trade.getAmount());

                    trade.setTrade_id(String.valueOf(stock_trade.getLong("trade_id")));
                    trade.setPair_name(pair_name.toLowerCase());
                    trade.setOperation(stock_trade.getString("type"));
                    Calendar cal = Calendar.getInstance();
                    cal.set(1970, Calendar.JANUARY, 1, 0, 0,  0);
                    cal.add(Calendar.SECOND, stock_trade.getInt("date"));

                    trade.setCreateDate(cal);

                    trades.add(trade);
    //trade.get("amount")

                }
            }
            
            System.out.append(jsonResponse.getRawBody().toString());
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void loadOrders(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
            String pairs = "";
            
            
            
            for(String pair_name: stockPairs){
                pairs = pairs+pair_name+",";
            }
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://api.exmo.me/v1/order_book/?pair="+pairs)
                    .header("accept", "application/json")
                    .asJson();
            for(String pair_name: stockPairs){
                    JSONObject operations = jsonResponse.getBody().getObject().getJSONObject(pair_name);    
                    JSONArray bids = operations.getJSONArray("bid");
                    JSONArray asks = operations.getJSONArray("ask");
                    orderbook orderbook = new orderbook();
                    orderbooks.add(orderbook);
                    orders = new java.util.ArrayList<orderbook_details>();
                    orderbook.setOrderbook_details(orders);
                    orderbook.setCreateDate(serverTime);
                    orderbook.setPair_name(pair_name.toLowerCase());
                    orderbook.setStock_name(stock_name);
                    orderbook.setAsk_quantity(operations.getDouble("ask_quantity"));
                    orderbook.setAsk_amount(operations.getDouble("ask_amount"));
                    orderbook.setAsk_top(operations.getDouble("ask_top"));
                    orderbook.setBid_quantity(operations.getDouble("bid_quantity"));
                    orderbook.setBid_amount(operations.getDouble("bid_amount"));
                    orderbook.setBid_top(operations.getDouble("bid_top"));
                    

                    
                    for( Object o : bids){

                        orderbook_details order = new orderbook_details(orderbook);
                        JSONArray stock_order = (JSONArray)o; 
                        order.setStock_name(stock_name);
                        order.setPrice(Double.valueOf(stock_order.get(0).toString()));
                        order.setAmount(Double.valueOf(stock_order.get(1).toString()));
                        order.setSumm(order.getPrice()*order.getAmount());
                        order.setOperation("bid");
                        order.setPair_name(pair_name.toLowerCase());

                        order.setCreateDate(serverTime);
                        orders.add(order);
                    }

                    for( Object o : asks){

                        orderbook_details order = new orderbook_details(orderbook);
                        JSONArray stock_order = (JSONArray)o; 
                        order.setStock_name(stock_name);
                        order.setPrice(Double.valueOf(stock_order.get(0).toString()));
                        order.setAmount(Double.valueOf(stock_order.get(1).toString()));

                        order.setSumm(order.getPrice()*order.getAmount());
                        order.setOperation("ask");
                        order.setPair_name(pair_name.toLowerCase());

                        order.setCreateDate(serverTime);
                        orders.add(order);
                    }

                    System.out.append(jsonResponse.getRawBody().toString());
            }
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplExmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void loadData() {
        
        loadServerInfo(); //загрузка времени сервера
        loadTrades();
        
        loadOrders();
    }

    
}
