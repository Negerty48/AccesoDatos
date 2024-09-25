/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tarde
 */
public class ArreglarFicheroTexto {
    public static void main(String[] args) {
        String nombreFichero = "f_texto.txt";
        File f = new File(nombreFichero);
        if (!f.exists()) {
            System.out.println("El fichero " + nombreFichero + " no existe.");
            return;
        }
        try (BufferedReader bfr = new BufferedReader(new FileReader(f));) {
            File fTemp = File.createTempFile(nombreFichero, "");
            System.out.println("Fichero temporal creado: " + fTemp.getAbsolutePath());
            BufferedWriter bfw = new BufferedWriter(new FileWriter(fTemp));
            String linea = bfr.readLine();
            while (linea != null) {
                boolean principioLinea = true;
                boolean espacios = false;
                boolean primeraAlfab = false;
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isWhitespace(c)) {
                        if (!espacios && !principioLinea) {
                            bfw.write(c);
                        }                        
                        espacios = true;
                    } else if (Character.isAlphabetic(c)) {
                        if (!primeraAlfab) {
                            bfw.write(Character.toUpperCase(c));
                            primeraAlfab = true;
                        } else {
                            bfw.write(c);
                            espacios = false;
                            principioLinea = false;
                        }                        
                    }                    
                }
                bfw.newLine();
                linea = bfr.readLine();                
            }
            bfw.close();
            f.renameTo((new File(nombreFichero + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".bak")));
            fTemp.renameTo(new File(nombreFichero));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}