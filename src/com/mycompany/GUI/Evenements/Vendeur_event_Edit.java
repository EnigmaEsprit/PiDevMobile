/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.Table;
import com.mycompany.GUI.Decouverte.Contact;
import com.mycompany.GUI.Promotions.Vendeur_List_Promotions;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.myapp.HomePage;
import com.mycompany.myapp.ToolbarForm;
import com.mycompany.service.Evenements.ServiceEvenements;
import com.mycompany.service.Utilisateurs.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author user
 */
public class Vendeur_event_Edit {
    
     Form fv;
    TextField NomEv;
    TextField Emp;
    Picker DD;
    Picker DDT;
    Picker DF;
    Picker DFT;
    TextField NBP;
    TextField NBPR;
    TextField Tarif;
    TextField description;
    Button imagelink;
    Button btnajout,btnaff;
    Table event;
    private Image img;
    private EncodedImage enc;
    private ImageViewer iV;
    private FileUploader file;
    private Form f2;
    String fileNameInServer;
     Form hi;
     private ConnectionRequest connectionRequest;
    
    private String imgPath;
            ImageViewer iv ;
            Boolean testDD = true;
       Boolean testDF = true;
         Boolean testNBR = true;
         Boolean testTarif = true;
        Boolean testTitre = true;
        Boolean testImage = true;
        Boolean testDescriptio = true;
        Boolean testVille = true;
        Boolean testplace = true;
 private static final String MAPS_KEY = "AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI"; // Your maps key here
        String[] searchLocations(String text) {
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key",MAPS_KEY );
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;
}
    public Vendeur_event_Edit() {
    }
            
