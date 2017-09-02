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
public class Console {
    
    double arrivee_x,arrivee_y,arrivee_z;

    
    String calcul( double position_x, double position_y, double position_z, double cap, double altitude){
        
        return (modification(position_x, position_y, position_z, cap, altitude));
    }
    
    String calcul( double arrivee_x,double arrivee_y, double arrivee_z,double position_x, double position_y, double position_z, double cap, double altitude){
       this.arrivee_x = arrivee_x;
       this.arrivee_y = arrivee_y;
       this.arrivee_z = arrivee_z;
       return (modification(position_x, position_y, position_z, cap, altitude));
    }
     
    String modification(double position_x, double position_y, double position_z, double cap, double altitude){
        
       String resultat = (Math.random()*100) + "/" + (Math.random()*100) + "/" +  (Math.random()*100) + "/" + (Math.random()*100) + "/" + (Math.random()*100) ;

       return resultat; //vitesse/cap/altitude
    }
    
}
