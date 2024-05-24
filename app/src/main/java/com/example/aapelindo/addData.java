package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import android.app.DatePickerDialog;
import android.widget.DatePicker;


public class addData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] kondisi = {"✓", "X"};
    private TextView kodeText, jenisaparText, beratText, tglexpText, spin, spin1, spin2, spin3, inspeksiText, keteranganText, tempatText;
    Button add;
    private DatePickerDialog datePickerDialog, datePickerDialog2;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.Handle);
        spin.setOnItemSelectedListener(this);
        Spinner spin1 = (Spinner) findViewById(R.id.Pin);
        spin1.setOnItemSelectedListener(this);
        Spinner spin2 = (Spinner) findViewById(R.id.tekanan);
        spin2.setOnItemSelectedListener(this);
        Spinner spin3 = (Spinner) findViewById(R.id.selang);
        spin3.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,kondisi);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        spin1.setAdapter(aa);
        spin2.setAdapter(aa);
        spin3.setAdapter(aa);
        kodeText = findViewById(R.id.kode);
        jenisaparText = findViewById(R.id.jenis_apar);
        beratText = findViewById(R.id.berat);
        tempatText = findViewById(R.id.tempat);
        keteranganText = findViewById(R.id.Keterangan);
        add = findViewById(R.id.tambahapar);
        initDatePicker();
        tglexpText = findViewById(R.id.datePickerButtonExpired);
        tglexpText.setText(getTodaysDate());
        initDatePicker2();
        inspeksiText = findViewById(R.id.datePickerButtonInspeksi);
        inspeksiText.setText(getTodaysDate2());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String kode = kodeText.getText().toString();
            String jenis_apar = jenisaparText.getText().toString();
            String berat = beratText.getText().toString();
            String tglexp = tglexpText.getText().toString();
            String handle = spin.getSelectedItem().toString();
            String pin = spin1.getSelectedItem().toString();
            String tekanan = spin2.getSelectedItem().toString();
            String selang = spin3.getSelectedItem().toString();
            String inspeksi = inspeksiText.getText().toString();
            String tempat = tempatText.getText().toString();
            String keterangan = keteranganText.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url ="http://192.168.18.141/myapar/Android/create_apar.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Succes")){
                                    Toast.makeText(addData.this, "Data Added",Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(addData.this, response,Toast.LENGTH_SHORT).show();
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
                        paramV.put("tglexp", tglexp);
                        paramV.put("handle", handle);
                        paramV.put("pin", pin);
                        paramV.put("tekanan", tekanan);
                        paramV.put("selang", selang);
                        paramV.put("inspeksi", inspeksi);
                        paramV.put("keterangan", keterangan);

                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });


    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int year = cal.get(Calendar.YEAR);
        return makeDateString(day, month, year);
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
                String date = makeDateString(day, month, year);

                tglexpText.setText(date);
            }
        };


        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;


        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker2, int year2, int month2, int day2)
            {
                month2 = month2 + 1;
                String date2 = makeDateString2(day2, month2, year2);
                inspeksiText.setText(date2);
            }
        };


        Calendar cal2 = Calendar.getInstance();
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year2 = cal2.get(Calendar.YEAR);
        int style2 = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog2 = new DatePickerDialog(this, style2, dateSetListener, year2, month2, day2);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + month + " " + year;
    }
    private String makeDateString2(int day2, int month2, int year2)
    {
        return day2 + " " + getMonthFormat(month2) + " " + year2;
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
    public void openDatePicker2(View view)
    {

        datePickerDialog2.show();
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),kondisi[position] , Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}