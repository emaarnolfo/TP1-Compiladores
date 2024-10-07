package org.compPL1;

import java.util.*;

public class Automata {
    private List<Character> alfabeto = new ArrayList<Character>();
    private List<Integer> estados = new ArrayList<Integer>();
    private Integer estado_inicial;
    private List<Integer> estados_finales = new ArrayList<Integer>();
    private HashMap<Integer, HashMap<Character, Integer>> matriz = new HashMap<>();

    // ====== GETERS AND SETTERS =======
    public List<Character> getAlfabeto() {
        return alfabeto;
    }
    public void setAlfabeto(List<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }
    public List<Integer> getEstados() {
        return estados;
    }
    public void setEstados(List<Integer> estados) {
        this.estados = estados;
    }
    public Integer getEstado_inicial() {
        return estado_inicial;
    }
    public void setEstado_inicial(Integer estado_inicial) {
        this.estado_inicial = estado_inicial;
    }
    public List<Integer> getEstados_finales() {
        return estados_finales;
    }
    public void setEstados_finales(List<Integer> estados_finales) {
        this.estados_finales = estados_finales;
    }
    public HashMap<Integer, HashMap<Character, Integer>> getMatriz() {
        return matriz;
    }
    public void setMatriz(HashMap<Integer, HashMap<Character, Integer>> matriz) {
        this.matriz = matriz;
    }
    //----- Final setters y gettters ------


    public void cargarAlfabeto(Character simobolo){
        this.alfabeto.add(simobolo);
    }

    public void cargarEstado(Integer estado){
        this.estados.add(estado);
    }
    public void establecerQi(Integer estado) throws Exception{
        if(this.estados.contains(estado)){
            this.estado_inicial = estado;
        } else {
            System.out.println("El estado no se encuentra en la lista de estados");
            throw new Exception("El estado no se encuentra en la lista de estados");
        }
    }
    public void establecerQf(Integer estadoFinal) throws Exception{
        if(this.estados.contains(estadoFinal)){
            this.estados_finales.add(estadoFinal);
        } else {
            System.out.println("El estado no se encuentra en la lista de estados");
            throw new Exception("El estado no se encuentra en la lista de estados");
        }
    }

    public void cargarMatriz(){
        inicializarMatriz();
        // se cargan las transiciones para cada estado
        for(Character simbolo : alfabeto){
            for(Integer estado : estados){
                System.out.println("Indique el estado siguiente si llega una: " + simbolo + ". Estando en el estado: " + estado + ": ");
                Scanner scanner = new Scanner(System.in);
                Integer estado_siguiente = scanner.nextInt();
                if(estado_siguiente < estados.size())
                    matriz.get(estado).put(simbolo, estado_siguiente);
            }
        }
    }

    public void inicializarMatriz(){
        for(Integer estado : estados){
            matriz.put(estado, new HashMap<>());
        }
    }
}
