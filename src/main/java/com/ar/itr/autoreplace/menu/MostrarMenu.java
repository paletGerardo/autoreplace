package com.ar.itr.autoreplace.menu;

import com.ar.itr.autoreplace.model.Archivo;
import com.ar.itr.autoreplace.reader.ArchivoReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;
import static org.apache.commons.logging.LogFactory.getLog;

public abstract class MostrarMenu {
    //==============================================================//
        static String CLASE = "MostrarMenu";
        private static final Log LOG = getLog(MostrarMenu.class);
    //==============================================================//

   public static Menu mostrar(){

       LOG.info("CLASS '" + CLASE + "' ====> " + "Iniciando clase MostrarMenu: solo carga la vista para completar " +
               "los datos y retorna una instancia de Menu(opcion,archivo,patronBusqueda,lineaParaAgregar), al cargar" +
               "la path se instancia ArchivoReader");

       Archivo archivo = new Archivo();
       ArchivoReader archivoReader = new ArchivoReader();
       Scanner scanner = new Scanner(in);

       System.out.println("====================================");
       System.out.println("                MENU                ");
       System.out.println("====================================");

       System.out.println("1. Leer un archivo       (a)");
       System.out.println("2. Leer una lista        (l)");
       System.out.println("3. Salir del sistema     (S)");
       System.out.println("====================================");

       System.out.println("Elegir opcion: ");
       String opcion = scanner.nextLine();


       System.out.println("Ingresar Path del archivo");
       String pathArchivo = scanner.nextLine();
       try {
           archivo = archivoReader.lectura(pathArchivo);
       } catch (IOException e) {
           e.printStackTrace();
       }


       System.out.println("Ingresar el patron de busqueda: ");
       String patronBusqueda = scanner.nextLine();

       System.out.println("Ingresar la linea que desea agregar: ");
       String lineaParaAgregar = scanner.nextLine();

       return new Menu(opcion,archivo,patronBusqueda,lineaParaAgregar);
   }
}
