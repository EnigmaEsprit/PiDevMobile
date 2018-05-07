/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Evenements;

import com.mycompany.entites.Utilisateurs.User;


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
    private String date;
    private String datefin;
    private User idUser;
    private String file;

    public Evenements() {
    }

    public Evenements(String nomevenement, int nombredeplaces, float tarifevenement, String description, String lieu, String date, String datefin, User idUser, String image) {
        this.nomevenement = nomevenement;
        this.nombredeplaces = nombredeplaces;
        this.tarifevenement = tarifevenement;
        this.description = description;
        this.lieu = lieu;
        this.date = date;
        this.datefin = datefin;
        this.idUser = idUser;
        this.file = image;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

  
    

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nomevenement=" + nomevenement + ", nombredeplaces=" + nombredeplaces + ", nombredeplacerestante=" + nombredeplacerestante + ", tarifevenement=" + tarifevenement + ", description=" + description + ", lieu=" + lieu + ", date=" + date + ", datefin=" + datefin + ", idUser=" + idUser + ", image=" + file + '}';
    }
    
    
    
}