package co.edu.uniquindio.proyectobasesdedatos.Logica;

public class Presentacion {

    private int encuesta;
    private int tipo_encuesta;
    private int codigo;
    private int estado;

    public Presentacion(int encuesta, int tipo_encuesta, int codigo, int estado) {
        this.encuesta = encuesta;
        this.tipo_encuesta = tipo_encuesta;
        this.codigo = codigo;
        this.estado = estado;
    }

    public int getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    public int getTipo_encuesta() {
        return tipo_encuesta;
    }

    public void setTipo_encuesta(int tipo_encuesta) {
        this.tipo_encuesta = tipo_encuesta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
