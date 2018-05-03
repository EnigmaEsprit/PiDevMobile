/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;

import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Evenements.NavigatorData;
import com.mycompany.entites.Participations.Participations;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Participations.ServiceParticipations;
import com.mycompany.service.Utilisateurs.Util;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author user
 */
public class detail {
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
    Button map;
     Button update;
     Container C4 ;
     Button partager = new Button( theme.getImage("facebook.png").fill(60, 60));
      private static final String HTML_API_KEY = "AIzaSyDcwHdDoI65jU6iHlOGv54Efo67fE_AWw0";
       static Location lastKnownLocation;
                       
static String address = "The White House, Washington DC";


      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
             private static final String MAPS_KEY = "AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI"; // Your maps key here


    public detail() {
    }
     public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
            
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
    
    public void detail(Evenements t) throws ParseException
    {
        
             f2=new Form("Detail");
                
  f2.getStyle().setBgColor(0xE6E6E6);
            /* Tabs tabs = new Tabs();
             MapContainer map = new MapContainer();
             tabs.addTab("Tab 1",map);*/
        if(MAPS_KEY == null) {
    f2.add(new SpanLabel("This demo requires a valid google API key to be set in the constant apiKey, "
            + "you can get this key for the webservice (not the native key) by following the instructions here: "
            + "https://developers.google.com/places/web-service/get-api-key"));
    f2.getToolbar().addCommandToRightBar(MAPS_KEY, null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
  
    return;
}
         try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {
                       
                    }
   
                     Toolbar tb = f2.getToolbar();
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
   
                   f2.getAllStyles().setPadding(0,0,0,0);
                      f2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                      int deviceWidth = Display.getInstance().getDisplayWidth();
                      
                  tb = f2.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           C4 = new Container(new FlowLayout(Component.CENTER));
                           Client_Liste_Events c = new Client_Liste_Events();
                           c.getF().show();                       }
                   });
                   
                                      tb.addMaterialCommandToSideMenu("LogIn",FontImage.MATERIAL_LOCK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LogIn log = new LogIn();
                log.getConnection().show();
            }
        });
                                   
               
   
                   if(Util.connectedUser != null)
        {
           
                    f2.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    LogIn l = new LogIn();
                                     Util.connectedUser=null;
                                    l.getConnection().show();
                                   
                                }
                            });
        }
                    partager.setUIID("LoginButton");
                     partager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                String accessToken = "EAACEdEose0cBAHSxhOgDr8HGSk22Hnu2OhVQb8ePjKbs00SV3fnCVyqFVJ4bo71RR6tGpngByEPB7tkEetMLo7YUWOfQ29A9phhm5AkeeSTGYNQZC6asUlGOpUidSNXksIRH2mZC64FjCPZCeBUNoTQ3SjpxZBKLBIMkYNxZBQY5VI5Yp1vWA4Q8z9b6inxOmsb8F9d3TZC50h9uSSTN3yfnoR3bmfb8YZD";
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                /*FacebookType responce = fbClient.publish("me/feed",FacebookType.class,Parameter.with("message", "Java Graph API Test"));
                System.err.println("fb.com/"+responce.getId());*/
                   FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                Parameter.with("message","Evenement publié pour un départ le   "
                +t.getDate().toString()+" A "+t.getDatefin().toString()+" avec un prix de   "+t.getTarifevenement()+" TND/personne, pour "+t.getNombredeplaces()+" places. Voici d'autre details: "),
               Parameter.with("link", "http://127.0.0.1/pidevweb/web/"+t.getId()+"/show"));
                System.out.println("fb.com/"+response.getId());
                Dialog.show("Succes", "Votre Evenement à été partagé sur facebook", "Fermer", null);
                
            }
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
                  Container C9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                  Container C13 = new Container(new FlowLayout(Component.CENTER));
                  Container C10 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                  Container C11 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                  Container C12 = new Container(new FlowLayout(Component.CENTER));

                   System.out.println(getSaltString());
                   System.out.println(t+"+++++++++");
                   System.out.println(t.getNomevenement());
                   System.out.println("http://localhost/pidevweb/web/uploads/Images/"+t.getImage()+"");
                   C6.add(new ImageViewer(URLImage.createToStorage(enc,t.getNomevenement(), "http://localhost/pidevweb/web/uploads/Images/"+t.getImage()+"", URLImage.RESIZE_SCALE).fill(1000, 850)));
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


                  C8.add(new SpanLabel(t.getDescription()));
                  Date datec = new Date();
                                    String dateco = formater.format( datec);
                                    
                                    Date dateco2 = formater.parse(dateco);
                                    Date datedebutp = formater.parse(datdebu);
                                        
                                    System.out.println(dateco);

                  System.out.println(dateco2.getTime()+"----------++++");
                  System.out.println(datedebutp.getTime()+"////////");
                  System.out.println((int)(datdebu.compareTo(dateco)));
                  //int rec =t.getDate().compareTo(datec);
                  if(t.getNombredeplacerestante() > 0 && (dateco2.getTime()<(datedebutp.getTime() )) )
                  {
                    C9.add(participation);  
                  }
                  else if((dateco2.getTime()>(datedebutp.getTime() )))
                  {
                       Label compler = new Label("Evenement Expiré");
                   compler.getAllStyles().setFgColor(0xff0000);
                    C13.add(compler); 
                  }
                  else
                  {
                       Label compler = new Label("Compler");
                   compler.getAllStyles().setFgColor(0xff0000);
                    C13.add(compler); 
                      
                  }
                  
                  //C10.add(delete);
                 // f2.add(tabs);
                   final MapContainer cnt = new MapContainer();
                 
                
                 Button btnMoveCamera = new Button("Move Camera");
                 btnMoveCamera.addActionListener(e->{
                 cnt.setCameraPosition(new Coord(-33.8688, 151.2195));
                 });
                 Style s = new Style();
                 s.setFgColor(0xFF7F50);
                 s.setBgTransparency(0);
                 FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
               MapContainer cn = new MapContainer();
               
               cn.zoom(getCoords(t.getLieu()), 18);
            
                cn.setCameraPosition(getCoords(t.getLieu())); // since the image is iin the jar this is unlikely
                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords(t.getLieu()), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
       
       
               
