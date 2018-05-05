/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Participations;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Participations.Participations;
import com.mycompany.entites.Utilisateurs.Users;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceParticipations {
       public ArrayList<Participations> getListTask(String json) {

        ArrayList<Participations> listParticipations = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Participatios = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Participatios);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Participatios.get("root");

            for (Map<String, Object> obj : list) {
                Participations p = new Participations();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idparticipation").toString());
             Map<String, Object> event  = (Map<String, Object>) obj.get("idevenement");

             Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");

                float idEvent = Float.parseFloat(event.get("id").toString());
                float idUser = Float.parseFloat(user.get("id").toString());
               
              

                
              
                
                
                System.out.println(id);
                
                p.setId((int) id);
               p.setIdevenement((int)idEvent);
               p.setIduser((int)idUser);
              
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // p.setEtat(obj.get("state").toString());
                //e.setNom(obj.get("name").toString());
                System.out.println(p);
                listParticipations.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listParticipations);
        return listParticipations;

    }
        ArrayList<Participations> listParticipations = new ArrayList<>();
        public ArrayList<Participations> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/Evenements/subscAll");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceParticipations ser = new ServiceParticipations();
                listParticipations = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listParticipations;
    }
    
    
}
