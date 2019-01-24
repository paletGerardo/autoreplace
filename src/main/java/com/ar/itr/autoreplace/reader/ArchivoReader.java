package com.ar.itr.autoreplace.reader;

import com.ar.itr.autoreplace.model.Archivo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.logging.LogFactory.getLog;

//Retorna el archivo leido en una clase de tipo "archivo" con su contenido en un List<String>
public class ArchivoReader {
    private static final Log LOG = getLog(ArchivoReader.class);

    public Archivo lectura(String path) throws IOException {

        LOG.info("CLASS 'ArchivoReader': leyendo archivo");
        Archivo archivo = new Archivo();
        BufferedReader reader = null;
        try {

            String line = null;
            reader = new BufferedReader(new FileReader(path));
            List<String> contenidoLinea = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                contenidoLinea.add(line);

            }
            LOG.info("CLASS 'ArchivoReader': guardando informacion en la instancia de archivo");
            archivo.setPath(path);
            archivo.setContenido(contenidoLinea);

        } finally {
            LOG.info("CLASS 'ArchivoReader': cerrando archivo");
            reader.close();
        }
        LOG.info("CLASS 'ArchivoReader': retorna archivo");
        return archivo;
    }
}
