package co.edu.uniquindio.proyectobasesdedatos.BD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Opcion;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Pregunta;
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
            resultSet = statement.executeQuery("SELECT * FROM pregunta");

            while (resultSet.next()){
                lista.add(new Pregunta(
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

        return lista;

    }


    public ArrayList<Opcion> listarOpcionesPregunta(Pregunta pregunta){
        ArrayList<Opcion> lista = new ArrayList<>();
        try{
            statement = cx.createStatement();
            resultSet = statement.executeQuery("select * from opcion where pregunta="+pregunta.getCodigo()+" and grupo="+pregunta.getId_grupo());

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


}
