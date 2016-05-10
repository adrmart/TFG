package com.uva.adrmart.tfg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Adrian on 13/04/2016.
 */
public class BDHelper extends SQLiteOpenHelper {
    private static final String TAG = BDHelper.class.getName();
    private static final String DATABASE_NAME = "TFG.db";
    private static final int DATABASE_VERSION = 2;
    private static BDHelper bdHelper;

    public BDHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static void init(Context context) {
        bdHelper = new BDHelper(context);
    }

    public static BDHelper getInstance() {
        return bdHelper;
    }

    //- Crea la BDD
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate");

        //- Limpiar BD
        db.execSQL(ScriptBD.DROP_CALLE);
        db.execSQL(ScriptBD.DROP_MARKER);
        db.execSQL(ScriptBD.DROP_IMAGEN);

        //- Crear BD
        db.execSQL(ScriptBD.CALLE_SCRIPT);
        db.execSQL(ScriptBD.MARKER_SCRIPT);
        db.execSQL(ScriptBD.IMAGEN_SCRIPT);

        //- Inserts predefinidos
        db.execSQL(ScriptBD.INSERT_CALLE1_SCRIPT);
        db.execSQL(ScriptBD.INSERT_CALLE2_SCRIPT);

        db.execSQL(ScriptBD.INSERT_MARKER1_SCRIPT);
        db.execSQL(ScriptBD.INSERT_MARKER2_SCRIPT);
        db.execSQL(ScriptBD.INSERT_MARKER3_SCRIPT);
        db.execSQL(ScriptBD.INSERT_MARKER4_SCRIPT);

        db.execSQL(ScriptBD.INSERT_IMAGEN1_SCRIPT);
        db.execSQL(ScriptBD.INSERT_IMAGEN2_SCRIPT);
        db.execSQL(ScriptBD.INSERT_IMAGEN3_SCRIPT);
        db.execSQL(ScriptBD.INSERT_IMAGEN4_SCRIPT);
        db.execSQL(ScriptBD.INSERT_IMAGEN5_SCRIPT);
        db.execSQL(ScriptBD.INSERT_IMAGEN6_SCRIPT);


    }

    //- Actualiza la BDD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG, "onUpgrade");
        onCreate(db);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
