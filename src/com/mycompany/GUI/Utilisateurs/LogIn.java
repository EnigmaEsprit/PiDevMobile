/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Utilisateurs;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_Evenet;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Utilisateurs.Users;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import com.mycompany.service.Utilisateurs.Util;



/**
 *
 * @author user
 */
public class LogIn {
     private Form current;
    private Resources theme;
private Form connection = (Form) new Form("LogIn",new FlowLayout(Component.CENTER, Component.CENTER));

    private ConnectionRequest cr;
    private String msg;
   
    public LogIn()
    {
         
         Form page2Form = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
         
           page2Form.getStyle().setBgColor(0xE6E6E6);         
         Toolbar tb = connection.getToolbar();
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
                             tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           Container C4 = new Container(new FlowLayout(Component.CENTER));
                           Client_Liste_Events c = new Client_Liste_Events();
                           c.getF().show();                       }
                   });
         Button bt =new Button("back");
         Label lab = new Label("Bonjour!");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField id = new TextField(null,"Username");
       // id.getHintLabel().setUIID("Username");
        TextField pass = new TextField(null,"Password");
        pass.setConstraint(TextField.PASSWORD);
          
       // pass.getHintLabel().setUIID("Password");
         Button conn = new Button("LogIn");
          // cr = new ConnectionRequest(url);
               // cr.setPost(false);
        // conn.setPreferredW(500);
        conn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
               String username =id.getText();
                String password = pass.getText();
                       ServiceUsers sc = new ServiceUsers();
                
                System.out.println();
                     for(Users t :sc.getList2())
                        {
                            System.out.println(t);
                String data = password;
                  
                    try {
                      
                       if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_CLIENT, ROLE_USER]"))
                       {
                           /*Vendeur_Evenet ve = new Vendeur_Evenet();
                            ve.getFv().show(); */
                           Client_Liste_Events h = new Client_Liste_Events();
                            Util.connectedUser=t;
                            h.getF().show();
                           
                       }
                       else if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]"))
                       {
                           Vendeur_Liste_Events ve = new Vendeur_Liste_Events();
                            Util.connectedUser=t;
                           ve.Vendeur_Liste_Events();
                        

                           

                       }
                       else
                       {
                           System.out.println("impossible");
                       }
                 
    } catch (Exception ex) {
    }


                        }
                
               
            }
        });
        c.add(id);
        c.add(pass);
        c.add(conn);
        connection.add(c);
        connection.show();
        
         
    }

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }

    public Form getConnection() {
        return connection;
    }

    public void setConnection(Form connection) {
        this.connection = connection;
    }
    
    
}
