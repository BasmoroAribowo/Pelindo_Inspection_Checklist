package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class profile extends AppCompatActivity {
    TextView idText, namaText, emailText;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutbtn = findViewById(R.id.logout);
        idText = findViewById(R.id.id);
        namaText = findViewById(R.id.nama);
        emailText = findViewById(R.id.emailProfile);


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                Toast.makeText(getApplicationContext(),"Anda Telah Logout",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finishAffinity();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.18.141/myapar/Android/api.php";

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
            public void onErrorResponse(VolleyError error) {idText.setText("That didn't work!");}
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        //resultText.setText(mResult);

    }

    public void parseJson(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            Intent n = getIntent();
            String mUser = n.getStringExtra("user1");
            //JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                String usernameVar, fullnameVar, idVar, emailVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                usernameVar = jsonObject2.getString("username");
                if(mUser.equals(usernameVar)){
                    fullnameVar = jsonObject2.getString("fullname");
                    idVar = jsonObject2.getString("id");
                    emailVar = jsonObject2.getString("email");

                    idText.setText(idVar);
                    namaText.setText(fullnameVar);
                    emailText.setText(emailVar);
                }else{
                    continue;
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}