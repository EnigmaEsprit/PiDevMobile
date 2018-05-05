
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Evenements;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Evenements.Evenements;
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
public class ServiceEvenements {
    public void subEvent(Users u , Evenements ev)
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/subsc?idevenement="+ev.getId()+"&iduser="+u.getId();
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
    public void udpateEvent(Evenements ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/edit/"+ev.getId()+"?nomevenement="+ev.getNomevenement()+"&nombredeplaces="+ev.getNombredeplaces()+"&nombredeplacesrestante="+ev.getNombredeplacerestante()+"&tarif="+ev.getTarifevenement()+"&descriptionevenement="+ev.getDescription()+"&lieu="+ev.getLieu()+"&date="+ev.getDateS()+"&datefin="+ev.getDatefS()+"&file="+ev.getImage()+"&iduser="+ev.getIdUser().getId() ;
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
    public void newEvent(Evenements ev) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(ev.getNomevenement());
        String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/new?nomevenement="+ev.getNomevenement()+"&nombredeplaces="+ev.getNombredeplaces()+"&tarif="+ev.getTarifevenement()+"&descriptionevenement="+ev.getDescription()+"&lieu="+ev.getLieu()+"&date="+ev.getDateS()+"&datefin="+ev.getDatefS()+"&file="+ev.getImage()+"&iduser="+ev.getIdUser().getId();
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
        public void deleteEvent(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/delete/"+id;
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
    public ArrayList<Evenements> getListTask(String json) throws ParseException {

        ArrayList<Evenements> listEvenement = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(evenements);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            for (Map<String, Object> obj : list) {
                Evenements e = new Evenements();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                String nomevnt = obj.get("nomevenement").toString();
                float nbp = Float.parseFloat(obj.get("nombredeplaces").toString());
                float nbpr = Float.parseFloat(obj.get("nombredesplacesrestante").toString());
                float tarif = Float.parseFloat(obj.get("tarifevenement").toString());
                String description = obj.get("descriptionevenement").toString();
                String lieu = obj.get("lieu").toString();
                String date = obj.get("date").toString();
                String datefin = obj.get("datefin").toString();
                String img = obj.get("imagelink").toString();
                float verifier = Float.parseFloat(obj.get("verifier").toString());
                System.out.println(img);
                System.out.println("DD/"+date);
                System.out.println("DF/"+datefin);
                 Map<String, Object> date2  = (Map<String, Object>) obj.get("date");
                 float da = Float.parseFloat(date2.get("timestamp").toString());
                 Date d = new Date((long)(da-3600 )*1000);
                  Map<String, Object> date3  = (Map<String, Object>) obj.get("datefin");
                 float da2 = Float.parseFloat(date3.get("timestamp").toString());
                 Date d2 = new Date((long)(da2-3600)*1000);
                 System.out.println(d);
               /* Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println(users);
           
                List<Map<String, Object>> listUsers = (List<Map<String, Object>>) users.get("iduser");
                 for (Map<String, Object> user : listUsers)
                 {
                     Users u = new Users();
                     
                     float idU = Float.parseFloat(user.get("id").toString());
                     e.setIdUser((int)idU);
                 }*/
               
              Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");
              Users u = new  Users();
                /*System.out.println(user.get("id"));
                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));
               
               
                System.out.println(id);
                System.out.println(nomevnt);
                System.out.println(nbp);
                System.out.println(nbpr); */
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
            
                e.setId((int) id);
                e.setNomevenement(nomevnt);
                e.setNombredeplaces((int)nbp);
                e.setNombredeplacerestante((int)nbpr);
                e.setDescription(description);
                e.setTarifevenement(tarif);
                e.setLieu(lieu);
                e.setDate(d);
                e.setIdUser(u);
                e.setDatefin(d2);
                e.setImage(img);
                e.setValider((int)verifier);
                
               
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // e.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());
                System.out.println(e);
                listEvenement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvenement);
        return listEvenement;

    }
        ArrayList<Evenements> listEvenementsv = new ArrayList<>();
        public ArrayList<Evenements> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServiceEvenements ser = new ServiceEvenements();
                    listEvenementsv = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenementsv;
    }
                ArrayList<Evenements> listEvenements = new ArrayList<>();
        public ArrayList<Evenements> getList3(){       
        ConnectionRequest con = new ConnectionRequest();
            System.out.println(Util.connectedUser);
            System.out.println("");
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/allV?iduser="+Util.connectedUser.getId());  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServiceEvenements ser = new ServiceEvenements();
                    listEvenementsv = ser.getListTask(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenementsv;
    }
}
