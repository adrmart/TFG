package com.uva.adrmart.tfg.callback;

import android.support.v4.app.Fragment;

/**
 * Created by Adrian on 09/03/2016.
 */
public interface FragmentCallback {
    void replaceMapFragment(Fragment fragment);
    void replaceWithMapFragment(Fragment fragment);

    void goBack();
}
