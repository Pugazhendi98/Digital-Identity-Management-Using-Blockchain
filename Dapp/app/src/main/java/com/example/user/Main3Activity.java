package com.example.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {
Context context =this.getBaseContext();
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button gqr=(Button)(findViewById(R.id.button2));
       Button sqr=(Button)(findViewById(R.id.button4));
        Button add=(Button)(findViewById(R.id.button5));
        Button vac=(Button)(findViewById(R.id.button3));
        gqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filehand f=new filehand(Main3Activity.this);

                if(f.getqr()== null) {
                    i = new Intent(Main3Activity.this, getdetails.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Main3Activity.this,"Already added",Toast.LENGTH_LONG).show();
                }
            }
        });

        sqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(Main3Activity.this, Main5Activity.class);
                startActivity(i);
            }
        });
           vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Main3Activity.this, account.class);
                startActivity(i);
            }
        });
    }
    public void act()
    {
        i = new Intent(this, Main4Activity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
