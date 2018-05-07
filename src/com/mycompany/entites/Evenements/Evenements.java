/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Evenements;

import com.mycompany.entites.Utilisateurs.User;

import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author user
 */
public class Evenements {
    private int id;
    private String nomevenement;
    private int nombredeplaces;
    private int nombredeplacerestante;
    private float tarifevenement;
    private String description;
    private String lieu;
    private Date date;
    private Date datefin;
    private String dateS;
    private String datefS;
    private User idUser;
    private String image;
    private int valider;
    private FileUploader file;

    public Evenements() {
    }

    public Evenements(String nomevenement, int nombredeplaces, int nombredeplacerestante, float tarifevenement, String description, String lieu, Date date, Date datefin, User idUser, String image) {
        this.nomevenement = nomevenement;
        this.nombredeplaces = nombredeplaces;
        this.nombredeplacerestante = nombredeplacerestante;
        this.tarifevenement = tarifevenement;
        this.description = description;
        this.lieu = lieu;
        this.date = date;
        this.datefin = datefin;
        this.idUser = idUser;
        this.image = image;
    }

    public Evenements(int id, String nomevenement, int nombredeplaces, int nombredeplacerestante, float tarifevenement, String description, String lieu, String dateS, String datefS, User idUser, String image) {
        this.id = id;
        this.nomevenement = nomevenement;
        this.nombredeplaces = nombredeplaces;
        this.nombredeplacerestante = nombredeplacerestante;
        this.tarifevenement = tarifevenement;
        this.description = description;
        this.lieu = lieu;
        this.dateS = dateS;
        this.datefS = datefS;
        this.idUser = idUser;
        this.image = image;
    }
    
    public Evenements(int id,String nomevenement, int nombredeplaces,  float tarifevenement, String description, String lieu, String dateS, String detefS, User idUser, String image) {
        this.id =id;
        this.nomevenement = nomevenement;
        this.nombredeplaces = nombredeplaces;
       
        this.tarifevenement = tarifevenement;
        this.description = description;
        this.lieu = lieu;
        this.dateS = dateS;
        this.datefS = detefS;
        this.idUser = idUser;
        this.image = image;
    }

    public Evenements(String nomevenement, int nombredeplaces,  float tarifevenement, String description, String lieu, String dateS, String detefS, User idUser, String image) {
        this.nomevenement = nomevenement;
        this.nombredeplaces = nombredeplaces;
       
        this.tarifevenement = tarifevenement;
        this.description = description;
        this.lieu = lieu;
        this.dateS = dateS;
        this.datefS = detefS;
        this.idUser = idUser;
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomevenement() {
        return nomevenement;
    }

    public void setNomevenement(String nomevenement) {
        this.nomevenement = nomevenement;
    }

    public int getNombredeplaces() {
        return nombredeplaces;
    }

    public void setNombredeplaces(int nombredeplaces) {
        this.nombredeplaces = nombredeplaces;
    }

    public int getNombredeplacerestante() {
        return nombredeplacerestante;
    }

    public void setNombredeplacerestante(int nombredeplacerestante) {
        this.nombredeplacerestante = nombredeplacerestante;
    }

    public float getTarifevenement() {
        return tarifevenement;
    }

    public void setTarifevenement(float tarifevenement) {
        this.tarifevenement = tarifevenement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

  

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public FileUploader getFile() {
        return file;
    }

    public void setFile(FileUploader file) {
        this.file = file;
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

    public int getValider() {
        return valider;
    }

    public void setValider(int valider) {
        this.valider = valider;
    }



   

  
    

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nomevenement=" + nomevenement + ", nombredeplaces=" + nombredeplaces + ", nombredeplacerestante=" + nombredeplacerestante + ", tarifevenement=" + tarifevenement + ", description=" + description + ", lieu=" + lieu + ", date=" + date + ", datefin=" + datefin + ", idUser=" + idUser + ", image=" + image+ '}';
    }
    
    
    
}
