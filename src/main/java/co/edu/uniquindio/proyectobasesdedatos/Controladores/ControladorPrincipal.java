package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Pregunta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {


    private ConexionBD conexion;

    private Pregunta preguntaSeleccionada;

    @FXML
    private ListView<Pregunta> lista;

    @FXML
    private Label lblPreguntaSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new ConexionBD();
        conexion.conectar();
        lblPreguntaSeleccionada.setText("");
        setearListView();
        lista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pregunta>() {
            @Override
            public void changed(ObservableValue<? extends Pregunta> observable, Pregunta oldValue, Pregunta newValue) {
                preguntaSeleccionada = lista.getSelectionModel().getSelectedItem();
                lblPreguntaSeleccionada.setText(preguntaSeleccionada.getEnunciado());
            }
        });
    }

    public void setearListView(){
        ArrayList<Pregunta> lista = conexion.listarPreguntas();

        this.lista.getItems().addAll(lista);


    }


}
