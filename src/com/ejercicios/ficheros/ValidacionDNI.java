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
    A partir del archivo adjunto, que contiene una lista de números de 
    identificación personal (DNI), desarrolla un programa que lea este archivo y
    copie los DNIs correctamente formateados en un archivo llamado 'dniok.txt',
    mientras que los DNIs incorrectamente formateados se guardarán en un archivo
    denominado 'dnionotok.txt'. Un DNI se considera correctamente formateado si 
    consta de 8 números seguidos de una letra, y la letra es válida según los 
    estándares especificados en la siguiente página web.
 */
public class ValidacionDNI {
    public static void main(String[] args) {
        File fDni = new File("dnis.txt");
        File fDniOk = new File("dniOk.txt");
        File fDniNotOk = new File("dniNotOk.txt");
        String linea;
        
        try (BufferedReader bfr = new BufferedReader(new FileReader(fDni));
                BufferedWriter bfwOk = new BufferedWriter(new FileWriter(fDniOk));
                BufferedWriter bfwNotOk = new BufferedWriter(new FileWriter(fDniNotOk));) {
            do {
                linea = bfr.readLine();
                if (linea != null) {
                    if (comprobarFormato(linea)) {
                        if (dniValido(linea)) {
                            bfwOk.write(linea + "\n");
                        } else {
                            bfwNotOk.write(linea + "\n");
                        }
                    } else {
                        bfwNotOk.write(linea + "\n");
                    }                
                } 
            } while (linea != null);            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean comprobarFormato(String linea) {
        boolean formato = false;
        if (linea.length() == 9) {
            for (int i = 0; i < linea.length(); i++) {
                if (i != 8) {
                    if (Character.isDigit(linea.charAt(i))) {
                        formato = true;
                    } else formato = false;                    
                } else {
                    if (Character.isAlphabetic(linea.charAt(i))) {
                        formato = true;
                    } else formato = false;
                }
            }
        }
        return formato;
    }
    
    public static boolean dniValido(String linea) {        
        int res;
        int numero;
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X',
                        'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'}; 
        numero = Integer.parseInt(linea.substring(0, 8));
        res = numero % 23;
        if (linea.charAt(8) == letras[res]) {
            return true;
        } else return false;        
    }
}