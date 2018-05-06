/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Utilisateurs;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.mycompany.entites.Utilisateurs.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ServiceUsers {
    public static Users connectedUser;

    public ArrayList<Users> getListUsers(String json) throws ParseException  {

        ArrayList<Users> listEtudiants = new ArrayList<>();

        try {
//            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> utilisateurs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) utilisateurs.get("root");

            for (Map<String, Object> obj : list) {
                    Users e = new Users();

                    float id = Float.parseFloat(obj.get("id").toString());
                    String nom = obj.get("nom").toString();
                    String prenom = obj.get("prenom").toString();
                    String email = obj.get("email").toString();
                    String password = obj.get("password").toString();
                    String roles = obj.get("roles").toString();                
                    String username = obj.get("username").toString();
                    e.setId((int) id);
                    e.setNom(nom);
                    e.setPrenom(prenom);
                    e.setUsername(username);
                    e.setEmail(email);
                    e.setRoles(roles);
                    e.setPassword(password);
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
//        System.out.println(listEtudiants);
        return listEtudiants;

    }
    
        ArrayList<Users> listUsers = new ArrayList<>();
        public ArrayList<Users> getListeUtilisateurs(){       
            ConnectionRequest con = new ConnectionRequest();
            con.setPost(false);
            con.setUrl("http://localhost/PiDevWeb/web/app_dev.php/Users/all");  
            con.addResponseListener((NetworkEvent evt) -> {
                try {
                    ServiceUsers ser = new ServiceUsers();
                    listUsers = ser.getListUsers(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
            return listUsers;
        }
}
