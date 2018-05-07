/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Produits;

import Utilities.ToolsUtilities;
import com.mycompany.GUI.Panier.HomePanierForm;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.GUI.utilisateurs.ProfileForm;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Panier.PanierService;
import com.mycompany.service.Panier.StatsService;
import com.mycompany.service.Produits.ProduitsServices;
import java.io.IOException;
import java.util.Iterator;
//import com.mycompagny.Service.ServiceTask;
//import com.mycompany.Entite.Task;

/**
 *
 * @author sana
 */
public class HomeProduitsForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout, btnaff;
    //Form f;
    SpanLabel lb;
     private Resources theme=UIManager.initFirstTheme("/theme3");

    public Container block(Produits p ) throws IOException {
        Container block = new Container(BoxLayout.x());
        Container imgblock = new Container(BoxLayout.y());
        Container infoblock = new Container(BoxLayout.y());
        // block.add(new ImageViewer(theme.getImage(p.Img)));
        Label nom = new Label(p.getNomProduit());
        Label prix = new Label("$"+p.getPrixProduit());
       ImageViewer img = new ImageViewer();
       
        img.setImage(theme.getImage(p.getIdProduit()+".jpg").fill(200, 200));
      
       
       imgblock.add(img);
     // block.setLeadComponent(img);
        infoblock.add(nom);
        infoblock.add(prix);
        //infoblock.setLeadComponent(nom);
        
        Button buttonadd = new Button("Add to Cart");
        infoblock.add(buttonadd);
        buttonadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Dialog.show("Panier", "Ajout du produit "+p.getNomProduit(), "ok", null);
                p.setQuantiteProduitClient(1);
                FonctionPanier.ajouterArticle(p);
                FonctionPanier.compterArticles();
                
                PanierService ps = new PanierService();
                ps.ajouterPanier(p);
               /* HomeProduitsForm hpf = new HomeProduitsForm();
                hpf.getF().show();*/
                ToastBar.showMessage("le produit "+p.getNomProduit()+" a été ajouté", FontImage.MATERIAL_THUMB_UP);
   
                
               // f.show();
            }
        });
        
        block.add(imgblock);
        block.add(infoblock);
        return block;
    }

    public HomeProduitsForm() {


        f = new Form("Listes Des Produits", BoxLayout.y());
        
        ToolbarForm tbf = new ToolbarForm();
        if(User.getActifUser()!= null)
        System.out.println(User.getActifUser().getRoles().toString());
   if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_USER]")){
            tbf.Menu(f); 
        }else if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]"))
                {
                    tbf.Menu2(f);
                }
                else{
             
            tbf.Menu0(f); 
        }
        
        
           
           
               //Menu();
        lb = new SpanLabel("");
        f.add(lb);
        ProduitsServices produits = new ProduitsServices();
        // lb.setText(produits.getList2().toString());

        for (Iterator<Produits> iterator = produits.getList2().iterator(); iterator.hasNext();) {
            Produits prodnext = iterator.next();
            try {
                f.add(block(prodnext));
                Slider s = new Slider();
                s.setEditable(false);
               // s.setUIID("SeparatorLine");
                f.addComponent(s);
                
            } catch (IOException ex) {

            }
        }

    }

      public void Menu() {
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
        tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {
            //-----------------------------------------
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
            logForm.getMain().show();
                }
            
        });
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_INFO, e -> {
              Display.getInstance().exitApplication();
        });
        //hi.addComponent(new Label("Hi World"));
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
