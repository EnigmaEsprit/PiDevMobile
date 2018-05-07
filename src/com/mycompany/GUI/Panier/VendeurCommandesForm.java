/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*****
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 ---------------
 ----------------------------
 
 ---------------------------------------------------
 */
package com.mycompany.GUI.Panier;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Panier.Commande;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Panier.CommandesServices;
import com.mycompany.service.Panier.PanierService;
import com.mycompany.service.Produits.ProduitsServices;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author jean
 */
public class VendeurCommandesForm {
    
    Form psdTutorial;
    private Resources theme = UIManager.initFirstTheme("/theme3");
    
     private Label createSeparator() {
        Label sep = new Label();
        sep.setUIID("Separator");
        // the separator line is implemented in the theme using padding and background color, bydefault labels
        // are hidden when they have no content, this method disables that behavior
        sep.setShowEvenIfBlank(true);
        return sep;
    }

    public VendeurCommandesForm() throws IOException {
           psdTutorial = new Form("Administration", new BorderLayout());
        Toolbar tb = psdTutorial.getToolbar();
        // psdTutorial.setToolbar(tb);
        //Menu();
                ToolbarForm tbf = new ToolbarForm();
         
            tbf.Menu2(psdTutorial); 
       
        Style iconStyle = psdTutorial.getUIManager().getComponentStyle("Title");
        FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
        FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

        // tb.addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
        Command doneCommand = tb.addCommandToRightBar("Done", null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                //PanierService ps = new PanierService();
               // ps.preparecommande();
                 Log.p("Done pressed");
                 
                 PaymentForm pf = new PaymentForm();
                 pf.getPsdTutorial().show();
            }
        });
         psdTutorial.getToolbar().addCommandToOverflowMenu("Stats", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    HomeStatsPanier hst = new HomeStatsPanier();
                                    hst.getStatsForm().show();
                                }
                            });
        
        tb.findCommandComponent(doneCommand).setUIID("RedCommand");

        Button cameraButton = new Button(theme.getImage("camera.png"));
       
        Label text = new Label("Liste des Commandes");
        text.getAllStyles().setFgColor(0xff0000);
    
        Container cameraLayer = new Container(BoxLayout.y());

        cameraLayer.add(createSeparator()); cameraLayer.add(createSeparator());
        cameraLayer.add(text);
       cameraLayer.add(createSeparator()); cameraLayer.add(createSeparator());
        Container titleContainer = Container.encloseIn(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER), cameraLayer, BorderLayout.CENTER);
        titleContainer.setUIID("TitleContainer");

     

        CommandesServices vcf = new CommandesServices();
        //System.out.println("----------------"+vcf.getList2());
        Container by = new Container(BoxLayout.y());
        for (Iterator<Commande> iterator = vcf.getList2().iterator(); iterator.hasNext();) {
            Commande prodnext = iterator.next();
               // System.out.println("******************************"+prodnext);
               by.add(block(prodnext));
               Slider s = new Slider();
               s.setEditable(false);
               by.addComponent(s);
        }
        by.setScrollableY(true);

        psdTutorial.add(BorderLayout.NORTH, titleContainer).add(BorderLayout.CENTER, by);
        
    }

     public Container block(Commande c)  {
       
        Container imgblock = new Container(BoxLayout.y());
        Container infoblock = new Container(BoxLayout.y());

        Container RefContainer = new Container(new BorderLayout());
         Label referenceLabel = new Label("Ref: "+c.getReference());
         Button etat =new Button("Effectuer");
         etat.setHeight(10);
         
         RefContainer.add(BorderLayout.WEST, referenceLabel);
          RefContainer.add(BorderLayout.EAST, etat);
         Label NomPrenomLabel = new Label(c.getPrenom() +" "+c.getNom());
       
       ImageViewer
               img = new ImageViewer();
       
        img.setImage(theme.getImage("user.png").fill(80, 80));
   
       // Container prixContainer = new Container(new BorderLayout());
         Label prixTotal = new Label("$"+c.getPrixTotal());
       // prixContainer.add(BorderLayout.EAST,prixTotal);
        prixTotal.getAllStyles().setFgColor(0xff0000);

        TableLayout qteLayout3 = new TableLayout(1, 3);
        Container blockqte = new Container(qteLayout3);
  

        imgblock.add(img);
        infoblock.add(RefContainer);
        infoblock.add(NomPrenomLabel);
       infoblock.add(prixTotal);
     
        
        TableLayout fullNameLayout2 = new TableLayout(1, 2);
        Container block = new Container(fullNameLayout2);

        block.add(fullNameLayout2.createConstraint().widthPercentage(40), imgblock)
                .add(fullNameLayout2.createConstraint().widthPercentage(60), infoblock);
   
        return block;
    }
     
     
    public Form getPsdTutorial() {
        return psdTutorial;
    }

    public void setPsdTutorial(Form psdTutorial) {
        this.psdTutorial = psdTutorial;
    }

     
     
}
