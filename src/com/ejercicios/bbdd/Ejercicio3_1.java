/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.bbdd;

import java.sql.*;

/**
 *
 * @author anyel
 */
public class Ejercicio3_1 {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }            
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);
                Statement s = con.createStatement();) {
            int nFilas = s.executeUpdate("UPDATE CLIENTES SET APELLIDOS = 'ROJAS' where DNI = '89012345E';");
            System.out.println(nFilas + " Filas afectadas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}