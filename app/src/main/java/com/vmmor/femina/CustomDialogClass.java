package com.vmmor.femina;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button call, reject;
    TextView txt_number;


    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        call = findViewById(R.id.btn_call);
        reject = findViewById(R.id.btn_nocall);
        txt_number = findViewById(R.id.txt_number);
        call.setOnClickListener(this);
        reject.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8750420053"));
                c.startActivity(callIntent);
                break;
            case R.id.btn_nocall:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}