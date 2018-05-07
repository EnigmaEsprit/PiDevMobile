/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.GUI.utilisateurs.LoginForm;

import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Reclamations.ServiceReclamations;
import com.mycompany.service.Utilisateurs.Util;

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
        /*suiviReclamation.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
        HomePage h = new HomePage();
        h.getHome().show();
        });
        suiviReclamation.getToolbar().addCommandToOverflowMenu("Envoi réclamations", null, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        
        EnvoiReclamationClient src = new EnvoiReclamationClient();
        src.getEnvoiReclamation().show();
        
        }
        });*/
         ToolbarForm tbf = new ToolbarForm();
                 if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_USER]")){
            tbf.Menu(suiviReclamation); 
        }
             
                else{
             
            tbf.Menu0(suiviReclamation); 
        }
        suiviReclamation.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();;
                                }
                            });
         
       
       
        
    }

    public Form getSuiviReclamation() {
        return suiviReclamation;
    }

    public void setSuiviReclamation(Form suiviReclamation) {
        this.suiviReclamation = suiviReclamation;
    }

}
