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
public class stockImplBtce implements stockInterface {

    
    public void loadServerInfo(){
        //https://btc-e.nz/api/3/info
        /*
        {
	"server_time":1370814956,
	"pairs":{
		"btc_usd":{
			"decimal_places":3,
			"min_price":0.1,
			"max_price":400,
			"min_amount":0.01,
			"hidden":0,
			"fee":0.2
		}
		...
	}
}
        */
        stockPairs.clear();
        
        HttpResponse<JsonNode> jsonResponse;
        try {
            jsonResponse = Unirest.get("https://btc-e.nz/api/3/info")
                    .header("accept", "application/json")
//                    .queryString("apiKey", "123")
//                    .field("parameter", "value")
//                    .field("foo", "bar")
                    .asJson();
        
            
            serverTime.set(1970, Calendar.JANUARY, 1, 0, 0,  0);
            serverTime.add(Calendar.SECOND, jsonResponse.getBody().getObject().getInt("server_time"));
            
            //JSONArray jsonPairs =  jsonResponse.getBody().getObject().getJSONArray("pairs");
            
            
            for(String pair_name : jsonResponse.getBody().getObject().getJSONObject("pairs").keySet()){
                stockPairs.add(pair_name);
            }
                    

        
        
        
        
        } catch (Exception ex) {
            Logger.getLogger(stockImplBtce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadTrades(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
           
            String pairs = "";
            trades.clear();
            for(String pair_name: stockPairs){
                pairs = pairs+pair_name+"-";
            }
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://btc-e.nz/api/3/trades/"+pairs)
                    .header("accept", "application/json")
                    .asJson();
            
            for(String pair_name: stockPairs){
                JSONArray stock_trades = jsonResponse.getBody().getObject().getJSONArray(pair_name);
                
                for( Object o : stock_trades){

                    trade trade = new trade();

                    JSONObject stock_trade = (JSONObject)o;
                    trade.setStock_name("BTC-E");
                    trade.setPrice(stock_trade.getDouble("price"));
                    trade.setAmount(stock_trade.getDouble("amount"));
                    trade.setSumm(trade.getPrice()*trade.getAmount());

                    trade.setTrade_id(String.valueOf(stock_trade.getLong("tid")));
                    trade.setPair_name(pair_name);
                    trade.setOperation(stock_trade.getString("type"));
                    Calendar cal = Calendar.getInstance();
                    cal.set(1970, Calendar.JANUARY, 1, 0, 0,  0);
                    cal.add(Calendar.SECOND, stock_trade.getInt("timestamp"));

                    trade.setCreateDate(cal);

                    trades.add(trade);
    //trade.get("amount")

                }
            }
            
            System.out.append(jsonResponse.getRawBody().toString());
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplBtce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void loadOrders(){
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
            String pairs = "";
            orders.clear();
            for(String pair_name: stockPairs){
                pairs = pairs+pair_name+"-";
            }
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://btc-e.nz/api/3/depth/"+pairs)
                    .header("accept", "application/json")
                    .asJson();
            for(String pair_name: stockPairs){
                    JSONObject operations = jsonResponse.getBody().getObject().getJSONObject(pair_name);
                    JSONArray bids = operations.getJSONArray("bids");
                    JSONArray asks = operations.getJSONArray("asks");

                    
                    for( Object o : bids){

                        order order = new order();
                        JSONArray stock_order = (JSONArray)o; 
                        order.setStock_name("BTC-E");
                        order.setPrice(Double.valueOf(stock_order.get(0).toString()));
                        order.setAmount(Double.valueOf(stock_order.get(1).toString()));
                        order.setSumm(order.getPrice()*order.getAmount());
                        order.setOperation("bid");
                        order.setPair_name(pair_name);

                        order.setCreateDate(serverTime);
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
                        order.setPair_name(pair_name);

                        order.setCreateDate(serverTime);
                        orders.add(order);
                    }

                    System.out.append(jsonResponse.getRawBody().toString());
            }
        } catch (UnirestException ex) {
            Logger.getLogger(stockImplBtce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void loadData() {
        
        loadServerInfo(); //загрузка времени сервера
        loadTrades();
        
        loadOrders();
    }

    
}
