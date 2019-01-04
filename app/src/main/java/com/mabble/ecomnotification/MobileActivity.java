package com.mabble.ecomnotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MobileActivity extends AppCompatActivity {

    EditText et_mobilenumber;
    Button btn_registermobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Welcome");
        setContentView(R.layout.activity_mobile);

        et_mobilenumber = findViewById(R.id.et_mobilenumber);
        btn_registermobile = findViewById(R.id.btn_registermobile);

        btn_registermobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = et_mobilenumber.getText().toString();
                if (mobile.equals("")) {
                    Toast.makeText(MobileActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MobileActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MobileActivity.this, MainActivity.class));
                }
            }
        });

    }
}
