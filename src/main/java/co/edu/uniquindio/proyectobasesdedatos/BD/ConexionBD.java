package co.edu.uniquindio.proyectobasesdedatos.BD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.*;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {

    private final String stringConexion = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private Connection cx;
    private String usr = "PROYECTOBASES";
    private String passwd = "admin1234";
    private Statement statement;
    private ResultSet resultSet;



    public static void main(String[] args) {

        ConexionBD conexion  = new ConexionBD();
        conexion.conectar();

    }


    public ConexionBD(){

    }

    public Connection conectar(){
        try{
            Class.forName(driver);
            cx = DriverManager.getConnection(stringConexion, usr, passwd);
            System.out.println("Se conectó a la base de datos mi rey");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se conectó a la base de datos mi rey");
        }
        return cx;
    }

    public ArrayList<Pregunta> listarPreguntas(){

        ArrayList<Pregunta> lista = new ArrayList<>();
        try{
            statement = cx.createStatement();
            resultSet = statement.executeQuery("select p.codigo,p.tipo,p.enunciado,p.grupo from pregunta p");

            while (resultSet.next()){
                lista.add(new Pregunta(
                        resultSet.getInt("CODIGO"),
                        resultSet.getInt("TIPO"),
                        resultSet.getString("ENUNCIADO"),
                        resultSet.getInt("GRUPO")
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;

    }

    public ArrayList<Pregunta> listarPreguntasSegunEncuesta(Encuesta encuesta){

        ArrayList<Pregunta> listaPreguntas = new ArrayList<>();

        try{
            statement = cx.createStatement();
            resultSet = statement.executeQuery("select * from pregunta where encuesta="+encuesta.getCodigo());
            

            while (resultSet.next()){
                listaPreguntas.add(new Pregunta(
                        resultSet.getInt("ENCUESTA_TIPO"),
                        resultSet.getInt("ENCUESTA"),
                        resultSet.getInt("GRUPO"),
                        resultSet.getInt("CODIGO"),
                        resultSet.getInt("TIPO"),
                        resultSet.getString("ENUNCIADO")
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaPreguntas;
    }


    public ArrayList<Opcion> listarOpcionesPregunta(Pregunta pregunta){
        ArrayList<Opcion> lista = new ArrayList<>();
        try{
            statement = cx.createStatement();
            resultSet = statement.executeQuery("select * from opcion where pregunta="+pregunta.getCodigo()+" and grupo="+pregunta.getId_grupo());

            System.out.println("select * from opcion where pregunta="+pregunta.getCodigo()+" and grupo="+pregunta.getId_grupo());

            while (resultSet.next()){
                lista.add(new Opcion(
                        resultSet.getInt("encuesta_tipo"),
                        resultSet.getInt("encuesta"),
                        resultSet.getInt("grupo"),
                        resultSet.getInt("pregunta"),
                        resultSet.getInt("codigo"),
                        resultSet.getString("texto")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Encuesta> listarEncuestas (){
        ArrayList<Encuesta> lista = new ArrayList<>();
        try{
            statement = cx.createStatement();
            resultSet = statement.executeQuery("select * from encuesta");

            while (resultSet.next()){
                lista.add(
                        new Encuesta(
                                resultSet.getInt("CODIGO"),
                                resultSet.getInt("TIPO"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getInt("CATEGORIA"),
                                resultSet.getInt("RANGO_EDADES"),
                                resultSet.getInt("GENERO_OBJETIVO")
                        )
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }


    public boolean crearEncuesta(Encuesta e){
        try{
            statement = cx.createStatement();

            String sentencia2 = "insert into encuesta values("+e.getCodigo()+","+e.getTipo()+ ",\'" +e.getNombre()+ "\'," +e.getCategoria()+","+e.getRango_edades()+","+e.getGenero_objetivo()+")";
            System.out.println(sentencia2);
            resultSet = statement.executeQuery(sentencia2);
        }catch (SQLException e2){
            e2.printStackTrace();
        }

        return true;
    }



    public boolean terminarEncusta(Encuesta e, int grupo){
        try {
            statement = cx.createStatement();
            int codigo = contarCantidadPreguntas();

            int codigoOpcion = contarOpciones()+1;
            for(Pregunta p: e.getPreguntas()){
                p.setCodigo(codigo);
                String sentencia = "insert into pregunta values ("+e.getTipo()+","+e.getCodigo()+","+grupo+","+codigo+","+p.getTipo()+",\'"+p.getEnunciado()+"\')";
                resultSet = statement.executeQuery(sentencia);
                System.out.println(sentencia);

                sentencia = "insert into opcion values ("+e.getTipo()+","+e.getCodigo()+","+grupo+","+codigo+","+codigoOpcion+",\'opcion de respuesta \')";
                System.out.println(sentencia);
                resultSet = statement.executeQuery(sentencia);
                codigo++;
                codigoOpcion++;
            }

            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public int contarOpciones(){
        int valor = 0;
        try{

            statement = cx.createStatement();
            resultSet = statement.executeQuery("select count(o.codigo) from opcion o");

            while (resultSet.next()){
                valor = resultSet.getInt("COUNT(O.CODIGO)");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return valor;
    }

    public boolean crearGrupo(int encuesta_tipo, int encuesta, int codigo, String enunciado){
        try{
            statement = cx.createStatement();

            String sentencia2 = "insert into grupo_preguntas values("+encuesta_tipo+","+encuesta+ "," +codigo+ ",\'" +enunciado+"\')";
            System.out.println(sentencia2);
            System.out.println(sentencia2);
            resultSet = statement.executeQuery(sentencia2);
        }catch (SQLException e2){
            e2.printStackTrace();
        }

        return true;

    }


    public ArrayList<Grupo> obtenerNombreGrupo(Encuesta encuesta){

        ArrayList<Grupo> list = new ArrayList<>();

        try{
            statement = cx.createStatement();
            String sentencia = "select * from grupo_preguntas where encuesta="+encuesta.getCodigo();
            resultSet = statement.executeQuery(sentencia);

            System.out.println(sentencia);

            while (resultSet.next()){
                list.add(
                        new Grupo(
                                resultSet.getInt("ENCUESTA_TIPO"),
                                resultSet.getInt("ENCUESTA"),
                                resultSet.getInt("CODIGO"),
                                resultSet.getString("ENUNCIADO")
                        )
                );
            }

        }catch (SQLException e2){
            e2.printStackTrace();
        }

        return list;
    }

    public int contarCantidadPreguntas(){
        int valor=0;

        try{

            statement = cx.createStatement();
            resultSet = statement.executeQuery("select count(p.codigo) from pregunta p");

            while (resultSet.next()){
                valor = resultSet.getInt("COUNT(P.CODIGO)");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return valor;
    }

    public int contarCantidadGrupo(){
        int valor = 0;
        try{

            statement = cx.createStatement();
            resultSet = statement.executeQuery("select count(c.codigo) from grupo_preguntas c");

            while (resultSet.next()){
                valor = resultSet.getInt("COUNT(C.CODIGO)");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return valor;
    }


    public int contarPresentaciones(){
        int valor = 0;
        try{

            statement = cx.createStatement();
            resultSet = statement.executeQuery("select count(p.codigo) from presentacion p");

            while (resultSet.next()){
                valor = resultSet.getInt("COUNT(P.CODIGO)");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return valor;
    }


    public Presentacion crearPresentacion(Encuesta encuesta, int estado){

        int codigo = contarPresentaciones()+1;
        Presentacion p=null;

        try{
            statement = cx.createStatement();

            String sentencia2 = "insert into presentacion values ("+encuesta.getCodigo()+","+encuesta.getTipo()+","+codigo+","+estado+")";
            System.out.println(sentencia2);
            resultSet = statement.executeQuery(sentencia2);


        }catch (SQLException e2){
            e2.printStackTrace();
        }

        p = obtenerPresentacion(codigo);

        return p;
    }

    public Presentacion obtenerPresentacion(int codigo){

        Presentacion p=null;


        try{
            statement = cx.createStatement();

            String sentencia2 = "select * from presentacion where codigo="+codigo;
            System.out.println(sentencia2);
            resultSet = statement.executeQuery(sentencia2);

            while (resultSet.next()){
                p = new Presentacion(
                        resultSet.getInt("ENCUESTA"),
                        resultSet.getInt("TIPO"),
                        resultSet.getInt("CODIGO"),
                        resultSet.getInt("ESTADO")
                );
            }


        }catch (SQLException e2){
            e2.printStackTrace();
        }

        return p;
    }

    public boolean insertarRespuesta(Encuesta encuesta, Presentacion presentacion, Opcion opcion, Pregunta pregunta ){

        try{
            statement = cx.createStatement();

            String sentencia2 = "insert into respunica values ("+encuesta.getTipo()+","+encuesta.getCodigo()+","+presentacion.getCodigo()+","+pregunta.getId_grupo()+","+pregunta.getCodigo()+","+opcion.getCodigo()+")";
            System.out.println(sentencia2);
            resultSet = statement.executeQuery(sentencia2);


        }catch (SQLException e2){
            e2.printStackTrace();
        }

        return  true;
    }




}
