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
    }

    public List<String> lectura(String pathCompleta) throws IOException {
        //metodo de lectura de archivo que devuelve List<String> y se carga en la variable contenido
        List<String> contenidoCargado = new ArrayList<>();
        BufferedReader reader = null;
        try {
            String linea = null;
            reader = new BufferedReader(new FileReader(pathCompleta));

            while ((linea = reader.readLine()) != null) {
                contenidoCargado.add(linea);
            }
        } finally {
            reader.close();
        }
        return contenidoCargado;
    }

    public void escribir(String valorDeEntrada) throws IOException {
        // metodo para procesar el contenido

        String suppressWaningCargado = null;

        String linea; // aca se guarda cada linea que se recorre en el contenido
        String SuppressBuscado = "@SuppressWarnings"; // parametro de busqueda en el contenido
        String publicClassBuscado = "public class"; // segundo parametro de busqueda si no encuentra el primero

        int posicion = 0;
        boolean haySuppress = false; //bandera para saber si se encontro un SuppressWarnings y no realizar la busqueda de public

        for (int i = 0; i < this.contenido.size(); i++) { // inicio de recorrido del contenido
            linea = this.contenido.get(i).toString();

            if (linea.contains(SuppressBuscado.trim())) { //si la linea contiene suppress
                List<String> valores = buscaValor(linea); // llama al metodo buscarValor para separa los valores contenidos
                posicion = i; // se guarda la posicion donde se encontro
                suppressWaningCargado = armarSuppress(valores, valorDeEntrada);//arma el suppress agregando el valor de entrada
                haySuppress = true;
                break; // si encontro suppress ya no sigue buscando

            } else {
                if (linea.contains(publicClassBuscado.trim())) { // busca linea con public class
                    posicion = i;
                    suppressWaningCargado = armarSuppressPublic(valorDeEntrada); // arma suppress solo con valor de entrada
                    break;
                }
            }
        }

        if (haySuppress) { // si hay suppress, agrega al contenido la nueva linea que reemplaza al anterior suppress
            this.contenido.add(posicion, suppressWaningCargado);
            this.contenido.remove(posicion + 1); //borra linea vieja de suppress
        } else {
            this.contenido.add(posicion, suppressWaningCargado);// si no habia suppress, solo lo agrega antes del public class
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

    private List<String> buscaValor(String linea) {
       //Metodo buscarValor: recibe linea de String y busca valores a devolver en una lista de Sring
        String stringDeConstruccion = ""; // Se crea un StringBuilder para agregar cada chart
        StringBuilder stringBuilder = new StringBuilder(stringDeConstruccion);
        List<String> listaDeValores = new ArrayList<>();// lista para devolver los valores
        String valorLimpio; // se usa para el traspaso del StrinBuilder a la listaDeValores

        for (int j = 0; j < linea.length(); j++) {
            if (linea.charAt(j) == '"') { // se buscan valores dentro de la comilla
                j++;
                while (linea.charAt(j) != '"') {
                    stringBuilder.append(linea.charAt(j));
                    j++;
                }
                valorLimpio = String.valueOf(stringBuilder);
                listaDeValores.add(valorLimpio);
                stringBuilder.setLength(0);// se limpia string bulider para la nueva carga
            }
        }
        return listaDeValores;
    }

    private String armarSuppress(List<String> valores, String valorDeEntrada) {
        //metodo para armar el suppress segun los valores recibidos
        String stringLimpio = "";
        StringBuilder stringBuilder = new StringBuilder(stringLimpio);
        String elSuppressWarning;
        boolean valorEntradaRepetido = false;
        String armado;
        for (String valor : valores) {
            stringBuilder.append("\"" + valor + "\",");
            if (valor.equals(valorDeEntrada)) { // si el valor de entrada es repetido cambia la bandera
                valorEntradaRepetido = true;
            }
        }
        if (valorEntradaRepetido) { // si el valor de entrada esta repetido no lo agrega
            armado = String.valueOf(stringBuilder);
        } else {
            stringBuilder.append("\"" + valorDeEntrada + "\"");
            armado = String.valueOf(stringBuilder);
        }

        elSuppressWarning = "@SuppressWarnings({" + armado + "})";
        return limpiarComaFinal(elSuppressWarning);
//        return  elSuppressWarning;
    }

    private String armarSuppressPublic(String valorDeEntrada) {

        return "@SuppressWarnings(\"" + valorDeEntrada + "\")";
    }

    //este metodo limpia la posible coma final si se cargo 2 veces el mismo codigo
    //ya que en el metodo armarSuppres los valores se arman con los ya obtenidos
    //y al no agregarce el valor de entrada del usuario, la lista se arma nuevamente
    //y agrega la coma al final
    private String limpiarComaFinal(String elSuppressWarning) {
        int tamanio = elSuppressWarning.length();
        String hayComa = elSuppressWarning.substring(tamanio-3,tamanio-2);
        if (hayComa.equals(",")){
            String antesDeLaComa = elSuppressWarning.substring(0,tamanio-3);
            String despuesDeLaComa = elSuppressWarning.substring(tamanio - 2,tamanio);
            return antesDeLaComa.concat(despuesDeLaComa);
        }
        return elSuppressWarning;
    }
}
