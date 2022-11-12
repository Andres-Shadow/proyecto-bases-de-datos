package co.edu.uniquindio.proyectobasesdedatos.BD;
import co.edu.uniquindio.proyectobasesdedatos.Logica.Pregunta;

import java.sql.*;
import java.util.ArrayList;

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


}
