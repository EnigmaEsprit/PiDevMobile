/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Produits;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ProduitsServices {

  

    public ArrayList<Produits> getListTask(String json) {

        ArrayList<Produits> listproduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Produits p = new Produits();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idproduit").toString());
                  float prix = Float.parseFloat(obj.get("prixproduit").toString());
                    float quantite = Float.parseFloat(obj.get("quantiteproduit").toString());
                System.out.println(id);
                p.setIdProduit((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // e.setEtat(obj.get("state").toString());
                p.setNomProduit(obj.get("nomproduit").toString());
               p.setPrixProduit((double)prix);
               p.setQuantiteProduit((int)quantite);
                System.out.println(p);
                listproduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listproduits);
        return listproduits;

    }
    
    
    ArrayList<Produits> listProduits2 = new ArrayList<>();
    
    public ArrayList<Produits> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
       // con.setUrl("http://41.226.11.243:10004/tasks/");  
       con.setUrl("http://"+Util.addip+"/PidevWebMobile/web/produit/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitsServices ser = new ProduitsServices();
                listProduits2 = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits2;
    }

}
