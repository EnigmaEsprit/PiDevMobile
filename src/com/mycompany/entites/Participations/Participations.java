/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Participations;

/**
 *
 * @author user
 */
public class Participations {
    private int id;
    private int idevenement;
    private int iduser;

    public Participations() {
    }

    public Participations(int id, int idevenement, int iduser) {
        this.id = id;
        this.idevenement = idevenement;
        this.iduser = iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Participations{" + "id=" + id + ", idevenement=" + idevenement + ", iduser=" + iduser + '}';
    }
    
    
}
