/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Reclamations;

import com.mycompany.entites.Utilisateurs.User;

import java.util.Date;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Reclamations {
    private int idReclamation;
    private String typeReclamation;
    private String objetReclamation;
    private String contenuReclamation;
    private String nomClient;
    private String emailClient;
    private String nomMagasin;
    private String nomVendeur;
    private Date dateEnvoiReclamation;  
    private Date dateReponseReclamation;  
    private User idUser;
    private int idMagasin;
    private int statusReclamation = 0;
    private String suiviReclamation = "En attente";
    private String visibiliteReclamation = "Oui";

    public Reclamations() {
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getObjetReclamation() {
        return objetReclamation;
    }

    public void setObjetReclamation(String objetReclamation) {
        this.objetReclamation = objetReclamation;
    }

    public String getContenuReclamation() {
        return contenuReclamation;
    }

    public void setContenuReclamation(String contenuReclamation) {
        this.contenuReclamation = contenuReclamation;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Date getDateEnvoiReclamation() {
        return dateEnvoiReclamation;
    }

    public void setDateEnvoiReclamation(Date dateEnvoiReclamation) {
        this.dateEnvoiReclamation = dateEnvoiReclamation;
    }

    public Date getDateReponseReclamation() {
        return dateReponseReclamation;
    }

    public void setDateReponseReclamation(Date dateReponseReclamation) {
        this.dateReponseReclamation = dateReponseReclamation;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public int getStatusReclamation() {
        return statusReclamation;
    }

    public void setStatusReclamation(int statusReclamation) {
        this.statusReclamation = statusReclamation;
    }

    public String getSuiviReclamation() {
        return suiviReclamation;
    }

    public void setSuiviReclamation(String suiviReclamation) {
        this.suiviReclamation = suiviReclamation;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }

    public String getVisibiliteReclamation() {
        return visibiliteReclamation;
    }

    public void setVisibiliteReclamation(String visibiliteReclamation) {
        this.visibiliteReclamation = visibiliteReclamation;
    }

    @Override
    public String toString() {
        return "Reclamations{" + "idReclamation=" + idReclamation + ", typeReclamation=" + typeReclamation + ", objetReclamation=" + objetReclamation + ", contenuReclamation=" + contenuReclamation + ", nomClient=" + nomClient + ", emailClient=" + emailClient + ", dateEnvoiReclamation=" + dateEnvoiReclamation + ", dateReponseReclamation=" + dateReponseReclamation + ", idUser=" + idUser + ", idMagasin=" + idMagasin + ", statusReclamation=" + statusReclamation + ", suiviReclamation=" + suiviReclamation + '}';
    }
    
    
}
