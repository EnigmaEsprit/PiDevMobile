/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Commentaires;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entites.Commentaires.Commentaires;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ServiceCommentaires {
    public void newCommentaire(Commentaires rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://localhost/PiDevWeb/web/app_dev.php/AjoutDeCommentaire";
        con.addArgument("commentaire", rcl.getContenuCommentaire());
        con.addArgument("id", String.valueOf(rcl.getIdUser().getId()));
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
}
