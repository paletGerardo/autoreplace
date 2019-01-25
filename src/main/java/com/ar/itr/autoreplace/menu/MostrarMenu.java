package com.ar.itr.autoreplace.menu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;

public class MostrarMenu {
    String clase = "Menu";
    private static final Log LOG = LogFactory.getLog(Menu.class);

    public static Map opciones(){

        Scanner scanner = new Scanner(in);
        Map<String,String> opciones =  new HashMap<String,String>();

        System.out.println("====================================");
        System.out.println("                MENU                ");
        System.out.println("====================================");

        System.out.println("1. Leer un archivo  (a)");
        System.out.println("2. Leer una lista   (l)");
        System.out.println("3. Salir del sistema   (S)");
        System.out.println("====================================");
        System.out.println("Elegir opcion: ");
        String opcion = scanner.nextLine();


        System.out.println("Ingresar Path del archivo");
        String pathArchivo = scanner.nextLine();

        System.out.println("Ingresar el patron de busqueda: ");
        String patronBusqueda = scanner.nextLine();

        System.out.println("Ingresar la linea que desea agregar: ");
        String lineaParaAgregar = scanner.nextLine();

        opciones.put("opcion", opcion);
        opciones.put("path", pathArchivo);
        opciones.put("patronBusqueda", patronBusqueda);
        opciones.put("lineaParaAgregar", lineaParaAgregar);

        return opciones;
    }
}
