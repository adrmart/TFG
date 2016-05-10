package com.uva.adrmart.tfg.domain;

/**
 * Created by Adrian on 10/05/2016.
 */
public class ElementoGallery {

    public String url;
    public String text;
    public String tipo;
    public int id;

    public ElementoGallery() {
    }

    public ElementoGallery(int id, String text, String tipo, String url) {
        this.id = id;
        this.text = text;
        this.tipo = tipo;
        this.url = url;
    }
}
