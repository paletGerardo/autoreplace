package com.ar.itr.autoreplace.prueba;

import com.ar.itr.autoreplace.menu.Menu;
import com.ar.itr.autoreplace.model.Archivo;
import com.ar.itr.autoreplace.reader.ArchivoReader;
import com.ar.itr.autoreplace.writer.ArchivoWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LecturaSinBatch {
    private static final Log LOG = LogFactory.getLog(LecturaSinBatch.class);

    public static void main(String[] args) throws IOException {
        String clase = "LecturaSinBatch";

        ArchivoReader archivoReader = new ArchivoReader();
        ArchivoWriter archivoWriter = new ArchivoWriter();
        Archivo archivo;

        LOG.info("CLASS '" + clase + "' ====> " + "llamada al menu de opciones");
        Map opciones = Menu.opciones();
        String opcion = opciones.get("opcion").toString();
        String path = opciones.get("path").toString();
        String patronBusqueda = opciones.get("patronBusqueda").toString();
        String lineaParaAgregar = opciones.get("lineaParaAgregar").toString();

        LOG.info("CLASS '" + clase + "' ====> " + "verificando la opcion");
        if (opcion.equals("a")){
            archivo = archivoReader.lectura(path);
            archivoWriter.escribir(archivo, lineaParaAgregar, patronBusqueda);

        }else if(opcion.equals("l")) {
            Archivo archivoConLista = archivoReader.lectura(path);
            List<String> lista;
            lista = archivoConLista.getContenido();

            for (String pathDeLaLista : lista){
                archivo = archivoReader.lectura(pathDeLaLista);
                archivoWriter.escribir(archivo, lineaParaAgregar, patronBusqueda);
            }
        }
    }
}

// /home/gerr/Desarrollos/archivo.txt