/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicios.ficheros;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author tarde
 */
public class VolcadoBinario {
    static int TAM_FILA = 32;
    static int MAX_BYTES = 2048;
    InputStream is = null;
    
    public VolcadoBinario(InputStream is) {
        this.is = is;
    }
    
    public void volcar() throws IOException{
        byte[] buffer = new byte[TAM_FILA];
        int bytesLeidos;
        int offset = 0;
        
        do {
            bytesLeidos = is.read(buffer);
            System.out.format("[%5d]", offset);
            for (int i = 0; i < bytesLeidos; i++) {
                System.out.format("%2x", buffer[i]);
            }
            offset += bytesLeidos;
            System.out.println();
        } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No se ha indicado ningún fichero");    
            return;
        }
        String nombreFichero = args[0];
        try (FileInputStream fis = new FileInputStream(nombreFichero)) {
            VolcadoBinario vb = new VolcadoBinario(fis);
            vb.volcar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}