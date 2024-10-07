package org.compPL1;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenas {
        private Automata AFD;

        public GeneradorDeCadenas(Automata automata) {
            this.AFD = automata;
        }

        // Método para generar todas las cadenas válidas
        public List<String> generarCadenasValidas(int maxCadenas, int maxLongitud) {
            List<String> cadenasValidas = new ArrayList<>();
            generarCadenas(new StringBuilder(), AFD.getEstado_inicial(), cadenasValidas, maxCadenas, maxLongitud);
            return cadenasValidas;
        }

        // Método recursivo para explorar todas las posibles cadenas válidas
        private void generarCadenas(StringBuilder cadenaActual, int estado, List<String> cadenasValidas, int maxCadenas, int maxLongitud) {
            // Si la longitud de la cadena excede el máximo, detener la búsqueda
            if (cadenaActual.length() > maxLongitud) {
                return;
            }

            // Si estamos en un estado final, agregar la cadena a la lista
            if (AFD.getEstados_finales().contains(estado) && !cadenasValidas.contains(cadenaActual.toString())) {
                cadenasValidas.add(cadenaActual.toString());
            }

            // Si ya alcanzamos el máximo número de cadenas, detener la búsqueda
            if (cadenasValidas.size() >= maxCadenas) {
                return;
            }

            /*
            for (int i = 0; i < alfabeto.size(); i++) {
            Character simbolo = alfabeto.get(random.nextInt(alfabeto.size()));
            Integer estadoSiguiente = AFD.getMatriz().get(estado).get(simbolo);
             */


            // Explorar todas las transiciones posibles desde el estado actual
            for (Character simbolo : AFD.getAlfabeto()) {
                Integer estadoSiguiente = AFD.getMatriz().get(estado).get(simbolo);
                if (estadoSiguiente != null) {
                    // Avanzar con el símbolo actual
                    cadenaActual.append(simbolo);
                    // Llamada recursiva al siguiente estado
                    generarCadenas(cadenaActual, estadoSiguiente, cadenasValidas, maxCadenas, maxLongitud);
                    // Retroceder  quitando el símbolo para probar otros
                    cadenaActual.deleteCharAt(cadenaActual.length() - 1);
                }
            }
        }
}
