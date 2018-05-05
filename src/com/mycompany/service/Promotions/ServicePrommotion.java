/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Promotions;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Promotions.Promotions;
import com.mycompany.entites.Utilisateurs.Users;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServicePrommotion {
    

    /*    public void subEvent(Users u , Evenements ev)
    {
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://localhost/pidevmobile/web/app_dev.php/Evenements/subsc?idevenement="+ev.getId()+"&iduser="+u.getId();
    con.setUrl(Url);
    System.out.println(Url);
    System.out.println("tt");
    
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    //            if (str.trim().equalsIgnoreCase("OK")) {
    //                f2.setTitle(tlogin.getText());
    //             f2.show();
    //            }
    //            else{
    //            Dialog.show("error", "login ou pwd invalid", "ok", null);
    //            }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
    public void udpatePromotion(Promotions ev) {
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/edit/"+ev.getIdpromotion()+"?nompromotion="+ev.getNompromotion()+"&porcentage="+ev.getPourcentage()+"&date="+ev.getDateS()+"&datefin="+ev.getDatefS()+"&file="+ev.getImage()+"&iduser="+ev.getIdUsers().getId()+"&produits="+ev.getIdproduits().getIdProduit();
    con.setUrl(Url);
    System.out.println(Url);
    System.out.println("tt");
    
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    //            if (str.trim().equalsIgnoreCase("OK")) {
    //                f2.setTitle(tlogin.getText());
    //             f2.show();
    //            }
    //            else{
    //            Dialog.show("error", "login ou pwd invalid", "ok", null);
    //            }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void udpateValidateur(int val,int id) {
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/"+id+"/edit?val="+val;
    con.setUrl(Url);
    System.out.println(Url);
    System.out.println("tt");
    
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    //            if (str.trim().equalsIgnoreCase("OK")) {
    //                f2.setTitle(tlogin.getText());
    //             f2.show();
    //            }
    //            else{
    //            Dialog.show("error", "login ou pwd invalid", "ok", null);
    //            }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void newOffer(Promotions ev) {
    ConnectionRequest con = new ConnectionRequest();
    System.out.println(ev.getNompromotion());
    String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/new?nompromotion="+ev.getNompromotion()+"&porcentage="+ev.getPourcentage()+"&date="+ev.getDateS()+"&datefin="+ev.getDatefS()+"&file="+ev.getImage()+"&iduser="+ev.getIdUsers().getId()+"&produits="+ev.getIdproduits().getIdProduit();
    System.out.println(Url);
    con.setUrl(Url);
    
    System.out.println("tt");
    
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    //            if (str.trim().equalsIgnoreCase("OK")) {
    //                f2.setTitle(tlogin.getText());
    //             f2.show();
    //            }
    //            else{
    //            Dialog.show("error", "login ou pwd invalid", "ok", null);
    //            }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void deletePromotion(int id) {
    ConnectionRequest con = new ConnectionRequest();
    String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/delete/"+id;
    System.out.println(Url);
    con.setUrl(Url);
    
    System.out.println("tt");
    
    con.addResponseListener((e) -> {
    String str = new String(con.getResponseData());
    System.out.println(str);
    //            if (str.trim().equalsIgnoreCase("OK")) {
    //                f2.setTitle(tlogin.getText());
    //             f2.show();
    //            }
    //            else{
    //            Dialog.show("error", "login ou pwd invalid", "ok", null);
    //            }
    });
    NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public ArrayList<Promotions> getListTask(String json) throws ParseException {

        ArrayList<Promotions> listPromotions = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(evenements);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            for (Map<String, Object> obj : list) {
                Promotions e = new Promotions();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idpromotion").toString());
                String nomProm = obj.get("nompromotion").toString();
                String imageprom = obj.get("imagelink").toString();

                float pourcentage = Float.parseFloat(obj.get("pourcentage").toString());
               
                 Map<String, Object> dated  = (Map<String, Object>) obj.get("datedebut");
                 float da = Float.parseFloat(dated.get("timestamp").toString());
                 Date d = new Date((long)(da-3600 )*1000);
                  Map<String, Object> datef  = (Map<String, Object>) obj.get("datefin");
                 float da2 = Float.parseFloat(datef.get("timestamp").toString());
                 Date d2 = new Date((long)(da2-3600)*1000);
                 System.out.println(d);
              Map<String, Object> produit  = (Map<String, Object>) obj.get("idproduit");
                Produits p = new Produits();
                float idp = Float.parseFloat(produit.get("idproduit").toString());
                String nomp = produit.get("nomproduit").toString();
                String imagep = produit.get("photoproduit").toString();
                float prix = Float.parseFloat(produit.get("prixproduit").toString());
                float nprix = Float.parseFloat(produit.get("newPrix").toString());
                float valid = Float.parseFloat(produit.get("valid").toString());
                p.setIdProduit((int)idp);
                p.setNomProduit(nomp);
                p.setImageProduit(imagep);
                p.setPrixProduit(prix);
                p.setNewprix(nprix);
                p.setValidateur((int)valid);



          
              Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");
              Users u = new  Users();
               
                float idu = Float.parseFloat(user.get("id").toString());
                String nom = user.get("nom").toString();
                String email = user.get("email").toString();
                
             //   Map<String, Object> time  = (Map<String, Object>) user.get("datedenaissance");
              //  Map<String, Object> time2  = (Map<String, Object>) time.get("timezone");
              //  Map<String, Object> time3  = (Map<String, Object>) time2.get("transitions");
              //  System.out.println(time3.get("time"));
                
                
             /*   
                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));*/
               
               
                System.out.println(id);
                u.setId((int)idu);
               u.setEmail(email);
                u.setNom(nom);
            
                e.setIdpromotion((int) id);
                e.setNompromotion(nomProm);
                e.setPourcentage((int)pourcentage);
                
                e.setIdUsers(u);
                e.setDatedebut(d);
                e.setDatefin(d2);
                e.setImage(imageprom);
                e.setIdproduits(p);
                
               
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // e.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());
                System.out.println(e);
                listPromotions.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPromotions);
        return listPromotions;

    }
        ArrayList<Promotions> listPromotion = new ArrayList<>();
        public ArrayList<Promotions> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServicePrommotion ser = new ServicePrommotion();
                    listPromotion = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotion;
    }
            ArrayList<Promotions> listPromotionVendeur = new ArrayList<>();
        public ArrayList<Promotions> getListPromotion(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/allA");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServicePrommotion ser = new ServicePrommotion();
                    listPromotionVendeur = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotionVendeur;
    }
        
            ArrayList<Promotions> listPromotion2 = new ArrayList<>();
        public ArrayList<Promotions> getListPromotionVendeur(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/allV?iduser="+Util.connectedUser.getId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServicePrommotion ser = new ServicePrommotion();
                    listPromotionVendeur = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotionVendeur;
    }
         public ArrayList<Produits> getListProduits(String json) throws ParseException {

        ArrayList<Produits> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> produits = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(produits);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");

            for (Map<String, Object> produit : list) {
             
                Produits p = new Produits();
                float idp = Float.parseFloat(produit.get("idproduit").toString());
                String nomp = produit.get("nomproduit").toString();
                String imagep = produit.get("photoproduit").toString();
                float prix = Float.parseFloat(produit.get("prixproduit").toString());
              
                float valid = Float.parseFloat(produit.get("valid").toString());
                p.setIdProduit((int)idp);
                p.setNomProduit(nomp);
                p.setImageProduit(imagep);
                p.setPrixProduit(prix);
              
                p.setValidateur((int)valid);

                System.out.println(p);
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }
               ArrayList<Produits> listProduits = new ArrayList<>();
        public ArrayList<Produits> getListProduits(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Promotions/findP/"+Util.connectedUser.getId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServicePrommotion ser = new ServicePrommotion();
                    listProduits = ser.getListProduits(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
        /* ArrayList<Evenements> listEvenements = new ArrayList<>();
        public ArrayList<Evenements> getList3(){
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(Util.connectedUser);
        System.out.println("");
        con.setUrl("http://localhost/pidevmobile/web/app_dev.php/Evenements/allV?iduser="+Util.connectedUser.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
        
        try {
        ServicePrommotion ser = new ServicePrommotion();
        listPromotion = ser.getListTask(new String(con.getResponseData()));
        } catch (ParseException ex) {
        
        }
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotion;
        }*/
}
