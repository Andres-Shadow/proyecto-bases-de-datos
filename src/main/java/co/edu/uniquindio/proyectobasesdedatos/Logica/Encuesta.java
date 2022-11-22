package co.edu.uniquindio.proyectobasesdedatos.Logica;

import java.util.ArrayList;

public class Encuesta   {

    private int codigo;
    private int tipo;
    private String nombre;
    private int categoria;
    private int rango_edades;
    private int genero_objetivo;

    private ArrayList<Pregunta> preguntas;

    public Encuesta(int codigo, int tipo, String nombre, int categoria, int rango_edades, int genero_objetivo){
        this.codigo = codigo;
        this.tipo = tipo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.rango_edades = rango_edades;
        this.genero_objetivo = genero_objetivo;
        this.preguntas = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getRango_edades() {
        return rango_edades;
    }

    public void setRango_edades(int rango_edades) {
        this.rango_edades = rango_edades;
    }

    public int getGenero_objetivo() {
        return genero_objetivo;
    }

    public void setGenero_objetivo(int genero_objetivo) {
        this.genero_objetivo = genero_objetivo;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public boolean agregarPreguntas(Pregunta p){
        if(!this.preguntas.contains(p)){
            this.preguntas.add(p);
            return true;
        }
        return false;
    }
}
