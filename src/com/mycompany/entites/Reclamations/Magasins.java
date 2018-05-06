/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Reclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Magasins {
    private int idMagasin;
    private String nomMagasin;
    private int idUser;

    public Magasins() {
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Magasins{" + "idMagasin=" + idMagasin + ", nomMagasin=" + nomMagasin + ", idUser=" + idUser + '}';
    }
        
}
