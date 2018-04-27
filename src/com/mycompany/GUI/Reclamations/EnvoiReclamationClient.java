/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class EnvoiReclamationClient {
    Form envoiReclamation = new Form("Envoyer une réclamation", new FlowLayout(Component.CENTER));
    TextField objReclamation;
    TextArea txReclamation;
    ComboBox<String> typReclamation;
    ComboBox<String> mags;
    Label title = new Label("Souk El Medina");    
    Label space = new Label("   ");    
    Button btnEnvoi;

    public EnvoiReclamationClient() {
        Toolbar tb = envoiReclamation.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            
        });
        tb.addMaterialCommandToSideMenu("Suivi des réclamations", FontImage.MATERIAL_ALARM_ON, (evt) -> {
            SuiviReclamationsClients sui = new SuiviReclamationsClients();
            sui.getSuiviReclamation().show();
        });
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        envoiReclamation.add(title);
        envoiReclamation.add(space);
        
        typReclamation = new ComboBox<>();
        typReclamation.addItem("-- Sélectionner le type de réclamation --");
        typReclamation.addItem("Réclamation liée à une vente");
        typReclamation.addItem("Réclamation liée au système"); 
        c1.add(typReclamation);
        
        objReclamation = new TextField("", "Entrer l'objet de la réclamation");
        c1.add(objReclamation);
        
        mags = new ComboBox<>();
        mags.addItem("-- Sélectionner le magasin --");
        mags.addItem("XYZ");
        mags.addItem("ABC"); 
        c1.add(mags);
        
        txReclamation = new TextArea();
        txReclamation.setHint("Entrez le contenu de la réclamation");
        txReclamation.setVisible(true);
        txReclamation.setRows(5);
        txReclamation.getAllStyles().setFgColor(0x000000);
        c1.add(txReclamation);
        
        btnEnvoi = new Button("Envoyer votre réclamation");
        c1.add(btnEnvoi);
        
        envoiReclamation.add(c1);
        btnEnvoi.addActionListener((evt) -> {
            ServiceReclamations ser = new ServiceReclamations();
            Reclamations r = new Reclamations();
            r.setTypeReclamation(typReclamation.getSelectedItem());
//            r.setIdMagasin(56);
            r.setContenuReclamation(txReclamation.getText());
            r.setObjetReclamation(objReclamation.getText());
            ser.newReclamation(r);
            System.out.println(r.toString());
        });
        

    }

    public Form getEnvoiReclamation() {
        return envoiReclamation;
    }

    public void setEnvoiReclamation(Form envoiReclamation) {
        this.envoiReclamation = envoiReclamation;
    }
    
}
