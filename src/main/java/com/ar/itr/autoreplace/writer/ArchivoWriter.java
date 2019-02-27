package com.ar.itr.autoreplace.writer;

import com.ar.itr.autoreplace.menu.Menu;
import com.ar.itr.autoreplace.model.Archivo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

import static org.apache.commons.logging.LogFactory.getLog;

// /home/gerr/Desarrollos/archivo.txt
public class ArchivoWriter {

    //==============================================================//
        static String CLASE = "MostrarMenu";
        private static final Log LOG = LogFactory.getLog(ArchivoWriter.class);
    //==============================================================//

    public void escribir(Menu menu) throws IOException {
        LOG.info("CLASS '" + CLASE + "' ====> " + "Iniciando metodo escribir: el metodo recibe el contenido del " +
                "archivo ya leido y preparado de ArchivoReader que se guardo en la clase Menu.Archivo.contenido");

        String linea;
        int posicion = 0;
        List<String> lista = menu.archivo.contenido;
        LOG.info("CLASS 'LA LISTA'====> " + lista);

        LOG.info("CLASS '" + CLASE + "' ====> " + "Leyendo cada linea del archivo y buscando la posicion del patron");
        for (int i = 0; i < lista.size(); i++) {
            linea = lista.get(i).toString();
            if (linea.contains(menu.patronBusqueda.trim())) {
                LOG.info("CLASS 'ArchivoWriter'====> " + "posicion en LA LINEA: " + i);
                posicion = i;
            }
        }
        LOG.info("CLASS '" + CLASE + "' ====> " + "encontro la posicion, agregando linea nueva a la lista");
        lista.add(posicion, menu.lineaParaAgregar);

        LOG.info("CLASS '" + CLASE + "' ====> " + "abriendo archivo para guardar la lista modificada");
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
