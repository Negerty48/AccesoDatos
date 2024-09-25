/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teoria.ficheros;

import java.io.File;
import java.util.Date;

/**
 *
 * @author tarde
 */
public class Fichero {
    public static void main(String[] args) {
        String ruta = ".";
        if (args.length >= 1) {
            ruta = args[0];
        }
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            System.out.println("No existe el fichero / directorio (" + ruta + ").");
        } else {
            if (fichero.isFile()) {                
                System.out.println(ruta + " es un fichero.");
                System.out.println("Tamaño: " + fichero.length()); 
                String[] info = info(fichero);
                System.out.println("Permisos: " + info[0] + " " + info[1] + " " + info[2]);
                System.out.println("Ultima modificación: " + new Date(fichero.lastModified()));
            } else {
                System.out.println(ruta + " es un directorio.");
                System.out.println("Tamaño: " + fichero.length());
                String[] info = info(fichero);
                System.out.println("Permisos: " + info[0] + " " + info[1] + " " + info[2]);
                System.out.println("Ultima modificación: " + new Date(fichero.lastModified()));                
                System.out.println("Contenidos: ");
                System.out.println("-----------------------------------------");
                File[] ficheros = fichero.listFiles();
                for (File f : ficheros) {
                    String textoDescr = f.isDirectory() ? "/" :
                    f.isFile() ? "-" : "?";
                    String[] infoFich = info(f);
                    System.out.println("(" + textoDescr + ") " + f.getName());
                    System.out.println("Tamaño: " + f.length());
                    System.out.println("Permisos: " + infoFich[0] + " " + infoFich[1] + " " + infoFich[2]);
                    System.out.println("Ultima modificación: " + new Date(f.lastModified()));
                    System.out.println("-----------------------------------------");
                }
            }
        }
    }

    public static String[] info(File f) {
        String[] str = new String[3];
        String lect = "-";
        String escr = "-";
        String ejec = "-";
        if (f.canRead()) {
            lect = "r";
        }
        if (f.canWrite()) {
            escr = "w";
        }
        if (f.canExecute()) {
            ejec = "x";
        }
        str[0] = lect;
        str[1] = escr;
        str[2] = ejec;
        return str;
    }
}