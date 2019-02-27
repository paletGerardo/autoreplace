package com.ar.itr.autoreplace.menu;

import org.apache.commons.logging.Log;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;
import static org.apache.commons.logging.LogFactory.getLog;

public abstract class MostrarMenu {
    //==============================================================//
        static String CLASE = "MostrarMenu";
        private static final Log LOG = getLog(MostrarMenu.class);
    //==============================================================//

   public static Menu cargarMenu(){

       LOG.info("CLASS '" + CLASE + "' ====> " + "Iniciando clase MostrarMenu: solo carga la vista para completar " +
               "los datos y retorna una instancia de Menu(opcion,archivo,patronBusqueda,lineaParaAgregar), al cargar" +
               "la path se instancia ArchivoReader");

       Scanner scanner = new Scanner(in);

       System.out.println("====================================");
       System.out.println("                MENU                ");
       System.out.println("====================================");

       System.out.println("1. Agregar supress a un archivo      (a)");
       System.out.println("2. Salir del sistema                 (S)");
       System.out.println("====================================");
       System.out.println("Elegir opcion: ");
       String opcion = scanner.nextLine();

       if (opcion.equals("s")){
           exit(1);
       }

       System.out.println("Ingresar Path 'RAIZ' incluyendo '/' al final:");
       String pathRaiz = scanner.nextLine();

       String patronBusqueda = "public class";

       System.out.println("Ingresar id del supressWarning: ");
       String idParaAgregar = scanner.nextLine();

       return new Menu(opcion,pathRaiz,patronBusqueda,idParaAgregar);
   }

   public static String ingresarPath(){
       Scanner scanner = new Scanner(in);

       System.out.println("====================================");
       System.out.println("      Ingresar path del archivo     ");
       System.out.println("====================================");


       System.out.println("path sin '/' al principio: ");
       String path = scanner.nextLine();

       return path;
   }
}
