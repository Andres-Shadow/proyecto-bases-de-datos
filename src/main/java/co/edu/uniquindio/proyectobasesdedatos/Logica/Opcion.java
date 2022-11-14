package co.edu.uniquindio.proyectobasesdedatos.Logica;

public class Opcion {

    private int encuesta_tipo;
    private int encuesta;
    private int grupo;
    private int fk_pregunta;
    private int codigo;
    private String texto;

    public Opcion(int encuesta_tipo, int encuesta, int grupo, int fk_pregunta, int codigo, String texto){
        this.encuesta_tipo = encuesta_tipo;
        this.encuesta = encuesta;
        this.grupo = grupo;
        this.fk_pregunta = fk_pregunta;
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getEncuesta_tipo() {
        return encuesta_tipo;
    }

    public void setEncuesta_tipo(int encuesta_tipo) {
        this.encuesta_tipo = encuesta_tipo;
    }

    public int getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getFk_pregunta() {
        return fk_pregunta;
    }

    public void setFk_pregunta(int fk_pregunta) {
        this.fk_pregunta = fk_pregunta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
