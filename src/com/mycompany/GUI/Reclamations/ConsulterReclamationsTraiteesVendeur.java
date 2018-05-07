/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.GUI.Commentaires.PageCommentaire;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ConsulterReclamationsTraiteesVendeur {
    Form consulterReclamations;
    static Reclamations selectedReclamation;
    SpanLabel lb;
    
    private Label createForFont(String s, int style, int size, int color) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    private Slider separateur() {
        Slider jSlider = new Slider();
        jSlider.setEditable(false);
        return jSlider;
    }

    public ConsulterReclamationsTraiteesVendeur() {
        consulterReclamations = new Form("Consulter les réclamations");
        Toolbar tb = consulterReclamations.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter réclamations", FontImage.MATERIAL_LIBRARY_BOOKS, (evt) -> {
            consulterReclamations.show();
        });
        tb.addMaterialCommandToSideMenu("Consulter les statistiques", FontImage.MATERIAL_EQUALIZER, (evt) -> {
            StatsReclamation str = new StatsReclamation();
            str.createPieChartForm().show();
        });
        tb.addMaterialCommandToSideMenu("Commentaires", FontImage.MATERIAL_COMMENT, (evt) -> {
            PageCommentaire pagecomment = new PageCommentaire();
            pagecomment.getPagecomment().show();
        });
        consulterReclamations.getToolbar().addCommandToOverflowMenu("Back", null, (evt3) -> {
            ConsulterReclamationsVendeur crv = new ConsulterReclamationsVendeur();
            crv.getConsulterReclamations().show();
        });
    
    
        ServiceReclamations src = new ServiceReclamations();
        Container cp = new Container(BoxLayout.y());
            if(src.getListeFinale3().isEmpty())
                cp.add(createForFont("Aucune réclamation traitée.", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0));
        for(Reclamations r : src.getListeFinale3()){
            Container conteneurReclamation = new Container(BoxLayout.x());
            Container conteneurIdReclamation = new Container(BoxLayout.y());
            Container conteneurElementsReclamation = new Container(BoxLayout.y());
            conteneurIdReclamation.getAllStyles().setMarginRight(25);
            Container status = new Container(BoxLayout.x());
            Container objet = new Container(BoxLayout.x());
            SimpleDateFormat formater = new SimpleDateFormat("dd MMM yyyy HH:mm");
            conteneurIdReclamation.add(createForFont("N°", Font.STYLE_PLAIN, Font.SIZE_MEDIUM,0xff7800));
            conteneurIdReclamation.add(createForFont(String.valueOf(r.getIdReclamation()), Font.STYLE_BOLD, Font.SIZE_LARGE, 0xff7800));
            objet.addAll(createForFont("Objet de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_SMALL, 0),createForFont(r.getObjetReclamation(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
            conteneurElementsReclamation.add(objet);
            conteneurElementsReclamation.add(createForFont("Nom du client :  "+r.getNomClient(), Font.STYLE_PLAIN, Font.SIZE_SMALL, 0));
            conteneurElementsReclamation.add(createForFont("Date d'envoi :  "+formater.format(r.getDateEnvoiReclamation()), Font.STYLE_PLAIN, Font.SIZE_SMALL, 0));
            Label o = createForFont("Voir plus", Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0);
            o.getAllStyles().setUnderline(true);
            Container mode = new Container();
            int bg;
            String etat = r.getSuiviReclamation();
            switch (etat)
            {
              case "Traitée":
                bg = 0x28A745;
                break;
              case "Rejétée":
                bg = 0xFF0000;
                break;
              default:
                bg = 0xEEDEEE;
            }
            mode.getStyle().setBgColor(bg);
            mode.getStyle().setMarginLeft(10);
            mode.getStyle().setBgTransparency(255);
            mode.add(createForFont(etat, Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0xffffff));
            status.addAll(o,mode);
            o.addPointerPressedListener((evt) -> {
                selectedReclamation = r;
                DetailsReclamationTraitee d = new DetailsReclamationTraitee();
                d.getDetails().show();
            });
            status.getAllStyles().setPaddingTop(2);
            conteneurElementsReclamation.add(status);
            conteneurReclamation.getAllStyles().setPaddingBottom(20);
            conteneurReclamation.add(conteneurIdReclamation);
            conteneurReclamation.add(conteneurElementsReclamation);
            cp.add(conteneurReclamation);
            cp.add(separateur());
        }
        consulterReclamations.add(cp);
        
    }

    public Form getConsulterReclamations() {
        return consulterReclamations;
    }

    public void setConsulterReclamations(Form consulterReclamations) {
        this.consulterReclamations = consulterReclamations;
    }

}
