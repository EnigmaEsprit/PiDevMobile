/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Decouverte;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.entites.Decouverte.ContactDecouverte;
  
import com.mycompany.entites.Utilisateurs.User;

import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceContact {
     public ArrayList<ContactDecouverte> getListConract(String json) throws ParseException {

        ArrayList<ContactDecouverte> listContacts = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> contacts = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if(contacts.values().isEmpty())
            {
                Dialog.show("Opps!", "Aucunne correspond", "Fermer", null);
                Contact c = new Contact();
                c.Contact();
            }
            else
            {
            System.out.println(contacts);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) contacts.get("root");

            for (Map<String, Object> obj : list) {
                ContactDecouverte e = new ContactDecouverte();

                // System.out.println(obj.get("id"));
                String nommag = obj.get("nommagasin").toString();
                String imagemag = obj.get("photomagasin").toString();
              Map<String, Object> user  = (Map<String, Object>) obj.get("iduser");
              User u = new  User();
           
                String nom = user.get("nom").toString();
                String prenom = user.get("prenom").toString();
                String email = user.get("email").toString();
                float zip = Float.parseFloat(user.get("zip").toString());
                String numTEL = user.get("numerodutelephone").toString();
                String adress = user.get("adresse").toString();
                String ville = user.get("ville").toString();

            
             
                e.setNom_magazin(nommag);
                e.setZip((int)zip);
                e.setImagemag(imagemag);
                e.setEmail(email);
                e.setAdresse(adress);
                e.setNt(numTEL);
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setVille(ville);
                System.out.println(e);
                listContacts.add(e);

            }
            }
        } catch (IOException ex) {
        }
        System.out.println(listContacts);
        return listContacts;
        
    }
     ArrayList<ContactDecouverte> listContact = new ArrayList<>();
        public ArrayList<ContactDecouverte> getListContact( String name){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+Util.addip+"/pidevmobile/web/app_dev.php/contact/"+name+"/search");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                     ServiceContact ser = new ServiceContact();
                    listContact = ser.getListConract(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listContact;
    }
    
}
