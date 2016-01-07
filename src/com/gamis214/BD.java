package com.gamis214;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BD {

    public boolean insertar(String nombre){

        boolean validate = false;

        ParametrosConexion parametros = new ParametrosConexion();

        try(Connection conexion = DriverManager.getConnection(parametros.DB_URL,parametros.USER,parametros.PASS)){

            Class.forName(parametros.JDBC_DRIVER);

            if(nombre.isEmpty()){
                nombre = "DEMO NOMBRE";
            }

            PreparedStatement st = conexion.prepareStatement("INSERT INTO persona(nombre) VALUES('"+nombre+"')");

            st.executeUpdate();
            st.close();

            validate = true;

        }catch (Exception e){
            e.getMessage();
            validate = false;
        }finally {

            return validate;

        }

    }

    public ArrayList<Persona> listar(){

        ParametrosConexion parametros = new ParametrosConexion();

        ArrayList<Persona> listaPersona = null;

        try(Connection conexion = DriverManager.getConnection(parametros.DB_URL,parametros.USER,parametros.PASS)){

            Class.forName(parametros.JDBC_DRIVER);

            PreparedStatement st = conexion.prepareStatement("SELECT * FROM persona");

            ResultSet rs = st.executeQuery();

            listaPersona = new ArrayList<>();

            while (rs.next()){
                Persona per = new Persona(rs.getString("nombre"),rs.getInt("id"));
                listaPersona.add(per);
            }

            rs.close();
            st.close();

        }catch (Exception e){
            e.getMessage();
        }
        return listaPersona;
    }

}
