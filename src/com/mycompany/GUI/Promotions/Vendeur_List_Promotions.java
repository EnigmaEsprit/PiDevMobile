/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Promotions;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.Table;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_Evenet;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.entites.Promotions.Promotions;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Promotions.ServicePrommotion;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author user
 */
public class Vendeur_List_Promotions {
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
     List items;
     private Label createForFont(Font fnt, String s) {
            Label l = new Label(s);
            l.getUnselectedStyle().setFont(fnt);
            
            return l;
          }
    
    public Vendeur_List_Promotions()
    {
         f = new Form("Liste des Promotions", new BoxLayout(BoxLayout.Y_AXIS));
           Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

      
         f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
         
         Toolbar tb = f.getToolbar();
                   tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyApplication m = new MyApplication();
               m.getHome().show();
            }
        });
          
             tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Client_Liste_Events h = new Client_Liste_Events();
        h.getF().show();
            }
        }); 
          tb.addMaterialCommandToSideMenu("LogIn",FontImage.MATERIAL_LOCK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LogIn log = new LogIn();
                log.getConnection().show();
            }
        });
   
        ServicePrommotion sc = new ServicePrommotion();
        

       int iter=0;
        for(Promotions t :sc.getListPromotionVendeur())
        {
                  //  lieux.setHint("lieux"); 

             try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
 
     
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C0 = new Container(new FlowLayout(Component.CENTER));
         Image i =(URLImage.createToStorage(enc,t.getIdproduits().getImageProduit(), "http://localhost/pidevweb/web/uploads/Images/"+t.getIdproduits().getImageProduit()+"", URLImage.RESIZE_SCALE));
         
            ImageViewer img2 = new ImageViewer(i.fill(800,250));
            C0.add(createForFont(mediumPlainSystemFont, t.getNompromotion().toUpperCase()));
            
            
              URLImage thumbImage = URLImage.createToStorage(enc, t.getImage(), "http://localhost/pidevweb/web/uploads/Images/"+t.getImage()+"", URLImage.RESIZE_SCALE_TO_FILL);
                    ScaleImageButton btn = new ScaleImageButton(thumbImage);
                    btn.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                    Label titleLabel = new Label(t.getNompromotion().toUpperCase(), "TintOverlay");
                       // titleLabel.setUIID("TintOverlaySmall");
                            titleLabel.setVerticalAlignment(Label.CENTER);
                            titleLabel.setAlignment(Label.CENTER);
                            titleLabel.getStyle().setBgColor(0xE6E6E6);
                          //  titleLabel.getAllStyles().setFgColor(0xff000);
                               titleLabel.getAllStyles().setBackgroundGradientEndColor(0xff000);
           
           
         
      
          f.add(LayeredLayout.encloseIn(btn, BorderLayout.south(titleLabel)));
           
          f.getStyle().setBgColor(0xE6E6E6);
          
            btn.addActionListener(e -> {
                detailVendeur dvp = new detailVendeur();
                dvp.detailVendeur(t);
                
            });
          
       
        } 
         f.getToolbar().addCommandToOverflowMenu("Add Promotion", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                   Vendeur_Promotions v  = new Vendeur_Promotions();
                                   v.getFv().show();
                                }
                            });
          f.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    LogIn l = new LogIn();
                                     Util.connectedUser=null;
                                    l.getConnection().show();
                                    
                                }
                            });
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    }
    

