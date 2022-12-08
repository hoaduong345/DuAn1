package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appbanhang.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class BeforeIntro extends AppCompatActivity {
    private Button btnloggoogle,btnlogfacebook,btnlogphone;
    private TextView txtlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_intro);
        btnloggoogle = findViewById(R.id.btnLogGoogle);
        btnlogfacebook = findViewById(R.id.btnLogFacebook);
        btnlogphone = findViewById(R.id.btnLogPhone);
        txtlogin = findViewById(R.id.txtLogin);

        btnloggoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnlogfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnlogphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeIntro.this, DangkiActivity.class);
                startActivity(intent);
            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeIntro.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}