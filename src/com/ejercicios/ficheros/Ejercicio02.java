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
import java.util.ArrayList;
import java.util.Collections;

/*
    Realiza un programa que sea capaz de ordenar alfabéticamente las palabras
    contenidas en un fichero de texto. El nombre del fichero que contiene las
    palabras se debe pasar como argumento en la línea de comandos. El nombre
    del fichero resultado debe ser el mismo que el original añadiendo la 
    coletilla <code>sort</code>, por ejemplo <code>palabras_sort.txt</code>. 
    Suponemos que cada palabra ocupa una línea
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        File f;
        File fRes = new File(args[0].replaceAll(".txt", "") + "_sort.txt");        
        
        if (args.length < 1) {
            System.out.print("Indique la ruta de los ficheros (fichero1 fichero2 ficheroResultado): ");            
            return;
        }
        f = new File(args[0]);
        ordenarPalabras(f, fRes);
    }
    
    public static void ordenarPalabras(File f, File fRes) {
        String palabra;
        ArrayList<String> palabras = new ArrayList<>();
        
        try (BufferedReader bfr = new BufferedReader(new FileReader(f));
                BufferedWriter bfw = new BufferedWriter(new FileWriter(fRes));) {
            do {
                palabra = bfr.readLine();
                if (palabra != null) {
                    palabras.add(palabra);
                }
            } while (palabra != null);
            Collections.sort(palabras);
            for (String p : palabras) {
                bfw.write(p + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}