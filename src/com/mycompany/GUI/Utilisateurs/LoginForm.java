/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.utilisateurs;


import Utilities.ToolsUtilities;
import com.codename1.components.InfiniteProgress;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.mycompany.GUI.Panier.PaymentForm;

import com.mycompany.GUI.Produits.HomeProduitsForm;
import com.mycompany.entites.Panier.FonctionPanier;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Panier.PanierService;
import com.mycompany.service.Utilisateurs.UserManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author cobwi
 */
public class LoginForm extends Form {

    private Form main;
    private Form current;
    public static String TOKEN;

    private Form loginForm;

    private Resources theme;
    private Login login;

    TextField username = new TextField("", "Login", 20, TextField.EMAILADDR);
    TextField password = new TextField("", "Password", 20, TextField.PASSWORD);

    public LoginForm() {
       
       main = new Form(new BoxLayout(BoxLayout.Y_AXIS));
       

        theme = UIManager.initFirstTheme("/theme3");
    
        main.setUIID("LoginForm");
         ToolbarForm tbf = new ToolbarForm();
      
             
            tbf.Menu0(main); 
        
        
        Container welcome = FlowLayout.encloseCenter(
                new Label("Souk El Medina, "),
                new Label("Application de vente en ligne, "),
                new Label("des objets artisanaux "),
                createSeparator()
                ,
                new Label(theme.getImage("logoSouk.png").fill(250, 300))
        );

        getTitleArea().setUIID("Container");

      //  Image profilePic = theme.getImage("LOGO_SOUK_noir.png");
        Image mask = theme.getImage("round-mask.png");
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(theme.getImage("logoSouk.png").fill(250, 300));
//        profilePicLabel.setMask(mask.createMask());

        //this.main = f;

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
        password.setConstraint(TextField.PASSWORD);

        username.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
       // loginIcon.getAllStyles().setMargin(RIGHT, 0);
        //passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button signIn = new Button("Log In");
        signIn.setUIID("LoginButton");

        Button TrueSignIn = new Button("Créer un compte");
        //TrueSignIn.setUIID("LoginButton");

        signIn.addActionListener(x -> {
            if (username.getText().length() != 0 && password.getText().length() != 0) {
                logIn();
            } else {
                // popup
                Dialog.show("Erreur de saisie", "Merci de vérifier vos paramétres de connexion", "Ok", null);
            }
        });
        TrueSignIn.addActionListener(x -> {
            
            SignInForm signForm = new SignInForm();
            signForm.getSender().show();
        });

        //Social actionbuttons
        Label loginWFace = new Label(theme.getImage("SignUpFB.png").fill(400, 100));
          Label loginWG = new Label(theme.getImage("google-sign-in.png").fill(400, 100));
        //loginWFace.setUIID("LoginButton");
        Container socialContainer = FlowLayout.encloseCenter(
                loginWFace,
               loginWG,
                
                createSeparator()
             
        );
        loginWFace.addPointerPressedListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                //create your own app identity on facebook follow the guide here:
                //facebook-login.html
                String clientId = "160701677931552";
                String redirectURI = "https://www.codenameone.com/";
                String clientSecret = "dfe9f991aaf7267fa0c70a9f79295004";
                //String clientSecret = "2532a457d1ca5c67196df97f9089799d";

                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                login = fb;

                fb.setCallback(new LoginListener(LoginListener.FACEBOOK));
                if (!fb.isUserLoggedIn()) {
                    fb.doLogin();
                } else {
                    showFacebookUser(fb.getAccessToken().getToken());
                }
            }
        });
        //Button loginWG = new Button(theme.getImage("google-sign-in.png"));
        //loginWG.setUIID("LoginButton");
        
      
        loginWG.addPointerPressedListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //create your own google project follow the guide here:
                //http://www.codenameone.com/google-login.html
                //String clientId = "839004709667-n9el6dup3gono67vhi5nd0dm89vplrka.apps.googleusercontent.com";
                String clientId = "981240080975-6b5mclv8n9uh23hsppmnb2stga61uq9d.apps.googleusercontent.com";
                String redirectURI = "https://www.codenameone.com/oauth2callback";
                String clientSecret = "P9inMyx-UdmQo9bhEyO8B1Tu";

                Login gc = GoogleConnect.getInstance();
                gc.setClientId(clientId);
                gc.setRedirectURI(redirectURI);
                gc.setClientSecret(clientSecret);
                login = gc;
                gc.setCallback(new LoginListener(LoginListener.GOOGLE));
                if (!gc.isUserLoggedIn()) {
                    gc.doLogin();
                } else {
                    showGoogleUser(gc.getAccessToken().getToken());
                }
            }
        });
        

        if (FacebookConnect.getInstance().isFacebookSDKSupported()) {
            if (!FacebookConnect.getInstance().isLoggedIn()) {
                FacebookConnect.getInstance().setCallback(new LoginCallback() {
                    public void loginSuccessful() {
                        updateLoginPhoto();
                    }
                });
                FacebookConnect.getInstance().login();
            }
        } else {
            //            LoginForm.login(main);
            //            updateLoginPhoto();
        }

        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container pp = new Container(new BorderLayout()
        );
        pp.add(BorderLayout.CENTER,profilePicLabel);
