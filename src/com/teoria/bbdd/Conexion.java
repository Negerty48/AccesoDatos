/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teoria.bbdd;

import java.sql.*;

/**
 *
 * @author tarde
 */
public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("No se ha podido cargar el driver");
        }
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);) {
            System.out.println("Conexión establcida");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
