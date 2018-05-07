/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.components.ImageViewer;
import com.codename1.components.SliderBridge;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
import com.codename1.ui.table.Table;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author user
 */
public class Client_Liste_Events {
    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;
    Table event;
    private Image img;
    private EncodedImage enc;
    private ImageViewer iV;
    private Form f2;
            
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
    public Client_Liste_Events()
    {
        
         
         f = new Form("Liste des Evenemeent", BoxLayout.y());
         Toolbar tb = f.getToolbar();
                   tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyApplication m = new MyApplication();
              // m.getHome().show();
            }
        });
             tb.addMaterialCommandToSideMenu("Evenement",FontImage.MATERIAL_EVENT,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Client_Liste_Events h = new Client_Liste_Events();
        h.getF().show();
            }
        }); 
   
       ServiceEvenements sc = new ServiceEvenements();
      
        for(Evenements t :sc.getList2())
        {
             try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
 
     
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Dimension d = new Dimension(20,20);
         Image i =(URLImage.createToStorage(enc, t.getNomevenement(), "http://localhost/pidevweb/web/uploads/Images/"+t.getFile()+"", URLImage.RESIZE_SCALE));
         
            ImageViewer img2 = new ImageViewer(i.fill(120, 130));
            Slider jSlider = new Slider();
            jSlider.setMaxValue(255);
            jSlider.setMinValue(0); 
            jSlider.setProgress(50); // Set  the starting value
            jSlider.setEditable(true); 
           
            
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Container C4 =new Container(new FlowLayout(Component.CENTER));
            
            Label l = new Label(t.getNomevenement());
            l.getAllStyles().setFgColor(0xff0000);
            Label tarif = new Label(t.getTarifevenement()+" TND");
            //C3.add(jSlider);
            
            Label DDF = new Label();
            
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            C3.add(createForFont(smallPlainSystemFont,"A"+ t.getDate()+" au "+t.getDatefin()));
            C3.add(createForFont(smallPlainSystemFont, " N° Place Total: "+t.getNombredeplaces()+" N° Place Disponible: "+t.getNombredeplacerestante()));
            C3.add(createForFont(smallPlainSystemFont, " Lieu: "+t.getLieu()));

           // C3.add(jSlider);
  
            
            C.add(l);
            
            C.add(tarif);
            C2.add(C);
            C1.add(img2);
            C1.add(C2);
            C2.add(C3);
           // C1.setLeadComponent(l);
            Label fargha = new Label();
            Slider khat = new Slider();
           f.add(C1);
           f.add(fargha);
           f.add(khat);
         // f.getContentPane().animateLayout(20000);
            l.addPointerPressedListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   f2=new Form(BoxLayout.y());
                   Toolbar tb = f2.getToolbar();
                   tb.addCommandToLeftBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           f.show();                       }
                   });
                   Container C5 = new Container(new FlowLayout(Component.CENTER));
                  
                   System.out.println(getSaltString()+"55555555555");
                   
                   C4.add(new ImageViewer(URLImage.createToStorage(enc,getSaltString(), "http://localhost/pidevweb/web/uploads/Images/"+t.getFile()+"", URLImage.RESIZE_SCALE)));
                   Label detail = new Label("DÉTAILS DU L'ÉVÈNEMENT");
                 
                   Button participation = new Button("Participer");
                   detail.getAllStyles().setFgColor(0xff0000);
                   C4.add(detail);
                   C4.add(createForFont(smallPlainSystemFont,"Titre: "+ t.getNomevenement()));
                   C4.add(createForFont(smallPlainSystemFont,"Date Start: "+ t.getDate()));
                   C4.add(createForFont(smallPlainSystemFont,"Date End "+t.getDatefin()));
                   C4.add(createForFont(smallPlainSystemFont, " Emplacement De L'évènement: "+t.getLieu()));
                   C4.add(createForFont(smallPlainSystemFont, " Nombre Des Places: "+t.getNombredeplaces()));
                   C4.add(createForFont(smallPlainSystemFont, " Nombre Des Places Disponible: "+t.getNombredeplacerestante()));


                  C4.add(new SpanLabel(t.getDescription()));
                  C4.add(participation);
                  f2.add(C4);
                  f2.show();
                           
                   
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

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    public TextField getTetat() {
        return tetat;
    }

    public void setTetat(TextField tetat) {
        this.tetat = tetat;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }
    
}
