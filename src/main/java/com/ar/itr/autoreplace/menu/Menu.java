package com.ar.itr.autoreplace.menu;


import com.ar.itr.autoreplace.model.Archivo;
import com.ar.itr.autoreplace.reader.ArchivoReader;
import com.ar.itr.autoreplace.writer.ArchivoWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;

public class Menu {
    private String opcion;
    private Archivo archivo;
    private String patronBusqueda;
    private String lineaParaAgregar;

    public Menu() {

        Archivo archivo;
        ArchivoReader archivoReader = new ArchivoReader();
        Scanner scanner = new Scanner(in);

        System.out.println("====================================");
        System.out.println("                MENU                ");
        System.out.println("====================================");

        System.out.println("1. Leer un archivo  (a)");
        System.out.println("2. Leer una lista   (l)");
        System.out.println("3. Salir del sistema   (S)");
        System.out.println("====================================");

        System.out.println("Elegir opcion: ");
        this.opcion = scanner.nextLine();


        System.out.println("Ingresar Path del archivo");
        String pathArchivo = scanner.nextLine();
        try {
            this.archivo = archivoReader.lectura(pathArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Ingresar el patron de busqueda: ");
        this.patronBusqueda = scanner.nextLine();

        System.out.println("Ingresar la linea que desea agregar: ");
        this.lineaParaAgregar = scanner.nextLine();


    }

    public Menu(String opcion, Archivo archivo, String patronBusqueda, String lineaParaAgregar) {
        this.opcion = opcion;
        this.archivo = archivo;
        this.patronBusqueda = patronBusqueda;
        this.lineaParaAgregar = lineaParaAgregar;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public String getPatronBusqueda() {
        return patronBusqueda;
    }

    public void setPatronBusqueda(String patronBusqueda) {
        this.patronBusqueda = patronBusqueda;
    }

    public String getLineaParaAgregar() {
        return lineaParaAgregar;
    }

    public void setLineaParaAgregar(String lineaParaAgregar) {
        this.lineaParaAgregar = lineaParaAgregar;
    }
}
