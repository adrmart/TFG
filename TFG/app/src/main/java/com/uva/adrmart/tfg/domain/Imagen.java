package com.uva.adrmart.tfg.domain;

import java.io.Serializable;

/**
 * Created by Adrian on 07/03/2016.
 */
public class Imagen implements Serializable {

    public int id;
    public int orientacion;
    public int idDrawable; //URL
    public String titulo;
    public String descripcion;
    public int año;
    public String autor;

    public MarkerPropio marker;
    public Calle calle;

    public Imagen(){

    }

    public Imagen(String autor, int año, Calle calle, String descripcion, int id, int idDrawable, MarkerPropio marker, int orientacion, String titulo) {
        this.autor = autor;
        this.año = año;
        this.calle = calle;
        this.descripcion = descripcion;
        this.id = id;
        this.idDrawable = idDrawable;
        this.marker = marker;
        this.orientacion = orientacion;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
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

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public MarkerPropio getMarker() {
        return marker;
    }

    public void setMarker(MarkerPropio marker) {
        this.marker = marker;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
