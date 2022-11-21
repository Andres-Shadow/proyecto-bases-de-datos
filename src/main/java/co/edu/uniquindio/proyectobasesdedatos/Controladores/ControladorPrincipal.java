package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Encuesta;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Opcion;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Pregunta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {


    /**--------VARIABLES------------*/

    private ConexionBD conexion;
    private Pregunta preguntaSeleccionada;
    private Encuesta encuesta;
    private int grupo;

    /**------ VISTA ----------**/

    @FXML
    private TextArea area_pregunta;

    @FXML
    private ComboBox<String> comboCategoria;
    @FXML
    private ComboBox<String> comboEdades;
    @FXML
    private ComboBox<String> comboGenero;
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private Button btnAddPregunta;


    @FXML
    private TextField txtNombreGrupo;


    @FXML
    private Button btnCrearGrupo;

    @FXML
    private TextField txtCodEn;

    @FXML
    private TextField txtNombre;

    @FXML
    private ListView<Pregunta> lista;


    @FXML
    private ListView<Opcion> list_opciones;


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

        String[] tipo = {"publica","privada"};
        String[] categoria = {"academico", "deportes"};
        String[] edades = {"15-18","18-25","25-35"};
        String[] genero = {"mujer", "hombre", "todos"};

        comboTipo.getItems().addAll(tipo);
        comboCategoria.getItems().addAll(categoria);
        comboEdades.getItems().addAll(edades);
        comboGenero.getItems().addAll(genero);

        btnAddPregunta.setVisible(false);
    }

    @FXML
    public void crearEncuesta(){

        int codigo = Integer.parseInt(txtCodEn.getText());
        int tipo = comboTipo.getSelectionModel().getSelectedIndex()+1;
        String nombre = txtNombre.getText();
        int categoria = comboCategoria.getSelectionModel().getSelectedIndex()+1;
        int rango = comboEdades.getSelectionModel().getSelectedIndex()+1;
        int genero = comboGenero.getSelectionModel().getSelectedIndex()+1;

        Encuesta e = new Encuesta(codigo, tipo, nombre, categoria, rango, genero);

        boolean a = this.conexion.crearEncuesta(e);

        if(!a){
            System.out.println("nonas mi pez");
        }
        else{
            System.out.println("sisas mi pez");
            this.encuesta = e;
            this.btnAddPregunta.setVisible(true);
        }


        limpiar();
    }

    @FXML
    public void agregarPregunta(){

        boolean centinela = encuesta.agregarPreguntas(preguntaSeleccionada);

        if(centinela)
            System.out.println("pregunta agregada");
        else
            System.out.println("pregunta repetida");
    }


    @FXML
    public void terminarEncuesta(){

        boolean centinela = conexion.terminarEncusta(this.encuesta, grupo);
    }

    @FXML
    public void crearGrupo(){
        String enunciado = txtNombreGrupo.getText();
        int encuesta_tipo = this.encuesta.getTipo();
        int encuesta = this.encuesta.getCodigo();
        int codigo = conexion.contarCantidadGrupo()+1;

        conexion.crearGrupo(encuesta_tipo, encuesta, codigo, enunciado);
        this.grupo = codigo;
    }
    /**---------------- MEOTODOS VISUALES ------------------------**/
    public void setearListView(){
        ArrayList<Pregunta> lista = conexion.listarPreguntas();
        this.lista.getItems().addAll(lista);
    }

    public void setearOpciones(ArrayList<Opcion> lista){
        this.list_opciones.getItems().clear();
        this.list_opciones.getItems().addAll(lista);
    }

    public void limpiar(){
        this.txtNombre.setText("");
        this.txtCodEn.setText("");
    }




}
