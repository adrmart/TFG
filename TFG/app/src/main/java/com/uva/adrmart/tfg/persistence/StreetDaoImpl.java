package com.uva.adrmart.tfg.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uva.adrmart.tfg.BDHelper;
import com.uva.adrmart.tfg.domain.Calle;
import com.uva.adrmart.tfg.domain.Imagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 10/05/2016.
 */
public class StreetDaoImpl implements StreetDao{

    //Tabla objetivo
    private static final String CALLE = "Calle";

    //Consultas sql
    private static final String FINDQUERY = "SELECT * FROM Calle;";

    //Campos de la tabla Imagen
    public static final String ID_CALLE = "_id";
    public static final String TIPO_CALLE = "tipo_calle";
    public static final String NOMBRE_CALLE = "nombre_calle";
    public static final String DESCRIPCION_CALLE = "descripcion_calle";
    public static final String REPRESENTATIVO = "representativo";

    //Variables sql
    private final BDHelper db;
    private static SQLiteDatabase sqldb;
    private static Cursor c;

    private static List<Calle> listaCalles;

    public StreetDaoImpl() {
        db = BDHelper.getInstance();
        listaCalles = new ArrayList<>();
    }

    @Override
    public void findStreets() {
        sqldb = db.getReadableDatabase();
        c = sqldb.rawQuery(FINDQUERY, null);
        Log.d(CALLE, "Calles devueltas devueltas: " + String.valueOf(c.getCount()));
        if (c.moveToFirst()){
            do{
                Imagen imagen = new Imagen();
                Calle calle = new Calle(c.getString(c.getColumnIndex(DESCRIPCION_CALLE)),
                        c.getInt(c.getColumnIndex(ID_CALLE)),
                        c.getString(c.getColumnIndex(NOMBRE_CALLE)),
                        c.getString(c.getColumnIndex(TIPO_CALLE)),
                        imagen);
                listaCalles.add(calle);

            }while(c.moveToNext());
        }
    }

    @Override
    public List<Calle> getListaCalles() {
        return listaCalles;
    }
}
