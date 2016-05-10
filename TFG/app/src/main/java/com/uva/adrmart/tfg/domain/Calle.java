package com.uva.adrmart.tfg.domain;

import java.io.Serializable;

/**
 * Created by Adrian on 25/04/2016.
 */
public class Calle implements Serializable {

    public int id;
    public String nombre;
    public String descripcion;
    public String tipo;
    public Imagen representativo;

    public Calle() {
    }

    public Calle(String descripcion, int id, String nombre, String tipo, Imagen representativo) {
        this.descripcion = descripcion;
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.representativo = representativo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public Imagen getRepresentativo() {
        return representativo;
    }

    public void setRepresentativo(Imagen representativo) {
        this.representativo = representativo;
    }
}
