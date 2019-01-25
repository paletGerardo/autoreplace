package com.ar.itr.autoreplace.writer;

import com.ar.itr.autoreplace.menu.Menu;
import com.ar.itr.autoreplace.model.Archivo;
import org.apache.commons.logging.Log;

import java.io.*;
import java.util.List;

import static org.apache.commons.logging.LogFactory.getLog;

// /home/gerr/Desarrollos/archivo.txt
public class ArchivoWriter {

    private static final Log LOG = getLog(ArchivoWriter.class);

    public void escribir(Menu menu) throws IOException {
        LOG.info("CLASS 'ArchivoWriter'====> " + "iniciando metodo escribir");

        String linea;
        int posicion = 0;
        List<String> lista = menu.archivo.contenido;
        LOG.info("CLASS 'LA LISTA'====> " + lista);

        for (int i = 0; i < lista.size(); i++) {
            linea = lista.get(i).toString();

            if (linea.contains(menu.patronBusqueda.trim())) {
                LOG.info("CLASS 'ArchivoWriter'====> " + "SE ENCONTRO LA LINEA: " + i);

                posicion = i;
            }

        }

        //intento de guardar
       System.out.println(posicion);
        lista.add(posicion, menu.lineaParaAgregar);

        File file = new File(menu.archivo.path);
        FileWriter fw = new FileWriter(file);
        BufferedWriter escritura = new BufferedWriter(fw);

        for(int i=0;i<lista.size();i++){
            escritura.write(lista.get(i));
            escritura.newLine();

        }
        escritura.close();

        //imprime lista modificada
        for (int i = 0; i < menu.archivo.contenido.size() ; i++) {
            linea = lista.get(i).toString();
            System.out.println(linea);
        }

        System.out.println("POSICION: " + posicion);


    }
}
