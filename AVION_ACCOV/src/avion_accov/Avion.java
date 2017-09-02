/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion_accov;

/**
 *
 * @author Maya
 */

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;

public class Avion {
    static final int INTERVALLE = 3000;//millisecondes
    static final String hostName = "localhost";
    static final int portNumber = 350;
    static final int VITMIN = 200;
    int id; //numero du vol
    String nom;
    String depart,arrivee;
    double arrivee_x,arrivee_y,arrivee_z;
    double cap,altitude;
    double vitesse_x,vitesse_y,vitesse_z;
    double position_x,position_y,position_z;
    
    public Avion(int id,String nom,String depart,String arrivee, double arrivee_x,double arrivee_y,double arrivee_z){
        this.id =id;
        this.nom = nom;
        this.depart = depart;
        this.arrivee = arrivee;
        this.arrivee_x = arrivee_x;
        this.arrivee_y = arrivee_y;
        this.arrivee_z = arrivee_z;
        position_x = 0;
        position_y = 0;
        position_z = 0;
        vitesse_x = 0;
        vitesse_y = 0;
        vitesse_z = 0;
        cap = 0;
        altitude = 0;
        
    }
    
    int modification(double vitesse_x,double vitesse_y,double vitesse_z,double cap,double altitude){
        this.vitesse_x = vitesse_x;
        this.vitesse_y = vitesse_y;
        this.vitesse_z = vitesse_z;
        this.cap = cap;
        this.altitude = altitude;
    
        
        double cosinus, sinus, tang;
        double dep_x, dep_y, dep_z;

        cosinus = Math.cos(cap * 2 * Math.PI / 360);
        sinus = Math.sin(cap * 2 * Math.PI / 360);
        tang = Math.tan(cap * 2 * Math.PI/360);

        //newPOS = oldPOS + Vt
        dep_x = cosinus * vitesse_x * 10 / VITMIN;
        dep_y = sinus * vitesse_y * 10 / VITMIN;
        dep_z = tang * vitesse_z * 10/ VITMIN;

        // on se d�place d'au moins une case quels que soient le cap et la vitesse
        // sauf si cap est un des angles droit
        if ((dep_x > 0) && (dep_x < 1)) dep_x = 1;
        if ((dep_x < 0) && (dep_x > -1)) dep_x = -1;

        if ((dep_y > 0) && (dep_y < 1)) dep_y = 1;
        if ((dep_y < 0) && (dep_y > -1)) dep_y = -1;
        
        if ((dep_z > 0) && (dep_z < 1)) dep_z = 1;
        if ((dep_z < 0) && (dep_z > -1)) dep_z = -1;

        //printf(" x : %f y : %f\n", dep_x, dep_y);

        position_x = position_x + (int) dep_x;
        position_y = position_y + (int) dep_y;
        position_z = position_z + (int) dep_z;
       
        
        if((position_x == arrivee_x) && (position_y == arrivee_y) && (position_z == arrivee_z) ){
            //arrivee a destination
            return -1;
        }
        return 0;
    }
    
    void traitement(Socket clientSocket){
        try{
            PrintWriter outToSaca = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader inFromSaca = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToSaca.println(id + "/" + nom + "/" + arrivee_x + "/" + arrivee_y + "/" + arrivee_z ); //evoie id/nom/arrivee
            outToSaca.flush();

            while(true){
                String [] s = inFromSaca.readLine().split("/"); //vitesse/cap/altitude
                int res = modification(Double.parseDouble(s[0]),Double.parseDouble(s[1]),Double.parseDouble(s[2]),Double.parseDouble(s[3]),Double.parseDouble(s[4]));
                if(res == -1){
                    outToSaca.println(position_x + "/" + position_y + "/" + position_z + "/end");
                    outToSaca.flush();
                    break;
                }
                sleep(INTERVALLE);

                outToSaca.println(position_x + "/" + position_y + "/" + position_z);
                outToSaca.flush();
            }
            outToSaca.close();
            inFromSaca.close();
        }catch(Exception e){System.out.println("arret d'execution"); System.exit(0);}
    }
    
    public void start ()throws Exception{
        
        try (Socket clientSocket = new Socket(hostName,portNumber)) {
            traitement(clientSocket);
        }
   
    }   
    public void Destroy ()throws Exception{
        
        try (Socket clientSocket = new Socket(hostName,portNumber)) {
            clientSocket.close();
        }
   
    }   
}
