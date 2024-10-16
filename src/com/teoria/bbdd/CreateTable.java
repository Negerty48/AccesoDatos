/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teoria.bbdd;

import java.sql.*;

/**
 *
 * @author anyel
 */
public class CreateTable {
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
                s.execute("CREATE TABLE CLIENTES (DNI CHAR(9) PRIMARY KEY, APELLIDOS "
                           + "VARCHAR(32) NOT NULL, CP CHAR(5))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}