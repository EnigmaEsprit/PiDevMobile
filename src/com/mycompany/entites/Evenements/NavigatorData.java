/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites.Evenements;

import com.mycompany.entites.Utilisateurs.Users;




public class NavigatorData {
    private final static NavigatorData instance = new NavigatorData();

    public static NavigatorData getInstance() {
        return instance;
    }        
          
    private Users ConnectedUser;
    private Evenements SelectedAnnonce;
    private String vd;
    private String date;
    private Users selectedUser;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    
    
    public String getVd() {
        return vd;
    }

    public void setVd(String vd) {
        this.vd = vd;
    }
    
    public void SetConnectedUser(Users CUser){
        this.ConnectedUser=CUser;
    }
    
    public Users getConnectedUser() {
        return ConnectedUser;
    }     
        public void SetSelectedAnnonce(Evenements annonce){
        this.SelectedAnnonce=annonce;
    }
    
    public Evenements getSelectedAnnonce() {
        return SelectedAnnonce;
    } 
}
