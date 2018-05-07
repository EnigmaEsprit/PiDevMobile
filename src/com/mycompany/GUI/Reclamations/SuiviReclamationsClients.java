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
import com.codename1.ui.Slider;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.mycompany.GUI.Commentaires.PageCommentaire;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class SuiviReclamationsClients {
    private Form suiviReclamation;
    EnvoiReclamationClient erc = new EnvoiReclamationClient();
    SpanLabel lb;
    public static Reclamations selRecl;
    
    private Label createForFont(String s, int style, int size, int color) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    private Slider separateur() {
        Slider jSlider = new Slider();
        jSlider.setMaxValue(200);
        jSlider.setMinValue(0); 
        jSlider.setEditable(false);
        return jSlider;
    }

    public SuiviReclamationsClients() {
        suiviReclamation = new Form("Suivi réclamations", BoxLayout.y());
        ServiceReclamations src = new ServiceReclamations();
        Container cp = new Container(BoxLayout.y());
        for(Reclamations r : src.getListeFinale()){
            Container conteneurReclamation = new Container(BoxLayout.x());
            Container conteneurIdReclamation = new Container(BoxLayout.y());
            Container conteneurElementsReclamation = new Container(BoxLayout.y());
            conteneurIdReclamation.getAllStyles().setMarginRight(30);
            Container status = new Container();
            SimpleDateFormat formater = new SimpleDateFormat("dd MMM yyyy HH:mm");
            conteneurIdReclamation.add(createForFont("N°", Font.STYLE_PLAIN, Font.SIZE_MEDIUM,0xff7800));
            conteneurIdReclamation.add(createForFont(String.valueOf(r.getIdReclamation()), Font.STYLE_BOLD, Font.SIZE_LARGE, 0xff7800));
            conteneurElementsReclamation.add(createForFont("Objet de la réclamation :  "+r.getObjetReclamation(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
            conteneurElementsReclamation.add(createForFont("Date d'envoi :  "+formater.format(r.getDateEnvoiReclamation()), Font.STYLE_PLAIN, Font.SIZE_SMALL, 0));
            int bg;
            String etat = r.getSuiviReclamation();
            switch (etat)
            {
              case "Traitée":
                bg = 0x28A745;
                etat = "   " + etat + "   ";
                break;
              case "Rejétée":
                bg = 0xFF0000;
                etat = "  " + etat + "  ";
                break;
              case "En attente":
                bg = 0xff7800;
                break;
              default:
                bg = 0xEEDEEE;
            }
            status.getStyle().setBgColor(bg);
            status.getStyle().setBgTransparency(255);
            status.getStyle().setMargin(0,0,4,4);
            Label u = createForFont(etat, Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0xffffff);
            u.addPointerPressedListener((evt) -> {
                String texte = u.getText();
                if(!texte.equals("En attente")) {
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

            status.add(u);
            Container st = new Container(BoxLayout.x());
            Label o = createForFont("Voir plus", Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0);
            o.getAllStyles().setUnderline(true);
            st.addAll(status, o);
            o.addPointerPressedListener((evt) -> {
                selRecl = r;
                DetailsReclamationClient drc = new DetailsReclamationClient();
                drc.getDetails().show();
            });
            conteneurElementsReclamation.add(st);

            conteneurReclamation.getAllStyles().setPaddingBottom(20);
            conteneurReclamation.add(conteneurIdReclamation);
            conteneurReclamation.add(conteneurElementsReclamation);
            cp.add(conteneurReclamation);
            cp.add(separateur());
        }
        suiviReclamation.add(cp);
        

        suiviReclamation.getToolbar().addCommandToOverflowMenu("Back", null, (evt3) -> {
            erc.getEnvoiReclamation().show();
        });
        suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Envoi réclamations", FontImage.MATERIAL_SEND, (evt) -> {
            erc.getEnvoiReclamation().show();
        });
        suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Suivi des réclamations", FontImage.MATERIAL_ALARM_ON, (evt2) -> {
            suiviReclamation.show();
        });
        suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Commentaires", FontImage.MATERIAL_COMMENT, (evt) -> {
            PageCommentaire pagecomment = new PageCommentaire();
            pagecomment.getPagecomment().show();
        });
        
    }

    public Form getSuiviReclamation() {
        return suiviReclamation;
    }

    public void setSuiviReclamation(Form suiviReclamation) {
        this.suiviReclamation = suiviReclamation;
    }

}
