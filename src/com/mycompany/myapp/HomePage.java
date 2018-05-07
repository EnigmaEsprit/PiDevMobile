/*
<<<<<<< HEAD
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> 05e1c67bc0642b8be995dde47f7c3067083e9511
package com.mycompany.myapp;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Evenements.Client_Liste_Events;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.GUI.Promotions.Client_List_Promotions;
import com.mycompany.GUI.Promotions.Vendeur_List_Promotions;
<<<<<<< HEAD
import com.mycompany.GUI.Reclamations.ConsulterReclamationsVendeur;
import com.mycompany.GUI.Reclamations.SuiviReclamationsClients;
=======
>>>>>>> 05e1c67bc0642b8be995dde47f7c3067083e9511
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.service.Utilisateurs.Util;


/**
<<<<<<< HEAD
*
* @author user
*/
public class HomePage  {
Form home = new Form("Home");

public HomePage() {

    if(Util.connectedUser== null || Util.connectedUser.getRoles().equalsIgnoreCase("[ROLE_CLIENT]"))
            {

     Toolbar tb = home.getToolbar();
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
            if(Util.connectedUser == null)
            {
                               tb.addMaterialCommandToSideMenu("LogIn",FontImage.MATERIAL_LOCK,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            LogIn log = new LogIn();
            log.getConnection().show();
        }
    });
}
            else
            {
                 home.getToolbar().addCommandToOverflowMenu("Reclamation", null, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {

                                SuiviReclamationsClients src = new SuiviReclamationsClients();
                                src.getSuiviReclamation().show();

                            }
                        });

                   home.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {


                                HomePage h = new HomePage();
                                 Util.connectedUser=null;
                                h.getHome().show();
                            }
                        });

            }
            tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Contact h = new Contact();
    h.Contact();
        }
    });
            }
    else
    {
            Toolbar tb = home.getToolbar();
               tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            HomePage h = new HomePage();
   h.getHome().show();
        }
    });
         tb.addMaterialCommandToSideMenu("Evenements",FontImage.MATERIAL_EVENT,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
              Container C4 = new Container(new FlowLayout(Component.CENTER));
                       Vendeur_Liste_Events c = new Vendeur_Liste_Events();
                       c.Vendeur_Liste_Events();        
        }
    }); 
          tb.addMaterialCommandToSideMenu("Promotions",FontImage.MATERIAL_MONEY_OFF,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
             Vendeur_List_Promotions h = new Vendeur_List_Promotions();
    h.getF().show();
        }
    }); 
           tb.addMaterialCommandToSideMenu("Reclamations",FontImage.MATERIAL_CONTACTS,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            ConsulterReclamationsVendeur crv = new ConsulterReclamationsVendeur();
            crv.getConsulterReclamations().show();
        }
    });
           tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Contact h = new Contact();
    h.Contact();
        }
    });
            home.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {


                                HomePage h = new HomePage();
                                 Util.connectedUser=null;
                                h.getHome().show();
                            }
                        });

    }
            }



public Form getHome() {
    return home;
}

public void setHome(Form home) {
    this.home = home;
}


=======
 *
 * @author user
 */
public class HomePage  {
    Form home = new Form("Home");

    public HomePage() {
          
        if(Util.connectedUser== null || Util.connectedUser.getRoles().equalsIgnoreCase("[ROLE_CLIENT]"))
                {
         
         Toolbar tb = home.getToolbar();
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
                if(Util.connectedUser == null)
                {
                                   tb.addMaterialCommandToSideMenu("LogIn",FontImage.MATERIAL_LOCK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LogIn log = new LogIn();
                log.getConnection().show();
            }
        });
    }
                else
                {
                       home.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    HomePage h = new HomePage();
                                     Util.connectedUser=null;
                                    h.getHome().show();
                                }
                            });
                }
                tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Contact h = new Contact();
        h.Contact();
            }
        });
                }
        else
        {
                Toolbar tb = home.getToolbar();
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
                home.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    HomePage h = new HomePage();
                                     Util.connectedUser=null;
                                    h.getHome().show();
                                }
                            });
              
        }
                }
    
    

    public Form getHome() {
        return home;
    }

    public void setHome(Form home) {
        this.home = home;
    }
    
    
>>>>>>> 05e1c67bc0642b8be995dde47f7c3067083e9511
}
