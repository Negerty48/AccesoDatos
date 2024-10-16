/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.bbdd.ejercicio03;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author tarde
 */
public class GestorProyectos implements AutoCloseable{    
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    
    Connection con = null;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.exit(1);
        }
    }
    
    public GestorProyectos() throws SQLException {
        try {        
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public boolean nuevoEmpleado(String dni, String name) throws SQLException {   
        String sql = "insert into EMPLEADOS values (?,?);";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dni);
            ps.setString(2, name);
            if (ps.executeUpdate() != 0) {
                return true;
            } else return false;
        }  catch (SQLException e) {
            throw e;
        }
    }
    
    public int nuevoProyecto(String name, String dniBoss, Date f_inicio, Date f_fin) throws SQLException {
        int r;
        String sqlInsert = "insert into PROYECTOS values(0, ?, ?, ?, ?);";
        String sqlSelect = "select NUM_PROY from PROYECTOS order by NUM_PROY;";
        ResultSet rs = null;
        try (PreparedStatement psInsert = con.prepareStatement(sqlInsert);
                PreparedStatement psSelect = con.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            psInsert.setString(1, name);
            psInsert.setString(2, dniBoss);
            if (f_inicio != null) {
                psInsert.setDate(3, f_inicio);
            } else {
                psInsert.setDate(3, Date.valueOf(LocalDate.now()));
            }
            psInsert.setDate(4, f_fin);
            psInsert.execute();
            rs = psSelect.executeQuery();
            rs.last();
            r = rs.getInt(1);
            return r;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public boolean asignaEmpAProyecto(String dniEmp, int numProy, Date f_inicio, Date f_fin) throws SQLException {
        String sql = "insert into ASIG_PROYECTOS values (?, ?, ?, ?);";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dniEmp);
            ps.setInt(2, numProy);
            if (f_inicio != null) {
                ps.setDate(3, f_inicio);
            } else {
                ps.setDate(3, Date.valueOf(LocalDate.now()));
            }
            ps.setDate(4, f_fin);
            if (ps.executeUpdate() != 0) {
                return true;
            } else return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        if (con != null) {
            con.close();
        }
    }
        
    public static void main(String[] args) {
        try {
            GestorProyectos gp = new GestorProyectos();
            gp.nuevoEmpleado("12345678Z", "Angel");
            gp.nuevoEmpleado("23456789A", "Eduardo");
            gp.nuevoEmpleado("56498732H", "Juan Pedro");
            gp.nuevoEmpleado("96385274E", "Domingo");
            gp.nuevoProyecto("Proyecto01", "96385274E", Date.valueOf("2024-05-15"), null);
            gp.nuevoProyecto("Proyecto02", "56498732H", null, null);
            gp.asignaEmpAProyecto("12345678Z", 8, null, Date.valueOf("2024-12-31"));
            gp.asignaEmpAProyecto("23456789A", 9, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}