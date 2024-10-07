package org.compPL1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeerAutomata {

    String rutaArchivo = "Automata.txt"; // Cambia esto por la ruta de tu archivo

    // Estructuras de datos
    List<Character> simbolos = new ArrayList<>();
    List<Integer> estados = new ArrayList<>();
    List<Integer> estados_iniciales = new ArrayList<>();
    List<Integer> estados_finales = new ArrayList<>();
    HashMap<Integer, HashMap<Character, Integer>> matrizTransiciones = new HashMap<>();
    public void leerAutomata() {

        try (BufferedReader br = new IgnoreComents(new FileReader(rutaArchivo))) {
            String linea;
            int seccion = 0;  // Para identificar qué parte del archivo estamos leyendo

            while ((linea = br.readLine()) != null) {

                // Identificar en qué sección estamos leyendo
                if (linea.equals("Simbolos del Alfabeto")) {
                    seccion = 1; // Sección de símbolos del alfabeto
                } else if (linea.equals("Estados del Automata")) {
                    seccion = 2; // Sección de estados del autómata
                } else if (linea.equals("Estados iniciales")) {
                    seccion = 3; // Sección de estados iniciales
                } else if (linea.equals("Estados Finales")) {
                    seccion = 4; // Sección de estados finales
                } else if (linea.startsWith("Matriz de transiciones")) {
                    seccion = 5; // Sección de matriz de transiciones
                } else {
                    continue;
                }
                linea = br.readLine();


                // Leer las secciones
                switch (seccion) {
                    case 1:
                        // Leer los símbolos del alfabeto
                        for (String simbolo : linea.split(", ")) {
                            simbolos.add(simbolo.charAt(0)); // Convertir a char
                        }
                        break;
                    case 2:
                        // Leer los estados del autómata
                        for (String estado : linea.split(", ")) {
                            estados.add(Integer.parseInt(estado));
                        }
                        break;
                    case 3:
                        // Leer los estados iniciales
                        for (String estadoInicial : linea.split(", ")) {
                            estados_iniciales.add(Integer.parseInt(estadoInicial));
                        }
                        break;
                    case 4:
                        // Leer los estados finales
                        for (String estadoFinal : linea.split(", ")) {
                            estados_finales.add(Integer.parseInt(estadoFinal));
                        }
                        break;
                    case 5:
                        // Leer la matriz de transiciones
                        linea = br.readLine();
                        String[] partes = linea.split("\\s+"); // Separar por espacios en blanco
                        int estadoActual = Integer.parseInt(partes[0]); // Primer número es el estado

                        while(true){
                            // Crear el mapa para el estado actual
                            HashMap<Character, Integer> transiciones = new HashMap<>();
                            for (int i = 1; i < partes.length; i++) {
                                if (!partes[i].equals("-")) {
                                    transiciones.put(simbolos.get(i - 1), Integer.parseInt(partes[i]));
                                }
                            }
                            matrizTransiciones.put(estadoActual, transiciones);

                            linea = br.readLine();
                            if(linea == null){
                                break;
                            }
                            partes = linea.split("\\s+"); // Separar por espacios en blanco
                            estadoActual = Integer.parseInt(partes[0]); // Primer número es el estado
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostrar los resultados
        System.out.println("Simbolos del alfabeto: " + simbolos);
        System.out.println("Estados del automata: " + estados);
        System.out.println("Estados iniciales: " + estados_iniciales);
        System.out.println("Estados finales: " + estados_finales);
        System.out.println("Matriz de transiciones: " + matrizTransiciones);

    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
    public List<Character> getSimbolos() {
        return simbolos;
    }
    public List<Integer> getEstados() {
        return estados;
    }
    public List<Integer> getEstados_iniciales() {
        return estados_iniciales;
    }
    public List<Integer> getEstados_finales() {
        return estados_finales;
    }
    public HashMap<Integer, HashMap<Character, Integer>> getMatrizTransiciones() {
        return matrizTransiciones;
    }
}
