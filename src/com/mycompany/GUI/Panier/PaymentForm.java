/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Panier;


import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Panier.StripePayement;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Panier.PanierService;
import com.stripe.model.Charge;
import com.stripe.model.Token;

/**
 *
 * @author jean
 */
public class PaymentForm {
    
      private Form psdTutorial;
 private Resources theme = UIManager.initFirstTheme("/theme3");
 
 
    private Label createSeparator() {
        Label sep = new Label();
        sep.setUIID("Separator");
        // the separator line is implemented in the theme using padding and background color, bydefault labels
        // are hidden when they have no content, this method disables that behavior
        sep.setShowEvenIfBlank(true);
        return sep;
    }

    public PaymentForm() {

        psdTutorial = new Form("Payment", new BorderLayout());
        Toolbar tb =  psdTutorial.getToolbar();
       // psdTutorial.setToolbar(tb);
      // Menu();
      ToolbarForm tbf = new ToolbarForm();
       
        if(User.getActifUser() !=null){
            tbf.Menu(psdTutorial); 
        }else{
             
            tbf.Menu0(psdTutorial); 
        }
        // we create 4mm material arrow images for the back button and the Get started button    
        Style iconStyle = psdTutorial.getUIManager().getComponentStyle("Stripe Payment");
        FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
        FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);
        // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command    // so we can color it in Red    
        tb.addCommandToRightBar("Back", leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomePanierForm pf = new HomePanierForm();
                pf.psdTutorial.show();
            }
        });
      //  Command doneCommand = tb.addCommandToRightBar("Done", null, (e) -> Log.p("Done pressed"));
       // tb.findCommandComponent(doneCommand).setUIID("RedCommand");    // The camera button is comprised of 3 pieces. A label containing the image and the transparent button    // with the camera icon on top. This is all wrapped in the title container where the title background image    // is placed using the theme. We chose to use a Label rather than a background using the cameraLayer so    // the label will preserve the original size of the image without scaling it and take up the space it needs    
        Button cameraButton = new Button(theme.getImage("paycard.png").fill(120, 96));
        Container cameraLayer = LayeredLayout.encloseIn(new Label(theme.getImage("camera-button.png")), cameraButton);
        cameraButton.setUIID("CameraButton");
        Container titleContainer = Container.encloseIn(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER), cameraLayer, BorderLayout.CENTER);
        titleContainer.setUIID("TitleContainerPayment");
        TextField month = new TextField("", "MM");
        TextField year = new TextField("", "YY");
        TextField ccvField = new TextField("", "CCV",20,TextField.PASSWORD);
        TextField codeZipField = new TextField("", "Code",20,TextField.PASSWORD);
        TextField email = new TextField("", "Email Address", 20, TextField.EMAILADDR);
        TextField numeroCarte = new TextField("", "Votre Numéro Bancaire", 20, TextField.PHONENUMBER);
        TextField phone = new TextField("", "Phone Number", 20, TextField.PHONENUMBER);
        Label phonePrefix = new Label("+216");
        phonePrefix.setUIID("TextField");    // The phone and full name have vertical separators, we use two table layouts to arrange them correctly    // so the vertical separator will be in the right place   
        TableLayout fullNameLayout = new TableLayout(1, 5);
        Container fullName = new Container(fullNameLayout);
        fullName.add(fullNameLayout.createConstraint().widthPercentage(33), month).add(fullNameLayout.createConstraint().widthPercentage(1), createSeparator()).add(fullNameLayout.createConstraint().widthPercentage(33), year)
                .add(fullNameLayout.createConstraint().widthPercentage(1), createSeparator()).add(fullNameLayout.createConstraint().widthPercentage(32), ccvField);
        Container fullPhone = TableLayout.encloseIn(3, phonePrefix, createSeparator(), phone);    // The button in the south portion needs the arrow icon to be on the right side so we place the text on the left   
        Button southButton = new Button("To Pay", rightArrow);
        southButton.setTextPosition(Component.LEFT);
        southButton.setUIID("SouthButton");   
        
        month.setText("");
        
        
        year.setText("");
            southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               int mois = Integer.parseInt(month.getText());
        int annee = Integer.parseInt(year.getText());
        
        
//        int codezip = Integer.parseInt(codeZip.getText());
        
        
        Token token = StripePayement.getToken("pk_test_Dzbm4KQQrkWxbmai0DEiNKC6", numeroCarte.getText(), mois, annee, ccvField.getText(), email.getText());
        if(FonctionPanier.MontantGlobal()!= 0){
             if(token !=null){
             Charge ch= StripePayement.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", FonctionPanier.MontantGlobal(),"sk_test_kXdQWE7YYBfcaZK9OoH63KaR", numeroCarte.getText(), mois, annee, ccvField.getText(), email.getText());
              Dialog.show("Paiment Succes", "Paiement Effectué avec succès", "OK", null);
                PanierService ps = new PanierService();
                
                ps.preparecommande(FonctionPanier.MontantGlobal());

              
        }else{
           // AlertMaker.showErrorMessage("Erreur de Paiement", "Numéro de Carte Invalide");
           Dialog.show("Erreur de Paiement", "Numéro de Carte Invalide", "ok", null);
        }
        }
        else{
           Dialog.show("Panier Vide", "Desolez vous ne pouvez pas effectuer de paiement car votre panier est vide", "ok", null);
        
                 } }
        });
// we add the components and the separators the center portion contains all of the elements in a box    // Y container which we allow to scroll. BorderLayout Containers implicitly disable scrolling   
        
        
        Container by = BoxLayout.encloseY(email, createSeparator(), numeroCarte, createSeparator(), fullPhone, createSeparator(),fullName,
                createSeparator(),codeZipField, createSeparator());
        by.setScrollableY(true);
        psdTutorial.add(BorderLayout.NORTH, titleContainer).add(BorderLayout.SOUTH, southButton).add(BorderLayout.CENTER, by);

//psdTutorial.show();
    }

   /* public String generate(int length) {
        String chars = "1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for (int x = 0; x < length; x++) {
            int i = (int) Math.floor(Math.random() * 10); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }
        System.out.println(pass);
        return pass;
    }*/
    
    public void Menu() {
        FonctionPanier.compterArticles();

        Toolbar tb = psdTutorial.getToolbar();
        Image icon = theme.getImage("icon.png").fill(70, 70);
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Souk El Medina.....", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeProduitsForm hp = new HomeProduitsForm();
            hp.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Cart= " + FonctionPanier.compterArticles(), FontImage.MATERIAL_SHOPPING_CART, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {
            //-----------------------------------------
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
            //-----------------------------------------
        });
        //hi.addComponent(new Label("Hi World"));
    }
    public Form getPsdTutorial() {
        return psdTutorial;
    }

    public void setPsdTutorial(Form psdTutorial) {
        this.psdTutorial = psdTutorial;
    }
}
