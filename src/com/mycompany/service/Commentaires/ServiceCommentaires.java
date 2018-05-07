/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Commentaires;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.List;
import com.mycompany.entites.Commentaires.Commentaires;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Reclamations.Magasins;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ServiceCommentaires {
    public static Evenements eventSelected;
    public static Produits produitSelected;
    
    public void newCommentaire(Commentaires rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://"+Util.addip+"/pidevwebservice/web/app_dev.php/AjoutDeCommentaire";
        con.addArgument("commentaire", rcl.getContenuCommentaire());
        con.addArgument("id", String.valueOf(Util.connectedUser.getId()));
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void updateCommentaire(Commentaires rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://"+Util.addip+"/pidevwebservice/web/app_dev.php/ModificationDeCommentaire/"+rcl.getIdCommentaire();
        con.addArgument("comment", rcl.getContenuCommentaire());
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void deleteCommentaire(Commentaires rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://"+Util.addip+"/pidevwebservice/web/app_dev.php/SuppressionDeCommentaire/"+rcl.getIdCommentaire();
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
//    public ArrayList<Commentaires> getListCommentaires(String json) throws ParseException  {
//
//        ArrayList<Commentaires> listEtudiants = new ArrayList<>();
//
//        try {
////            System.out.println(json);
//            JSONParser j = new JSONParser();
//
//            Map<String, Object> comments = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            List<Map<String, Object>> list = (List<Map<String, Object>>) comments.get("root");
//
//            for (Map<String, Object> obj : list) {
//                Commentaires e = new Commentaires();
//
//                float id = Float.parseFloat(obj.get("idcommentaire").toString());
//                
//                Map<String, Object> dateEnvoi  = (Map<String, Object>) obj.get("dateajoutcommentaire");
//                float date = Float.parseFloat(dateEnvoi.get("timestamp").toString());
//                Date d = new Date((long)(date-3600 )*1000);
//                
//                e.setIdCommentaire((int) id);
//                e.setContenuCommentaire(obj.get("contenucommentaire").toString());
//                e.setDateAjoutCommentaire(d);
//                Map<String, Object> client  = (Map<String, Object>) obj.get("iduser");
//                
////                System.out.println(e);
//                listEtudiants.add(e);
//
//            }
    public ArrayList<Commentaires> getListCommentaires(String json) throws ParseException  {

        ArrayList<Commentaires> listcomments = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> comments = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> liist = (java.util.List<Map<String, Object>>) comments.get("root");

            for (Map<String, Object> obj : liist) {
                Commentaires e = new Commentaires();

                float id = Float.parseFloat(obj.get("idcommentaire").toString());
                
                Map<String, Object> dateEnvoi  = (Map<String, Object>) obj.get("dateajoutcommentaire");
                float date = Float.parseFloat(dateEnvoi.get("timestamp").toString());
                Date d = new Date((long)(date-3600 )*1000);
                
                e.setIdCommentaire((int) id);
                e.setContenuCommentaire(obj.get("contenucommentaire").toString());
                e.setDateAjoutCommentaire(d);
                Map<String, Object> client  = (Map<String, Object>) obj.get("iduser");
                User u = new User();
                u.setUsername(client.get("username").toString());
                u.setNom(client.get("nom").toString());
                u.setPrenom(client.get("prenom").toString());
                float iduser = Float.parseFloat(client.get("id").toString());
                u.setId((int) iduser);
                e.setIdUser(u);
//                System.out.println(e);
                listcomments.add(e);

            }

        } catch (IOException ex) {
        }
        return listcomments;

    }
    
    ArrayList<Commentaires> listRecl = new ArrayList<>();

    public ArrayList<Commentaires> getListeComments(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevwebservice/web/app_dev.php/AffichageDeCommentaire");        
        con.setPost(false);
//        con.addArgument('idevent',String.valueOf(ServiceCommentaires.eventSelected.getId()));
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceCommentaires ser = new ServiceCommentaires();
            try {
                listRecl = ser.getListCommentaires(new String(con.getResponseData()));
            } catch (ParseException ex) {
                System.out.println("Error while parsing !");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecl;
    }

}
