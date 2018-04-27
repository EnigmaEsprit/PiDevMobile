/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Reclamations;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entites.Reclamations.Reclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ServiceReclamations {
    public void newReclamation(Reclamations rcl) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(rcl.toString());
        String url = "http://localhost/PiDevMobile/web/app_dev.php/envoiReclamations?typereclamation="+rcl.getTypeReclamation()+"&objetreclamation="+rcl.getObjetReclamation()+"&recl="+rcl.getContenuReclamation()+"&suivi="+rcl.getSuiviReclamation();
        System.out.println(url);
        con.setUrl(url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