     public void Vendeur_event_Edit(Evenements ev) {
         InfiniteProgress ip8 = new InfiniteProgress();
              Dialog d8 = ip8.showInifiniteBlocking();
         TextModeLayout tl = new TextModeLayout(3, 2);

        fv = new Form("Edit",new FlowLayout(Component.CENTER));
                 fv.getStyle().setBgColor(0xE6E6E6);

        if(MAPS_KEY == null) {
    fv.add(new SpanLabel("This demo requires a valid google API key to be set in the constant apiKey, "
            + "you can get this key for the webservice (not the native key) by following the instructions here: "
            + "https://developers.google.com/places/web-service/get-api-key"));
    fv.getToolbar().addCommandToRightBar(MAPS_KEY, null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
    fv.show();
    return;
}
                  Toolbar tb = fv.getToolbar();
                  /* tb.addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_HOME,new ActionListener() {
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
                  });*/
                                           ToolbarForm tbf = new ToolbarForm();
        if(User.getActifUser()!= null)
        System.out.println(User.getActifUser().getRoles().toString());
   if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_USER]")){
            tbf.Menu(fv); 
        }else if(User.getActifUser() !=null && User.getActifUser().getRoles().equalsIgnoreCase("[ROLE_VENDEUR, ROLE_USER]"))
                {
                    tbf.Menu2(fv);
                }
                else{
             
            tbf.Menu0(fv); 
        }
              tb = fv.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           Container C4 = new Container(new FlowLayout(Component.CENTER));
                          detailVendeur d = new detailVendeur();
                           d.detailVendeur(ev);                      }
                   });
   
         Container C =new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container CDD = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container CDF = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        NomEv = new TextField(ev.getNomevenement(),"Titre");
      //  Emp = new TextField(ev.getLieu(),"Emplacement");
       final DefaultListModel<String> options = new DefaultListModel<>();
       AutoCompleteTextField ac = new AutoCompleteTextField(options) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }
 };
        DD = new Picker();
      
        DF = new  Picker();
         DD.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        DD.setWidth(500);
        DF.setType(Display.PICKER_TYPE_DATE_AND_TIME);
      
        NBP = new TextField(String.valueOf(ev.getNombredeplaces()),"Nombre des places");
        
        Tarif = new TextField(String.valueOf(ev.getTarifevenement()),"Tarif");
       TextArea popupBody = new TextArea();
            popupBody.setUIID("PopupBody");
            popupBody.setText(ev.getDescription());
            //TextArea description2 = new TextArea("", 5, TextField.TEXT_CURSOR);
            
        description = new TextField(null,"Description");
        imagelink = new Button("Ajouter image");
       fileNameInServer=ev.getImage();
        btnajout = new Button("Edit");
        iv=new ImageViewer();
        
        btnaff=new Button("Affichage");
        DD.setDate(ev.getDate());
       
        DF.setDate(ev.getDatefin());
        ac.setMinimumElementsShownInPopup(5);
        ac.setText(ev.getLieu());
        C.add(NomEv);
        C.add(ac);
        CDD.add(DD);
 
        CDD.add(DF);
       
        C.add(CDD);
        C.add(CDF);
        C.add(NBP);
        C.add(Tarif);
       // C.add(description);
        C.add(popupBody);
        C.add(imagelink);
        //f.add(imagelink);
        C.add(btnajout);
      //  C.add(btnaff);
      DD.addActionListener((evt) -> {
            Picker d = (Picker)evt.getSource();
            Date dc = new Date();
            Date d2 = d.getDate() ;
            if(d2.getTime()<dc.getTime())
            {
                 testDD=false;
                 Dialog.show("Stop", "Date de debut doit etre superieur a date de jour", "OK", null);

            }
            else
            {
                testDD=true;
            }
        });
    
       DF.addActionListener((evt) -> {
            Picker d = (Picker)evt.getSource();
            Date dc = new Date();
            Date dd2 = DD.getDate();
            Date d2 = d.getDate() ;
            if(d2.getTime()<=dd2.getTime())
            {
                testDF=false;
                 Dialog.show("Stop", "Date de Fin doit etre superieur a date dubut", "OK", null);

            }
            else
            {
                testDF=true;
            }
       });
         NBP.addDataChangedListener(new DataChangedListener() {
                @Override
                public void dataChanged(int type, int index) {
                if((NBP.getText().isEmpty()))
                    {
                        
                        testNBR=false;
                    }
                else if((!Util.numerique_Validation(NBP.getText()))||(Integer.valueOf(NBP.getText())<1))
                {
                    Dialog.show("Stop", "Verifier NBP", "OK", null);
                    testNBR=false;
                }
                    else
                    {
                        testNBR=true;
                    }
                }
            });
            Tarif.addDataChangedListener(new DataChangedListener() {
                @Override
                public void dataChanged(int type, int index) {
                if((Tarif.getText().isEmpty()))
                    {
                        
                        testTarif=false;
                    }
                else if((!Util.isValidFloat(Tarif.getText())))
                {
                    Dialog.show("Stop", "Verifier Tarif", "OK", null);
                    testTarif=false;
                }
                    else
                    {
                        testTarif=true;
                    }
                }
            });
      imagelink.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        imgPath = Capture.capturePhoto();
                       // img = Image.createImage(imgPath);    
                      // iv.setImage(img);
                       // file = new FileUploader(imgPath);
                        System.out.println(imgPath);
                        String link = imgPath.toString();
                        int pod= link.indexOf("/", 2);
                        String news = link.substring(pod+2, link.length());
                        System.out.println(""+news);
                     
      FileUploader fu = new FileUploader("http://localhost/pidevweb/web/uploads/");
        
        //Upload
        fileNameInServer = fu.upload(news);
                        System.out.println("-----------"+fileNameInServer+"---------------");
        
       
                     
                    } catch (IOException ex) {
                          ex.printStackTrace();
                    } catch (Exception ex) {
                    }
                }
            });
        
        btnajout.addActionListener((e) -> {
            ServiceEvenements ser = new ServiceEvenements();
            String datdebu,datfin;
                  System.out.println(imgPath);
                  System.out.println("-----------"+DD.getDate());
                 //LocalDate d = LocalDate.parse(DD.getDate().toString());
                  //System.out.println("yar7am waldik imchiiiiiiii"+d);
                  SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                  
                   datdebu = formater.format(DD.getDate());
                  datfin = formater.format(DF.getDate());
                  int res = ev.getNombredeplacerestante()-(ev.getNombredeplaces()-Integer.valueOf(NBP.getText()));
                           if(!NomEv.getText().isEmpty())
                {

                    testTitre=true;
                }
                else
                {
                testTitre=false;
               Dialog.show("Stop", "Titre obligatoire", "OK", null);

                    
                }
                System.out.println(fileNameInServer+"lll");
                   if(fileNameInServer != null)
                {

                    testTitre=true;
                }
                else
                {
                testTitre=false;
               Dialog.show("Stop", "Image obligatoire", "OK", null);

                    
                }
                   System.out.println(ac.getText()+"---------*******");
                  
                   if(ac.getText()!= null)
                   {
                   
                   testVille=true;
                   }
                   else
                   {
                   testVille=false;
                   Dialog.show("Stop", "Ville stp obligatoire", "OK", null);
                   
                   
                   }
                   System.out.println(popupBody.getText());
                                    if(!popupBody.getText().isEmpty())
                {

                    testDescriptio=true;
                }
                else
                {
                testDescriptio=false;
               Dialog.show("Stop", "Description stp obligatoire", "OK", null);

                    
                }
                  if(Integer.valueOf(NBP.getText())<ev.getNombredeplaces()&& ev.getNombredeplacerestante() ==0 || res<0)
                  {
                      testplace=false;
                        Dialog.show("Erreur", "Verifier Nombre des place", "OK", null);
                       
                  }
                         InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
                  if (testDD == true && testDF == true && testNBR == true && testTarif == true &&testTitre == true && testImage == true && testDescriptio ==true && testVille==true  && testplace == true )

                  {
               
                       Evenements v = new Evenements(ev.getId(),NomEv.getText(),Integer.valueOf(NBP.getText()),res ,Float.valueOf(Tarif.getText()),popupBody.getText(), ac.getText(), datdebu, datfin,Util.connectedUser,fileNameInServer);
                        Evenements v2 = new Evenements(NomEv.getText(),Integer.valueOf(NBP.getText()),res ,Float.valueOf(Tarif.getText()),popupBody.getText(), ac.getText(), DD.getDate(), DF.getDate(),Util.connectedUser,fileNameInServer);
           ser.udpateEvent(v);
          
            detailVendeur cl = new detailVendeur();
            cl.detailVendeur(v2);
             d.dispose();
            
                  }
                  else
                  {
                       d.dispose();
                     System.out.println("FAILED");
                  }
                /*  if(datdebu== null )
                  {
                      datdebu= formater.format(ev.getDate());

                  }
                  if( datfin == null)
                  {
                     datfin = formater.format(ev.getDatefin());

                  }*/
                 
               
        });
        
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
        fv.add(C);
        d8.dispose();
        fv.show();
          fv.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                  LoginForm logForm = new LoginForm();
                                      Util.connectedUser=null;
                                      User.setActifUser(null);
                                      logForm.getMain().show();
                                    
                                }
                            });
          
    }

    public Form getFv() {
        return fv;
    }

    public void setFv(Form fv) {
        this.fv = fv;
    }

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }

    
}
