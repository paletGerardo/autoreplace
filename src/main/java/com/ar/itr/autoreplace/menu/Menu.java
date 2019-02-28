package com.ar.itr.autoreplace.menu;

public class Menu {
    public String opcion;
    public String pathRaiz;
    public String pathArchivo;
    public String idParaAgregar;

    public Menu() {

    }

    public Menu(String opcion, String pathRaiz, String idParaAgregar) {
        this.opcion = opcion;
        this.pathRaiz = pathRaiz;
        this.idParaAgregar = idParaAgregar;
    }

}
