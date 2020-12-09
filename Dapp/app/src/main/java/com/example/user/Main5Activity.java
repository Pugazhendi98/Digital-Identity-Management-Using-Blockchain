package com.example.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.StringTokenizer;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class Main5Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int REQUEST_CAMERA = 1;
    String ret=" ";
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();

            } else {
                requestPermission();
            }
        }
    }
    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private <QrCodeScannerActivity> void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {

        StringTokenizer st=new StringTokenizer(message,"|");
        String Data=st.nextToken();
        String signature=st.nextToken();
        String toaddr=st.nextToken();

        new android.app.AlertDialog.Builder(Main5Activity.this)
                .setMessage(Data)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }
    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(mScannerView == null) {
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        final String result = rawResult.getText();
        StringTokenizer st=new StringTokenizer(result,"|");
        String Data=st.nextToken();
        String signature=st.nextToken();
        String toaddr=st.nextToken();
        filehand f=new filehand(Main5Activity.this);

        Data= Data.replace(',','\n');
        getsign(signature,toaddr,Data);

        Log.d("QRCodeScanner", rawResult.getText());
        Log.d("QRCodeScanner", rawResult.getBarcodeFormat().toString());


    }
    public void getsign(String sign,String toaddr,final String Data){
        final RequestQueue queue = Volley.newRequestQueue(Main5Activity.this);
        final String url = "http://192.168.43.99:1223/getverify";
        final String response="";

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("Sign",sign);
        params.put("toadr",toaddr);// the entered data as the body.
        //params.put("pkey",pkey);
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
                            Log.d(response.getString("Result"),"this post");
                            String res=response.getString("Result");
                            String trh=response.getString("transhash");
                             ret="\n"+res+"\n"+trh;
                            AlertDialog.Builder builder = new AlertDialog.Builder(Main5Activity.this);
                            builder.setTitle("Scan Result");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mScannerView.resumeCameraPreview(Main5Activity.this);
                                }
                            });
                            if(ret!="Error")
                            {
                                builder.setMessage(Data+"\n"+ret);}
                            else
                            {
                                builder.setMessage("Invalid");
                            }
                            AlertDialog alert1 = builder.create();
                            alert1.show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NO".toString(),"That didn't work!");
                ret="Error";
            }
        });


        queue.add(jsObjRequest);
        //return ret;
    }

}
