package com.example.mylaundryadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class adminPage extends AppCompatActivity {

    FloatingActionButton qrCodeScan;
    String prevRes="";
    TextView result;
    Button del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        qrCodeScan = findViewById(R.id.qrscanner);
        del=findViewById(R.id.delete);
        qrCodeScan.setOnClickListener(v -> {
            startActivity(new Intent(adminPage.this, scanner.class));
        });
        result=findViewById(R.id.res);
        retriveData();

        del.setOnClickListener(v -> {
            delResult();
        });
    }

    private void delResult() {
        prevRes="";
        result.setText("");
        SharedPreferences.Editor editor = getSharedPreferences("result", MODE_PRIVATE).edit();
        editor.putString("res",prevRes);
        editor.apply();

    }

    private void retriveData() {
        SharedPreferences prefs = getSharedPreferences("result", MODE_PRIVATE);
        prevRes = prefs.getString("res", "");//"No name defined" is the default value.
        result.setText(prevRes);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(constats.result!=""){
            prevRes=prevRes+ "\n" + constats.result+"\n";
            SharedPreferences.Editor editor = getSharedPreferences("result", MODE_PRIVATE).edit();
            editor.putString("res",prevRes);
            editor.apply();
            result.setText(prevRes);
        }
        constats.result="";
    }
}