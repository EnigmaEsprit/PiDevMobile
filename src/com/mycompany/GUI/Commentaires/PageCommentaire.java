/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Commentaires;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Commentaires.Commentaires;
import com.mycompany.service.Commentaires.ServiceCommentaires;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;

/**
 *
 * @author Ivan Landry ONANA
 */
public class PageCommentaire {
    private Form pagecomment;
    private Resources theme;
    private EncodedImage encode;

    private Label createForFont(String s, int style, int size, int color) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    private Slider separateur() {
        Slider jSlider = new Slider();
        jSlider.setEditable(false);
        return jSlider;
    }
    public void PageComentaire(Form f){
     try {
            encode = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
        }
        
        pagecomment = new Form("Commenter un évènement", new FlowLayout(Component.CENTER));
        Container ui = new Container(BoxLayout.y());
        ui.getAllStyles().setMargin(0, 3, 3, 3);
        Container ui2 = new Container(BoxLayout.x());
        ui2.getAllStyles().setMargin(4, 3, 3, 3);
        ImageViewer pp = new ImageViewer(URLImage.createToStorage(encode,
            "Piiuo", "http://"+Util.addip+"/pidevwebservice/web/uploads/Images/50hc.png", URLImage.RESIZE_SCALE).fill(35, 35));
        

        TextField cmt = new TextField(null, "Ajouter un commentaire");
        Button bt = new Button("Ok");
        ui2.addAll(pp,cmt,bt);
        Container cms = new Container(BoxLayout.y());
        bt.addActionListener((evt) -> {
            ServiceCommentaires sco = new ServiceCommentaires();
            if(cmt.getText().isEmpty()){
               Dialog.show("Erreur", "Veuillez insérer un commentaire", "OK", null);
            }
            else {
                Commentaires b = new Commentaires(cmt.getText());
                sco.newCommentaire(b);
                cmt.setText(null);
            }
            for(Commentaires co : sco.getListeComments()) {
                System.out.println("666666666666");
                Container un_cmt = new Container(BoxLayout.x());
                ImageViewer iv = new ImageViewer(URLImage.createToStorage(encode,
            "Pusera", "http://"+Util.addip+"/pidevwebservice/web/uploads/Images/50hc.png", URLImage.RESIZE_SCALE).fill(50, 50));
                Container ctn = new Container(BoxLayout.y());
                SpanLabel txt_cmt = new SpanLabel(co.getContenuCommentaire());
                txt_cmt.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                Container op = new Container(BoxLayout.x());
                Label o = createForFont("Modifier", Font.STYLE_PLAIN, Font.SIZE_MEDIUM, 0xFF7800);
                o.getAllStyles().setUnderline(true);
                op.add(o);
                o.addPointerPressedListener((evt2) -> {
                    if(Dialog.show("Confirmation", "Modifier ce commentaire ?", "Non", "OUI")) {
                        System.out.println("Nooon");
                    }
                    else{
                        if(co.getIdUser().getId() != Util.connectedUser.getId())
                            Dialog.show("Access Denied", "Impossible de modifier ce commentaire", "OK", null);
                        else {
                            cmt.setText(co.getContenuCommentaire());
                            o.addPointerPressedListener((evt4) -> {
                            if(cmt.getText().isEmpty())
                               Dialog.show("Erreur", "Veuillez insérer un commentaire", "OK", null);
                            else {
                                sco.updateCommentaire(co);
                                cmt.setText(null);
                            }
                            });
                        }
                            
                    }
                });        

                Label p = createForFont("Supprimer", Font.STYLE_PLAIN, Font.SIZE_MEDIUM, 0xFF7800);
                p.getAllStyles().setUnderline(true);
                op.add(p);
                p.addPointerPressedListener((evt2) -> {
                    if(Dialog.show("Confirmation", "Supprimer ce commentaire ?", "Non", "OUI")) {
                        System.out.println("Nooon");
                    }
                    else{
                        if(co.getIdUser().getId() != Util.connectedUser.getId())
                            Dialog.show("Access Denied", "Impossible de supprimer ce commentaire", "OK", null);
                        else {
                            sco.deleteCommentaire(co);
                            un_cmt.setVisible(false);
                        }
                    }
                });        

                ctn.addAll(txt_cmt,op);
                ctn.getStyle().setMargin(2,0,7,0);
                un_cmt.addAll(iv, ctn);
                un_cmt.getStyle().setMargin(2,3,0,0);
                cms.add(un_cmt);
            }
        });
        
        cms.getStyle().setMarginTop(5);
        ui.addAll(ui2,cms);
        
        f.add(ui);
    
}
    public PageCommentaire() {
        /*    theme = UIManager.initFirstTheme("/theme");
        try {
        encode = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
        }
        
        pagecomment = new Form("Commenter un évènement", new FlowLayout(Component.CENTER));
        Container ui = new Container(BoxLayout.y());
        ui.getAllStyles().setMargin(0, 3, 3, 3);
        Container ui2 = new Container(BoxLayout.x());
        ui2.getAllStyles().setMargin(4, 3, 3, 3);
        ImageViewer img = new ImageViewer(URLImage.createToStorage(encode,
        "Piñata", "http://localhost/PiDevWeb/web/uploads/Images/ff_1.jpeg", URLImage.RESIZE_SCALE).fill(380, 200));
        ImageViewer pp = new ImageViewer(URLImage.createToStorage(encode,
        "Piiuo", "http://localhost/PiDevWeb/web/uploads/Images/50hc.png", URLImage.RESIZE_SCALE).fill(35, 35));
        
        Container objet = new Container(BoxLayout.x());
        objet.getAllStyles().setMarginTop(2);
        objet.addAll(createForFont("Objet de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont("ASDZDZDqafzknfgjeejgggrgjnj", Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        Container objet1 = new Container(BoxLayout.x());
        objet1.getAllStyles().setMarginTop(2);
        objet1.addAll(createForFont("Objet de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont("ASDZDZDqafzknfgjeejgggrgjnj", Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        Container objet3 = new Container(BoxLayout.x());
        objet3.getAllStyles().setMarginTop(2);
        objet3.addAll(createForFont("Objet de la réclamation :", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0),createForFont("ASDZDZDqafzknfgjeejgggrgjnj", Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0));
        
        TextField cmt = new TextField(null, "Ajouter un commentaire");
        Button bt = new Button("Ok");
        ui2.addAll(pp,cmt,bt);
        Container cms = new Container(BoxLayout.y());
        bt.addActionListener((evt) -> {
        ServiceCommentaires sco = new ServiceCommentaires();
        if(cmt.getText().isEmpty()){
        Dialog.show("Erreur", "Veuillez insérer un commentaire", "OK", null);
        }
        else {
        Commentaires b = new Commentaires(cmt.getText());
        sco.newCommentaire(b);
        cmt.setText(null);
        }
        for(Commentaires co : sco.getListeComments()) {
        Container un_cmt = new Container(BoxLayout.x());
        ImageViewer iv = new ImageViewer(URLImage.createToStorage(encode,
        "Pusera", "http://localhost/PiDevWeb/web/uploads/Images/50hc.png", URLImage.RESIZE_SCALE).fill(30, 30));
        Container ctn = new Container(BoxLayout.y());
        SpanLabel txt_cmt = new SpanLabel(co.getContenuCommentaire());
        txt_cmt.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        Container op = new Container(BoxLayout.x());
        Label o = createForFont("Modifier", Font.STYLE_PLAIN, Font.SIZE_MEDIUM, 0xFF7800);
        o.getAllStyles().setUnderline(true);
        op.add(o);
        o.addPointerPressedListener((evt2) -> {
        if(Dialog.show("Confirmation", "Modifier ce commentaire ?", "Non", "OUI")) {
        System.out.println("Nooon");
        }
        else{
        if(co.getIdUser().getId() != ServiceUsers.connectedUser.getId())
        Dialog.show("Access Denied", "Impossible de modifier ce commentaire", "OK", null);
        else {
        cmt.setText(co.getContenuCommentaire());
        o.addPointerPressedListener((evt4) -> {
        if(cmt.getText().isEmpty())
        Dialog.show("Erreur", "Veuillez insérer un commentaire", "OK", null);
        else {
        sco.updateCommentaire(co);
        cmt.setText(null);
        }
        });
        }
        
        }
        });
        
        Label p = createForFont("Supprimer", Font.STYLE_PLAIN, Font.SIZE_MEDIUM, 0xFF7800);
        p.getAllStyles().setUnderline(true);
        op.add(p);
        p.addPointerPressedListener((evt2) -> {
        if(Dialog.show("Confirmation", "Supprimer ce commentaire ?", "Non", "OUI")) {
        System.out.println("Nooon");
        }
        else{
        if(co.getIdUser().getId() != ServiceUsers.connectedUser.getId())
        Dialog.show("Access Denied", "Impossible de supprimer ce commentaire", "OK", null);
        else {
        sco.deleteCommentaire(co);
        un_cmt.setVisible(false);
        }
        }
        });
        
        ctn.addAll(txt_cmt,op);
        ctn.getStyle().setMargin(2,0,7,0);
        un_cmt.addAll(iv, ctn);
        un_cmt.getStyle().setMargin(2,3,0,0);
        cms.add(un_cmt);
        }
        });
        
        cms.getStyle().setMarginTop(5);
        ui.addAll(img,objet,objet3,objet1,ui2,cms);
        
        pagecomment.add(ui);*/
    }

    public Form getPagecomment() {
        return pagecomment;
    }

    public void setPagecomment(Form pagecomment) {
        this.pagecomment = pagecomment;
    }
    
    
}
