/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Promotions;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.table.Table;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_event_Edit;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Promotions.Promotions;
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Promotions.ServicePrommotion;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author user
 */
public class detailVendeur {
     Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;
    Table event;
    private Image img;
    private EncodedImage enc;
    private ImageViewer iV;
    private Form f2;
    Button delete;
     Button update;
     Container C4 ;
      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

    public detailVendeur() {
    }
            
    private ArrayList<Evenements> teams;
    
       private Label createForFont(Font fnt, String s) {
            Label l = new Label(s);
            l.getUnselectedStyle().setFont(fnt);
            
            return l;
          }
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
      public void detailVendeur(Promotions t)
    {
        InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
      try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
        f2=new Form("Detail");
          f2.getStyle().setBgColor(0xE6E6E6);
                             Toolbar tb = f2.getToolbar();
                   tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomePage h = new HomePage();
       h.getHome().show();
            }
        });
             tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  Container C4 = new Container(new FlowLayout(Component.CENTER));
                           Vendeur_Liste_Events c = new Vendeur_Liste_Events();
                           c.Vendeur_Liste_Events();        
            }
        }); 
              tb.addMaterialCommandToSideMenu("Promotion",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Vendeur_List_Promotions h = new Vendeur_List_Promotions();
        h.getF().show();
            }
        }); 
               tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Contact h = new Contact();
        h.Contact();
            }
        });
              
                                f2.getToolbar().addCommandToOverflowMenu("Edite", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                     Vendeur_Promotion_Edit v = new Vendeur_Promotion_Edit();
                                    v.Vendeur_Promotion_Edit(t);
                    
                                }
                            });
                                f2.getToolbar().addCommandToOverflowMenu("Delete", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    ServicePrommotion ser = new ServicePrommotion();
                                    ser.deletePromotion(t.getIdpromotion());
                                            C4 = new Container(new FlowLayout(Component.CENTER));
                                            Vendeur_List_Promotions  c = new Vendeur_List_Promotions();
                                            c.getF().show();                  
                                }
                            });
                                f2.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    HomePage h = new HomePage();
                                     Util.connectedUser=null;
                                    h.getHome().show();
                                   
                                }
                            });
                   f2.getAllStyles().setPadding(0,0,0,0);
                      f2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                      int deviceWidth = Display.getInstance().getDisplayWidth();
                      
                  tb = f2.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           C4 = new Container(new FlowLayout(Component.CENTER));
                           Vendeur_List_Promotions ve = new Vendeur_List_Promotions();
                           ve.getF().show();       }
                   });
                         
                  SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                  String datdebu = formater.format( t.getDatedebut());
                  String datfin = formater.format(t.getDatefin());
                  System.out.println(datdebu);
                  System.out.println(datfin);
                   Container C5 = new Container(new FlowLayout(Component.CENTER));
                     Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               
                  Container C8 = new Container(new FlowLayout(Component.CENTER));
                  Container C9 = new Container(new FlowLayout(Component.CENTER));
                  Container C13 = new Container(new FlowLayout(Component.CENTER));
                  Container C10 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                  Container C11 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   System.out.println(getSaltString());
                   System.out.println(t+"+++++++++");
                   System.out.println(t.getNompromotion());
                   System.out.println("http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getImage()+"");
                   C6.add(new ImageViewer(URLImage.createToStorage(enc,t.getImage(), "http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getImage()+"", URLImage.RESIZE_SCALE).fill(1000 , 1000)));
                   Label detail = new Label("DÉTAILS DU PROMOTION");
                   detail.getAllStyles().setFgColor(0xff0000);
                 
                   Button participation = new Button("Participer");
                   delete= new Button("Delete");
                   update = new  Button("Edit");
           
                   Container CPRD = new Container(new BoxLayout(BoxLayout.X_AXIS));
                   C4=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
                   C13.add(detail);
                    Label titleLabel = new Label(t.getIdproduits().getNomProduit(), "TintOverlay");
                    URLImage thumbImage = URLImage.createToStorage(enc, t.getIdproduits().getImageProduit(), "http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getIdproduits().getImageProduit()+"", URLImage.RESIZE_SCALE_TO_FILL);
                    ScaleImageButton btn = new ScaleImageButton(thumbImage.fill(250, 380));
                    
                     CPRD.add(LayeredLayout.encloseIn(btn));
                   C4.add(createForFont(smallPlainSystemFont,"Titre: "+ t.getNompromotion()));
                   C4.add(createForFont(smallPlainSystemFont,"Date Start: "+ datdebu));
                   C4.add(createForFont(smallPlainSystemFont,"Date End: "+ datfin));
                   C4.add(createForFont(smallPlainSystemFont,"Produit: "+ t.getIdproduits().getNomProduit()));
                   C4.add(createForFont(smallPlainSystemFont, " Solde: "+t.getPourcentage()+"%"));
                   C4.add(createForFont(smallPlainSystemFont, " Ancien Prix: "+t.getIdproduits().getPrixProduit()));
                   C4.add(createForFont(smallPlainSystemFont, " New Prix: "+t.getIdproduits().getNewprix()));
                  

                 Slider ns = new Slider();
                  
                  f2.add(C6);
                  f2.add(C13);
                
                 CPRD.add(C4);
                  f2.add(CPRD);
                 
                  f2.show();
                             
                  
                  delete.addActionListener((e) -> {
            ServiceEvenements ser = new ServiceEvenements();
           
            
           d.dispose();
           f.show();
            

        });  
                 
                   
                                
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form getF2() {
        return f2;
    }

    public void setF2(Form f2) {
        this.f2 = f2;
    }
    
    
}
