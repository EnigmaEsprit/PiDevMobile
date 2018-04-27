/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author Ivan Landry ONANA
 */
public class SuiviReclamationsClients {
    Form suiviReclamation = new Form("Suivi des réclamations", new FlowLayout(Component.CENTER));
    EnvoiReclamationClient erc = new EnvoiReclamationClient();

    public SuiviReclamationsClients() {
        suiviReclamation.getToolbar().addCommandToOverflowMenu("Back", null, (evt3) -> {
            erc.getEnvoiReclamation().show();
        });
        suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            erc.getEnvoiReclamation().show();            
        });
    }

    public Form getSuiviReclamation() {
        return suiviReclamation;
    }

    public void setSuiviReclamation(Form suiviReclamation) {
        this.suiviReclamation = suiviReclamation;
    }
    
}
