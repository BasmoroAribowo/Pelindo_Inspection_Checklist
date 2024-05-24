package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class denah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denah);

        Button buttoncy01 = findViewById(R.id.cy01);
        buttoncy01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.cy01);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncy02 = findViewById(R.id.cy02);
        buttoncy02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.cy02);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncy05 = findViewById(R.id.cy05);
        buttoncy05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.cy05);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncyd = findViewById(R.id.cyd);
        buttoncyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.cydomestik);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncfs = findViewById(R.id.cfs);
        buttoncfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.cfs);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncrlt2 = findViewById(R.id.crlt2);
        buttoncrlt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.controlroomlantai2);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttoncrlt3 = findViewById(R.id.crtl3);
        buttoncrlt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.controlroomlantai3);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttontpsm1 = findViewById(R.id.tpsm1);
        buttontpsm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.gedungtpsmlantai1);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttontpsm2 = findViewById(R.id.tpsm2);
        buttontpsm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.gedungtpsmlantai2);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttontpsm3 = findViewById(R.id.tpsm3);
        buttontpsm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.gedungtpsmlantai3);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttontekni1 = findViewById(R.id.teknik1);
        buttontekni1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.tekniklantai1);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttontekni2 = findViewById(R.id.teknik2);
        buttontekni2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.tekniklantai2);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttonlongroom = findViewById(R.id.longroom);
        buttonlongroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.longroom);
                builder.setView(view);
                builder.show();

            }
        });
        Button buttonwork = findViewById(R.id.work);
        buttonwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kode untuk menampilkan pop up gambar
                AlertDialog.Builder builder = new AlertDialog.Builder(denah.this);
                View view = getLayoutInflater().inflate(R.layout.activity_popup, null);
                ImageView imageView = view.findViewById(R.id.image_popup);
                imageView.setImageResource(R.drawable.workshop);
                builder.setView(view);
                builder.show();

            }
        });
    }
}