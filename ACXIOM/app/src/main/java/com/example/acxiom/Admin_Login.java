package com.example.acxiom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Admin_Login extends AppCompatActivity {
Button cancel,login;
EditText admin_n,admin_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admin_n=findViewById(R.id.adminSignUP_User);
        admin_p=findViewById(R.id.adminLogin_P);
      cancel=findViewById(R.id.admin_login_cancel);
      login=findViewById(R.id.adminSignUP_User);

    }
}