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
import com.mycompany.GUI.Commentaires.PageCommentaire;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class DetailsReclamationTraitee {
    Form details;
    ConsulterReclamationsTraiteesVendeur crtv = new ConsulterReclamationsTraiteesVendeur();
    Reclamations selRecl = ConsulterReclamationsTraiteesVendeur.selectedReclamation;

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

    public DetailsReclamationTraitee() {
        details = new Form("Réclamation n° "+selRecl.getIdReclamation(), new FlowLayout(Component.CENTER));
        Toolbar tb = details.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter réclamations", FontImage.MATERIAL_LIBRARY_BOOKS, (evt) -> {
            ConsulterReclamationsVendeur con = new ConsulterReclamationsVendeur();
            con.getConsulterReclamations().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter les statistiques", FontImage.MATERIAL_EQUALIZER, (evt) -> {
            StatsReclamation str = new StatsReclamation();
            str.createPieChartForm().show();
        });
        tb.addMaterialCommandToSideMenu("Commentaires", FontImage.MATERIAL_COMMENT, (evt) -> {
            PageCommentaire pagecomment = new PageCommentaire();
            pagecomment.getPagecomment().show();
        });
        tb.addCommandToOverflowMenu("Back", null, (evt3) -> {
            crtv.getConsulterReclamations().show();
        });



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
        
        Container objet5 = new Container(BoxLayout.y());
        objet5.getAllStyles().setMarginTop(15);
        objet5.addAll(createForFont("Contenu de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont2(selRecl.getContenuReclamation(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        tout.add(objet5);
        
        Container objet6 = new Container(new FlowLayout(Component.CENTER));
        String etat = selRecl.getSuiviReclamation();
        int bg; String text = "";
        switch (etat){
            case "Traitée": bg = 0x28A745; text = "Réclamation traitée"; break;
            case "Rejétée": bg = 0xFF0000; text = "Réclamation rejétée"; break;
            default: bg = 0xEEDEEE;
        }
        Label reponse = createForFont(text, Font.STYLE_BOLD, Font.SIZE_LARGE, 0xFFFFFF);
        objet6.getAllStyles().setMarginTop(30);
        objet6.getStyle().setBgColor(bg);
        objet6.getStyle().setBgTransparency(255);
        objet6.addAll(reponse);
        tout.add(objet6);
        reponse.addPointerPressedListener((evt) -> {
            if(Dialog.show("Confirmation", "Placer cette réclamation en attente ?", "Non", "OUI")) {
                System.out.println("Nooon");
            }
            else{
                System.out.println("OK");
                ServiceReclamations ser = new ServiceReclamations();
                selRecl.setSuiviReclamation("En attente");
                selRecl.setDateReponseReclamation(null);
                ser.updateReclamation(selRecl);
                ConsulterReclamationsTraiteesVendeur cv = new ConsulterReclamationsTraiteesVendeur();
                cv.getConsulterReclamations().show();
                
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
