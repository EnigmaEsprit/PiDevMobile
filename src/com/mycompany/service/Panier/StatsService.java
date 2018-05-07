/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Panier;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Panier.Stats;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.service.Produits.ProduitsServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jean
 */
public class StatsService {
    
    
    public ArrayList<Stats> getListTask(String json) {

        ArrayList<Stats> listproduitsStats = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> stats = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(stats);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) stats.get("root");

            for (Map<String, Object> obj : list) {
               Stats statObject = new Stats();

               int quantite = Integer.parseInt(obj.get("1").toString());
                System.out.println(quantite);
               statObject.setQuantiteStats(quantite);
                
           
                statObject.setNomproduitStats(obj.get("nomproduit").toString());
               
                System.out.println(statObject);
                listproduitsStats.add(statObject);

            }

        } catch (IOException ex) {
        }
        System.out.println(listproduitsStats);
        return listproduitsStats;

    }
    
    
    ArrayList<Stats> listProduitsStats2 = new ArrayList<>();
    
    public ArrayList<Stats> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
       // con.setUrl("http://41.226.11.243:10004/tasks/");  
       con.setUrl("http://localhost/PidevWebMobile/web/vendeur1/stats");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                StatsService ser = new StatsService();
                listProduitsStats2 = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduitsStats2;
    } 
}