//         Container corps = FlowLayout.encloseCenter(profilePicLabel);
        Container by = BoxLayout.encloseY(
                welcome,
                /*
                spaceLabel,*/
                BorderLayout.center(username).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                signIn,
                TrueSignIn,
                socialContainer
        );
        //add(BorderLayout.CENTER, by);

        
        by.setScrollableY(true);
        by.setScrollVisible(false);
        
        main.add(by);

    }

     private Label createSeparator() {
        Label sep = new Label();
        sep.setUIID("Separator"); // the separator line  is implemented in the theme using padding and background color, by default labels    // are hidden when they have no content, this method disables that behavior    
        sep.setShowEvenIfBlank(true);
        return sep;
    }
    private void logIn(/*final Form main*/) {

        ConnectionRequest req = new ConnectionRequest();
        //req.setUrl(ToolsUtilities.ServerIp + ":" + ToolsUtilities.port + "/piwebService/webresources/com.mycompany.piwebservice.user/login/" + username.getText() + "");
        req.setUrl(ToolsUtilities.ServerIp + ":" + ToolsUtilities.port + "/PiDevWebMobile/web/Users/all/" + username.getText() + "");
        System.out.println("REQU : " + req.getUrl());
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                byte[] data = (byte[]) req.getResponseData();
                String s = new String(data);
                User user = mapToJson(new String(req.getResponseData()));
                String pwd = StringUtil.replaceAll(user.getPassword(), "$2y", "$2a");
                System.out.println("passs "+pwd);
                System.out.println("\n\n"+password.getText());
              //  System.out.println(BCrypt.checkpw(password.getText(), pwd));
                if (BCrypt.checkpw(password.getText(), pwd) == true) {
                    User.setActifUser(user);
                    User.setIdOfConnectedUser(user.getId());
                    //redirect 
                    //main.updateLoginPhoto();a
  /////////////////////////////                  AcceuilForm acceuil = new AcceuilForm(current);
        /////////////////////////            acceuil.show();
        
        //if(!User.UserConnectedPayment){
             HomeProduitsForm hpf = new HomeProduitsForm();
                    hpf.getF().show();
                    /*}else{
                    PaymentForm pf = new PaymentForm();
                    pf.getPsdTutorial().show();
                    
                    }*/
                   
                    

                    //  main.show();
                } else {
                    System.out.println("FAILED");
                    Dialog.show("Erreur", "Merci de vérifier vos paramétres de connexion", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);
        /*
        InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                req.setDisposeOnCompletion(dlg);
         */
    }

    private void signIn() {

    }

    public Form getMain() {
        return main;
    }

    public void setMain(Form main) {
        this.main = main;
    }
    

    public void updateLoginPhoto() {

       User user = User.getActifUser();
        //Image src = (Image) user.getPhotoProfil();
        System.out.println(user.getPhotoProfil());

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100), true);
        Storage.getInstance().deleteStorageFile("imgprofile");
        Image src = URLImage.createToStorage(placeholder, "imgprofile", ToolsUtilities.ServerIp + user.getPhotoProfil(), URLImage.RESIZE_SCALE);

        if (src != null) {
            Command pc = new Command("", src) {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Lets do nothign");
                }
            };
            main.addCommand(pc, main.getCommandCount());
        }
    }

    private void showFacebookUser(String token) {
        
        ConnectionRequest req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl("https://graph.facebook.com/v2.3/me");
        req.addArgumentNoEncoding("access_token", token);
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        NetworkManager.getInstance().addToQueueAndWait(req);
        byte[] data = req.getResponseData();
        JSONParser parser = new JSONParser();
        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String name = (String) map.get("name");
        d.dispose();

        User socialUser = new User();
        try {
            socialUser.setLogin(name);
        } catch (Exception ex) {

        }

        try {
            List<String> tokenzed = StringUtil.tokenize(name, " ");
            System.out.println("tokenzed = " + tokenzed);
            socialUser.setNom(tokenzed.get(0));
            socialUser.setPrenom(tokenzed.get(1));
        } catch (Exception ex) {

        }

        try {
            socialUser.setSocialid((String) map.get("id"));
        } catch (Exception ex) {

        }

        try {
            socialUser.setPhotoProfil("https://graph.facebook.com/v2.3/me/picture?access_token=" + token);
        } catch (Exception ex) {

        }

        // check existance before socialSignIn
        UserManager um = new UserManager();
        User user = um.checkExistanceBySocialId(socialUser.getSocialid());

        System.out.println("SOCIAL USER : " + user);
        if (user != null) {
            User.setActifUser(user);
            User.setIdOfConnectedUser(user.getId());
           // AcceuilForm acceuil = new AcceuilForm(current);
           // acceuil.show();
           
           HomeProduitsForm hpf = new HomeProduitsForm();
           hpf.getF().show();
        } else {
            SocialSignInForm socialSign = new SocialSignInForm( socialUser);
            socialSign.getSender().show();
        }

        //invoke Social Sign iN
        /*
        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), "https://graph.facebook.com/v2.3/me/picture?access_token=" + token);
        userForm.show();
         */
    }

    private void showGoogleUser(String token) {

        //Submit this newly created user
        ConnectionRequest req = new ConnectionRequest();
        req.addRequestHeader("Authorization", "Bearer " + token);
        req.setUrl("https://www.googleapis.com/plus/v1/people/me");
        req.setPost(false);
        InfiniteProgress ip = new InfiniteProgress();
        Dialog d = ip.showInifiniteBlocking();
        NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
        byte[] data = req.getResponseData();
        JSONParser parser = new JSONParser();

        Map map = null;
        try {
            map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //String name = (String) map.get("displayName");
        Map im = (Map) map.get("image");
        String url = (String) im.get("url");
        ArrayList email = (ArrayList) map.get("emails");
        System.out.println("MAP" + map);
        System.out.println("email = " + email);
        LinkedHashMap nomprn = (LinkedHashMap) map.get("name");
        System.out.println("nomprn = " + nomprn);

        User socialUser = new User();
        try {
            //String[] compo = name.split(" ");
            socialUser.setLogin(map.get("displayName").toString());
        } catch (Exception ex) {

        }

        try {
            Map mapMail = (Map) email.get(0);
            System.out.println("mapMail");
            socialUser.setEmail(mapMail.get("value").toString());
        } catch (Exception ex) {

        }

        try {
            socialUser.setNom(nomprn.get("givenName").toString());
            socialUser.setPrenom(nomprn.get("familyName").toString());
        } catch (Exception ex) {

        }

        try {
            socialUser.setSocialid((String) map.get("id"));
        } catch (Exception ex) {

        }

        try {
            socialUser.setPhotoProfil(url);
        } catch (Exception ex) {
        }

        System.out.println("PRINTED USERRRR" + socialUser);

        // check existance before socialSignIn
        UserManager um = new UserManager();
        User user = um.checkExistanceBySocialId(socialUser.getSocialid());
        System.out.println("SOCIAL USER : " + user);
        if (user != null) {
            User.setActifUser(user);
            User.setIdOfConnectedUser(user.getId());
            AcceuilForm acceuil = new AcceuilForm(current);
            acceuil.show();
        } else {
            SocialSignInForm socialSign = new SocialSignInForm( socialUser);
            socialSign.getSender().show();
        }/*
        Form userForm = new UserForm(name, (EncodedImage) theme.getImage("user.png"), url);
        userForm.show();
         */
    }

    public class LoginListener extends LoginCallback {

        public static final int FACEBOOK = 0;

        public static final int GOOGLE = 1;

        private int loginType;

        public LoginListener(int loginType) {
            this.loginType = loginType;
        }

        public void loginSuccessful() {

            try {
                AccessToken token = login.getAccessToken();
                if (loginType == FACEBOOK) {
                    showFacebookUser(token.getToken());
                } else if (loginType == GOOGLE) {
                    showGoogleUser(token.getToken());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void loginFailed(String errorMessage) {
            Dialog.show("Login Failed", errorMessage, "Ok", null);
        }
    }

    /*
    *Version 2 catched errors
     */
    public User mapToJson(String json) {
        User user = new User();
        System.out.println("PRINT in case of error " + json);
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println(users);
            //userId = Integer.valueOf(users.get("id").toString().substring(0, users.get("id").toString().indexOf('.')));
          float  userId= Float.parseFloat(users.get("id").toString());
            if (users != null) {
                user.setId((int)userId);
                user.setPassword(users.get("password").toString());
                user.setLogin(users.get("username").toString());
                try {
                   user.setDateNaissance(ToolsUtilities.formater.parse(users.get("datedenaissance").toString()));
                } catch (ParseException ex) {

                }
                user.setEmail(users.get("email").toString());
                user.setEnabled(users.get("enabled").toString().equals("true") ? 1 : 0);
                user.setNom(users.get("nom").toString());
                user.setPrenom(users.get("prenom").toString());
                
                 if(users.get("roles").toString().isEmpty()){
                                user.setRoles("[ROLE_CLIENT]");
                           }else{
                               user.setRoles(users.get("roles").toString());
                           }
                 
                try {
                    user.setPhotoProfil(users.get("imageuser").toString());
                    System.out.println("profile" + user.getPhotoProfil());
                } catch (Exception ex) {

                }

            }
        } catch (Exception ex) {
            System.out.println("Invalid JsonResponse : " + json);
        }
        return user;

    }

    public static boolean firstLogin() {
        return Storage.getInstance().readObject("token") == null;
    }

    public static void login(final Form form) {
        if (firstLogin()) {
            LoginForm logForm = new LoginForm();
            logForm.getMain().show();
        } else {
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    //token has expired
                    if (code == 400) {
                        LoginForm logForm = new LoginForm();
            logForm.getMain().show();
                    }
                }
            });
        }
    }

}
