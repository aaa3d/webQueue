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

    @Override
    public void loadData() {
        try {
            //здесь реализовать парсеры данных, раскладывание их в модели
            
            
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://btc-e.nz/api/3/trades/btc_usd")
                    .header("accept", "application/json")
                    .queryString("apiKey", "123")
                    .field("parameter", "value")
                    .field("foo", "bar")
                    .asJson();
            
            JSONArray stock_trades = jsonResponse.getBody().getObject().getJSONArray("btc_usd");
            for( Object o : stock_trades){
                
                trade trade = new trade();
                JSONObject stock_trade = (JSONObject)o;
                trade.setPrice(stock_trade.getDouble("price"));
                trade.setAmount(stock_trade.getDouble("amount"));
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

    
}
