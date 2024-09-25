package com.ejercicios.ficheros;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tarde
 */
public class Actividad_2_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto;
        File f = new File("YoMeDrogoQueMasDa.txt");        
        System.out.print("Introduce un texto a buscar en el fichero: ");
        texto = sc.nextLine();
        leerFicheroConArray(f, texto);
    }
    
    public static void leerFicheroConArray(File f, String texto) {
        int i = 0;        
        try (BufferedReader fbr = new BufferedReader(new FileReader(f));) {   
            String linea = fbr.readLine();
            i++;
            do {                
                if (linea != null) {   
                    String[] palabras = linea.split(" ");
                    for (int j = 0; j < palabras.length; j++) {
                        if (palabras[j].equalsIgnoreCase(texto)) {
                            System.out.println(texto + " | Linea: " + i + " Columna: " + j);
                        }
                    }
                    linea = fbr.readLine();                    
                    i++;
                }
            } while (linea != null);
        } catch (FileNotFoundException e) {
            System.err.println("El fichero seleccionado no existe");
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void leerFicheroConIndexOf () {
        
    }
}