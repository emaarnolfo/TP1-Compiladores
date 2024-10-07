package org.compPL1;

import java.util.HashMap;
import java.util.List;

public class MaquinaEstados {
    private Integer estado_actual;
    private Automata AFD;

    public void setEstado_actual(Integer estado_actual){
        this.estado_actual = estado_actual;
    }

    public Integer getEstado_actual(){
        return estado_actual;
    }

    public Automata getAFD(){
        return AFD;
    }

    public MaquinaEstados(List<Character> simbolos, List<Integer> estados, List<Integer> estados_iniciales, List<Integer> estados_finales, HashMap<Integer, HashMap<Character, Integer>> matriz){
        AFD = new Automata();
        AFD.inicializarMatriz();

        for(Character simbolo : simbolos)
            AFD.cargarAlfabeto(simbolo);

        for(Integer estado : estados)
            AFD.cargarEstado(estado);

        for(Integer estado_inicial : estados_iniciales) {
            try {
                AFD.establecerQi(estado_inicial);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        for(Integer estado_final : estados_finales) {
            try {
                AFD.establecerQf(estado_final);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        AFD.setMatriz(matriz);
    }

    public void inicializar(){
        this.estado_actual = AFD.getEstado_inicial();
    }

    public Integer getEstadoSiguiente(Character simbolo){
        return AFD.getMatriz().get(estado_actual).get(simbolo);
    }

    public Boolean isFinal(){
        return AFD.getEstados_finales().contains(estado_actual);
    }

    public void acepta(Character simbolo) throws Exception {
        Integer estado_tmp = this.getEstadoSiguiente(simbolo);
        if(estado_tmp != null){
            this.estado_actual = estado_tmp;
        } else {
            System.out.println("El simbolo no pertenece al alfabeto");
            throw new Exception("El simbolo no se acepta en el automata");
        }
    }

    public Boolean cadenaCompleta(String cadena){
        for(Character simbolo : cadena.toCharArray()){
            try{
                this.acepta(simbolo);
            } catch (Exception e){
                System.out.println("La cadena no pertene al Automata");
                return false;
            }
        }
        if(isFinal()){
            System.out.println("La cadena pertenece al automata");
            return true;
        } else {
            System.out.println("La cadena no finalizo en un estado final");
            return false;
        }
    }
}
