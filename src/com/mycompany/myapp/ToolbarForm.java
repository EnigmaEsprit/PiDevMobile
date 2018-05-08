/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Utilities.ToolsUtilities;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.GUI.Panier.HomePanierForm;
import com.mycompany.GUI.Panier.VendeurCommandesForm;
import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.GUI.Promotions.Client_List_Promotions;
import com.mycompany.GUI.Promotions.Vendeur_List_Promotions;
import com.mycompany.GUI.Reclamations.ConsulterReclamationsTraiteesVendeur;
import com.mycompany.GUI.Reclamations.SuiviReclamationsClients;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.GUI.utilisateurs.ProfileForm;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;



/**
 *
 * @author user
 */
public class ToolbarForm {
    
    Resources theme = UIManager.initFirstTheme("/theme3");
           public void Menu(Form f){
               FonctionPanier.compterArticles();
           
        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("fbuser.jpg");
        Container topBar = BorderLayout.east(new Label(icon.fill(70, 70)));
        topBar.add(BorderLayout.SOUTH, new Label("Souk El Medina.....", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        Storage.getInstance().deleteStorageFile("imgprofile");
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("fbuser.jpg"), true);

        Image src = null;
        try {
            String imgLink = User.getActifUser().getPhotoProfil();

            if (imgLink.indexOf("http") > -1) {
                src = URLImage.createToStorage(placeholder, "imgprofile", User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            } else {
                src = URLImage.createToStorage(placeholder, "imgprofile", ToolsUtilities.ServerIp + User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            }
        } catch (NullPointerException ne) {
            src = URLImage.createToStorage(placeholder, "imgprofile", "", URLImage.RESIZE_SCALE);
            System.out.println("Photo null");
        }
        Image userPicture = src;

        final Command profileCommand = new Command("My Profile", userPicture) {
            public void actionPerformed(ActionEvent evt) {
                // updateLoginPhoto();

                ProfileForm profileForm = new ProfileForm();
                profileForm.getProfile().show();

             
            }
        };

      
       

      
    

        
       tb.addMaterialCommandToSideMenu("My Profile", FontImage.MATERIAL_HOME, e -> {
             ProfileForm profileForm = new ProfileForm();
                profileForm.getProfile().show();
        });
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeProduitsForm hp = new HomeProduitsForm();
            hp.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Cart= "+FonctionPanier.compterArticles(), FontImage.MATERIAL_SHOPPING_CART, e -> {
            HomePanierForm hpanier = new HomePanierForm();
            hpanier.getPsdTutorial().show();
        });
            tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Client_Liste_Events h = new Client_Liste_Events();
             h.getF().show();
        }});
               tb.addMaterialCommandToSideMenu("Promotion",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Client_List_Promotions h = new Client_List_Promotions();
             h.getF().show();
        }});
        tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_HELP, e -> {
            SuiviReclamationsClients sr = new SuiviReclamationsClients();
            sr.getSuiviReclamation().show();
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_CLOSE, e -> {
            //-----------------------------------------
            if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
                    FacebookConnect.getInstance().logout();
                } else {
                     LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();
                }
            
        });
         tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent evt) {
                             Contact h = new Contact();
                             h.Contact();
                             }
                             });
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
              Display.getInstance().exitApplication();
        });
        //hi.addComponent(new Label("Hi World"));
    
           }
           
            public void Menu2(Form f){
               FonctionPanier.compterArticles();
           
        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("fbuser.jpg");
        Container topBar = BorderLayout.east(new Label(icon.fill(70, 70)));
        topBar.add(BorderLayout.SOUTH, new Label("Souk El Medina.....", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        Storage.getInstance().deleteStorageFile("imgprofile");
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("fbuser.jpg"), true);

        Image src = null;
        try {
            String imgLink = User.getActifUser().getPhotoProfil();

            if (imgLink.indexOf("http") > -1) {
                src = URLImage.createToStorage(placeholder, "imgprofile", User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            } else {
                src = URLImage.createToStorage(placeholder, "imgprofile", ToolsUtilities.ServerIp + User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            }
        } catch (NullPointerException ne) {
            src = URLImage.createToStorage(placeholder, "imgprofile", "", URLImage.RESIZE_SCALE);
            System.out.println("Photo null");
        }
        Image userPicture = src;

        final Command profileCommand = new Command("My Profile", userPicture) {
            public void actionPerformed(ActionEvent evt) {
                // updateLoginPhoto();

                ProfileForm profileForm = new ProfileForm();
                profileForm.getProfile().show();

             
            }
        };
        
       tb.addMaterialCommandToSideMenu("My Profile", FontImage.MATERIAL_HOME, e -> {
             ProfileForm profileForm = new ProfileForm();
                profileForm.getProfile().show();
        });
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeProduitsForm hp = new HomeProduitsForm();
            hp.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_SHOPPING_CART, e -> {
            VendeurCommandesForm vcf;
                   try {
                       vcf = new VendeurCommandesForm();
                       vcf.getPsdTutorial().show();
                   } catch (IOException ex) {
                      // Logger.getLogger(ToolbarForm.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
        });
            tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Vendeur_Liste_Events h = new Vendeur_Liste_Events();
             h.getF().show();
        }});
               tb.addMaterialCommandToSideMenu("Promotion",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Vendeur_List_Promotions h = new Vendeur_List_Promotions();
             h.getF().show();
        }});
       tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_HELP, e -> {
            ConsulterReclamationsTraiteesVendeur sr = new ConsulterReclamationsTraiteesVendeur();
            sr.getConsulterReclamations().show();
        });
        tb.addMaterialCommandToSideMenu("Contact", FontImage.MATERIAL_CONTACTS, e -> {
             Contact h = new Contact();
                             h.Contact();
        });
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_CLOSE, e -> {
            //-----------------------------------------
            if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
                    FacebookConnect.getInstance().logout();
                } else {
                   LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();
                }
            
        });
         
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
              Display.getInstance().exitApplication();
        });
        //hi.addComponent(new Label("Hi World"));
    
           }
            
             public void Menu0(Form f){
               FonctionPanier.compterArticles();
           
        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("fbuser.jpg");
        Container topBar = BorderLayout.east(new Label());
        topBar.add(BorderLayout.SOUTH, new Label("Souk El Medina.....", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        Storage.getInstance().deleteStorageFile("imgprofile");
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("fbuser.jpg"), true);

  
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeProduitsForm hp = new HomeProduitsForm();
            hp.getF().show();
        });
        
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Cart= "+FonctionPanier.compterArticles(), FontImage.MATERIAL_SHOPPING_CART, e -> {
            HomePanierForm hpanier = new HomePanierForm();
            hpanier.getPsdTutorial().show();
        });
            tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Client_Liste_Events h = new Client_Liste_Events();
             h.getF().show();
        }});
          tb.addMaterialCommandToSideMenu("Promotion",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Client_List_Promotions h = new Client_List_Promotions();
             h.getF().show();
        }});
        tb.addMaterialCommandToSideMenu("LogIn",FontImage.MATERIAL_LOCK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               LoginForm lf = new LoginForm();
               lf.getMain().show();
            }
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
            //-----------------------------------------
        });
         tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent evt) {
                             Contact h = new Contact();
                             h.Contact();
                             }
                             });
        
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
              Display.getInstance().exitApplication();
        });
        //hi.addComponent(new Label("Hi World"));
    
           }
}
