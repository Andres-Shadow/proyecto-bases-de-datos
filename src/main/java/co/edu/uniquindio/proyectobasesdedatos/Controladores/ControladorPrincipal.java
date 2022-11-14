package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Opcion;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Pregunta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {


    private ConexionBD conexion;

    private Pregunta preguntaSeleccionada;

    @FXML
    private ListView<Pregunta> lista;


    @FXML
    private ListView<Opcion> list_opciones;

    @FXML
    private TextArea area_pregunta;


    @FXML
    private TextArea area_opciones;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexion = new ConexionBD();
        conexion.conectar();
        setearListView();
        lista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pregunta>() {
            @Override
            public void changed(ObservableValue<? extends Pregunta> observable, Pregunta oldValue, Pregunta newValue) {
                preguntaSeleccionada = lista.getSelectionModel().getSelectedItem();
                area_pregunta.setText(preguntaSeleccionada.toString());
                ArrayList<Opcion> opcions = conexion.listarOpcionesPregunta(preguntaSeleccionada);
                setearOpciones(opcions);

            }
        });
    }

    public void setearListView(){
        ArrayList<Pregunta> lista = conexion.listarPreguntas();

        this.lista.getItems().addAll(lista);
    }

    public void setearOpciones(ArrayList<Opcion> lista){
        this.list_opciones.getItems().clear();
        this.list_opciones.getItems().addAll(lista);
    }


}
