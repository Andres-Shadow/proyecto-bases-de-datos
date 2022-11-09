package co.edu.uniquindio.proyectobasesdedatos.Principal;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import co.edu.uniquindio.proyectobasesdedatos.Controladores.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Principal extends Application {

    private ConexionBD conexionBD;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage){

        try {
            Parent root = FXMLLoader.load((getClass().getResource("/co/edu/uniquindio/proyectobasesdedatos/vista/VentanaInicio.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ENCUESTAS UQ");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
