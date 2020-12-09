package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


public class Main2Activity extends AppCompatActivity {
String s="",s1="";

    TextView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t= (TextView)(findViewById(R.id.textView5));
        final EditText e= (EditText)(findViewById(R.id.editText3));
        final EditText e1=  (EditText)(findViewById(R.id.editText2));
        Button but =(Button)findViewById(R.id.button);
       but.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               s= e.getText().toString();

               s1= e1.getText().toString();
              check(s,s1);

           }
       });
        //Intent intent = getIntent();



    }

    public void check(String s,String s1)
    {
        if((s.length() == 12) && TextUtils.isDigitsOnly(s))
        {
            if(s1.length()==10)
            {  // filehand f =new filehand(this);

                // connect to the server
               //CallAPI con=new CallAPI();
                //con.doInBackground(s);
                 post( s, s1);
                //f.saveLoginDetails(s,s1);

            }
            else
            {

                Toast.makeText(Main2Activity.this,"invalid mobile number",Toast.LENGTH_LONG).show();

            }
        }
        else
        {

            Toast.makeText(Main2Activity.this,"invalid aadhar number",Toast.LENGTH_LONG).show();
        }


    }
    void post(String s,String s1){
        final RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
         final String url = "http://192.168.43.99:1223/verify";
         //final String response="";

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("aadhar",s); // the entered data as the body.
        params.put("mobile",s1);
        JsonObjectRequest jsObjRequest = new
                JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //isplayText.setText(response.getString("message"));
                        try {
                        Log.d(response.getString("otp"),"this post");
                            //Toast.makeText(Main2Activity.this,"successfully connected",Toast.LENGTH_LONG);
                            //t.setText(response.getString("otp").toString());
                            Intent i= new Intent(Main2Activity.this,verifyotp.class);
                            i.putExtra("otp",response.getString("otp"));
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NO".toString(),"That didn't work!"+error);
                Toast.makeText(Main2Activity.this,"server error",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsObjRequest);
    }
       // queue.add(postRequest);




}

