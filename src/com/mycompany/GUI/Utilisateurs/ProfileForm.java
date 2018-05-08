package com.mycompany.GUI.utilisateurs;

import AbstractClass.AbstractForm;
import Utilities.ToolsUtilities;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;

import java.io.IOException;
import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cobwi
 */
public class ProfileForm  {

    
    Form profile ;
     private Resources theme;

    public ProfileForm(/*Form f*/) {
        profile = new Form(BoxLayout.y());
      //  profile.add(showMyProfile());
          theme = UIManager.initFirstTheme("/theme3");
   
           ToolbarForm tbf = new ToolbarForm();
        if(User.getActifUser()!= null)
        System.out.println(User.getActifUser().getRoles().toString());
   if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_USER]")){
            tbf.Menu(profile); 
        }else if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]"))
                {
                    tbf.Menu2(profile);
                }
         
        
        System.out.println("PHOTOTOOO " + User.getActifUser().getPhotoProfil());
        Container c = new Container(new BorderLayout());
        BorderLayout bl = new BorderLayout();
        bl.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE);
        Container p = new Container(bl);
        p.addComponent(BorderLayout.CENTER, new InfiniteProgress());
        c.addComponent(BorderLayout.CENTER, p);

        User user = User.getActifUser();

        user.setPhotoProfil("fbuser.jpg");
        int leftCol = Display.getInstance().getDisplayWidth() / 3;
        ComponentGroup gr = new ComponentGroup();
        gr.setLayout(new GridLayout(4, 1));
        System.out.println("USER TO LOAD PROFILE : " + user);
        gr.addComponent(getPairContainer("Name", user.getNom(), leftCol));
        gr.addComponent(getPairContainer("Prenom", user.getPrenom(), leftCol));
        gr.addComponent(getPairContainer("Email", user.getEmail(), leftCol));
       // gr.addComponent(getPairContainer("Birthday", ToolsUtilities.formater.format(user.getDateNaissance()), leftCol));

        c.removeAll();
        c.addComponent(BorderLayout.CENTER, gr);

        //Image i = getTheme().getImage("fbuser.jpg");
        Container imageCnt = new Container(new BorderLayout());
        
        Image src;
       

        Label myPic = new Label(theme.getImage("fbuser.jpg"));

        myPic.addPointerPressedListener((evt) -> {
            Display.getInstance().openImageGallery(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    if (evt == null) {
                        return;
                    }
                    String filename = (String) evt.getSource();

                }
            });
        });

        imageCnt.addComponent(BorderLayout.NORTH, myPic);
        c.addComponent(BorderLayout.EAST, imageCnt);
        
        profile.add(c);
       // c.revalidate();

    }

    

    public Form getProfile() {
        return profile;
    }

    public void setProfile(Form profile) {
        this.profile = profile;
    }

    
    private Container getPairContainer(String key, String val, int padding) {
        Label keyLabel = new Label(key);
        keyLabel.setUIID("Header");
        keyLabel.setPreferredW(padding);
        keyLabel.getStyle().setAlignment(Component.RIGHT);
        Label valLabel = new Label(val);
        valLabel.getStyle().setAlignment(Component.LEFT);
        Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cnt.addComponent(keyLabel);
        cnt.addComponent(valLabel);
        return cnt;
    }

}
