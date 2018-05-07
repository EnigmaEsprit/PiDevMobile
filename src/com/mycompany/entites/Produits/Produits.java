/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Produits;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class Produits {
     private int idProduit;
    private String nomProduit;
    private int quantiteProduit;
    private double prixProduit;
    private String imageProduit;
    private float newprix;
    private int validateur;  
    private int quantiteProduitClient;

    public Produits() {
    }
     public Produits(int idProduit, String nomProduit, int quantiteProduit, double prixProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
    }

   

    public Produits(int idProduit, String nomProduit, double prixProduit, int quantiteProduitClient) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduitClient = quantiteProduitClient;
    }
    

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public float getNewprix() {
        return newprix;
    }

    public void setNewprix(float newprix) {
        this.newprix = newprix;
    }

    public int getValidateur() {
        return validateur;
    }

    public void setValidateur(int validateur) {
        this.validateur = validateur;
    }

    public int getQuantiteProduitClient() {
        return quantiteProduitClient;
    }

    public void setQuantiteProduitClient(int quantiteProduitClient) {
        this.quantiteProduitClient = quantiteProduitClient;
    }
    
    
    
     
     
   

    
    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    @Override
    public String toString() {
        return  nomProduit;
    }

    
}
