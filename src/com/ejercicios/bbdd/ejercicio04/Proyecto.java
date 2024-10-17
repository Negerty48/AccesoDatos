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
public class Proyecto {
    public static final String URL = "jdbc:mysql://localhost:3306/empresa";
    public static final String USER = "root";
    public static final String PWD = "root";
    
    private int num_proy;
    private String name;
    private String dniBoss;
    private Date f_inicio;
    private Date f_fin;
    
    public Proyecto() {
        
    }
    
    public Proyecto(int num_proy) throws SQLException {
        boolean exists;
        String sql = "select * from PROYECTOS where NUM_PROY = ?";
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PWD);
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, num_proy);
            rs = ps.executeQuery();
            exists = rs.next();
            onSave(con, num_proy, exists);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public void onSave(Connection con, int num_proy, boolean exists) {
        String sqlInsert = "insert into PROYECTOS values (0, ?, ?, ?, ?)";
        String sqlUpdate = "update PROYECTOS set NOMBRE = ? where NUM_PROY = ?";
        if (!exists) {
            try (PreparedStatement ps = con.prepareStatement(sqlInsert);) {
                ps.setString(1, "OtroProyecto");
                ps.setString(2, "12345678Z");
                ps.setDate(3, Date.valueOf(LocalDate.now()));
                ps.setDate(4, null);
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("El proyecto se ha insertado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate);) {
                ps.setString(1, "Actualizado");
                ps.setInt(2, num_proy);
                int r = ps.executeUpdate();
                if (r != 0) {
                    System.out.println("El proyecto se ha actualizado correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNum_proy() {
        return num_proy;
    }

    public void setNum_proy(int num_proy) {
        this.num_proy = num_proy;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDniBoss() {
        return dniBoss;
    }

    public void setDniBoss(String dniBoss) {
        this.dniBoss = dniBoss;
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
            Proyecto p = new Proyecto(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}