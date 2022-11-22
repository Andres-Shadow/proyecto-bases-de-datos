package co.edu.uniquindio.proyectobasesdedatos.Controladores;

import co.edu.uniquindio.proyectobasesdedatos.BD.ConexionBD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.*;
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
    private Opcion opcionSeleccionada;
    private Encuesta encuesta;

    private int grupo;

    /**------ VISTA ENCUESTAS ----------**/
    private Encuesta encuestaSeleccionadaPresentacion;
    private int contadorIndicesGrupo = 0;
    private int contadorPreguntasPresentacion = 0;
    private ArrayList<Pregunta> listaPreguntasPresentacion;
    private Presentacion presentacion;
    private Pregunta preguntaPresentacion;


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


    /**----------------VISTA PRESENTACION ------------------**/

    @FXML
    private ListView<Encuesta> lst_encuetas;

    @FXML
    private TextField txtPreguntaPresentacion;

    @FXML
    private TextField txtNombreEncustaPresentacion;

    @FXML
    private TextField txtEnunciadoEncuestaPresentacion;

    @FXML
    private ListView<Opcion> listOpcionesPresentacion;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaPreguntasPresentacion = new ArrayList<>();
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

        lst_encuetas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Encuesta>() {
            @Override
            public void changed(ObservableValue<? extends Encuesta> observable, Encuesta oldValue, Encuesta newValue) {
                encuestaSeleccionadaPresentacion = lst_encuetas.getSelectionModel().getSelectedItem();
                presentacion= conexion.crearPresentacion(encuestaSeleccionadaPresentacion, 1);
                lst_encuetas.setVisible(false);
                setearValoresPresentacion();

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
    public void responderPregunta(){

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


    /**-----------------PRESENTACIÓN-----------------------**/


    public void setearValoresPresentacion(){

        ArrayList<Grupo> gruposEncuesta = conexion.obtenerNombreGrupo(this.encuestaSeleccionadaPresentacion);
        this.listaPreguntasPresentacion = conexion.listarPreguntasSegunEncuesta(this.encuestaSeleccionadaPresentacion);

        if(gruposEncuesta.isEmpty())
            System.out.println("grupovacio");
        this.txtNombreEncustaPresentacion.setText(encuestaSeleccionadaPresentacion.getNombre());

        if(gruposEncuesta.size()==1)
            txtEnunciadoEncuestaPresentacion.setText(gruposEncuesta.get(0).getEnunciado());
        else{
            if(contadorIndicesGrupo!=gruposEncuesta.size()){
                txtEnunciadoEncuestaPresentacion.setText(gruposEncuesta.get(contadorIndicesGrupo).getEnunciado());
                this.contadorIndicesGrupo++;
            }
        }

        if(this.listaPreguntasPresentacion.size()==1){
            txtPreguntaPresentacion.setText(this.listaPreguntasPresentacion.get(0).getEnunciado());
            ArrayList<Opcion> opcions = conexion.listarOpcionesPregunta(this.listaPreguntasPresentacion.get(0));
            setearOpcionesPresentacion(opcions);
        }else{
            if(contadorPreguntasPresentacion!=this.listaPreguntasPresentacion.size()){
                txtPreguntaPresentacion.setText(this.listaPreguntasPresentacion.get(contadorPreguntasPresentacion).getEnunciado());
                ArrayList<Opcion> opcions = conexion.listarOpcionesPregunta(this.listaPreguntasPresentacion.get(contadorPreguntasPresentacion));
                setearOpcionesPresentacion(opcions);
                this.contadorPreguntasPresentacion++;
            }
        }


    }

    @FXML
    public void responder(){

        Opcion seleccionada = listOpcionesPresentacion.getSelectionModel().getSelectedItem();
        this.preguntaPresentacion = this.listaPreguntasPresentacion.get(contadorPreguntasPresentacion);

        boolean correcto = conexion.insertarRespuesta(this.encuestaSeleccionadaPresentacion, this.presentacion, seleccionada, preguntaPresentacion);


    }

    /**---------------- MEOTODOS VISUALES ------------------------**/
    public void setearListView(){
        ArrayList<Pregunta> lista = conexion.listarPreguntas();
        this.lista.getItems().addAll(lista);

        ArrayList<Encuesta> lisa2 = conexion.listarEncuestas();
        this.lst_encuetas.getItems().addAll(lisa2);
    }

    public void setearOpciones(ArrayList<Opcion> lista){
        this.list_opciones.getItems().clear();
        this.list_opciones.getItems().addAll(lista);
    }

    private void setearOpcionesPresentacion(ArrayList<Opcion> opcions) {
        this.listOpcionesPresentacion.getItems().clear();
        this.listOpcionesPresentacion.getItems().addAll(opcions);
    }

    public void limpiar(){
        this.txtNombre.setText("");
        this.txtCodEn.setText("");
    }




}
