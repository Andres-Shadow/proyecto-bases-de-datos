package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import javafx.application.Application;
import javafx.stage.Stage;

public class ControladorPrincipal extends Application {


    private ConexionBD conexion;
    @Override
    public void start(Stage primaryStage) throws Exception {

        conexion = new ConexionBD();
        conexion.conectar();
    }
}
