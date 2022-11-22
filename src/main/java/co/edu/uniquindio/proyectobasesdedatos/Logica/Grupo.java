package co.edu.uniquindio.proyectobasesdedatos.Logica;

public class Grupo {

    private int encuesta_tip;
    private int encuesta;
    private int codigo;
    private String enunciado;

    public Grupo(int encuesta_tip, int encuesta, int codigo, String enunciado) {
        this.encuesta_tip = encuesta_tip;
        this.encuesta = encuesta;
        this.codigo = codigo;
        this.enunciado = enunciado;
    }

    public int getEncuesta_tip() {
        return encuesta_tip;
    }

    public void setEncuesta_tip(int encuesta_tip) {
        this.encuesta_tip = encuesta_tip;
    }

    public int getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
