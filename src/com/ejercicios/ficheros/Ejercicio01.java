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

/*  
    Escribe un programa que guarde en un fichero el contenido de otros dos 
    ficheros, de tal forma que en el fichero resultante aparezcan las líneas 
    de los primeros dos ficheros mezcladas, es decir, la primera línea será 
    del primer fichero, la segunda será del segundo fichero, la tercera será la 
    siguiente del primer fichero, etc. Los nombres de los dos ficheros origen y 
    el nombre del fichero destino se deben pasar como argumentos en la línea de
    comandos. Hay que tener en cuenta que los ficheros de donde se van cogiendo 
    las líneas pueden tener tamaños diferentes.
*/
public class Ejercicio01 {
    public static void main(String[] args) {
        File f1;
        File f2;
        File fR;        
        
        if (args.length < 1) {
            System.out.print("Indique la ruta de los ficheros (fichero1 fichero2 ficheroResultado): ");                       
        } else {
            f1 = new File(args[0]);
            f2 = new File(args[1]);
            fR = new File(args[2]);        
            crearFichero(f1, f2, fR);
        }        
    }    
    
    public static void crearFichero(File f1, File f2, File fR) {
        String lineaF1;
        String lineaF2;
        
        try (BufferedReader bfr1 = new BufferedReader(new FileReader(f1));
                BufferedReader bfr2 = new BufferedReader(new FileReader(f2));
                BufferedWriter bfw = new BufferedWriter(new FileWriter(fR));) {            
            do {
                lineaF1 = bfr1.readLine();
                lineaF2 = bfr2.readLine();
                if (lineaF1 != null || lineaF2 != null) {
                    if (lineaF1 != null) {
                        bfw.write(lineaF1 + "\n");
                    }
                    if (lineaF2 != null) {
                        bfw.write(lineaF2 + "\n");
                    }
                }                
            } while (lineaF1 != null || lineaF2 != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}