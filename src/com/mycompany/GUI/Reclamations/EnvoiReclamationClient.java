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
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.GUI.Commentaires.PageCommentaire;
import com.mycompany.entites.Reclamations.Magasins;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;
import com.mycompany.service.Utilisateurs.ServiceUsers;

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
    Button btnEnvoi;

    private Label createForFont(Font font, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(font);
        l.getAllStyles().setFgColor(0xff7800);
        return l;
    }

    public EnvoiReclamationClient() {
        ServiceReclamations ser = new ServiceReclamations();
        Toolbar tb = envoiReclamation.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Envoi réclamations", FontImage.MATERIAL_SEND, (evt) -> {
            envoiReclamation.show();
        });
        tb.addMaterialCommandToSideMenu("Suivi des réclamations", FontImage.MATERIAL_ALARM_ON, (evt2) -> {
            SuiviReclamationsClients sui = new SuiviReclamationsClients();
            sui.getSuiviReclamation().show();
        });
        tb.addMaterialCommandToSideMenu("Commentaires", FontImage.MATERIAL_COMMENT, (evt) -> {
            PageCommentaire pagecomment = new PageCommentaire();
            pagecomment.getPagecomment().show();
        });
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Reclamations r = new Reclamations();
        c1.getAllStyles().setMargin(3, 0, 5, 5);
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        envoiReclamation.add(createForFont(smallPlainSystemFont," SOUK EL MEDINA "));

        typReclamation = new ComboBox<>();
        typReclamation.getAllStyles().setMarginTop(5);
        typReclamation.getStyle().setBgColor(0xF0F0F0);
        typReclamation.getStyle().setBgTransparency(255);
        typReclamation.addItem("-- Sélectionner le type de réclamation --");
        typReclamation.addItem("Réclamation liée à une vente");
        typReclamation.addItem("Réclamation liée au système"); 
        c1.add(typReclamation);
        
        objReclamation = new TextField("", "Entrer l'objet de la réclamation");
        objReclamation.getStyle().setMarginTop(3);
        c1.add(objReclamation);
        
        mags = new ComboBox<>();
        mags.getStyle().setMarginTop(3);
        mags.getStyle().setBgColor(0xF0F0F0);
        mags.getStyle().setBgTransparency(255);
        String selection = "-- Sélectionner le magasin --";
        mags.addItem(selection);
        for(Magasins m : ser.getListeMagasin()) {
            mags.addItem(m.getNomMagasin());
        }
        c1.add(mags);
        typReclamation.addActionListener((evt) -> {
            if(typReclamation.getSelectedItem().equals("Réclamation liée au système")) {
                mags.setEnabled(false);
                mags.getStyle().setFgColor(0xA4A4A4);
                r.setIdMagasin(0);
            }
            else{
                mags.setEnabled(true);
                mags.getStyle().setFgColor(0);
//                mags.getStyle().setBgTransparency(255);
            }
        });
        System.out.println(mags.getSelectedIndex());
        txReclamation = new TextArea();
        txReclamation.getStyle().setMarginTop(3);
        txReclamation.setHint("Entrez le contenu de la réclamation");
        txReclamation.setVisible(true);
        txReclamation.setRows(5);
        txReclamation.getStyle().setBgColor(0xF0F0F0);
        txReclamation.getStyle().setBgTransparency(255);
        txReclamation.getAllStyles().setFgColor(0x000000);
        c1.add(txReclamation);
        
        btnEnvoi = new Button("Envoyer votre réclamation");
        btnEnvoi.getStyle().setMargin(3,3,0,0);
        c1.add(btnEnvoi);
        
        envoiReclamation.add(c1);
        btnEnvoi.addActionListener((evt3) -> {
            if(typReclamation.getSelectedItem().equals("-- Sélectionner le type de réclamation --"))
                Dialog.show("Vérification", "Veuillez choisir le type de réclamation", "Ok", null);
            else if(typReclamation.getSelectedItem().equals("-- Sélectionner le type de réclamation --")
                    && mags.getSelectedItem().equals("-- Sélectionner le magasin --"))
                Dialog.show("Vérification", "Veuillez choisir le magasin à adresser votre réclamation", "Ok", null);
            else if(objReclamation.getText().isEmpty())
                Dialog.show("Vérification", "Veuillez renseigner l'objet de votre réclamation", "Ok", null);
            else if(txReclamation.getText().isEmpty())
                Dialog.show("Vérification", "Veuillez renseigner le contenu de votre réclamation", "Ok", null);
            else{
                int idmag = 0;
                for(Magasins m : ser.getListeMagasin()) {
                    if(mags.getSelectedItem().equals(m.getNomMagasin())) 
                        idmag = m.getIdMagasin();
                }
                r.setTypeReclamation(typReclamation.getSelectedItem());
                r.setIdMagasin(idmag);
                r.setContenuReclamation(txReclamation.getText());
                r.setObjetReclamation(objReclamation.getText());
                r.setIdUser(ServiceUsers.connectedUser);
                ser.newReclamation(r);
                System.out.println(r.toString());
                if(Dialog.show("Envoi terminé", "Votre réclamation a été envoyé", "Ok", null)){
                    SuiviReclamationsClients sui = new SuiviReclamationsClients();
                    sui.getSuiviReclamation().show();
                }    
            }
        });       

    }

    public Form getEnvoiReclamation() {
        return envoiReclamation;
    }

    public void setEnvoiReclamation(Form envoiReclamation) {
        this.envoiReclamation = envoiReclamation;
    }
    
}
