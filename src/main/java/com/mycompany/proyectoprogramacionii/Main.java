/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectoprogramacionii;

/**
 *
 * @author Toshiba
 */
public class Main {
static ConexionDB connMongo;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connMongo =new ConexionDB();
        connMongo.mostrarDB();
    }
}
