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
public class Ejercicio3_2 {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "";
    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM clientes");) {
            int i = 1;
            while (rs.next()) {
                System.out.println("[" + (i++) + "]");
                System.out.println("DNI: " + rs.getString("DNI"));
                System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                System.out.println("CP: " + rs.getInt("CP"));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}