package com.solo.security.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Messi on 16-11-18.
 */

public class SecurityPreference {
    private static final String PREFS_NAME_SECURITY = "security_prefs";

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFS_NAME_SECURITY, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    public static void setString(Context context, String key, String value) {
        getEditor(context).putString(key, value).apply();
    }

    public static String getString(Context context, String key, String def) {
        return getSharedPreference(context).getString(key, def);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean def) {
        return getSharedPreference(context).getBoolean(key, def);
    }

    public static void setStringSet(Context context, String key, String value) {
        Set<String> sets = getStringSet(context, key);
        if (sets == null) {
            sets = new HashSet<>();
        }
        sets.add(value);
    }

    public static Set<String> getStringSet(Context context, String key) {
        return getSharedPreference(context).getStringSet(key, null);
    }

    public static void removeStringSetItem(Context context, String key, String remove) {
        Set<String> sets = getStringSet(context, key);
        if (sets == null) {
            return;
        }
        sets.remove(remove);
    }

    public static void removeStringSetAll(Context context, String key) {
        Set<String> sets = getStringSet(context, key);
        if (sets == null) {
            return;
        }
        sets.clear();
    }
}
