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
public class InsertInto {
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
                Statement s = con.createStatement();) {
            int nFil = s.executeUpdate( "INSERT INTO CLIENTES (DNI, APELLIDOS, CP) VALUES "
                    + "('78901234X', 'NADALES', '44126')," 
                    + "('89012345E', 'HOJAS', null)," 
                    + "('56789012B', 'SAMPER', '29730')," 
                    + "('09876543K', 'LAMIQUIZ', null);");
            System.out.println(nFil + " FIlas insertadas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}