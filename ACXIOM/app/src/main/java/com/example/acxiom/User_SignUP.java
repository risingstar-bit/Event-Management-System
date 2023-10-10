package com.example.acxiom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class User_SignUp extends AppCompatActivity {
    EditText userName,password,email;
    Button sign;
    ProgressDialog progressDialog;
    String eM;
    ArrayList<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        userName = findViewById(R.id.userSIGNUP_name);
        password = findViewById(R.id.USERLOGIN_PASSWORD);
        email = findViewById(R.id.USERSIGNUP_EMAIL);
        sign = findViewById(R.id.USER_SIGNUP_BUTTON);
        progressDialog = new ProgressDialog(User_SignUp.this);
        progressDialog.setMessage("Loading.....");
        eM = email.getText().toString();
        if (list.contains(eM)) {
            Toast.makeText(this, "Email Already Exists", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),User_LogIN.class);
            startActivity(intent);
        } else {
            list.add(eM);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addData();
                    progressDialog.show();
                }
            });
        }
    }
    private void addData() {
        String userN=userName.getText().toString();
        String passW=password.getText().toString();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwL6Dx4pgOenH4vyCwcRbQTKefSQTSUdXw6QEj5ZUp2YdWolaHTwbIlx2XmJwrhbuXj/exec", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String>params=new HashMap<>();
                params.put("action","addAdmin");
                params.put("uN",userN);
                params.put("pS",passW);
                params.put("emM",eM);
                return params;
            }
        };
        int socketTimeout=50000;
        RetryPolicy retryPolicy=new DefaultRetryPolicy(socketTimeout,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy((retryPolicy));
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}