package com.uva.adrmart.tfg.domain;

import java.io.Serializable;

/**
 * Created by Adrian on 13/04/2016.
 */
public class MarkerPropio implements Serializable {

    public int id;
    public float latitud;
    public float logitud;
    public String titulo;
    public String descripcion;

    public MarkerPropio(){

    }

    public MarkerPropio(String descripcion, int id, float latitud, float logitud, String titulo) {
        this.descripcion = descripcion;
        this.id = id;
        this.latitud = latitud;
        this.logitud = logitud;
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLogitud() {
        return logitud;
    }

    public void setLogitud(float logitud) {
        this.logitud = logitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

