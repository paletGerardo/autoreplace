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

    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        ArchivoReader archivoReader = new ArchivoReader();
        ArchivoWriter archivoWriter = new ArchivoWriter();

        if (menu.getOpcion().equals("a")){
            archivoWriter.escribir(menu.getArchivo(), menu.getLineaParaAgregar(), menu.getPatronBusqueda());
        }else if(menu.getOpcion().equals("l")) {
            List<String> lista;
            lista = menu.getArchivo().getContenido();
            Archivo archivo;

            for (String pathDeLaLista : lista){
                archivo = archivoReader.lectura(pathDeLaLista);
                archivoWriter.escribir(archivo, menu.getLineaParaAgregar(), menu.getPatronBusqueda());
            }
        }
    }
}

// /home/gerr/Desarrollos/archivo.txt