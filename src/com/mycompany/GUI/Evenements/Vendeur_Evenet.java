/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Evenements;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.Table;
import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.service.Evenements.ServiceEvenements;
import java.io.IOException;
import java.util.Date;
import javafx.scene.control.DatePicker;

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
    private Form f2;
     Form hi;
     private ConnectionRequest connectionRequest;
    
    private String imgPath;
            ImageViewer iv ;
     public Vendeur_Evenet() {
          hi = new Form("Picker", new BoxLayout(BoxLayout.Y_AXIS));
Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);
Picker dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
Picker timePicker = new Picker();
timePicker.setType(Display.PICKER_TYPE_TIME);
Picker stringPicker = new Picker();
stringPicker.setType(Display.PICKER_TYPE_STRINGS);

datePicker.setDate(new Date());
dateTimePicker.setDate(new Date());
timePicker.setTime(10 * 60); // 10:00AM = Minutes since midnight
stringPicker.setStrings("A Game of Thrones", "A Clash Of Kings", "A Storm Of Swords", "A Feast For Crows",
        "A Dance With Dragons", "The Winds of Winter", "A Dream of Spring");
stringPicker.setSelectedString("A Game of Thrones");

hi.add(datePicker).add(dateTimePicker).add(timePicker).add(stringPicker);

        fv = new Form("Ajout",new FlowLayout(Component.CENTER));
         Container C =new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container CDD = new Container(new FlowLayout(Component.CENTER));
          Container CDF = new Container(new BoxLayout(BoxLayout.X_AXIS));
        NomEv = new TextField(null,"Titre");
        Emp = new TextField(null,"Emplacement");
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
        btnaff=new Button("Affichage");
        DD.setType(Display.PICKER_TYPE_DATE);
        DDT.setType(Display.PICKER_TYPE_TIME);
        DF.setType(Display.PICKER_TYPE_DATE);
        DFT.setType(Display.PICKER_TYPE_TIME);
        C.add(NomEv);
        C.add(Emp);
        CDD.add(DD);
        CDD.add(DDT);
        CDD.add(DF);
        CDD.add(DFT);
        C.add(CDD);
        C.add(CDF);
        C.add(NBP);
        C.add(Tarif);
        C.add(description);
        C.add(imagelink);
        //f.add(imagelink);
        C.add(btnajout);
        C.add(btnaff);
        imagelink.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        img = Image.createImage(imgPath);                      
                        iv.setImage(img);
                        System.out.println(imgPath);
                    } catch (IOException ex) {
                    }
                }
            });
        
        btnajout.addActionListener((e) -> {
            ServiceEvenements ser = new ServiceEvenements();
          //  Evenements v = new Evenements(NomEv.getText(),Integer.valueOf(NBP.getText()), Float.valueOf(Tarif.getText()), description.getText(), Emp.getText(), DD.getValue().toString(), DF.getValue().toString(),null,imgPath);
            //ser.newEvent(v);
            

        });
        
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
        fv.add(C);
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
