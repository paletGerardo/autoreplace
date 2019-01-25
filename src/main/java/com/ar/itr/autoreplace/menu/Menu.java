package com.ar.itr.autoreplace.menu;


import com.ar.itr.autoreplace.model.Archivo;

public class Menu {
    public String opcion;
    public Archivo archivo;
    public String patronBusqueda;
    public String lineaParaAgregar;

    public Menu() {

    }

    public Menu(String opcion, Archivo archivo, String patronBusqueda, String lineaParaAgregar) {
        this.opcion = opcion;
        this.archivo = archivo;
        this.patronBusqueda = patronBusqueda;
        this.lineaParaAgregar = lineaParaAgregar;
    }

}
