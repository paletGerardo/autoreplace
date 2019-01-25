package com.ar.itr.autoreplace.model;

import java.util.Arrays;
import java.util.List;

public class Archivo {
    public String path;
    public List<String> contenido;

    public Archivo() {
    }

    public Archivo(String path, List<String> contenido) {
        this.path = path;
        this.contenido = contenido;
    }
}
