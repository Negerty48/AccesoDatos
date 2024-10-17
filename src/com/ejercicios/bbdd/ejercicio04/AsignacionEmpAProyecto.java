/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.bbdd.ejercicio04;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author tarde
 */
public class AsignacionEmpAProyecto {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    
    private String dniEmp;
    private int num_proy;   
    private Date f_inicio;
    private Date f_fin;
    
    public AsignacionEmpAProyecto() {
        
    }
    
    public AsignacionEmpAProyecto(String dniEmp, int num_proy, Date f_inicio) throws SQLException {
        boolean exists;
        String sql = "select * from ASIG_PROYECTOS where DNI_NIF_EMP = ? and NUM_PROY = ? and F_INICIO = ?";
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dniEmp);
            ps.setInt(2, num_proy);
            ps.setDate(3, f_inicio);
            rs = ps.executeQuery();
            exists = rs.next();
            onSave(con, dniEmp, num_proy, f_inicio, exists);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public void onSave(Connection con, String dniEmp, int num_proy, Date f_inicio, boolean exists) {
        String sqlInsert = "insert into ASIG_PROYECTOS values (?, ?, ?, ?)";
        String sqlUpdate = "update ASIG_PROYECTOS set F_INICIO = ? where DNI_NIF_EMP = ? and NUM_PROY = ?";
        if (!exists) {
            try (PreparedStatement ps = con.prepareStatement(sqlInsert);) {
                ps.setString(1, dniEmp);
                ps.setInt(2, num_proy);
                ps.setDate(3, Date.valueOf(LocalDate.now()));
                ps.setDate(4, null);
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("La asignacion se ha insertado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate);) {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setString(2, dniEmp);
                ps.setInt(3, num_proy);
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("El proyecto se ha actualizado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDniEmp() {
        return dniEmp;
    }

    public void setDniEmp(String dniEmp) {
        this.dniEmp = dniEmp;
    }

    public int getNum_proy() {
        return num_proy;
    }

    public void setNum_proy(int num_proy) {
        this.num_proy = num_proy;
    }

    public Date getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(Date f_inicio) {
        this.f_inicio = f_inicio;
    }

    public Date getF_fin() {
        return f_fin;
    }

    public void setF_fin(Date f_fin) {
        this.f_fin = f_fin;
    }
    
    public static void main(String[] args) {
        try {
            AsignacionEmpAProyecto a = new AsignacionEmpAProyecto("23456789A", 10, Date.valueOf("2024-10-17"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}