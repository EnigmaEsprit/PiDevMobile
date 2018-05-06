/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
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
import com.codename1.util.StringUtil;
import com.mycompany.entites.Utilisateurs.Users;
import com.mycompany.service.Utilisateurs.ServiceUsers;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ivan Landry ONANA
 */
public class HomeForm {
    private Form connection = (Form) new Form("Login",new FlowLayout(Component.CENTER, Component.CENTER));
    private boolean existe = false;
    private String role;

    public HomeForm() {
        Toolbar tb = connection.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            connection.show();
            ServiceUsers.connectedUser = null;
        });
        
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField id = new TextField(null,"Username");  
        TextField pass = new TextField(null,"Password");
        pass.setConstraint(TextField.PASSWORD);
        Button conn = new Button("Se connecter");
       
        conn.addActionListener((ActionListener) (ActionEvent evt) -> {
            InfiniteProgress ip1 = new InfiniteProgress();
            Dialog d1 = ip1.showInifiniteBlocking();
            String username =id.getText();
            String password = pass.getText();
            ServiceUsers sc = new ServiceUsers();
            System.out.println();
            for(Users t : sc.getListeUtilisateurs())
            {
                String pwd = StringUtil.replaceAll(t.getPassword(), "$2y", "$2a");          
                try {
                    
                    if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_CLIENT, ROLE_USER]")&&BCrypt.checkpw(password, pwd) == true)
                    {
                        existe = true;
                        role = t.getRoles();
                        System.out.println("SUCCESS " + password);                        
                        ServiceUsers.connectedUser = t;
                        System.out.println(ServiceUsers.connectedUser);
                    }
                    
                    else if(t.getUsername().equalsIgnoreCase(username) && t.getRoles().equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]")&&BCrypt.checkpw(password, pwd) == true)
                    {
                        existe = true;
                        role = t.getRoles();
                        System.out.println("SUCCESS " + password);                        
                        ServiceUsers.connectedUser = t;
                        System.out.println(ServiceUsers.connectedUser);
                    }
                } catch (Exception ex) {
                }
            }
            if (existe && role.equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]")) {
                d1.dispose();
                ConsulterReclamationsVendeur con = new ConsulterReclamationsVendeur();
                con.getConsulterReclamations().show();
            } 
            else if (existe && role.equalsIgnoreCase("[ROLE_CLIENT, ROLE_USER]")) {
                d1.dispose();
                EnvoiReclamationClient env = new EnvoiReclamationClient();
                env.getEnvoiReclamation().show();                        
            } else {
                d1.dispose();
                Dialog.show("Stop", "FAILED"
                        + "", "OK", null);
                System.out.println("FAILED "+ password);
            }
        });
        c.add(id);
        c.add(pass);
        c.add(conn);
       
        connection.add(c);
        d.dispose();
    }

    public Form getAccueil() {
        return connection;
    }

    public void setAccueil(Form accueil) {
        this.connection = accueil;
    }
    
}
