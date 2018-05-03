/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Decouverte;

import com.codename1.components.ImageViewer;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.maps.Coord;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.mycompany.GUI.Evenements.detail;
import static com.mycompany.GUI.Evenements.detail.getCoords;
import com.mycompany.entites.Decouverte.ContactDecouverte;
import com.mycompany.entites.Produits.Produits;

import com.mycompany.service.Decouverte.ServiceContact;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class Contact {
    
        Container cnt;
         ComboBox<String> cp;
           Container C4 ;
            Form f = new Form( BoxLayout.y());
             private EncodedImage enc;
             
             
      private static final String HTML_API_KEY = "AIzaSyDcwHdDoI65jU6iHlOGv54Efo67fE_AWw0";

             
        private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);

        return l;
        }

    public Contact() {
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
public  void Map()
{
     cp = new ComboBox<>();
   cp.addItem("--Ville--");
   cp.addItem("Grand Tunis");
    cp.addItem("Beja");
     cp.addItem("Bizerte");
      cp.addItem("Gabes");
       cp.addItem("Gafsa");
        cp.addItem("Jendouba");
         cp.addItem("Kairouan");
          cp.addItem("Kassrine");
           cp.addItem("Kebili");
            cp.addItem("Le Kef");
             cp.addItem("Mahdia");
              cp.addItem("Médnine");
               cp.addItem("Monastir");
                cp.addItem("Nabeul");
                 cp.addItem("Sfax");
                  cp.addItem("Sidi Bouzid");
                   cp.addItem("Siliana");
                    cp.addItem("Sousse");
                     cp.addItem("Tataouine");
                      cp.addItem("Touzeur");
                      cp.addItem("Zaghouan");
     Style s = new Style();
                 s.setFgColor(0xFF7F50);
                 
                 s.setBgTransparency(0);
                 FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(1));
                 
               
                 
            
                 
              
             
               MapContainer cn = new MapContainer(); 
               
               Coord x = new Coord(33.8869, 9.5375);
               cn.zoom(x, 7);
            
                cn.setCameraPosition(x); // since the image is iin the jar this is unlikely
                cp.addSelectionListener(new SelectionListener() {
                    
       @Override
       public void selectionChanged(int oldSelected, int newSelected) {
                String selectedVille = (String) cp.getSelectedItem();
                if(selectedVille.equalsIgnoreCase("Grand Tunis"))
                {
                    cn.zoom(getCoords("tunis,Tunisie"), 9);
            
                cn.setCameraPosition(getCoords("tunis,Tunisie")); // since the image is iin the jar this is unlikely
                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Rue jamaa Ezzitouna, Tunis"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Rue Hedi Zarrouk, Carthage"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
             
                }
                else if (selectedVille.equalsIgnoreCase("Nabeul"))
                {
                    cn.zoom(getCoords("nabeul,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("nabeul,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Avenue Farhat Hached, Nabeul‎"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                            cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("La Medina Yasmine Hammamet, Yasmine Hammamet"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                                cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Médina de Hammamet, Ruelle Ibrahim Chérif, Hammamet"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                    
                }
                 else if (selectedVille.equalsIgnoreCase("Sidi Bouzid"))
                {
                    cn.zoom(getCoords("sid bouzid,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("sidi bouzid,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("السوق الاسبوعية بسيدي بوزيد"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                  else if (selectedVille.equalsIgnoreCase("Monastir"))
                {
                    cn.zoom(getCoords("monastir,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("monastir,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Independence, Monastir"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                else if (selectedVille.equalsIgnoreCase("Kairouan"))
                {
                    cn.zoom(getCoords("kairouan,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("kairouan,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("bab djalladine, Kairouan"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                 else if (selectedVille.equalsIgnoreCase("Bizerte"))
                {
                    cn.zoom(getCoords("bizerte,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("bizerte,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Rue de la Regence,Bizerte"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                              cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Rue Cheikh Idriss Chéerif,Bizerte"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                   else if (selectedVille.equalsIgnoreCase("Médnine"))
                {
                    cn.zoom(getCoords("mednine,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("mednine,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Houmt Souk,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                              cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Zarzis,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Kebili"))
                {
                    cn.zoom(getCoords("Kebili,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Kebili,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Douz,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                     else if (selectedVille.equalsIgnoreCase("Touzeur"))
                {
                    cn.zoom(getCoords("Touzeur,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Touzeur,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Touzeur,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Gabes"))
                {
                    cn.zoom(getCoords("Gabes,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Gabes,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Matmata,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                                   cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Gabes,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                else if (selectedVille.equalsIgnoreCase("Tataouine"))
                {
                    cn.zoom(getCoords("Tataouine,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Tataouine,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("souk el jomla,Tataouine"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                 else if (selectedVille.equalsIgnoreCase("Gafsa"))
                {
                    cn.zoom(getCoords("Gafsa,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Gafsa,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Gafsa,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Beja"))
                {
                    cn.zoom(getCoords("Beja,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Beja,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Testour,Beja,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
              
                
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Beja,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                      else if (selectedVille.equalsIgnoreCase("Sousse"))
                {
                    cn.zoom(getCoords("Sousse,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Sousse,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("avenue Mohamed Ali,Sousse"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Mahdia"))
                {
                    cn.zoom(getCoords("Mahdia,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Mahdia"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("El Djem,Mahdia"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                               cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Bain Maure Hammam el souk,Mahdia"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Le Kef"))
                {
                    cn.zoom(getCoords("Le Kef,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Le Kef,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Rue Hassan Harbouch,Le Kef"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Kassrine"))
                {
                    cn.zoom(getCoords("Kassrine,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Kassrine,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Kassrine,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Zaghouan"))
                {
                    cn.zoom(getCoords("Zaghouan,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Zaghouan,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Zaghouan,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Siliana"))
                {
                    
                    cn.zoom(getCoords("Siliana,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Siliana,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Siliana,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Jendouba"))
                {
                    cn.zoom(getCoords("Jendouba,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Jendouba,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("Jendouba,Tunisie"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
                    else if (selectedVille.equalsIgnoreCase("Sfax"))
                {
                    cn.zoom(getCoords("Sfax,Tunisie"), 9);
                     cn.setCameraPosition(getCoords("Sfax,Tunisie"));
                        cn.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords("sidi dawed ,sfax"), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                });
                }
       }
   });
              
                f.add(LayeredLayout.encloseIn(cn, BorderLayout.north(cp)));
                }
    

public void Contact()
{
     
  
          
    
     
       MapContainer cn = new MapContainer();
       Map();
     
        f.getToolbar().addSearchCommand(e -> {
           
            String tc = (String)e.getSource();
            
            System.out.println(tc);
            if(tc.isEmpty())
            {
                System.out.println("nulll");
            }
            if(tc == null) {
                tc = "";
            } else {
                tc = tc.toLowerCase();
            }
            f.removeAll();
           if(!tc.isEmpty())
            {
                  f.removeAll();
            ServiceContact sc = new ServiceContact();
            for (ContactDecouverte t : sc.getListContact(tc))
            {
               
            ContactDecouverte mb = (ContactDecouverte)t;
            
            try {
            enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
            
            }
            
            
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Image i =(URLImage.createToStorage(enc,t.getImagemag(), "http://localhost/pidevweb/web/uploads/Images/"+t.getImagemag()+"", URLImage.RESIZE_SCALE));
            
            
            ImageViewer img2 = new ImageViewer(i.fill(250,300));
            
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));
            
            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            C4=new Container(new FlowLayout(Component.CENTER));
            
            Label l = new Label(t.getNom_magazin().toLowerCase());
            l.getAllStyles().setFgColor(0xff0000);
            
            Label DDF = new Label();
            
            
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            C3.add(createForFont(smallPlainSystemFont,"Vendeur: "+t.getNom()+" "+t.getPrenom()));
            C3.add(createForFont(smallPlainSystemFont, "E-mail: "+t.getEmail()));
            C3.add(createForFont(smallPlainSystemFont, " Addresse: "+t.getAdresse()));
            C3.add(createForFont(smallPlainSystemFont, " Ville: "+t.getVille()+"   "+" Zip:"+t.getZip()+" N°Tel: "+t.getNt()));
            
            
            // C3.add(jSlider);
            
            
            C.add(l);
            
            
            C2.add(C);
            C1.add(img2);
            C1.add(C2);
            C2.add(C3);
            // C1.setLeadComponent(l);
            Label fargha = new Label();
            Slider khat = new Slider();
            
            f.add(C1);
            f.add(fargha);
            f.add(khat);
            
            
            }
           
            /* for(Component c : cnt) {
            DemoComponent mb = (DemoComponent)c;
            boolean show = t.length() == 0 || mb.getText().toLowerCase().indexOf(t) > -1;
            mb.setVisible(show);
            mb.setHidden(!show);
            }
            cnt.animateLayout(200);*/
        }
           else
           {
           f.removeAll();
          
           Map();

           }
                f.show();
        },3 );
      
        f.show();
        
}
                

    public Container getCnt() {
        return cnt;
    }

    public void setCnt(Container cnt) {
        this.cnt = cnt;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
