package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

public class data extends AppCompatActivity {
    ListView data;
    ArrayList<HashMap<String, String>> datalist;
    ArrayAdapter adapter;
    String kode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        data = findViewById(R.id.listData);
        datalist = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.18.141/myapar/Android/api_apar.php";

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
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        //resultText.setText(mResult);
        data.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cod = 1+position;


            }

        });
    }

    public void parseJson(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("apar");
            for (int i = 0; i < jsonArray.length(); i++){
                String kodeVar, tempatVar, jenisVar, beratVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                kodeVar = jsonObject2.getString("kode");
                tempatVar = jsonObject2.getString("tempat");
                jenisVar = jsonObject2.getString("jenis_apar");
                beratVar = jsonObject2.getString("berat");

                HashMap<String, String> data = new HashMap<>();
                data.put("kode", kodeVar);
                data.put("tempat", tempatVar);
                data.put("jenis_apar", jenisVar);
                data.put("berat", beratVar);

                datalist.add(data);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                com.example.aapelindo.data.this,datalist
                ,R.layout.rowlistview,
                new String[] {"kode", "tempat", "jenis_apar", "berat"},
                new int[]{R.id.koderow, R.id.tempatrow, R.id.jenisrow, R.id.beratrow}
        );
        data.setAdapter(adapter);
    }

}