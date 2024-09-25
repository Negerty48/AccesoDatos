/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teoria.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author tarde
 */
public class Buffered {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.print("Indique la ruta del fichero: ");
            return;
        }
        String nombreFichero = args[0];
        try (BufferedReader fbr = new BufferedReader(new FileReader(nombreFichero))) {
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null) {
                System.out.format("[%3d] %s", i++, linea);
                System.out.println();
                linea = fbr.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("No existe el fichero " + nombreFichero);
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}