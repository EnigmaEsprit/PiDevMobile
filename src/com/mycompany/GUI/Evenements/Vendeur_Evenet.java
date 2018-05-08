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

import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.myapp.HomePage;

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
public class Vendeur_Evenet {
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
      Boolean testDD = true;
       Boolean testDF = true;
         Boolean testNBR = true;
         Boolean testTarif = true;
        Boolean testTitre = true;
        Boolean testImage = true;
        Boolean testDescriptio = true;
        Boolean testVille = true;
    String fileNameInServer;
     Form hi;
     private ConnectionRequest connectionRequest;
    
    private String imgPath;
    ImageViewer iv ;
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
     public Vendeur_Evenet() {
      
        TextModeLayout tl = new TextModeLayout(3, 2);
        fv = new Form("Ajout",tl);
                 fv.getStyle().setBgColor(0xE6E6E6);
                  InfiniteProgress ip8 = new InfiniteProgress();
              Dialog d8 = ip8.showInifiniteBlocking();

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
             tb = fv.getToolbar();
                   tb.addCommandToRightBar("Back", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           Container C4 = new Container(new FlowLayout(Component.CENTER));
                           Vendeur_Liste_Events c = new Vendeur_Liste_Events();
                           c.Vendeur_Liste_Events();
                       }
                   });
   
         Container C =new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container CDD = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container CDF = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        NomEv = new TextField(null,"Titre");
       // Emp = new TextField(null,"Emplacement");
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
        DDT = new Picker();
        DF = new  Picker();
        DFT = new Picker();
        NBP = new TextField(null,"Nombre des places");
      
        Tarif = new TextField(null,"Tarif");
        description = new TextField(null,"Description");
        imagelink = new Button("Ajouter image");
       
        btnajout = new Button("ajouter");
        iv=new ImageViewer();
        TextArea popupBody = new TextArea("Description");
            popupBody.setUIID("PopupBody");
        btnaff=new Button("Affichage");
        DD.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        DD.setWidth(500);
        
        DF.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    
       
        //PickerComponent date = PickerComponent.createDate(new Date()).label("Date");
       // fv.add(tl.createConstraint().widthPercentage(40), date);
         
 ac.setMinimumElementsShownInPopup(5);
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
       // C.add(btnaff);
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
                     
      FileUploader fu = new FileUploader("http://"+Util.addip+"/pidevweb/web/uploads/");
        
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
                  System.out.println(imgPath);
                  System.out.println("-----------"+DD.getDate());
                 
                  SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                  String datdebu = formater.format(DD.getDate());
                  String datfin = formater.format(DF.getDate());
                
                  int pos= DD.toString().indexOf(" ", 7);
                  String news =DD.toString().substring(pos+43,DD.toString().length()-10 );
                   String news2 =DF.toString().substring(pos+43,DF.toString().length()-10 );
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
                                     InfiniteProgress ip = new InfiniteProgress();
              Dialog d = ip.showInifiniteBlocking();
                                    
             //  System.out.println(date);
                 /* DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                  Date startDate = df.parse(news);
                  Date startDate2 = df.parse(news2);
                  System.out.println(startDate);*/
                  
                 if (testDD == true && testDF == true && testNBR == true && testTarif == true &&testTitre == true && testImage == true && testDescriptio ==true && testVille==true )
                   {
                       
                

                  Evenements v = new Evenements(NomEv.getText(),Integer.valueOf(NBP.getText()), Float.valueOf(Tarif.getText()), popupBody.getText(), ac.getText(), datdebu, datfin,Util.connectedUser,fileNameInServer);
                  ser.newEvent(v);
                   
                  Vendeur_Liste_Events cl = new Vendeur_Liste_Events();
                    d.dispose();
                  cl.Vendeur_Liste_Events();
                 
                   }
                 else
                 {
                     d.dispose();
                     System.out.println("FAILED");
                 }
                 
               
        });
        
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
        fv.add(C);
        d8.dispose();
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
