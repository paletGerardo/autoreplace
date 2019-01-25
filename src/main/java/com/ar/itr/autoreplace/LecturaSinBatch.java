package com.ar.itr.autoreplace;

import com.ar.itr.autoreplace.menu.Menu;
import com.ar.itr.autoreplace.menu.MostrarMenu;
import com.ar.itr.autoreplace.model.Archivo;
import com.ar.itr.autoreplace.reader.ArchivoReader;
import com.ar.itr.autoreplace.writer.ArchivoWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import java.util.List;

//@SpringBootApplication
public class LecturaSinBatch {
     ArchivoReader archivoReader = new ArchivoReader();
     ArchivoWriter archivoWriter = new ArchivoWriter();

    public static void main(String[] args){
        new LecturaSinBatch().execute();
    }

    private void execute() {

        Menu menu = MostrarMenu.mostrar();

        if (menu.opcion.equals("a")){
            leerArchivo(menu);
        }else if(menu.opcion.equals("l")) {
            leerLista(menu);
        }
    }

    private void leerArchivo(Menu menu){
        try {
            archivoWriter.escribir(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void leerLista(Menu menu){
        List<String> lista;
        lista = menu.archivo.contenido;

        for (String pathDeLaLista : lista){
            try {
                menu.archivo = archivoReader.lectura(pathDeLaLista);
                archivoWriter.escribir(menu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*@SpringBootApplication
public class AutoreplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoreplaceApplication.class, args);
    }

}*/

// /home/gerr/Desarrollos/archivo.txt