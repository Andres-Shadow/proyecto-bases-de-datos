package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;

public class ControladorPrincipal {


    private ConexionBD conexion;


    public void initialize(ConexionBD conexionBD) {
        this.conexion = conexionBD;
    }


}
