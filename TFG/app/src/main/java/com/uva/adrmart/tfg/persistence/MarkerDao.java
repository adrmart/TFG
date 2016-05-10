package com.uva.adrmart.tfg.persistence;

import com.uva.adrmart.tfg.domain.MarkerPropio;

import java.util.List;

/**
 * Created by Adrian on 13/04/2016.
 */
public interface MarkerDao {

    void getMarkers();

    List <MarkerPropio> getListaMarkers();
}
