package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView =  new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String encryptedText = rawResult.getText();
        String decryptionKey = "PELINDOIIITPKS";
        String decryptedText = decryptVigenere(encryptedText, decryptionKey);

        // Menampilkan hasil dekripsi dengan Toast
        Toast.makeText(this, "Decrypted Text: " + decryptedText, Toast.LENGTH_SHORT).show();


        //Kode Yang Dikirim Ke Activity Lain
        Intent intent = new Intent();
        intent.putExtra("decryptedText", decryptedText);
        setResult(RESULT_OK, intent);
        finish();


    }

    // Fungsi untuk melakukan dekripsi Vigenere Cipher
    private String decryptVigenere(String cipherText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();
        int cipherLength = cipherText.length();

        for (int i = 0; i < cipherLength; i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            // Menghitung nilai karakter terdekripsi hanya untuk huruf
            char decryptedChar;
            if (Character.isLetter(cipherChar)) {
                int decryptedValue = ((cipherChar - 'A') - (keyChar - 'A') + 26) % 26;
                decryptedChar = (char) (decryptedValue + 'A');
            } else {
                decryptedChar = cipherChar; // Mengabaikan deskripsi untuk karakter angka
            }

            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString().toLowerCase();
    }










}