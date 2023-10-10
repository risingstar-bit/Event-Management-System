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

public class Admin_SignUp extends AppCompatActivity {
    EditText userName,password,email,category;
    Button sign;
    ProgressDialog progressDialog;
    String eM;
    ArrayList<String>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        userName = findViewById(R.id.adminSignUP_User);
        password = findViewById(R.id.vendor_Password);
        email = findViewById(R.id.adminSignuP_Email);
        category = findViewById(R.id.admin_DropDown);
        sign = findViewById(R.id.admin_signUP);
        progressDialog = new ProgressDialog(Admin_SignUp.this);
        progressDialog.setMessage("Loading.....");
        eM = email.getText().toString();
        if (list.contains(eM)) {
            Toast.makeText(this, "Email Already Exists", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),Admin_Login.class);
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
        String cat=category.getText().toString();
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
                params.put("cA",cat);
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