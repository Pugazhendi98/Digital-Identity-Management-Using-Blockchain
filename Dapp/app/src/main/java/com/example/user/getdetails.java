package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class getdetails extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b;
    RadioGroup rg;
    RadioButton rb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdetails);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText6);
        e3=(EditText)findViewById(R.id.editText5);
        b=(Button)findViewById(R.id.button7);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getsign();
            }
        });
    }

    public void getsign(){
        final RequestQueue queue = Volley.newRequestQueue(getdetails.this);
        final String url = "http://192.168.43.99:1223/getsign";
        final String response="";
        rg=(RadioGroup)findViewById(R.id.radioSex);
        int selectedId = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        rb1 = (RadioButton) findViewById(selectedId);
        final String data=e1.getText().toString().toUpperCase()+","+e2.getText().toString().toUpperCase()+","+e3.getText().toString().toUpperCase()+","+rb1.getText().toString();

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("data",data); // the entered data as the body.
        //params.put("mobile",s1);
        JsonObjectRequest jsObjRequest = new
                JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //isplayText.setText(response.getString("message"));
                        try {
                            Log.d(response.getString("sign"),"this post");
                            Toast.makeText(getdetails.this,"signature received succesfully",Toast.LENGTH_LONG).show();
                            //t.setText(response.getString("otp").toString());
                            filehand f=new filehand(getdetails.this);
                            f.savename(e1.getText().toString().toUpperCase());
                            String sign=response.getString("sign");
                            f.savesign(data,sign);
                            Toast.makeText(getdetails.this,"signature received succesfully",Toast.LENGTH_LONG).show();
                            Intent i= new Intent(getdetails.this,Main3Activity.class);
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
