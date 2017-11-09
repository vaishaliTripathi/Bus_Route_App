package com.wallmart.busroute.widgets;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

import com.wallmart.busroute.constants.AppConstants;

/**
 * TypefaceCache
 *
 * @author vaishali
 * @version 1.0
 * @date 9/11/17
 */

public class TypefaceCache {

    private static final ConcurrentHashMap<String, Typeface> CACHE = new ConcurrentHashMap<String, Typeface>();

    public static Typeface get(final AssetManager manager, final String name) {

        synchronized (CACHE) {

            if (!CACHE.containsKey(name)) {
                try {
                    final Typeface t = Typeface.createFromAsset(manager, name);
                    CACHE.put(name, t);
                } catch (Exception ex) {
                    Log.e(AppConstants.APP_TAG, "Custom font <" + name + " > can not be loaded from the assets");
                    ex.printStackTrace();
                }
            }
            return CACHE.get(name);
        }
    }
}
