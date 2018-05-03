/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Promotions;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.Table;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.detail;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Promotions.Promotions;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Promotions.ServicePrommotion;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author user
 */
public class Client_List_Promotions {
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
     private Label createForFont(Font fnt, String s) {
            Label l = new Label(s);
            l.getUnselectedStyle().setFont(fnt);
            
            return l;
          }
    
    public Client_List_Promotions()
    {
        ServicePrommotion ser = new ServicePrommotion();
        Date cd = new Date();
        for(Promotions t : ser.getListPromotion())
        {
            if(t.getDatedebut().getTime()>cd.getTime())
            {
                ser.udpateValidateur(0, t.getIdpromotion());
            }
            else if (t.getDatefin().getTime()<cd.getTime())
            {
                 ser.udpateValidateur(0, t.getIdpromotion());
            }
            else
            {
                 ser.udpateValidateur(1, t.getIdpromotion());
            }
        }
        f = new Form("Liste des Promotions", new GridLayout(2, 2));
      
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
      
        for(Promotions t :sc.getList2())
        {
                  //  lieux.setHint("lieux"); 

             try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
 
     
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Image i =(URLImage.createToStorage(enc,t.getIdproduits().getImageProduit(), "http://localhost/pidevweb/web/uploads/Images/"+t.getIdproduits().getImageProduit()+"", URLImage.RESIZE_SCALE));
         
         
            ImageViewer img2 = new ImageViewer(i.fill(400,400));
           /* Slider jSlider = new Slider();
            jSlider.setMaxValue(255);
            jSlider.setMinValue(0); 
            jSlider.setProgress(50); // Set  the starting value
            jSlider.setEditable(true); 
           */
            
         
           

            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             C4=new Container(new FlowLayout(Component.CENTER));
              
            Label l = new Label(t.getIdproduits().getNomProduit().toUpperCase());
            l.getAllStyles().setFgColor(0x61210B);
       Button addToCard = new Button("ADD To Cart");
            //C3.add(jSlider);
              C4.add(l);
               C3.add(C4);
            Label DDF = new Label();
               SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
               
                  String datdebu = formater.format( t.getDatedebut());
                  String datfin = formater.format(t.getDatefin());
            
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            C3.add(createForFont(smallPlainSystemFont,"Du"+datdebu+" au "+datfin));
            C3.add(createForFont(smallPlainSystemFont, t.getIdproduits().getNewprix()+" TND   "+t.getIdproduits().getPrixProduit()+"TND  "));
            C3.add(addToCard);
           

           // C3.add(jSlider);
  
            
            
           
            
           
            C1.add(img2);
          
           
            C1.add(C3);
          C1.setUIID("ColorB");
            C1.getStyle().setBgColor(0x99CCCC);
           // C1.getStyle().setBorder(Border.createLineBorder(1));
            C1.getStyle().setBorder(Border.getDefaultBorder());
             img2.getStyle().setBorder(Border.createDottedBorder(1, 0x99CCCC));
             f.getStyle().setBgColor(0xE6E6E6);
           // C1.setLeadComponent(l);
            Label fargha = new Label();
            Slider khat = new Slider();
           
           f.add(C1);
          
           //f.animateLayoutFadeAndWait(200, 100);
         // f.getContentPane().animateLayout(20000);
            l.addPointerPressedListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   
                   
               }
           });
      
        }  
        if(Util.connectedUser != null)
        {
            
      
       
          f.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    LogIn l = new LogIn();
                                     Util.connectedUser=null;
                                    l.getConnection().show();
                                    
                                }
                            });
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
