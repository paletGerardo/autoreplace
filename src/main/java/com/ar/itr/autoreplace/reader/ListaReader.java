package com.ar.itr.autoreplace.reader;

import org.apache.commons.logging.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.logging.LogFactory.getLog;

public class ListaReader {

String clase = "ListaReader";
private static final Log LOG = getLog(ListaReader.class);

    public List leerLista(String path) throws IOException {
        LOG.info("CLASS '" + clase + "' ====> " + "leerLista()");

        File file = new File(path);
        BufferedReader bf;

        List lista = new ArrayList();
        String linea;

        LOG.info("CLASS '" + clase + "' ====> " + "verificando si existe el archivo");
        if (file.exists()){
            FileReader fileReader = new FileReader(file);
            bf = new BufferedReader(fileReader);

            LOG.info("CLASS '" + clase + "' ====> " + "guardando archivo en la lista");
            while ((linea = bf.readLine()) != null){
                lista.add(linea);
            }


        }else{

        }

        return lista;

    }
}
