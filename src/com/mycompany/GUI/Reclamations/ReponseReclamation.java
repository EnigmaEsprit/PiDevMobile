/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.GUI.Commentaires.PageCommentaire;
import com.mycompany.entites.Reclamations.Reclamations;
import com.mycompany.service.Reclamations.ServiceReclamations;
import java.util.Date;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ReponseReclamation {
    Form formulaire;
    Reclamations selRecl = ConsulterReclamationsVendeur.selectedReclamation;

    private Label createForFont(String s, int style, int size, int color) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, style, size));
        l.getAllStyles().setFgColor(color);
        return l;
    }

    public ReponseReclamation() {
        formulaire = new Form("Répondre à une réclamation", new FlowLayout(Component.CENTER));
        Toolbar tb = formulaire.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter réclamations", FontImage.MATERIAL_LIBRARY_BOOKS, (evt) -> {
            ConsulterReclamationsVendeur con = new ConsulterReclamationsVendeur();
            con.getConsulterReclamations().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter les statistiques", FontImage.MATERIAL_EQUALIZER, (evt) -> {
            StatsReclamation str = new StatsReclamation();
            str.createPieChartForm().show();
        });
        tb.addMaterialCommandToSideMenu("Commentaires", FontImage.MATERIAL_COMMENT, (evt) -> {
            PageCommentaire pagecomment = new PageCommentaire();
            pagecomment.getPagecomment().show();
        });
        tb.addCommandToOverflowMenu("Back", null, (evt3) -> {
            DetailsReclamation det = new DetailsReclamation();
            det.getDetails().show();
        });
        
        
        Container cp = new Container(BoxLayout.y());
        cp.getAllStyles().setMarginLeft(5);
        Label client = createForFont("Réponse adressée à :", Font.STYLE_BOLD, Font.SIZE_LARGE, 0);
        client.getAllStyles().setMarginBottom(10);
        Label nom = createForFont(selRecl.getNomClient(), Font.STYLE_BOLD, Font.SIZE_MEDIUM, 0);
        Label email = createForFont("<"+selRecl.getEmailClient()+">", Font.STYLE_ITALIC, Font.SIZE_MEDIUM, 0);
        TextField subj = new TextField("", "Titre reponse");
        TextArea txReclamation = new TextArea();
        txReclamation.setHint("Entrez le contenu de la réponse");
        txReclamation.setVisible(true);
        txReclamation.setRows(4);
        txReclamation.getAllStyles().setFgColor(0x000000);
        subj.getAllStyles().setMarginTop(5);
        Button envoyer = new Button("Répondre à la réclamation");
        envoyer.getAllStyles().setMarginTop(5);
        envoyer.addActionListener((evt) -> {
            envoyerMail(selRecl.getEmailClient(), subj.getText(), txReclamation.getText());
            System.out.println("Reponse envoyée");
            ServiceReclamations ser = new ServiceReclamations();
            selRecl.setSuiviReclamation("Traitée");
            selRecl.setDateReponseReclamation(new Date());
            ser.updateReclamation(selRecl);
            ConsulterReclamationsVendeur crv = new ConsulterReclamationsVendeur();
            crv.getConsulterReclamations().show();                                
        });
        cp.addAll(client,nom,email,subj,txReclamation,envoyer);
        formulaire.add(cp);
    }

    public Form getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(Form formulaire) {
        this.formulaire = formulaire;
    }

    public void envoyerMail(String receiver, /* String name, */ String subject, String mesg) {
        Message m = new Message(mesg);
//        m.getAttachments().put(textAttachmentUri, "text/plain");
//        m.getAttachments().put(imageAttachmentUri, "image/png");
        Display.getInstance().sendMessage(new String[] {receiver}, subject, m);
//        if(m.sendMessageViaCloudSync("no-reply", receiver, "name nama", subject, mesg)) {
//            System.out.println("Envoi d'une reponse.. .");
//        }
    }

    
}
