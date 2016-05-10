package com.uva.adrmart.tfg.persistence;

import com.uva.adrmart.tfg.domain.Calle;

import java.util.List;

/**
 * Created by Adrian on 10/05/2016.
 */
public interface StreetDao {

    void findStreets ();

    List<Calle> getListaCalles();
}
