package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class verifyotp extends AppCompatActivity {
    String s=" ";
    EditText e;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);
        e= (EditText)(findViewById(R.id.editText));
        tv=(TextView)(findViewById(R.id.textView));
        Intent i=getIntent();
        final String otp=i.getStringExtra("otp");


        Button but =(Button)findViewById(R.id.button6);
        new CountDownTimer(60000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                tv.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent i= new Intent(verifyotp.this,Main2Activity.class);
                startActivity(i);
            }
        }.start();
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s= e.getText().toString();
                e.clearComposingText();
                if (s.equals(otp))
                {
                    post();

                }
                else
                {
                    Toast.makeText(verifyotp.this,"invalid otp number",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    void post(){
        final RequestQueue queue = Volley.newRequestQueue(verifyotp.this);
        final String url = "http://192.168.43.99:1223/createacc";
        final String response="";

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("aadhar",s); // the entered data as the body.
        //params.put("mobile",s1);
        JsonObjectRequest jsObjRequest = new
                JsonObjectRequest(Request.Method.GET,
                url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //isplayText.setText(response.getString("message"));
                        try {
                            Log.d(response.getString("address"),"this post");
                            Toast.makeText(verifyotp.this,response.getString("address").toString(),Toast.LENGTH_LONG).show();
                            //t.setText(response.getString("otp").toString());
                            filehand f=new filehand(verifyotp.this);
                            f.saveaccountDetails(response.getString("address"),response.getString("privatekey"));
                            signhandler s = new signhandler(verifyotp.this);
                            s.save(response.getString("address"));
                            Toast.makeText(verifyotp.this,"Address generated",Toast.LENGTH_LONG).show();
                            Intent i= new Intent(verifyotp.this,Main3Activity.class);

                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NO".toString(),"That didn't work!");
            }
        });
        queue.add(jsObjRequest);
    }
}
