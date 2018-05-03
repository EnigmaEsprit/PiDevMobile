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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Utilisateurs.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceUsers {
       
    public ArrayList<Users> getListTask(String json) {

        ArrayList<Users> listUsers = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(evenements);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            for (Map<String, Object> obj : list) {
                Users e = new Users();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();
                String email = obj.get("email").toString();
                String password = obj.get("password").toString();
            
              

                
              
                
                String roles =obj.get("roles").toString();
                
if (roles.equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]")){System.out.println("aaaaaaaaaaaaaaa");}
                
                String username = obj.get("username").toString();

                
                
               /* Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println(users);
           
                List<Map<String, Object>> listUsers = (List<Map<String, Object>>) users.get("iduser");
                 for (Map<String, Object> user : listUsers)
                 {
                     Users u = new Users();
                     
                     float idU = Float.parseFloat(user.get("id").toString());
                     e.setIdUser((int)idU);
                 }*/
               
              /* Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");
                System.out.println(user.get("id"));
                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));
               
               
                System.out.println(id);
                System.out.println(nomevnt);
                System.out.println(nbp);
                System.out.println(nbpr); 
                
                
                Map<String, Object> time  = (Map<String, Object>) user.get("datedenaissance");
                Map<String, Object> time2  = (Map<String, Object>) time.get("timezone");
              //  Map<String, Object> time3  = (Map<String, Object>) time2.get("transitions");
              //  System.out.println(time3.get("time"));
                
                
                
                System.out.println(user.get("nom"));
                                System.out.println(user.get("email"));*/
               
               
                System.out.println(id);
                
                e.setId((int) id);
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setUsername(username);
                e.setEmail(email);
                e.setRoles(roles);
                e.setPassword(password);
               
                
               
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // e.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());
                System.out.println(e);
                listUsers.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listUsers);
        return listUsers;

    }
        ArrayList<Users> listUsers = new ArrayList<>();
        public ArrayList<Users> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidevmobile/web/app_dev.php/Users/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUsers ser = new ServiceUsers();
                listUsers = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }
    
}
