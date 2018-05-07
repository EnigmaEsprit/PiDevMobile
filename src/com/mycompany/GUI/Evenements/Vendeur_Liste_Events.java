/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Promotions.Vendeur_List_Promotions;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author user
 */
public class Vendeur_Liste_Events  {
     Form f;
    private Resources theme = UIManager.initFirstTheme("/theme");
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
       private EncodedImage placeholder;
       private static final String WEBSERVICE_URL =  "https://www.codenameone.com/files/kitchensink/dogs/list2.json";
            
    private ArrayList<Evenements> teams;
    
      
       private Label createForFont(Font fnt, String s,String img) {
            Label l = new Label(s,theme.getImage(img).fill(40, 40));
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

    public Vendeur_Liste_Events() {
    }
    
    public void Vendeur_Liste_Events()
    {
        
         
         f = new Form("Liste des Evenemeent", BoxLayout.y());
           f.getStyle().setBgColor(0xE6E6E6);
           InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
         f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
         
                  Toolbar tb = f.getToolbar();
                  /*  tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
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
                  });*/
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
               tb = f.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           Container C4 = new Container(new FlowLayout(Component.CENTER));
                                    HomePage h = new HomePage();
                                     h.getHome().show();
                       }
                   });
       ServiceEvenements sc = new ServiceEvenements();
      
        for(Evenements t :sc.getList3())
        {
             try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
 
     
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         
         Image i =(URLImage.createToStorage(enc,t.getNomevenement(), "http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getImage()+"", URLImage.RESIZE_SCALE));
         
         
            ImageViewer img2 = new ImageViewer(i.fill(300,300));
           /* Slider jSlider = new Slider();
            jSlider.setMaxValue(255);
            jSlider.setMinValue(0); 
            jSlider.setProgress(50); // Set  the starting value
            jSlider.setEditable(true); 
           */
            
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             C4=new Container(new FlowLayout(Component.CENTER));
            
            Label l = new Label(t.getNomevenement().toUpperCase());
            l.getAllStyles().setFgColor(0xA52A2A);
            Label tarif = new Label(t.getTarifevenement()+" TND","bgcolor");
            //C3.add(jSlider);
            
            Label DDF = new Label();
               SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
               
                  String datdebu = formater.format( t.getDate());
                  String datfin = formater.format(t.getDatefin());
            
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            C3.add(createForFont(smallPlainSystemFont,"Du"+datdebu+" au "+datfin,"calendar (1).png"));
            C3.add(createForFont(smallPlainSystemFont, " N° Place Total: "+t.getNombredeplaces()+" N° Place Disponible: "+t.getNombredeplacerestante(),"chair.png"));
            C3.add(createForFont(smallPlainSystemFont, " Lieu: "+t.getLieu(),"placeholder.png"));

           // C3.add(jSlider);
  
            
            C.add(l);
            C.add(new Label("       "));
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
                  detailVendeur d = new detailVendeur();
                   System.out.println(t);
                  d.detailVendeur(t);
                   
               }
           });
      
        }  
         f.getToolbar().addCommandToOverflowMenu("Add Event", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                   Vendeur_Evenet v  = new Vendeur_Evenet();
                                   v.getFv().show();
                                }
                            });
          f.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                      LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();
                                }
                            });
           d.dispose();
          f.show();
         
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

    public Form getF2() {
        return f2;
    }

    public void setF2(Form f2) {
        this.f2 = f2;
    }
   
    
}
