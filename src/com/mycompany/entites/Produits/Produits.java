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
    private ImageView imageProduit;
    private Button bouton;
    private TextField quantiteTextField;
    private double prixTotal;
 
    private int quantiteProduitClient;

    public Produits(int idProduit, String nomProduit, int quantiteProduit, double prixProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
    }

    public Produits() {
    }

    public Produits(int idProduit, String nomProduit, double prixProduit, int quantiteProduitClient) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduitClient = quantiteProduitClient;
    }
    
    
     
     
   
    

    public ImageView getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(ImageView image) {
        this.imageProduit = image;
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

    public Button getBouton() {
        return bouton;
    }

    public void setBouton(Button bouton) {
        this.bouton = bouton;
    }

    public TextField getQuantiteTextField() {
        return quantiteTextField;
    }

    public void setQuantiteTextField(TextField quantiteTextField) {
        this.quantiteTextField = quantiteTextField;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getQuantiteProduitClient() {
        return quantiteProduitClient;
    }

    public void setQuantiteProduitClient(int quantiteProduitClient) {
        this.quantiteProduitClient = quantiteProduitClient;
    }

    @Override
    public String toString() {
        return "Produits{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", quantiteProduit=" + quantiteProduit + ", prixProduit=" + prixProduit + '}';
    }

    
    
}
