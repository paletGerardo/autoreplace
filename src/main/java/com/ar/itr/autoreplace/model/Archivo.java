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
    static String CLASE = "ARCHIVO";
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

    public void escribir(String valorDeEntrada) throws IOException {
        LOG.info("CLASS '" + CLASE + "' ====> " + "Ingresando a metodo escribir");

        String suppressWaningCargado = null;

        String linea;
        String buscarSuppressWarningSimple = "@SuppressWarnings";
        String buscarPublicClass = "public class";

        int posicion = 0;
        boolean haySuppress = false;

        LOG.info("CLASS '" + CLASE + "' ====> " + "ingresando para recorrer contenido");
        for (int i = 0; i < this.contenido.size(); i++) {
            linea = this.contenido.get(i).toString();

            if (linea.contains(buscarSuppressWarningSimple.trim())) {
                System.out.println("SUPPRESSWARNING ENCONTRADO >====================");
                List<String> valores = buscaValor(linea);
                System.out.println(valores + "VALORES ENCONTRADOS");
                posicion = i;
                suppressWaningCargado = armarSuppress(valores,valorDeEntrada);
                System.out.println(suppressWaningCargado + "SUPPRES CARGADO EN LINEA 74");
                haySuppress= true;
                break;

            } else {
                if (linea.contains(buscarPublicClass.trim())) {
                    System.out.println("SOLO SE ENCONTRO LA LINEA PUBLIC >====================");
                    posicion = i;
                    suppressWaningCargado = armarSuppressPublic(valorDeEntrada);
                    break;
                }
            }
        }

       if (haySuppress){
           this.contenido.add(posicion, suppressWaningCargado);
           System.out.println("borrando posicion repetida");
           this.contenido.remove(posicion+1);
       }else{
           this.contenido.add(posicion, suppressWaningCargado);
       }

        File file = new File(this.pathCompleta);
        FileWriter fw = new FileWriter(file);
        BufferedWriter escritura = new BufferedWriter(fw);

        for (int i = 0; i < this.contenido.size(); i++) {
            escritura.write(this.contenido.get(i));
            escritura.newLine();
        }
        escritura.close();
    }

    private List<String> buscaValor(String linea){
        LOG.info("CLASS '" + CLASE + "' ====> " + "Metodo buscarValor: recibe linea de String y busca valores a " +
                "devolver en una lista de Sring ");
        String stringDeConstruccion = new String(""); // Se crea un StringBuilder para agregar cada chart
        StringBuilder stringBuilder = new StringBuilder(stringDeConstruccion);
        List<String> listaDeValores= new ArrayList<>();// lista para devolver los valores
        String valorLimpio = ""; // se usa para el traspaso del StrinBuilder a la listaDeValores

        for(int j= 0; j < linea.length(); j++){
            if(linea.charAt(j) == '"'){

                j++;
                while (linea.charAt(j) != '"'){
                    stringBuilder.append(linea.charAt(j));
                    j++;
                }
                valorLimpio = String.valueOf(stringBuilder);
                listaDeValores.add(valorLimpio);
                stringBuilder.setLength(0);
            }
        }
        return listaDeValores;
    }

    private String armarSuppress(List<String> valores, String valorDeEntrada){
        LOG.info("CLASS '" + CLASE + "' ====> " + "DENTRO DEL METODO ARMARSUPPRESS: la lista de valores es " + valores + "y la lista de entradas es " + valorDeEntrada);
        String stringLimpio = new String("");
        StringBuilder stringBuilder = new StringBuilder(stringLimpio);
        for (String valor : valores){
            stringBuilder.append("\""+valor+"\",");
        }
        stringBuilder.append("\""+valorDeEntrada+"\"");
        String armado = String.valueOf(stringBuilder);
        return "@SuppressWarnings({"+armado+"})";

    }

    private String armarSuppressPublic(String valorDeEntrada){

        return "@SuppressWarnings(\"" + valorDeEntrada + "\")";
    }
}
