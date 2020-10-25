package cn.itcast.order.utils;

import android.content.Context;

import java.util.Map;

public class SPUtil {

    public static boolean saveAccont(Context context, String account, String password){
        context.getSharedPreferences("data", Context.MODE_PRIVATE).edit()
                .putString("account",account)
                .putString("password",password)
                .apply();
        return true;
    }
    public static Map<String, String> getAll(Context context){
        return (Map<String, String>) context.getSharedPreferences("data", Context.MODE_PRIVATE).getAll();
    }
    public static void clear(Context context){
        context.getSharedPreferences("data", Context.MODE_PRIVATE).edit()
                .clear()
                .apply();
    }
}
