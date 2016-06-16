package com.conways.demo.utils;

import android.util.Log;

/**
 * Created by user on 2016/5/25.
 */
public class LogUtils {

    public static boolean debugModel = true;

    public static void isDebugModel(boolean isDebug) {
        debugModel = isDebug;
    }


    public static void d(String TAG,String msg){
        if (debugModel){
            Log.d(TAG,msg);
        }
    }
    public static void i(String TAG,String msg){
        if (debugModel){
            Log.i(TAG,msg);
        }
    }
    public static void e(String TAG,String msg){
        if (debugModel){
            Log.e(TAG,msg);
        }
    }

}
