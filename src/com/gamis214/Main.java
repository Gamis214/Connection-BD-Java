package com.gamis214;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Main mn = new Main();
        BD bd = new BD();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------");
        System.out.println("Selecione una opcion: \n 1.- Inercion automatica \n 2.- Insercion Manual \n 3.- Listado");
        int opc = scanner.nextInt();
        switch (opc){
            case 1:
                boolean validar = bd.insertar("");
                mn.validar(validar);
                break;
            case 2:
                System.out.println("Introdusca su nombre: ");
                String nombre = scanner.next();
                boolean validar2 = bd.insertar(nombre);
                mn.validar(validar2);
                break;
            case 3:

                for (Persona p : bd.listar()) {
                    System.out.println("-- ID: "+p.getId()+"    -- Nombre: "+p.getNombre());
                }

                break;
            default:
                System.out.println(" OPCION INCORRECTA");
                break;
        }
    }

    public void validar(boolean val){
        if(val){
            System.out.println("INSERCION EXITOSA");
        }else{
            System.out.println("ERROR AL INSERTAR");
        }
    }
}
