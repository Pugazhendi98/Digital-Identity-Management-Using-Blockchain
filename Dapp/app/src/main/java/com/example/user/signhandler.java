package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class signhandler extends Activity {
    Context context;
    String text = "";

    public signhandler(Context context) {
        this.context = context;
    }


    public boolean isusernotlogged(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LogDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Address", "").isEmpty();
        //boolean isPasswordEmpty = sharedPreferences.getString("Privatekey", "").isEmpty();
        return isEmailEmpty ;
    }
    public void save(String address) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LogDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Address", address);
        //editor.putString("PrivateKey", privatekey);
        editor.commit();
    }
}