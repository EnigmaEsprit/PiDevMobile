/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Promotions;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.mycompany.GUI.Evenements.Affichage;
import com.mycompany.GUI.Evenements.Vendeur_Liste_Events;
import com.mycompany.GUI.Utilisateurs.LogIn;
import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Promotions.Promotions;
import com.mycompany.myapp.MyApplication;
import com.mycompany.service.Promotions.ServicePrommotion;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author user
 */
public class Vendeur_Promotion_Edit {
    

    Form fv;
    TextField Titre;
    TextField pourcentage;
    Picker DD;
    ComboBox<Produits> cp;
    
    
    Picker DF;

  
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
         Boolean testPourc = true;
         Boolean testProd = true;
        Boolean testTitre = true;
        Boolean testImage = true;
    public Vendeur_Promotion_Edit()
    {
        
    }
    
     public void Vendeur_Promotion_Edit(Promotions t) {
      
        TextModeLayout tl = new TextModeLayout(3, 2);
        fv = new Form("Edit",tl);
        
          Toolbar tb = fv.getToolbar();
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
                  Container C4 = new Container(new FlowLayout(Component.CENTER));
                           Vendeur_Liste_Events c = new Vendeur_Liste_Events();
                           c.Vendeur_Liste_Events();        
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
        Titre = new TextField(null,"Titre");
        Titre.setText(t.getNompromotion());
      
        DD = new Picker();
       
        DF = new  Picker();
          ServicePrommotion sc = new ServicePrommotion();
      
       
      
     
      pourcentage = new TextField(null, "Pourcentage de remise %");
      pourcentage.setText(String.valueOf(t.getPourcentage()));
        imagelink = new Button("Ajouter image");
       
        btnajout = new Button("Edit");
        iv=new ImageViewer();
        TextArea popupBody = new TextArea("Description");
            popupBody.setUIID("PopupBody");
        btnaff=new Button("Affichage");
        DD.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        DD.setWidth(500);
        
        DF.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    
       
        //PickerComponent date = PickerComponent.createDate(new Date()).label("Date");
       // fv.add(tl.createConstraint().widthPercentage(40), date);

        C.add(Titre);
        DD.setDate(t.getDatedebut());

        CDD.add(DD);
        DF.setDate(t.getDatefin());
   
        CDD.add(DF);
    
        C.add(CDD);
        C.add(CDF);
        List<Produits> ls = new ArrayList<Produits>();
        ls = sc.getListProduits();
         cp = new ComboBox<>();
         /*  DefaultListModel dd = new DefaultListModel(ls);
         cp.setModel(dd);*/
          for(Produits i : sc.getListProduits())
          {
              cp.addItem(i);
              if(i.getIdProduit()==t.getIdproduits().getIdProduit())
              {
                  cp.setSelectedItem(i);
              }
          }
          
         
       
        C.add(pourcentage);
        C.add(cp);
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
        
            pourcentage.addDataChangedListener(new DataChangedListener() {
                @Override
                public void dataChanged(int type, int index) {
                if((pourcentage.getText().isEmpty()))
                    {
                        
                        testPourc=false;
                    }
                else if((!Util.numerique_Validation(pourcentage.getText()))||(Integer.valueOf(pourcentage.getText())<1)||(Integer.valueOf(pourcentage.getText())>100))
                {
                    Dialog.show("Stop", "verivier", "OK", null);
                    testPourc=false;
                }
                    else
                    {
                        testPourc=true;
                    }
                }
            });
       fileNameInServer = t.getImage();
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
     
                  ServicePrommotion ser = new ServicePrommotion();
                  System.out.println(imgPath);
                  
                
                  SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                  String datdebu = formater.format(DD.getDate());
                  String datfin = formater.format(DF.getDate());
            
                   if(!Titre.getText().isEmpty())
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
                   Produits selectedProduits = (Produits) cp.getSelectedItem();
                   if(DD.getDate().getTime()==t.getDatedebut().getTime()&&DF.getDate().getTime()==t.getDatefin().getTime()&&selectedProduits.getIdProduit()==t.getIdproduits().getIdProduit())
                   {
                       testProd =true;
                   }
                   else
                   {
               
                for(Promotions pr : ser.getListPromotionVendeur() )
                {
                    System.out.println("-----"+pr);
                    
                   if(selectedProduits.getIdProduit()==pr.getIdproduits().getIdProduit()&& selectedProduits.getValidateur()==0 &&(DD.getDate().getTime()>pr.getDatefin().getTime()||DF.getDate().getTime()<pr.getDatedebut().getTime())&&pr.getIdpromotion()!=t.getIdpromotion())
                   {
                        testProd =true;
                    }
                   else if(selectedProduits.getIdProduit()==pr.getIdproduits().getIdProduit()&& selectedProduits.getValidateur()==0 &&(DD.getDate().getTime()<pr.getDatefin().getTime()||(DF.getDate().getTime()>pr.getDatedebut().getTime()&&(DF).getDate().getTime()<pr.getDatefin().getTime()))&&pr.getIdpromotion()!=t.getIdpromotion())
                   {
                       testProd=false;
                        Dialog.show("Stop", "Ce produit deja en cour de promotion(date)", "OK", null);

                   }
                  
           
                if(selectedProduits.getValidateur()==1&& selectedProduits.getIdProduit() != t.getIdproduits().getIdProduit())
                    {
                        Dialog.show("Stop", "Ce produit deja en cour de promotion", "OK", null);
                       testProd = false; 
                    }
                }
                   }

                  if (testDD == true && testDF == true && testPourc == true && testProd == true &&testTitre == true && testImage == true )
                  {
                        Promotions v = new Promotions(t.getIdpromotion(),Titre.getText(), datdebu, datfin,Integer.valueOf(pourcentage.getText()),selectedProduits,Util.connectedUser,fileNameInServer);
                 ser.udpatePromotion(v);
                  
                  Client_List_Promotions cl = new Client_List_Promotions();
                  cl.getF().show();
                  }
               
        
        });
        
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
        fv.add(C);
          fv.getToolbar().addCommandToOverflowMenu("LogOut", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    
                                    LogIn l = new LogIn();
                                    Util.connectedUser=null;
                                    l.getConnection().show();
                                }
                            });
          fv.show();
        
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
