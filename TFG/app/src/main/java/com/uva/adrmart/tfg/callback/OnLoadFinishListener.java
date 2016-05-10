package com.uva.adrmart.tfg.callback;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adrian on 07/03/2016.
 */
public interface OnLoadFinishListener {
    void onLoadFinish(List<? extends Serializable> items);
}
