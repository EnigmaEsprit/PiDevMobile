/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Promotions.Vendeur_List_Promotions;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Participations.Participations;
  
import com.mycompany.entites.Utilisateurs.User;
  
  
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Participations.ServiceParticipations;
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
      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font mediumPlainMonospaceFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

    public detailVendeur() {
    }
            
    private ArrayList<Evenements> teams;
    
         
       private Label createForFont(Font fnt, String s,String img) {
            Label l = new Label(s,theme.getImage(img).fill(40, 40));
            l.getUnselectedStyle().setFont(fnt);
            
            return l;
          }
       private SpanLabel createForFontSL(Font fnt, String s,int align) {
            SpanLabel l = new SpanLabel(s);
            l.getTextAllStyles().setFont(fnt);
           
            l.getTextAllStyles().setAlignment(align);
            //right = 3 center = 4 left= 1 top = 0
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
    
    public void detailVendeur(Evenements t)
    {
  
        System.out.println(t);
  
        
  
         try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
        f2=new Form("Detail");
          f2.getStyle().setBgColor(0xE6E6E6);
          InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
                              Toolbar tb = f2.getToolbar();
  
                              /* tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
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
                              */
  
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
                                    
                                    
                                    Vendeur_event_Edit v = new Vendeur_event_Edit();
                                        v.Vendeur_event_Edit(t);
                    
                                }
                            });
                                f2.getToolbar().addCommandToOverflowMenu("Delete", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println(t.getId());
                                      ServiceEvenements ser = new ServiceEvenements();
                                      ser.deleteEvent(t.getId());
                                            C4 = new Container(new FlowLayout(Component.CENTER));
                                            Vendeur_Liste_Events  c = new Vendeur_Liste_Events();
                                            c.Vendeur_Liste_Events();                  
                                }
                            });
                                f2.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
  
                                     LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();
  
                                     HomePage h = new HomePage();
                                     Util.connectedUser=null;
                                    h.getHome().show();
  
                                }
                            });
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
                   f2.getAllStyles().setPadding(0,0,0,0);
                      f2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                      int deviceWidth = Display.getInstance().getDisplayWidth();
                      
                  tb = f2.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           C4 = new Container(new FlowLayout(Component.CENTER));
                           Vendeur_Liste_Events ve = new Vendeur_Liste_Events();
                           ve.Vendeur_Liste_Events();       }
                   });
                         
                  SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                  String datdebu = formater.format( t.getDate());
                  String datfin = formater.format(t.getDatefin());
                  System.out.println(datdebu);
                  System.out.println(datfin);
                   Container C5 = new Container(new FlowLayout(Component.CENTER));
                     Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                  Container C7 = new Container(new FlowLayout(Component.CENTER));
                  Container C8 = new Container(new FlowLayout(Component.CENTER));
                  Container C9 = new Container(new FlowLayout(Component.CENTER));
                  Container C13 = new Container(new FlowLayout(Component.CENTER));
                  Container C10 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                  Container C11 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   System.out.println(getSaltString());
                   System.out.println(t+"+++++++++");
                   System.out.println(t.getNomevenement());
                   System.out.println("http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getImage()+"");
                   C6.add(new ImageViewer(URLImage.createToStorage(enc,t.getNomevenement(), "http://"+Util.addip+"/pidevweb/web/uploads/Images/"+t.getImage()+"", URLImage.RESIZE_SCALE).fill(1000 , 850)));
                   Label detail = new Label("DÉTAILS DU L'ÉVÈNEMENT");
                   detail.getAllStyles().setFgColor(0xff0000);
                 
                   Button participation = new Button("Participer");
                   delete= new Button("Delete");
                   update = new  Button("Edit");
           
            
                   C4=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
                   C5.add(detail);
                   C7.add(createForFont(smallPlainSystemFont,"Titre: "+ t.getNomevenement(),"street-name.png"));
                   C4.add(createForFont(smallPlainSystemFont,"Date Start: "+ datdebu,"calendar (1).png"));
                   C4.add(createForFont(smallPlainSystemFont,"Date End: "+ datfin,"calendar (1).png"));
                   C4.add(createForFont(smallPlainSystemFont, " Emplacement De L'évènement: "+t.getLieu(),"placeholder.png"));
                   C4.add(createForFont(smallPlainSystemFont, " Nombre Des Places: "+t.getNombredeplaces(),"chair.png"));
                   C4.add(createForFont(smallPlainSystemFont, " Nombre Des Places Disponible: "+t.getNombredeplacerestante(),"chair.png"));
                   C4.add(createForFont(smallPlainSystemFont, " Description: ","signal.png"));
                    C4.add(createForFontSL(mediumPlainMonospaceFont, t.getDescription(),4));
                    /*SpanLabel spd = new SpanLabel(t.getDescription());
                    C4.add(spd);*/
                   if(t.getValider() == 0)
                   {
                       Label l3 = new Label("En Cour");
                       l3.getAllStyles().setFgColor(0xff0000);
                       C9.add(l3);
                   }
                   else if(t.getValider() == 1)
                   {
                       Label l3 = new Label("Accepter");
                       l3.getAllStyles().setFgColor(0xff0000);
                      C9.add(l3); 
                   }
                   else
                   {
                       Label l3 = new Label("Refuser");
                       l3.getAllStyles().setFgColor(0xff0000);
                       C9.add(l3);
                   }


                 
                  
                  //C10.add(delete);
                  f2.add(C6);
                  f2.add(C5);
                  f2.add(C7);
                  f2.add(C4);
                  f2.add(C8);
                  f2.add(C9);
                  f2.add(C13);
                  f2.add(C10);
                 // f2.add(update);
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
