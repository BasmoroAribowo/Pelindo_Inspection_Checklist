package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Map;

public class edit_scan extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] lokasi = {"Benar", "Tidak"};
    String[] handle = {"Kondisi Baik", "Rusak"};
    String[] tabung = {"Berkarat/Berlubang", "Tidak Berkarat/Berlubang"};
    String[] pinsegel = {"Tersegel", "Tidak Tersegel"};
    String[] selang = {"Retak/Putus/Rusak", "Kondisi Baik"};
    String[] tekanan = {"Low Pressure", "Normal","Overpressure"};
    String[] penempatan = {"Sesuai", "Tidak Sesuai"};
    String[] identifikasi1i = {"Ada", "Tidak Ada"};
    String[] identifikasi2i = {"Ada", "Tidak Ada"};
    private TextView  kodeText, jenisaparText, beratText, tglexpText, inspeksiText, keteranganText, tempatText;
    Button saveApar;
    String  bulanexpText, tahunexpText;
    private DatePickerDialog datePickerDialog, datePickerDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scan);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.Lokasiscan);
        spin.setOnItemSelectedListener(this);
        Spinner spin1 = (Spinner) findViewById(R.id.tabung);
        spin1.setOnItemSelectedListener(this);
        Spinner spin2 = (Spinner) findViewById(R.id.Pinscan);
        spin2.setOnItemSelectedListener(this);
        Spinner spin3 = (Spinner) findViewById(R.id.selangscan);
        spin3.setOnItemSelectedListener(this);
        Spinner spin4 = (Spinner) findViewById(R.id.tekananscan);
        spin4.setOnItemSelectedListener(this);
        Spinner spin5 = (Spinner) findViewById(R.id.penempatan);
        spin5.setOnItemSelectedListener(this);
        Spinner spin6 = (Spinner) findViewById(R.id.Identifikasi1);
        spin6.setOnItemSelectedListener(this);
        Spinner spin6i = (Spinner) findViewById(R.id.Identifikasi2);
        spin6.setOnItemSelectedListener(this);
        Spinner spin7 = (Spinner) findViewById(R.id.Handle);
        spin7.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter lokasi1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lokasi);
        lokasi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter tabung1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tabung);
        tabung1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter pinsegel1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pinsegel);
        pinsegel1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter selang1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,selang);
        selang1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter tekanan1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tekanan);
        tekanan1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter penempatan1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,penempatan);
        penempatan1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter identifikasi1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,identifikasi1i);
        identifikasi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter identifikasi2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,identifikasi2i);
        identifikasi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter handle1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,handle);
        handle1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(lokasi1);
        spin1.setAdapter(tabung1);
        spin2.setAdapter(pinsegel1);
        spin3.setAdapter(selang1);
        spin4.setAdapter(tekanan1);
        spin5.setAdapter(penempatan1);
        spin6.setAdapter(identifikasi1);
        spin6i.setAdapter(identifikasi2);
        spin7.setAdapter(handle1);

        kodeText = findViewById(R.id.kodescan);
        jenisaparText = findViewById(R.id.jenis_aparscan);
        beratText = findViewById(R.id.beratscan);
        tempatText = findViewById(R.id.tempatscan);
        keteranganText = findViewById(R.id.Keteranganscan);

        initDatePicker();
        //

        initDatePicker2();
        inspeksiText = findViewById(R.id.datePickerButtonInspeksi2);
        inspeksiText.setText(getTodaysDate2());
        tglexpText = findViewById(R.id.datePickerButtonExpired2);
        tglexpText.setText(getTodaysDate2());

        saveApar = findViewById(R.id.saveApar);
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlAPI ="http://192.168.32.50/myapar/Android/api_apar.php";

        Intent n = getIntent();
        String user1 = n.getStringExtra("user1");
        String mResult = n.getStringExtra("kode");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAPI,
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

        //resultText.setText(mResult);

        saveApar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kode = kodeText.getText().toString();
                String tempat = tempatText.getText().toString();
                String jenis_apar = jenisaparText.getText().toString();
                String berat = beratText.getText().toString();
                String bulanexp = bulanexpText;
                String tahunexp = tahunexpText;
                String lokasi = spin.getSelectedItem().toString();
                String handle = spin7.getSelectedItem().toString();
                String tabung = spin1.getSelectedItem().toString();
                String pin = spin2.getSelectedItem().toString();
                String tekanan = spin4.getSelectedItem().toString();
                String selang = spin3.getSelectedItem().toString();
                String penempatan = spin5.getSelectedItem().toString();
                String identifikasi = spin6.getSelectedItem().toString();
                String identifikasi2 = spin6i.getSelectedItem().toString();
                String inspeksi = inspeksiText.getText().toString();
                String keterangan = keteranganText.getText().toString();
                String inspeksi_oleh = user1;
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String urlUpdate ="http://192.168.32.50/myapar/Android/update_apar.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Succes")){
                                    Toast.makeText(edit_scan.this, "Data Berhasil Di Update",Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(edit_scan.this, response,Toast.LENGTH_LONG).show();
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
                        paramV.put("tempat", tempat);
                        paramV.put("jenis_apar", jenis_apar);
                        paramV.put("berat", berat);
                        paramV.put("lokasi", lokasi);
                        paramV.put("bulanexp", bulanexp);
                        paramV.put("tahunexp", tahunexp);
                        paramV.put("handle", handle);
                        paramV.put("tabung", tabung);
                        paramV.put("pin", pin);
                        paramV.put("tekanan", tekanan);
                        paramV.put("selang", selang);
                        paramV.put("penempatan", penempatan);
                        paramV.put("identifikasi", identifikasi);
                        paramV.put("identifikasi2", identifikasi2);
                        paramV.put("inspeksi", inspeksi);
                        paramV.put("keterangan", keterangan);
                        paramV.put("inspeksi_oleh", inspeksi_oleh);

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


    }
    public void parseJson(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("apar");
            //JSONArray jsonArray = new JSONArray(response);
            Intent n = getIntent();
            String mResult = n.getStringExtra("kode");
            for (int i = 0 ; i < jsonArray.length(); i++){
                String kodeVar, beratVar, jenisaparVar, tempatVar, lokasiVar, bulanexpVar, tahunexpVar, inspeksiVar, keteranganVar, handleVar, pinVar, tabungVar, tekananVar,selangVar, identifikasiVar, penempatanVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                kodeVar = jsonObject2.getString("kode");
                String kodeVar2 = (kodeVar);
                if(mResult.equals(kodeVar2)){
                    kodeVar = jsonObject2.getString("kode");
                    tempatVar = jsonObject2.getString("tempat");

                    jenisaparVar = jsonObject2.getString("jenis_apar");
                    beratVar = jsonObject2.getString("berat");
                    bulanexpVar = jsonObject2.getString("bulanexp");
                    tahunexpVar = jsonObject2.getString("tahunexp");

                    keteranganVar = jsonObject2.getString("keterangan");

                    kodeText.setText(kodeVar);
                    tempatText.setText(tempatVar);
                    jenisaparText.setText(jenisaparVar);
                    beratText.setText(beratVar);
                    keteranganText.setText(keteranganVar);
                }else{
                    continue;
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String getTodaysDate2()
    {
        Calendar cal2 = Calendar.getInstance();
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        month2 = month2 + 1;
        int year = cal2.get(Calendar.YEAR);
        return makeDateString2(day2, month2, year);
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String datebulan = makeDateString( month);
                bulanexpText = datebulan;
                String datetahun = makeDateString3( year);
                tahunexpText = datetahun;
                String date2 = makeDateString2(day, month, year);
                tglexpText.setText(date2);
            }
        };

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }
    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker2, int year2, int month2, int day2)
            {
                month2 = month2 + 1;
                String date2 = makeDateString2(year2, month2, day2);
                inspeksiText.setText(date2);
            }
        };


        Calendar cal2 = Calendar.getInstance();
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year2 = cal2.get(Calendar.YEAR);
        int style2 = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog2 = new DatePickerDialog(this, style2, dateSetListener, year2, month2, day2);

    }

    private String makeDateString( int month)
    {
        return "" + month ;
    }
    private String makeDateString3( int year)
    {
        return "" + year ;
    }
    private String makeDateString2(int day2, int month2, int year2)
    {
        return day2 + "-" +  getMontFormat(month2) + "-" + getFormat(year2);
    }

    private String getFormat(int year2)
    {
        if(year2 == 1)
            return "01";
        if(year2 == 2)
            return "02";
        if(year2 == 3)
            return "03";
        if(year2 == 4)
            return "04";
        if(year2 == 5)
            return "05";
        if(year2 == 6)
            return "06";
        if(year2 == 7)
            return "07";
        if(year2 == 8)
            return "08";
        if(year2 == 9)
            return "09";


        //default should never happen
        return ""+ year2;
    }
    private String getMontFormat(int month2)
    {
        if(month2 == 1)
            return "01";
        if(month2 == 2)
            return "02";
        if(month2 == 3)
            return "03";
        if(month2 == 4)
            return "04";
        if(month2 == 5)
            return "05";
        if(month2 == 6)
            return "06";
        if(month2 == 7)
            return "07";
        if(month2 == 8)
            return "08";
        if(month2 == 9)
            return "09";


        //default should never happen
        return ""+ month2;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    public void openDatePicker2(View view)
    {
        datePickerDialog2.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}