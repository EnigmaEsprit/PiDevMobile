/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class DetailsReclamationClient {
    Form details;
    SuiviReclamationsClients g = new SuiviReclamationsClients();
    Reclamations selRecl = SuiviReclamationsClients.selRecl;

    private Label createForFont(String s, int style, int size, int color) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    private SpanLabel createForFont2(String s, int style, int size, int color) {
        SpanLabel l = new SpanLabel(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    public DetailsReclamationClient() {
        details = new Form("Réclamation n° "+selRecl.getIdReclamation(), new FlowLayout(Component.CENTER));
        Toolbar tb = details.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Envoi réclamations", FontImage.MATERIAL_SEND, (evt) -> {
            EnvoiReclamationClient con = new EnvoiReclamationClient();
            con.getEnvoiReclamation().show();
        });
        tb.addMaterialCommandToSideMenu("Suivi des réclamations", FontImage.MATERIAL_ALARM_ON, (evt) -> {
            SuiviReclamationsClients con = new SuiviReclamationsClients();
            con.getSuiviReclamation().show();
        });
        tb.addCommandToOverflowMenu("Back", null, (evt3) -> {
            g.getSuiviReclamation().show();
        });
        
        
//        details.add(createForFont("N°", Font.STYLE_PLAIN, Font.SIZE_MEDIUM,0xff7800));
//        details.add(createForFont(String.valueOf(selRecl.getIdReclamation()), Font.STYLE_BOLD, Font.SIZE_LARGE, 0xff7800));
        Container tout = new Container(BoxLayout.y());
        tout.getAllStyles().setMargin(0,3,2,0);
        
        Container objet = new Container(BoxLayout.y());
        objet.getAllStyles().setMarginTop(15);
        objet.addAll(createForFont("Objet de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(selRecl.getObjetReclamation(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet);
        
        Container objet2 = new Container(BoxLayout.y());
        objet2.getAllStyles().setMarginTop(15);
        objet2.addAll(createForFont("Nom du client :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(selRecl.getNomClient(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet2);
        
        Container objet3 = new Container(BoxLayout.y());
        objet3.getAllStyles().setMarginTop(15);
        objet3.addAll(createForFont("E-mail :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(selRecl.getEmailClient(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet3);
        
        SimpleDateFormat formater = new SimpleDateFormat("dd MMM yyyy HH:mm");
        
        Container objet4 = new Container(BoxLayout.y());
        objet4.getAllStyles().setMarginTop(15);
        objet4.addAll(createForFont("Date d'envoi :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(formater.format(selRecl.getDateEnvoiReclamation()), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet4);
        
        Container objet7 = new Container(BoxLayout.y());
        objet7.getAllStyles().setMarginTop(15);
        objet7.addAll(createForFont("Nom du magasin :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(selRecl.getNomMagasin(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet7);

        Container objet8 = new Container(BoxLayout.y());
        objet8.getAllStyles().setMarginTop(15);
        objet8.addAll(createForFont("Nom du Vendeur :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont(selRecl.getNomVendeur(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet8);

        Container objet5 = new Container(BoxLayout.y());
        objet5.getAllStyles().setMarginTop(15);
        objet5.addAll(createForFont("Contenu de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont2(selRecl.getContenuReclamation(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet5);
        
        Container objet6 = new Container(new FlowLayout(Component.CENTER));
        String etat = selRecl.getSuiviReclamation();
        int bg; String text;
        switch (etat){
            case "Traitée": bg = 0x28A745; text = "Réclamation traitée"; break;
            case "Rejétée": bg = 0xFF0000; text = "Réclamation rejétée"; break;
            default: bg = 0xff7800; text = "Réclamation en attente"; break;
        }
        Label reponse = createForFont(text, Font.STYLE_BOLD, Font.SIZE_LARGE, 0xFFFFFF);
        objet6.getAllStyles().setMarginTop(30);
        objet6.getStyle().setBgColor(bg);
        objet6.getStyle().setBgTransparency(255);
        objet6.addAll(reponse);
        tout.add(objet6);
        reponse.addPointerPressedListener((evt) -> {
            String texte = reponse.getText();
            if(!texte.equals("Réclamation en attente")) {
                if(Dialog.show("Confirmation", "Supprimer cette réclamation ?", "Non", "OUI")) {
                    System.out.println("Nooon");
                }
                else{
                    System.out.println("OK");
                    ServiceReclamations ser = new ServiceReclamations();
                    selRecl.setVisibiliteReclamation("Non");
                    ser.updateReclamation(selRecl);
                    if(Dialog.show("Suppression", "Réclamation Supprimée.", "Ok", null)) {
                        SuiviReclamationsClients sui = new SuiviReclamationsClients();
                        sui.getSuiviReclamation().show();
                    }

                } 
            }
        });        
        
        details.add(tout);    
    
    }

    public Form getDetails() {
        return details;
    }

    public void setDetails(Form details) {
        this.details = details;
    }
    
}
