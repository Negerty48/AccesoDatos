/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.bbdd.ejercicio04;

import java.sql.*;

/**
 *
 * @author tarde
 */
public class Empleado {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    
    private String dni;
    private String name;
    
    public Empleado() {
        
    }
    
    public Empleado(String dni) throws SQLException {
        boolean exists;
        String sql = "select * from EMPLEADOS where DNI_NIF = ?";
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dni);
            rs = ps.executeQuery();
            exists = rs.next();
            onSave(con, dni, exists);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public void onSave(Connection con, String dni, boolean exists) {
        String sqlInsert = "insert into EMPLEADOS values (?, ?)";
        String sqlUpdate = "update EMPLEADOS set NOMBRE = ? where DNI_NIF = ?";
        if (!exists) {
            try (PreparedStatement ps = con.prepareStatement(sqlInsert);) {
                ps.setString(1, dni);
                ps.setString(2, "Pedro Luis");
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("El empleado se ha insertado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate);) {
                ps.setString(1, "Paco Pepe");
                ps.setString(2, dni);
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("El empleado se ha actualizado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) {
        try {
            Empleado e = new Empleado("AAA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}