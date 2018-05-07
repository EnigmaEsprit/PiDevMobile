/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.utilisateurs;


import Utilities.ToolsUtilities;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Utilisateurs.UserManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 *
 * @author cobwi
 */
public class SignInForm extends Form {

    private Form sender;
    private static Form current;
    public static String TOKEN;

    private Resources theme;
    private Login login;
    final TextField username = new TextField();
    final TextField email = new TextField();
    final TextField nom = new TextField();
    final TextField prenom = new TextField();
    final TextField password = new TextField();
    final Picker datePick = new Picker();

    public SignInForm(/*Form f*/) {
        //super("Sign In Form");
       // super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
       // this.current = this;

       sender = new Form("Sing In",new BoxLayout(BoxLayout.Y_AXIS));
       Toolbar tb = sender.getToolbar();
        theme = UIManager.initFirstTheme("/theme3");
       // UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
 ToolbarForm tbf = new ToolbarForm();
       
             
            tbf.Menu0(sender); 
        
        
        //this.sender = f;
        sender.setUIID("SigninForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Bienvenu !"),
                new Label("Inscrivez vous"),
                new Label(" en un clic")
        );

        this.setToolbar(tb);
        this.getToolbar().addCommandToLeftBar("Back", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // sender.show();
                LoginForm lf = new LoginForm();
                lf.getMain().show();
            }
        });

        this.setLayout(new BorderLayout());
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("ContainerWithPadding");

        Image logo = theme.getImage("CodenameOne.png");
        Label l = new Label(logo);
        Container flow = new Container(new FlowLayout(Component.CENTER));
        flow.addComponent(l);
        center.addComponent(flow);

        username.setHint("Username");
        password.setHint("Password");
        email.setHint("Email");
        nom.setHint("Nom");
        prenom.setHint("Prenom");

        password.setConstraint(TextField.PASSWORD);

        username.getAllStyles().setMargin(LEFT, 0);
       // password.getAllStyles().setMargin(LEFT, 0);

        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
       // loginIcon.getAllStyles().setMargin(RIGHT, 0);
        //passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Label emailIcon = new Label("", "TextField");
        Label nameIcon = new Label("", "TextField");
        Label prnIcon = new Label("", "TextField");
      //  emailIcon.getAllStyles().setMargin(RIGHT, 0);
       // nameIcon.getAllStyles().setMargin(RIGHT, 0);
       // prnIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(nameIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(prnIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Button signIn = new Button("S'enregistrer");
        signIn.addActionListener(x -> {
            signIn();
        });

        Container by = BoxLayout.encloseY(
                welcome,
                spaceLabel,
                BorderLayout.center(username).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(nom).
                        add(BorderLayout.WEST, nameIcon),
                BorderLayout.center(prenom).
                        add(BorderLayout.WEST, prnIcon),
                datePick,
                signIn
        );
       // add(BorderLayout.CENTER, by);

        by.setScrollableY(true);
        by.setScrollVisible(false);
        
        sender.add(by);
    }

    private void signIn() {
// perform some checks
//http://localhost/piproj/web/app_dev.php/new/user/ly/ly/ly@ly.com/ly/ly/1993-08-09

        UserManager em = new UserManager();
        System.out.println("ok");
        User user = new User(6,
                username.getText(),
                nom.getText(),
                prenom.getText(),
                ToolsUtilities.formater.format(datePick.getDate()),
                email.getText(),
                password.getText()
        );
 System.out.println("ok");
        User persistedUser = em.persist(user);
persistedUser =user;
        System.out.println(persistedUser);
        if (persistedUser != null) {
            user.setDateNaissance(datePick.getDate());

            User.setActifUser(user);
            User.setIdOfConnectedUser(user.getId());
            //redirect to Acceuil 
          //  AcceuilForm acceuil = new AcceuilForm(current);
           // acceuil.show();
           
            HomeProduitsForm hpf = new HomeProduitsForm();
            hpf.getF().show();
        } else {
            //stay here
        }
//

    }

    public Form getSender() {
        return sender;
    }

    public void setSender(Form sender) {
        this.sender = sender;
    }

    
    public User mapToJson(String json) {
        User user = new User();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println(users);
            int userId = Integer.valueOf(users.get("id").toString().substring(0, users.get("id").toString().indexOf('.')));
            if (users != null) {
                user.setId(userId);
                user.setPassword(users.get("password").toString());
                user.setLogin(users.get("username").toString());
                try {
                    user.setDateNaissance(ToolsUtilities.formater.parse(users.get("datenaissance").toString()));
                } catch (ParseException ex) {

                }
                user.setEmail(users.get("email").toString());
                user.setEnabled(users.get("enabled").toString().equals("true") ? 1 : 0);
                user.setNom(users.get("nom").toString());
                user.setPrenom(users.get("prenom").toString());
                user.setPhotoProfil(users.get("photo_profile").toString());

                System.out.println("profile" + user.getPhotoProfil());

            }
        } catch (IOException ex) {
        }
        return user;

    }

}
