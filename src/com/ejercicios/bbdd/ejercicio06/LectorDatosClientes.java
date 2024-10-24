/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.bbdd.ejercicio06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

/**
 *
 * @author anyel
 */
public class LectorDatosClientes {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    //CREATE TABLE CLIENTES (DNI CHAR(9) NOT NULL, APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5), PRIMARY KEY(DNI))
    public void insertarDatosClientes(String nombreFichero, String nombreTabla, String separadorCampos) {
        File f = new File(nombreFichero);
        String sql = "insert into CLIENTES values (?, ?, ?);";
        String linea;
        try (BufferedReader bfr = new BufferedReader(new FileReader(f));
                Connection con = DriverManager.getConnection(URL, USER, PWD);
                PreparedStatement ps = con.prepareStatement(sql);) {
            con.setAutoCommit(false);
            do {
                linea = bfr.readLine();
                if (linea != null) {
                    String[] campos = linea.split(separadorCampos);                    
                    ps.setString(1, campos[1]);
                    ps.setString(2, campos[2]);
                    ps.setString(3, campos[3]);
                    ps.executeUpdate();
                }
            } while (linea != null);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}