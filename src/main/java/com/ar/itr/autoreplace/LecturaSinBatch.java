package com.ar.itr.autoreplace;

import com.ar.itr.autoreplace.menu.Menu;
import com.ar.itr.autoreplace.menu.MostrarMenu;
import com.ar.itr.autoreplace.model.Archivo;

import java.io.IOException;

public class LecturaSinBatch {


    public static void main(String[] args) {
        new LecturaSinBatch().execute();
    }

    private void execute() {

        Menu menu = MostrarMenu.cargarMenu();
        leerArchivo(menu);
    }

    private void leerArchivo(Menu menu) {
        String path = MostrarMenu.ingresarPath();
        menu.pathArchivo = path;

        Archivo archivo = null;
        try {
            archivo = new Archivo(menu.pathRaiz, menu.pathArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!path.equals("x")) {
            try {
                archivo.escribir(menu.idParaAgregar);
            } catch (IOException e) {
                System.out.println("Archivo no encontrado, verificar la path");
            }
            path = MostrarMenu.ingresarPath();
            menu.pathArchivo = path;
        }
    }

}

// /home/gerr/Desarrollos
// archivo2.txt