// });
/*Container root = LayeredLayout.encloseIn(
BorderLayout.center(cn),
BorderLayout.south(
FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker, btnAddPath, btnClearAll)
)
);*/           ;
                 //f2.add( root);
                  
                  f2.add(C6);
                  f2.add(C5);
                  f2.add(C7);
                  f2.add(C4);
                  f2.add(C8);
                  Label sh = new Label("Share:");
                  C11.add(sh).add(partager);
                  f2.add(C11);
                  f2.add(C9);
                  f2.add(C13);
              
                  
       
                  f2.add(C10);
                  
                  cn.getStyle().setBorder(Border.createLineBorder(2));
                 
                  f2.add(cn);
                  f2.show();
                             
                  
                  delete.addActionListener((e) -> {
            ServiceEvenements ser = new ServiceEvenements();
           
            
           
           f.show();
            

        });  
                  participation.addPointerPressedListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   if(Util.connectedUser != null)
                   {
                   boolean exist=false;
                   ServiceParticipations sc = new ServiceParticipations();
                   for(Participations tp :sc.getList2())
                    {
                        System.out.println(tp);
                        System.out.println((Util.connectedUser.getId() ));
                        System.out.println(t.getId());
                        if(Util.connectedUser.getId() == tp.getIduser() && t.getId() == tp.getIdevenement())
                        {
                            exist=true;
                        }

                    }
                   if (exist) {
                       Dialog.show("Stop", "Vous êtes deja participer", "OK", null);
                       
                   }
                   else
                   {
                       ServiceEvenements s = new ServiceEvenements();
                       s.subEvent(Util.connectedUser, t);
                       Dialog.show("Bienvenue", "Bravo vous êtes participer maintenant", "OK", null);
                       
                   }
                   }
                   else
                   {
                       LogIn l = new LogIn();
                       l.getConnection().show();
                   }
                 
                   
               }
           });
                    
                  /*   update.addPointerPressedListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   Vendeur_event_Edit v = new Vendeur_event_Edit();
                   v.Vendeur_event_Edit(t);
                 
               }
           });*/
                                      
                               
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
