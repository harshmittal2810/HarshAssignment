package desynova.harsh.harshassignment.utils;

import com.orhanobut.logger.Logger;

import desynova.harsh.harshassignment.BuildConfig;

public class L {
    public static void d(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.d(tag, massage);
        }
    }

    public static void i(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.i(tag, massage);
        }
    }

    public static void v(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.v(tag, massage);
        }
    }

    public static void e(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.e(tag, massage);
        }
    }

    public static void json(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.i(tag);
            Logger.json(massage);
        }
    }
}
