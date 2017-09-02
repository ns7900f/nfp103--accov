/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion_accov;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Maya
 */
public class Radar{
    
    int WIDTH = 900, HEIGHT = 325;
    
    private static final Object key = new Object();
    ArrayList<String>listAvions = new ArrayList<>();
    //ArrayList<ArrayList<String>> listAvions = new ArrayList<>();
    String[] information = {"id","nom","position_x","position_y","position_z","cap","vitesse_x","vitesse_y","vitesse_z","altitude","remarque"};
    String[][] t = new String [10][information.length];
    JTable table;
    JFrame frame;
    JPanel panel;
    JButton close;
    
    int index = 0;
    
    public Radar(){
        frame = new JFrame ("Radar");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       
        close= new JButton ("close");
        close.addActionListener((ActionEvent e) -> {
           System.exit(0);
        });
      
        table = new JTable(t,information);
        table.setBackground(Color.WHITE);
        table.setRowHeight(25);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(850,273));
        panel = new JPanel();
        panel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
        panel.setBackground (Color.white);
        panel.add(pane);
        panel.add(close);
        frame.getContentPane().add (panel);
        frame.pack(); 
        frame.setVisible(true);
       
      /*  for(int i=0;i<information.length;i++){
            table.setValueAt(information[i], 0, i);
        }*/
    }
    
    
    void afficher(String id,String nom,String position_x,String position_y,String position_z,String vitesse_x,String vitesse_y,String vitesse_z,String cap,String altitude,String remarque){
        synchronized (key) {
            boolean found =false;
            for (int i=0;i<listAvions.size();i++){
                if(listAvions.get(i).equals(id)){
                   /* listAvions.get(i).add(1, nom);
                    listAvions.get(i).add(2, position_x);
                    listAvions.get(i).add(3, position_y);
                    listAvions.get(i).add(4, position_z);
                    listAvions.get(i).add(5, cap);
                    listAvions.get(i).add(6, vitesse_x);
                    listAvions.get(i).add(7, vitesse_y);
                    listAvions.get(i).add(8, vitesse_z);
                    listAvions.get(i).add(9, altitude);
                    listAvions.get(i).add(10, remarque);*/
                    
                    table.setValueAt(nom, i, 1);
                    table.setValueAt(position_x, i, 2);
                    table.setValueAt(position_y, i, 3);
                    table.setValueAt(position_z, i, 4);
                    table.setValueAt(cap, i, 5);
                    table.setValueAt(vitesse_x, i, 6);
                    table.setValueAt(vitesse_y, i, 7);
                    table.setValueAt(vitesse_z, i, 8);
                    table.setValueAt(altitude, i, 9);
                    table.setValueAt(remarque, i, 10);
      
                    found=true;
                    break;
                }
            }
            if(found ==false){
                listAvions.add(id);
                table.setValueAt(id, index, 0);
                table.setValueAt(nom, index, 1);
                table.setValueAt(position_x, index, 2);
                table.setValueAt(position_y, index, 3);
                table.setValueAt(position_z, index, 4);
                table.setValueAt(cap, index, 5);
                table.setValueAt(vitesse_x, index, 6);
                table.setValueAt(vitesse_y, index, 7);
                table.setValueAt(vitesse_z, index, 8);
                table.setValueAt(altitude, index, 9);
                table.setValueAt(remarque, index, 10);
                index++;
                /*ArrayList <String> a = new ArrayList<>();
                a.add(id);
                a.add(nom);
                a.add(position_x);
                a.add(position_y);
                a.add(position_z);
                a.add(cap);
                a.add(vitesse_x);
                a.add(vitesse_y);
                a.add(vitesse_z);
                a.add(altitude);
                a.add(remarque);

                listAvions.add(a);*/
            }

          /* for(int i=0;i<listAvions.size();i++){
               for(int j=0;j<information.length;j++){
                   System.out.println(information[j] + " : " + listAvions.get(i).get(j));
               }
               System.out.println("------------------------------------------");
           }
            System.out.println();
            System.out.println();*/
        }
    }
}
