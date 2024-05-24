package com.example.aapelindo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScannerPage extends AppCompatActivity {
    private Button scannbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_page);

        scannbutton = findViewById(R.id.button_scan);

        scannbutton.setOnClickListener(s -> {
            if (ContextCompat.checkSelfPermission(ScannerPage.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(ScannerPage.this, Manifest.permission.CAMERA)) {
                    startScan();
                }else{
                    ActivityCompat.requestPermissions(ScannerPage.this, new  String[]{Manifest.permission.CAMERA}, 0);
                }
            } else{
                startScan();
            }

        });
    }

    private void startScan(){
        Intent intent = new Intent(getApplicationContext(),Scan.class);
        startActivityForResult(intent, 21);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 21){
            if (resultCode == RESULT_OK && data!=null);
            String kode = data.getStringExtra("decryptedText");
            Intent n = getIntent();
            String user1 = n.getStringExtra("user1");
            Intent intent = new Intent(getApplicationContext(),edit_view.class);
            intent.putExtra("kode", kode);
            intent.putExtra("user1", user1);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScan();
            }else{
                Toast.makeText(this,"Gagal Membuka Kamera", Toast.LENGTH_LONG).show();
            }
        }
    }
}