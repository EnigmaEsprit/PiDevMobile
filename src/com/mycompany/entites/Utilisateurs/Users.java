/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Utilisateurs;

import java.util.Date;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Users {

    private int id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String sexe;
    private String adresse;
    private String ville;
    private int zip;
    private String numerodutelephone;
    private String email;
    private String password;
    private String img;
    private String username;
    private String roles;
    private String salt;


    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getNumerodutelephone() {
        return numerodutelephone;
    }

    public void setNumerodutelephone(String numerodutelephone) {
        this.numerodutelephone = numerodutelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", sexe=" + sexe + ", adresse=" + adresse + ", ville=" + ville + ", zip=" + zip + ", numerodutelephone=" + numerodutelephone + ", email=" + email + ", password=" + password + ", img=" + img + ", username=" + username + ", roles=" + roles + ", salt=" + salt + '}';
    }
    
}
