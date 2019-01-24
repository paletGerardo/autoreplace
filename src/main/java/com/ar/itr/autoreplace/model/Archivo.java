package com.ar.itr.autoreplace.model;

import java.util.Arrays;
import java.util.List;

public class Archivo {
    private String path;
    private List<String> contenido;

    public Archivo() {
    }

    public Archivo(String path, List<String> contenido) {
        this.path = path;
        this.contenido = contenido;
    }

    public List<String> getContenido() {
        return contenido;
    }

    public void setContenido(List<String> contenido) {
        this.contenido = contenido;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
