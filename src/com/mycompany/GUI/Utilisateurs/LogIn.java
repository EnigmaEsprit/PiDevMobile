/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Utilisateurs;


import com.codename1.components.InfiniteProgress;
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
import com.codename1.util.StringUtil;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_Evenet;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.GUI.Promotions.Client_List_Promotions;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import com.mycompany.service.Utilisateurs.Util;
import org.mindrot.jbcrypt.BCrypt;



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
   private static int workload = 12;
   private boolean existe=false;
    public LogIn()
    {
         connection.getStyle().setBgColor(0xE6E6E6);
     
         InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
                    
         Toolbar tb = connection.getToolbar();
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
                 Client_Liste_Events h = new Client_Liste_Events();
        h.getF().show();
            }
        }); 
                tb.addMaterialCommandToSideMenu("Promotion",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Client_List_Promotions h = new Client_List_Promotions();
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
   
                tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Contact h = new Contact();
        h.Contact();
            }
        });
                             tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                             HomePage h = new HomePage();
       h.getHome().show();
                       }
                   });
         Button bt =new Button("back");
         Label lab = new Label("Bonjour!");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField id = new TextField(null,"Username");
  
        TextField pass = new TextField(null,"Password");
        pass.setConstraint(TextField.PASSWORD);
          

         Button conn = new Button("LogIn");
       
        conn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();

             
               String username =id.getText();
                String password = pass.getText();
                
                
                       ServiceUsers sc = new ServiceUsers();
                
                System.out.println();
                     for(User t :sc.getList2())
                        {
                            System.out.println(t);
                String data = password;
                String pwd = StringUtil.replaceAll(t.getPassword(), "$2y", "$2a");
                
            

                            System.out.println(t.getPassword());
                            System.out.println(pwd);
                            System.out.println(t.getUsername().equalsIgnoreCase(username));
                            System.out.println(t.getRoles().equalsIgnoreCase("[ROLE_CLIENT]"));
                           // System.out.println(BCrypt.checkpw(password, pwd));
                            System.out.println(t.getRoles());
                            System.out.println( t.getRoles().equalsIgnoreCase("[ROLE_VENDEUR]"));
                  
                            System.out.println(existe+"1");
                    try {
                      
                       if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_CLIENT]")&&BCrypt.checkpw(password, pwd) == true)
                       {
                           /*Vendeur_Evenet ve = new Vendeur_Evenet();
                            ve.getFv().show(); */
                           
                           existe=true;
                           System.out.println(existe+"2");
                           System.out.println("SUCCESS " + password);
                  
                            Util.connectedUser=t;
                            System.out.println(Util.connectedUser);

                            
                           
                       }
                       else if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_VENDEUR]")&&BCrypt.checkpw(password, pwd) == true)
                       {
                             existe=true;
                             System.out.println(existe+"3");
                           Util.connectedUser=t;
                           System.out.println(Util.connectedUser);
                        

                           

                       }
                      
                 
    } catch (Exception ex) {
    }


                        }
                     System.out.println(existe+"4");
                     if(existe)
                     {
                          System.out.println("SUCCESS " + password);
                           d.dispose();
                           HomePage h = new HomePage();
                           h.getHome().show();
                     }
                     else
                     {
                          d.dispose();
                             Dialog.show("Stop", "FAILED"
                                      + "", "OK", null);

                             System.out.println("FAILED"+ password);
                     }
                
               
            }
        });
        c.add(id);
        c.add(pass);
        c.add(conn);
       
        connection.add(c);
        d.dispose();
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
