package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class edit_view extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView kodeText, jenisaparText, beratText, lokasiText,tglexpText, pinText, tekananText, selangText, handleText, tabungText, identifikasiText, identifikasiText2 , inspeksi, keteranganText, penempatanText, tempatText;

    private DatePickerDialog datePickerDialog;
    Button editApar, deleteApar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        context = this;

        editApar = findViewById(R.id.editApar);
        deleteApar = findViewById(R.id.hapusApar);
        kodeText = findViewById(R.id.kodescan1);
        jenisaparText = findViewById(R.id.jenis_aparscan1);
        beratText = findViewById(R.id.beratscan1);
        tempatText = findViewById(R.id.tempatscan1);
        lokasiText = findViewById(R.id.Lokasiscan1);
        keteranganText = findViewById(R.id.Keteranganscan1);
        handleText = findViewById(R.id.Handlescan1);
        tabungText = findViewById(R.id.Tabungscan1);
        pinText = findViewById(R.id.Pinscan1);
        tekananText = findViewById(R.id.tekananscan1);
        selangText = findViewById(R.id.selangscan1);
        identifikasiText = findViewById(R.id.Identifikasi1i);
        identifikasiText2 = findViewById(R.id.Identifikasi2i);
        penempatanText = findViewById(R.id.penempatan1);
        initDatePicker();
        tglexpText = findViewById(R.id.datePickerButtonExpired1);
        //tglexpText.setText(getTodaysDate());
        inspeksi = findViewById(R.id.datePickerButtonInspeksi1);
        //inspeksi.setText(getTodaysDate());

        Intent n = getIntent();
        String mResult = n.getStringExtra("kode");
        Toast.makeText(this, "Decrypted Text: " + mResult, Toast.LENGTH_SHORT).show();
        Intent u = getIntent();
        String user1 = u.getStringExtra("user1");

        editApar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),edit_scan.class);
                intent.putExtra("kode", mResult);
                intent.putExtra("user1", user1);
                startActivity(intent);
                finish();
            }
        });


        deleteApar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Apakah Anda Yakin");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                String kode = kodeText.getText().toString();
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                String urlUpdate ="http://192.168.32.50/myapar/Android/delete.php";

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                if (response.equals("Succes")){
                                                    Toast.makeText(edit_view.this, "Data Berhasil Di Update",Toast.LENGTH_SHORT).show();
                                                }
                                                else Toast.makeText(edit_view.this, response,Toast.LENGTH_LONG).show();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e("Error", error.getLocalizedMessage());
                                    }
                                }){
                                    protected Map<String, String> getParams(){
                                        Map<String, String> paramV = new HashMap<>();
                                        paramV.put("kode", kode);

                                        return paramV;
                                    }
                                };
                                queue.add(stringRequest);
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                intent.putExtra("user1", user1);
                                startActivity(intent);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.32.50/myapar/Android/api_apar.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        parseJson(response);
                   }
               }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {kodeText.setText("That didn't work!");}
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public void parseJson(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("apar");
            Intent n = getIntent();
            String mResult = n.getStringExtra("kode");
            for (int i = 0 ; i < jsonArray.length(); i++){
                String kodeVar, beratVar, jenisaparVar, tempatVar, lokasiVar, bulanexpVar, tahunexpVar, inspeksiVar,
                        keteranganVar, handleVar, pinVar, tabungVar, tekananVar,selangVar, identifikasiVar, identifikasiVar2,
                        penempatanVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                kodeVar = jsonObject2.getString("kode");
                String kodeVar2 = (kodeVar);
                if(mResult.equals(kodeVar2)){
                    kodeVar = jsonObject2.getString("kode");
                    tempatVar = jsonObject2.getString("tempat");
                    lokasiVar = jsonObject2.getString("lokasi");
                    jenisaparVar = jsonObject2.getString("jenis_apar");
                    beratVar = jsonObject2.getString("berat");
                    bulanexpVar = jsonObject2.getString("bulanexp");
                    tahunexpVar = jsonObject2.getString("tahunexp");
                    handleVar = jsonObject2.getString("handle");
                    tabungVar = jsonObject2.getString("tabung");
                    pinVar = jsonObject2.getString("pin");
                    tekananVar = jsonObject2.getString("tekanan");
                    selangVar = jsonObject2.getString("selang");
                    identifikasiVar = jsonObject2.getString("identifikasi");
                    identifikasiVar2 = jsonObject2.getString("identifikasi2");
                    penempatanVar = jsonObject2.getString("penempatan");
                    inspeksiVar = jsonObject2.getString("inspeksi");
                    keteranganVar = jsonObject2.getString("keterangan");

                    kodeText.setText(kodeVar);
                    tempatText.setText(tempatVar);
                    jenisaparText.setText(jenisaparVar);
                    beratText.setText(beratVar);
                    lokasiText.setText(lokasiVar);
                    tglexpText.setText(bulanexpVar + "/" +tahunexpVar);
                    handleText.setText(handleVar);
                    tabungText.setText(tabungVar);
                    pinText.setText(pinVar);
                    tekananText.setText(tekananVar);
                    selangText.setText(selangVar);
                    identifikasiText.setText(identifikasiVar);
                    identifikasiText2.setText(identifikasiVar2);
                    penempatanText.setText(penempatanVar);
                    inspeksi.setText(inspeksiVar);
                    keteranganText.setText(keteranganVar);
                }else{
                    continue;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                tglexpText.setText(date);
                inspeksi.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "Januari";
        if(month == 2)
            return "Febuari";
        if(month == 3)
            return "Maret";
        if(month == 4)
            return "April";
        if(month == 5)
            return "Mei";
        if(month == 6)
            return "Juni";
        if(month == 7)
            return "Juli";
        if(month == 8)
            return "Agustus";
        if(month == 9)
            return "September";
        if(month == 10)
            return "Oktober";
        if(month == 11)
            return "November";
        if(month == 12)
            return "Desember";

        //default should never happen
        return "Januari";
    }

    public void openDatePicker(View view)
    {

        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}