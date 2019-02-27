package com.ar.itr.autoreplace.menu;


import com.ar.itr.autoreplace.model.Archivo;

public class Menu {
    public String opcion;
    public String pathRaiz;
    public String pathArchivo;
    public String patronBusqueda;
    public String idParaAgregar;

    public Menu() {

    }

    public Menu(String opcion, String pathRaiz, String patronBusqueda, String idParaAgregar) {
        this.opcion = opcion;
        this.pathRaiz = pathRaiz;
        this.patronBusqueda = patronBusqueda;
        this.idParaAgregar = idParaAgregar;
    }

}
