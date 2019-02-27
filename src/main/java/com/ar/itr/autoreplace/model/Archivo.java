package com.ar.itr.autoreplace.model;

import com.ar.itr.autoreplace.menu.Menu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
///home/gerr/Desarrollos
public class Archivo {
    //==============================================================//
        static String CLASE = "MostrarMenu";
        private static final Log LOG = LogFactory.getLog(Archivo.class);
    //==============================================================//

    public String pathRaiz;
    public String pathArchivo;
    public String pathCompleta;
    public List<String> contenido;

    public Archivo() {
    }

    public Archivo(String pathRaiz, String pathArchivo) throws IOException {
        this.pathRaiz = pathRaiz;
        this.pathArchivo = pathArchivo;
        this.pathCompleta = pathRaiz + "/" + pathArchivo;
        this.contenido = lectura(pathCompleta);
        LOG.info("CLASS '" + CLASE + "' ====> " + "cargando constructor de Archivo: pathRaiz - parthArchivo - pathCompleta - contenido" + pathRaiz + "-" + pathArchivo + "-" + pathCompleta + "-" + contenido);
    }

    public List<String> lectura(String pathCompleta) throws IOException {
        List<String> contenidoCargado = new ArrayList<>();
        BufferedReader reader = null;
        try {

            String linea = null;
            reader = new BufferedReader(new FileReader(pathCompleta));


            while ((linea = reader.readLine()) != null) {

                contenidoCargado.add(linea);

            }
            this.contenido = contenidoCargado;

        } finally {
            reader.close();
        }
        return contenidoCargado;
    }

    public void escribir(String patronBusqueda, String idParaAgregar) throws IOException {
        LOG.info("CLASS '" + CLASE + "' ====> " + "Ingresando a metodo escribir");

        String suppressWaningCargado = null;

        String linea;
        String lineaAnteriorAPublic;
        String buscarSuppressWarning = "@SuppressWarnings";


        int posicion = 0;
        LOG.info("CLASS '" + CLASE + "' ====> " + "ingresando para recorrer contenido");


        for (int i = 0; i < this.contenido.size(); i++) {
            linea = this.contenido.get(i).toString();
            LOG.info("CLASS '" + CLASE + "' ====> " + "recorriendo contenido");
            if (linea.contains(patronBusqueda.trim())) {

                posicion = i;
                suppressWaningCargado = "@SuppressWarnings(\"" + idParaAgregar + "\")";
                lineaAnteriorAPublic = this.contenido.get(posicion -1).toString();

                if (lineaAnteriorAPublic.contains(buscarSuppressWarning.trim())) {
                    System.out.println("=============================encontro supress: cambiar por codigo");
                }
            }
        }

        this.contenido.add(posicion, suppressWaningCargado);

        File file = new File(this.pathCompleta);
        FileWriter fw = new FileWriter(file);
        BufferedWriter escritura = new BufferedWriter(fw);

        for(int i=0;i<this.contenido.size();i++){
            escritura.write(this.contenido.get(i));
            escritura.newLine();
        }
        escritura.close();
    }
}
