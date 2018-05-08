/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractClass;


import com.mycompany.GUI.utilisateurs.LoginForm;

import Utilities.ToolsUtilities;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;

import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.GUI.utilisateurs.ProfileForm;
import com.mycompany.entites.Utilisateurs.User;
import java.io.IOException;

/**
 *
 * @author cobwi
 */
public abstract class AbstractForm extends Form {

    private Form sender;
    public static String TOKEN;
    private Resources theme;
    private Image userPicture = null;

    public AbstractForm(/*String title, Form sender*/) {
       // super(title);
        theme = UIManager.initFirstTheme("/theme3");
        //UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        sender = new Form(new BorderLayout());
       // this.sender = sender;
        //this.setLayout(new BorderLayout());
 //this.setScrollable(false);

        //Storage.getInstance().clearCache();
        Storage.getInstance().deleteStorageFile("imgprofile");
        EncodedImage placeholder = EncodedImage.createFromImage(theme.getImage("fbuser.jpg"), true);

        Image src = null;
        try {
            String imgLink = User.getActifUser().getPhotoProfil();

            if (imgLink.indexOf("http") > -1) {
                src = URLImage.createToStorage(placeholder, "imgprofile", User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            } else {
                src = URLImage.createToStorage(placeholder, "imgprofile", ToolsUtilities.ServerIp + User.getActifUser().getPhotoProfil(), URLImage.RESIZE_SCALE);
            }
        } catch (NullPointerException ne) {
            src = URLImage.createToStorage(placeholder, "imgprofile", "", URLImage.RESIZE_SCALE);
            System.out.println("Photo null");
        }
        userPicture = src;

        final Command profileCommand = new Command("My Profile", userPicture) {
            public void actionPerformed(ActionEvent evt) {
                // updateLoginPhoto();

                ProfileForm profileForm = new ProfileForm();
                profileForm.getProfile().show();

             
            }
        };

        this.addCommand(profileCommand);

       

      
    

        this.addCommand(new Command("Exit") {

            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().exitApplication();
            }
        });
        this.addCommand(new Command("Logout") {
            public void actionPerformed(ActionEvent evt) {
                if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
                    FacebookConnect.getInstance().logout();
                } else {
                    LoginForm logForm = new LoginForm();
            logForm.getMain().show();
                }
            }
        });
    }

    public Form getSender() {
        return sender;
    }

    public void setSender(Form sender) {
        this.sender = sender;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        AbstractForm.TOKEN = TOKEN;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

}
