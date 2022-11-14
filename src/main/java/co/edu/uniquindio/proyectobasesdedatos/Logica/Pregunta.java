package co.edu.uniquindio.proyectobasesdedatos.Logica;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {


    private int encuesttaTipo;
    private int id_encuesta;
    private int id_grupo;
    private int codigo;
    private int tipo;
    private String enunciado;

    private List<Opcion> opciones = new ArrayList<>();

    public Pregunta(int encuesttaTipo, int id_encuesta, int id_grupo, int codigo, int tipo, String enunciado){
        this.encuesttaTipo = encuesttaTipo;
        this.id_encuesta = id_encuesta;
        this.id_grupo = id_grupo;
        this.codigo = codigo;
        this.tipo = tipo;
        this.enunciado = enunciado;
    }

    public int getEncuesttaTipo() {
        return encuesttaTipo;
    }

    public void setEncuesttaTipo(int encuesttaTipo) {
        this.encuesttaTipo = encuesttaTipo;
    }

    public int getId_encuesta() {
        return id_encuesta;
    }

    public void setId_encuesta(int id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
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

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public String toString() {
        return enunciado;
    }
}
