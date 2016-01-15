package com.github.lzyzsd.leakmemoryexample;

import android.content.Context;

/**
 * Created by bruce on 1/13/16.
 */
public class Listener {
    private static Context sContext;
    public static void add(Context context) {
        sContext = context;
    }
}
