/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Promotions;

import com.mycompany.entites.Produits.Produits;
import com.mycompany.entites.Utilisateurs.Users;
import java.util.Date;

/**
 *
 * @author user
 */
public class Promotions {
    private int idpromotion;
    private String nompromotion;
    private Date datedebut;
    private Date datefin;
    private int pourcentage;
    private Produits idproduits;
    private Users idUsers;
    private String image;
    private String dateS;
    private String datefS;

    public Promotions() {
    }

    public Promotions(int idpromotion, String nompromotion, String dateS, String datefS, int pourcentage, Produits idproduits, Users idUsers, String image) {
        this.idpromotion = idpromotion;
        this.nompromotion = nompromotion;
        this.dateS = dateS;
        this.datefS = datefS;
        this.pourcentage = pourcentage;
        this.idproduits = idproduits;
        this.idUsers = idUsers;
        this.image = image;
    }

    public Promotions(int idpromotion, String nompromotion, int pourcentage, Produits idproduits, Users idUsers, String image, String dateS, String datefS) {
        this.idpromotion = idpromotion;
        this.nompromotion = nompromotion;
        this.pourcentage = pourcentage;
        this.idproduits = idproduits;
        this.idUsers = idUsers;
        this.image = image;
        this.dateS = dateS;
        this.datefS = datefS;
    }

    public Promotions(String nompromotion, String dateS, String datefS, int pourcentage, Produits idproduits, Users idUsers, String image) {
        this.nompromotion = nompromotion;
        this.dateS = dateS;
        this.datefS = datefS;
        this.pourcentage = pourcentage;
        this.idproduits = idproduits;
        this.idUsers = idUsers;
        this.image = image;
    }

    public int getIdpromotion() {
        return idpromotion;
    }

    public void setIdpromotion(int idpromotion) {
        this.idpromotion = idpromotion;
    }

    public String getNompromotion() {
        return nompromotion;
    }

    public void setNompromotion(String nompromotion) {
        this.nompromotion = nompromotion;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Produits getIdproduits() {
        return idproduits;
    }

    public void setIdproduits(Produits idproduits) {
        this.idproduits = idproduits;
    }

    public Users getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Users idUsers) {
        this.idUsers = idUsers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getDatefS() {
        return datefS;
    }

    public void setDatefS(String datefS) {
        this.datefS = datefS;
    }
    

    /*@Override
    public String toString() {
    return "Promotions{" + "idpromotion=" + idpromotion + ", nompromotion=" + nompromotion + ", datedebut=" + datedebut + ", datefin=" + datefin + ", pourcentage=" + pourcentage + ", idproduits=" + idproduits + ", idUsers=" + idUsers + ", image=" + image + '}';
    }
    */

    @Override
    public String toString() {
        return "Promotions{" + "idpromotion=" + idpromotion + ", nompromotion=" + nompromotion + ", datedebut=" + datedebut + ", datefin=" + datefin + ", pourcentage=" + pourcentage + ", idproduits=" + idproduits + ", idUsers=" + idUsers + ", image=" + image + ", dateS=" + dateS + ", datefS=" + datefS + '}';
    }

    
    
    
}
