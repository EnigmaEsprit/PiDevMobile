/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Panier.PanierService;
import com.mycompany.service.Produits.ProduitsServices;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author jean
 */
public class HomePanierForm {

    Form hi = new Form("Souk El Medina", new BorderLayout());
    Form psdTutorial;
    private Resources theme = UIManager.initFirstTheme("/theme3");

     private Label createForFont(Font fnt, String s) {

  Label l = new Label(s);

  l.getUnselectedStyle().setFont(fnt);

  return l;

}
     
    public HomePanierForm() {

        psdTutorial = new Form("Panier", new BorderLayout());
        Toolbar tb = psdTutorial.getToolbar();
        // psdTutorial.setToolbar(tb);
        //Menu();
        ToolbarForm tbf = new ToolbarForm();
       
      if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_USER]")){
            tbf.Menu(psdTutorial); 
        }else{
             
            tbf.Menu0(psdTutorial); 
        }
        Style iconStyle = psdTutorial.getUIManager().getComponentStyle("Title");
        FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
        FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

        // tb.addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
        Command doneCommand = tb.addCommandToRightBar("Done", null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                               Log.p("Done pressed");
                 if(User.UserConnected){
                      PaymentForm pf = new PaymentForm();
                 pf.getPsdTutorial().show();

                 
                }else{
                    User.UserConnectedPayment=true;
                    User.UserConnected =true;
                    LoginForm lf = new LoginForm();
                    lf.getMain().show();
                }
                
            }
        });
        
        tb.findCommandComponent(doneCommand).setUIID("RedCommand");

        Button cameraButton = new Button(theme.getImage("camera.png"));
        //  Container cameraLayer = LayeredLayout.encloseIn(new Label(theme.getImage("camera-button.png")), cameraButton);
        //  cameraButton.setUIID("CameraButton");
        Label text = new Label("Panier");
        text.getAllStyles().setFgColor(0xff0000);
        SpanLabel text2 = new SpanLabel(FonctionPanier.compterArticles() + " article $" + FonctionPanier.MontantGlobal());
        // Container cameraLayer = LayeredLayout.encloseIn(text,text2);
        
        
        
        
        
        Container backShoppingContainer = new Container(new BorderLayout());
        Label backShopping =new Label("Back Shopping");
        backShopping.setUIID("BackShopping");
        backShoppingContainer.add(BorderLayout.WEST,backShopping);
        Container cameraLayer = new Container(BoxLayout.y());
        cameraLayer.add(backShoppingContainer);
        cameraLayer.add(text);
        cameraLayer.add(text2);
        Container titleContainer = Container.encloseIn(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER), cameraLayer, BorderLayout.CENTER);
        //titleContainer.add(new ImageViewer(theme.getImage("background.jpg")));
        titleContainer.setUIID("TitleContainer");

        TextField firstName = new TextField("", "First Name");
        TextField lastName = new TextField("", "Last Name");
        TextField email = new TextField("", "Email Address", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Choose a Password", 20, TextField.PASSWORD);
        TextField phone = new TextField("", "Phone Number", 20, TextField.PHONENUMBER);
        Label phonePrefix = new Label("+1");
        phonePrefix.setUIID("TextField");

        TableLayout fullNameLayout = new TableLayout(1, 3);
        Container fullName = new Container(fullNameLayout);
        fullName.add(fullNameLayout.createConstraint().widthPercentage(49), firstName).add(fullNameLayout.createConstraint().widthPercentage(1), createSeparator()).add(fullNameLayout.createConstraint().widthPercentage(50), lastName);
        Container fullPhone = TableLayout.encloseIn(3, phonePrefix, createSeparator(), phone);

        Label montant = new Label("$" + FonctionPanier.MontantGlobal());
        montant.setUIID("TextField");
        Button southButton = new Button("Mettre à jour le panier");

        Container footer1 = TableLayout.encloseIn(1,/* createSeparator(),*/ southButton);
        //Container footer2 = TableLayout.encloseIn(2, new Label("Total à Regler"), montant);
          Container footer2= new Container(new BorderLayout());
        footer2.add(BorderLayout.WEST, new Label("Total à Regler"));
        footer2.add(BorderLayout.EAST,montant);
        
        southButton.setHeight(10);
        TextField codePromo = new TextField();
        codePromo.setHint("Saisisr Code Promo");
        Button ok = new Button("OK");
        //ok.setWidth(50);
        TableLayout fullNameLayout2 = new TableLayout(1, 2);
        Container footer3 = new Container(fullNameLayout2);
        footer3.add(fullNameLayout2.createConstraint().widthPercentage(80), codePromo)
                .add(fullNameLayout2.createConstraint().widthPercentage(20), ok);

        Container footer = new Container(BoxLayout.y());
        footer.add(footer1);
        footer.add(footer2);
        footer.add(footer3);
        //southButton.setTextPosition(Component.LEFT);
        //southButton.setUIID("SouthButton");

        ProduitsServices produits = new ProduitsServices();
        // lb.setText(produits.getList2().toString());

        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomePanierForm h = new HomePanierForm();
                h.getPsdTutorial().show();
            }
        });

        /*Container by = BoxLayout.encloseY(fullName, createSeparator(),
                email, createSeparator(), password, createSeparator(), fullPhone, createSeparator());*/
        Container by = new Container(BoxLayout.y());
        for (Iterator<Produits> iterator = FonctionPanier.getListeProduit().iterator(); iterator.hasNext();) {
            Produits prodnext = iterator.next();
            try {
                by.add(block(prodnext));
                Slider s = new Slider();
                s.setEditable(false);
                by.addComponent(s);

            }catch (IOException ex){

            }
        }
        by.setScrollableY(true);

        psdTutorial.add(BorderLayout.NORTH, titleContainer).add(BorderLayout.SOUTH, footer).add(BorderLayout.CENTER, by);

    }

    private Label createSeparator() {
        Label sep = new Label();
        sep.setUIID("Separator"); // the separator line  is implemented in the theme using padding and background color, by default labels    // are hidden when they have no content, this method disables that behavior    
        sep.setShowEvenIfBlank(true);
        return sep;
    }

    public Container block(Produits p) throws IOException {
        //Container block = new Container(BoxLayout.x());
        Container imgblock = new Container(BoxLayout.y());
        Container infoblock = new Container(BoxLayout.y());
        // block.add(new ImageViewer(theme.getImage(p.Img)));
        Label nom = new Label(p.getNomProduit());
        Container prixContainer = new Container(new BorderLayout());
        Label prix = new Label("$" + p.getPrixProduit()*p.getQuantiteProduitClient());
        prixContainer.add(BorderLayout.EAST,prix);
        prix.getAllStyles().setFgColor(0xff0000);
        
        
        ImageViewer img = new ImageViewer();

        img.setImage(theme.getImage(p.getIdProduit() + ".jpg").fill(200, 200));

         int fontSize = Display.getInstance().convertToPixels(2);
        Font ttfFont = Font.createTrueTypeFont("Handlee", "Handlee-Regular.ttf"). derive(fontSize, Font.STYLE_PLAIN);
        Label prixUnitaireLabel =  createForFont(ttfFont, "prix unitaire");
        Label prixUnitaireValueLabel =  createForFont(ttfFont, ""+p.getPrixProduit());
        
        Container prixUnitaireContainer= new Container(new BorderLayout());
        prixUnitaireContainer.add(BorderLayout.WEST,prixUnitaireLabel);
        prixUnitaireContainer.add(BorderLayout.EAST,prixUnitaireValueLabel);
        
        
        
        
        TableLayout qteLayout3 = new TableLayout(1, 3);
        Container blockqte = new Container(qteLayout3);
        TextField quantiteField = new TextField();
        quantiteField.setText(p.getQuantiteProduitClient() + "");

        Label modifyQuantiteLabel = new Label(theme.getImage("modifyqte.png").fill(50, 50));

        Label quantiteLabel = new Label("QTE");
        TextField quantiteValueLabel = new TextField( p.getQuantiteProduitClient() + "");
        
       // quantiteValueLabel.setUIID("TextField");

        Label deleteLabel = new Label(theme.getImage("deleteicon.png").fill(20, 20));
        
        Container NomContainer= new Container(new BorderLayout());
        NomContainer.add(BorderLayout.WEST,nom);
        NomContainer.add(BorderLayout.EAST,deleteLabel);
        /* blockqte.add(qteLayout3.createConstraint().widthPercentage(30), quantiteLabel)
        .add(qteLayout3.createConstraint().widthPercentage(20), quantiteValueLabel)
        .add(qteLayout3.createConstraint().widthPercentage(20), modifyQuantiteLabel)
        .add(qteLayout3.createConstraint().widthPercentage(20), deleteLabel);*/
         blockqte.add(qteLayout3.createConstraint().widthPercentage(30), quantiteLabel)
                .add(qteLayout3.createConstraint().widthPercentage(20), quantiteValueLabel)
              .add(qteLayout3.createConstraint().widthPercentage(50), prixContainer);

        imgblock.add(img);

         quantiteValueLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int quantite = Integer.parseInt(quantiteValueLabel.getText());
                      if(FonctionPanier.modifierQTeArticle(p, quantite)){
             
              ToastBar.showMessage("le produit "+p.getNomProduit()+" a été modifié", FontImage.MATERIAL_THUMB_UP);
   
           // Dialog.show("Success", "Quantite Modifie","OK",null);
                         HomePanierForm h = new HomePanierForm();
                    h.getPsdTutorial().show();
         }else{
                            Dialog.show("Failed", "Ne peut pas modifier, veuillez saisir une quantite inférieure","OK",null);
            
         }
            }
        });
        // block.setLeadComponent(img);
        deleteLabel.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Dialog.show("Suppression", "Suppression du produit " + p.getNomProduit(), "Ok", "Cancel")) {
                    FonctionPanier.supprimerArticle(p);
                    FonctionPanier.getListeProduit();
                    System.out.println(FonctionPanier.getListeProduit());
                    PanierService ps = new PanierService();
                    ps.supprimerPanier(p);
                    HomePanierForm h = new HomePanierForm();
                    h.getPsdTutorial().show();
                }
            }
        });
        final Button showPopup = new Button("Show Popup");

        modifyQuantiteLabel.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Command c = new Command("aaa");
                //Dialog.show("Modifier", quantiteField,c );
                Dialog dlg = new Dialog("At Bottom");
                dlg.setLayout(new BorderLayout());
// span label accepts the text and the UIID for the dialog body
                dlg.add(new SpanLabel("Dialog Body text", "DialogBody"));
                int h = Display.getInstance().getDisplayHeight();
                dlg.setDisposeWhenPointerOutOfBounds(true);
                dlg.show(h / 8 * 7, 0, 0, 0);
            }
        });

        TableLayout fullNameLayout2 = new TableLayout(1, 2);
        Container block = new Container(fullNameLayout2);

        infoblock.add(NomContainer);
        infoblock.add(prixUnitaireContainer);
       // infoblock.add(prixContainer);
        infoblock.add(blockqte);

        block.add(fullNameLayout2.createConstraint().widthPercentage(40), imgblock)
                .add(fullNameLayout2.createConstraint().widthPercentage(60), infoblock);
        //infoblock.setLeadComponent(nom);
        // block.add(imgblock);
        // block.add(infoblock);
        return block;
    }

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

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }

    public Form getPsdTutorial() {
        return psdTutorial;
    }

    public void setPsdTutorial(Form psdTutorial) {
        this.psdTutorial = psdTutorial;
    }

}
