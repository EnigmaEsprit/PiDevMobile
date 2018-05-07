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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Panier.Commande;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author jean
 */
public class CommandesServices {
    public ArrayList<Commande> getListTask(String json) throws ParseException {

        ArrayList<Commande> listCommandes = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> commandes = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(commandes);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) commandes.get("root");

           // List<Map<String, Object>> listUser; 
            
            for (Map<String, Object> obj : list) {
               Commande CommandeObject = new Commande();

               float idCommande = Float.parseFloat(obj.get("idcommande").toString());
               Double prixTotal = Double.parseDouble(obj.get("prixtotal").toString());
               float etat =Float.parseFloat(obj.get("etat").toString());
               float reference = Float.parseFloat(obj.get("reference").toString());
               
                //System.out.println(idCommande);
               CommandeObject.setIdCommande((int)idCommande);
                CommandeObject.setEtat((int)etat);
                CommandeObject.setPrixTotal(prixTotal);
                CommandeObject.setReference((int)reference);
          
               Map<String, Object>  listUser = (Map<String, Object>) obj.get("iduser");
                CommandeObject.setNom(listUser.get("nom").toString());
                     CommandeObject.setPrenom(listUser.get("prenom").toString());
           
                    Map<String, Object>  DateCommande = (Map<String, Object>) obj.get("datedecommande");   
                     double obdatem=Double.parseDouble(DateCommande.get("timestamp").toString());
                     System.out.println(obdatem);
                    long dm=(long)obdatem;
              DateFormat readformat=new SimpleDateFormat("dd MMM z yyyy");
   
   DateFormat writeFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat writeFormat2= new SimpleDateFormat("hh:mm");
    
   Date dmh=new Date(dm*1000L);
    String dateStr =dmh.toString();
            
   Date heurematch;
   Date horairemymatch;
   
                   /** System.out.println("ok ok \nok\nok");
                         System.out.println(dateStr);
                        heurematch = readformat.parse(dateStr);
                         System.out.println("ok ok \nok\nok");
                      String mymathdate=writeFormat.format(heurematch);
                      System.out.println("ok ok \nok\nok");
                                               
                      System.out.println(mymathdate);*/
               listCommandes.add(CommandeObject);
               System.out.println(CommandeObject);
              
          

            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(listCommandes);
        return listCommandes;

    }
    
      ArrayList<Commande> listCommandes2 = new ArrayList<>();
    
    public ArrayList<Commande> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
       // con.setUrl("http://41.226.11.243:10004/tasks/");  
       con.setUrl("http://"+Util.addip+"/PiDevWebMobile/web/vendeur1/commandes");  
       
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandesServices ser = new CommandesServices();
                try {
                    listCommandes2 = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                  
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCommandes2;
    } 
}
