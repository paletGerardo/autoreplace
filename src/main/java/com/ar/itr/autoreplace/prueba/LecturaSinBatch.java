package com.ar.itr.autoreplace.prueba;

import com.ar.itr.autoreplace.model.Archivo;
import com.ar.itr.autoreplace.reader.ArchivoReader;
import com.ar.itr.autoreplace.writer.ArchivoWriter;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.in;

public class LecturaSinBatch {
    public static void main(String[] args) throws IOException {

        ArchivoReader archivoReader = new ArchivoReader();
        ArchivoWriter archivoWriter = new ArchivoWriter();
        Archivo archivo = new Archivo();
        Scanner scanner = new Scanner(in);

        System.out.println("Ingresar Path del archivo");
        String pathArchivo = scanner.nextLine();

        System.out.println("Ingresar el patron de busqueda: ");
        String patronBusqueda = scanner.nextLine();

        System.out.println("Ingresar la linea que desea agregar: ");
        String lineaParaAgregar = scanner.nextLine();



        archivo = archivoReader.lectura(pathArchivo);

        archivoWriter.escribir(archivo, lineaParaAgregar, patronBusqueda);

    }
}

// /home/gerr/Desarrollos/archivo.txt