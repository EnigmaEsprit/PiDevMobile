/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.Utilisateurs;

import com.mycompany.entites.Evenements.Evenements;
import com.mycompany.entites.Utilisateurs.User;




/**
 *
 * @author user
 */
public class Util {
    public static User connectedUser =User.getActifUser();
    public static Evenements event;

    public static String addip = "172.16.236.248";

     public static boolean numerique_Validation(String numerique){
       char[] s = numerique.toCharArray();
       boolean testnum = true;
       for(int i=0;i<s.length;i++)
       {
           if(!Character.isDigit(s[i]))
           {
               testnum = false;
           }
       }
       return testnum;
   }
   public static boolean isValidFloat(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;		
	}
}
