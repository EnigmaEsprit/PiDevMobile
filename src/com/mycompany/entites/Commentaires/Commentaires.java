/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Commentaires;

import com.mycompany.entites.Utilisateurs.Users;
import java.util.Date;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Commentaires {
    private int idCommentaire;
    private String contenuCommentaire;
    private Date dateAjoutCommentaire;
    private Date dateModificationCommentaire;
    private String urlUserPhoto;
    private Users idUser;
    private int idevenement;
    private int produit;
    
    public Commentaires() {
    }

    public Commentaires(String text) {
        this.contenuCommentaire = text;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public Date getDateAjoutCommentaire() {
        return dateAjoutCommentaire;
    }

    public void setDateAjoutCommentaire(Date dateAjoutCommentaire) {
        this.dateAjoutCommentaire = dateAjoutCommentaire;
    }

    public Date getDateModificationCommentaire() {
        return dateModificationCommentaire;
    }

    public void setDateModificationCommentaire(Date dateModificationCommentaire) {
        this.dateModificationCommentaire = dateModificationCommentaire;
    }

    public String getUrlUserPhoto() {
        return urlUserPhoto;
    }

    public void setUrlUserPhoto(String urlUserPhoto) {
        this.urlUserPhoto = urlUserPhoto;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commentaires{" + "idCommentaire=" + idCommentaire + ", contenuCommentaire=" + contenuCommentaire + ", dateAjoutCommentaire=" + dateAjoutCommentaire + ", dateModificationCommentaire=" + dateModificationCommentaire + ", urlUserPhoto=" + urlUserPhoto + ", idUser=" + idUser + ", idevenement=" + idevenement + ", produit=" + produit + '}';
    }

}
