/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Reclamations;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.mycompany.entites.Reclamations.Magasins;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.entites.Reclamations.Statisques;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ServiceReclamations {
    
    /* *******************************  CLIENTS  ****************************************** */
    
    public void newReclamation(Reclamations rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://localhost/PiDevWeb/web/app_dev.php/envoieReclamations";
        con.addArgument("typereclamation", rcl.getTypeReclamation());
        con.addArgument("objetreclamation", rcl.getObjetReclamation());
        con.addArgument("recl", rcl.getContenuReclamation());
        con.addArgument("idmag", String.valueOf(rcl.getIdMagasin()));
        con.addArgument("suivi", rcl.getSuiviReclamation());
        con.addArgument("id", String.valueOf(rcl.getIdUser().getId()));
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Reclamations> getListReclamationsClients(String json) throws ParseException  {

        ArrayList<Reclamations> listEtudiants = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> reclamationsClients = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(reclamationsClients);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsClients.get("root");

            for (Map<String, Object> obj : list) {
                Reclamations e = new Reclamations();

                float id = Float.parseFloat(obj.get("idreclamation").toString());
                Map<String, Object> dateEnvoi  = (Map<String, Object>) obj.get("dateenvoireclamation");
                float date = Float.parseFloat(dateEnvoi.get("timestamp").toString());
                Date d = new Date((long)(date-3600 )*1000);
                Map<String, Object> client  = (Map<String, Object>) obj.get("iduser");
                Map<String, Object> mag  = null;
                Map<String, Object> vendeur = null;
                try{
                    mag  = (Map<String, Object>) obj.get("idmagasin");
                    vendeur  = (Map<String, Object>) mag.get("iduser");
                }catch(NullPointerException ex){
                    System.out.println(ex);
                }
                e.setIdReclamation((int) id);
                e.setObjetReclamation(obj.get("objetreclamation").toString());
                e.setNomClient(client.get("prenom").toString()+" "+client.get("nom").toString());
                e.setEmailClient(client.get("email").toString());
                e.setDateEnvoiReclamation(d);
                e.setSuiviReclamation(obj.get("suivireclamation").toString());
                e.setContenuReclamation(obj.get("contenureclamation").toString());
                try{
                    e.setNomMagasin(mag.get("nommagasin").toString());
                    e.setNomVendeur(vendeur.get("nom").toString()+" "+vendeur.get("prenom").toString());
                }catch(NullPointerException ex){
                }
                finally{
                    e.setNomMagasin("Système");
                    e.setNomVendeur("Système");
               }
//                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
//        System.out.println(listEtudiants);
        return listEtudiants;

    }
    
    public ArrayList<Magasins> getListMagasins(String json) throws ParseException  {

        ArrayList<Magasins> listMags = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> magazins = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> liist = (List<Map<String, Object>>) magazins.get("root");

            for (Map<String, Object> obj : liist) {
                Magasins m = new Magasins();
                float id = Float.parseFloat(obj.get("idmagasin").toString());
                m.setIdMagasin((int) id);
                m.setNomMagasin(obj.get("nommagasin").toString());
                listMags.add(m);
            }

        } catch (IOException ex) {
        }
        return listMags;

    }
    
    ArrayList<Reclamations> listTasks = new ArrayList<>();
    
    public ArrayList<Reclamations> getListeFinale(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/consulterReclamationsClients");
        con.setPost(false);
        con.addArgument("id", String.valueOf(ServiceUsers.connectedUser.getId()));
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            try {
                listTasks = ser.getListReclamationsClients(new String(con.getResponseData()));
            } catch (ParseException ex) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    ArrayList<Magasins> listMags = new ArrayList<>();
    
    public ArrayList<Magasins> getListeMagasin(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/listeMagasins");        
        con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            try {
                listMags = ser.getListMagasins(new String(con.getResponseData()));
            } catch (ParseException ex) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listMags;
    }

    
    /* **********************************  VENDEUR  ****************************************** */
    

    public void updateReclamation(Reclamations rcl) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        System.out.println(rcl.toString());
        String url = "http://localhost/PiDevWeb/web/app_dev.php/editReclamation/"+rcl.getIdReclamation();
        con.addArgument("date", String.valueOf(rcl.getDateReponseReclamation()));
        con.addArgument("suivi", rcl.getSuiviReclamation());
        con.addArgument("visible", rcl.getVisibiliteReclamation());
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
   public ArrayList<Reclamations> getListReclamationsVendeur(String json) throws ParseException  {

        ArrayList<Reclamations> listEtudiants = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> reclamationsClients = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(reclamationsClients);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsClients.get("root");

            for (Map<String, Object> obj : list) {
                Reclamations e = new Reclamations();

                float id = Float.parseFloat(obj.get("idreclamation").toString());
                
                Map<String, Object> dateEnvoi  = (Map<String, Object>) obj.get("dateenvoireclamation");
                float date = Float.parseFloat(dateEnvoi.get("timestamp").toString());
                Date d = new Date((long)(date-3600 )*1000);
                
                e.setIdReclamation((int) id);
                e.setObjetReclamation(obj.get("objetreclamation").toString());
                e.setDateEnvoiReclamation(d);
                Map<String, Object> client  = (Map<String, Object>) obj.get("iduser");
                e.setNomClient(client.get("prenom").toString()+" "+client.get("nom").toString());
                e.setEmailClient(client.get("email").toString());
                e.setContenuReclamation(obj.get("contenureclamation").toString());
                e.setSuiviReclamation(obj.get("suivireclamation").toString());
                
//                System.out.println(e);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
//        System.out.println(listEtudiants);
        return listEtudiants;

    }

    public ArrayList<Statisques> getStatisques(String json) throws ParseException  {

        ArrayList<Statisques> listStats = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> magazins = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> liist = (List<Map<String, Object>>) magazins.get("root");

            for (Map<String, Object> obj : liist) {
                Statisques m = new Statisques();
                m.setEtat(obj.get("etat_reclamation").toString());
                float id = Float.parseFloat(obj.get("nombre").toString());
                m.setNombre((double) id);
                listStats.add(m);
            }

        } catch (IOException ex) {
        }
        return listStats;

    }
    
    ArrayList<Reclamations> listRecl = new ArrayList<>();

    public ArrayList<Reclamations> getListeFinale2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/consulterReclamationsVendeur");        
        con.setPost(false);
        con.addArgument("id", String.valueOf(ServiceUsers.connectedUser.getId()));
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            try {
                listRecl = ser.getListReclamationsVendeur(new String(con.getResponseData()));
            } catch (ParseException ex) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecl;
    }

    public ArrayList<Reclamations> getListeFinale3(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/consulterReclamationsVendeur2");        
        con.setPost(false);
        con.addArgument("id", String.valueOf(ServiceUsers.connectedUser.getId()));
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            try {
                listRecl = ser.getListReclamationsVendeur(new String(con.getResponseData()));
            } catch (ParseException ex) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecl;
    }

    ArrayList<Statisques> listStatis = new ArrayList<>();
    public ArrayList<Statisques> showStats(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/consulterReclamationsStats");        
        con.setPost(false);
        con.addArgument("id", String.valueOf(ServiceUsers.connectedUser.getId()));
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            try {
                listStatis = ser.getStatisques(new String(con.getResponseData()));
            } catch (ParseException ex) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStatis;
    }
}
