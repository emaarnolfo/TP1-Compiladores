package org.compPL1;

import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Programa desarrollado por Emanuel Arnolfo estudiante de Ingenieria en Computadores\n" +
                "para la asignatura Compiladores de la UAH");

        System.out.println("\nA continuacion se muestra el automata proporcionado en el archivo Automata.txt");
        System.out.println("    Modifique ese archivo si quiere modificar la condiguracion del automata.");
        System.out.println("    Para la correcta lectura por parte del programa el archivo debe llamarse \"Automata.txt\"\n");

        LeerAutomata lectura = new LeerAutomata();
        lectura.leerAutomata();

        Scanner scanner = new Scanner(System.in);

        MaquinaEstados maquina_estados = new MaquinaEstados(
                lectura.getSimbolos(),
                lectura.getEstados(),
                lectura.getEstados_iniciales(),
                lectura.getEstados_finales(),
                lectura.getMatrizTransiciones()
        );

        GeneradorDeCadenas generador = new GeneradorDeCadenas(maquina_estados.getAFD());

        maquina_estados.inicializar();

        System.out.println();
        System.out.println("Seleccione una opción:");
        System.out.println("1. Verificar si una cadena pertenece al autómata.");
        System.out.println("2. Generar todas las posibles cadenas válidas.");
        System.out.println("3. Salir.");

        int opcion = scanner.nextInt();

        while (opcion != 3) {
            switch (opcion) {
                case 1:
                    System.out.println("Inserte una cadena para comprobar que pertenece al autómata (o inserte 'menu' para volver al menú): ");
                    String cadena = scanner.next();

                    while (!cadena.equalsIgnoreCase("menu")) {
                        maquina_estados.cadenaCompleta(cadena);

                        System.out.println("Inserte una cadena para comprobar que pertenece al autómata (o inserte 'menu' para volver al menú): ");
                        cadena = scanner.next();
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el número máximo de cadenas a generar: ");
                    int maxCadenas = scanner.nextInt();

                    System.out.println("Ingrese la longitud máxima de las cadenas: ");
                    int maxLongitud = scanner.nextInt();

                    System.out.println("Generando todas las cadenas válidas...");
                    List<String> cadenasValidas = generador.generarCadenasValidas(maxCadenas, maxLongitud);

                    if (cadenasValidas.isEmpty()) {
                        System.out.println("No se encontraron cadenas válidas.");
                    } else {
                        System.out.println("Cadenas válidas encontradas:");
                        for (String cadenaValida : cadenasValidas) {
                            System.out.println(cadenaValida);
                        }
                        System.out.println("Seleccione una opción:");
                        System.out.println("1. Exportar las cadenas generadas en un archivo .txt.");
                        System.out.println("2. Continuar con la ejecucion del programa.");

                        opcion = scanner.nextInt();

                        if(opcion == 1){
                            // Crear el archivo de salida
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("cadenasValidas.txt"))) {
                                // Recorrer la lista de cadenas válidas y escribir en el archivo
                                for (String cadValida : cadenasValidas) {
                                    writer.write(cadValida);
                                    writer.newLine(); // Agregar un salto de línea después de cada cadena
                                }
                                System.out.println("Cadenas exportadas exitosamente a 'cadenasValidas.txt. (Si el archivo ya existe se soobrescrirá)");
                            } catch (IOException e) {
                                System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
                            }
                        }
                    }

                    break;

                default:
                    System.out.println("Opción no válida. Inténtelo nuevamente.");
                    break;
            }

            System.out.println();
            System.out.println("Seleccione una opción:");
            System.out.println("1. Verificar si una cadena pertenece al autómata.");
            System.out.println("2. Generar una cadena aleatoria que pertenezca al autómata.");
            System.out.println("3. Salir.");
            opcion = scanner.nextInt();
        }

        System.out.println("Programa finalizado.");
    }
}