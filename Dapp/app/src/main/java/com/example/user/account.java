package com.example.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class account extends AppCompatActivity {
TextView t1;
    TextView t2;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        t1=(TextView)findViewById(R.id.textView4);
        t2=(TextView)findViewById(R.id.textView6);


        setdata(account.this);
    }

    public void setdata(Context context){
        filehand f =new filehand(context);
        String name = f.getname();
        String addr = f.getaddr();
        String nam="Name :"+name;
        String add="Ethreum Address :"+addr;
        t1.setText(nam);
        t2.setText(add);

    }
}
