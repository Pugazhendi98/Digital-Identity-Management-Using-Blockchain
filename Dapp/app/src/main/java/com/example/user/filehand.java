package com.example.user;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class filehand extends Activity {
    Context context;
    String text="";
    public filehand(Context context)
    {
        this.context=context;
    }




    public boolean isusernotlogged(Context con){
        SharedPreferences sharedPreferences = con.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Address", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Privatekey", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
    public void saveaccountDetails(String address, String privatekey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Address",address);
        editor.putString("PrivateKey", privatekey);
        editor.commit();
    }
    public void savename(String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",name);

        editor.commit();
    }
    public void savesign(String data,String sign) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data",data);
        editor.putString("sign",sign);

        editor.commit();

    }

    public String getaddr()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        String addr = sharedPreferences.getString("Address", "");

        return addr;

    }
    public String getname()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");

        return name;

    }
    public String getkey()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        String key = sharedPreferences.getString("PrivateKey", "");

        return key;

    }
    public String getqr()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AccDetails", Context.MODE_PRIVATE);
        String qrdata = sharedPreferences.getString("data", "");
        String qrsign = sharedPreferences.getString("sign", "");
        String qraddr = sharedPreferences.getString("Address", "");

        return qrdata+"|"+qrsign+"|"+qraddr;

    }
}

