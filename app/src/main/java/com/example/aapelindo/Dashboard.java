package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView scan1, Map1;
    private TextView  user1;
    ImageView profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        scan1 = (CardView) findViewById(R.id.scan);
        Map1 = (CardView) findViewById(R.id.petaApar);
        profil = findViewById(R.id.profil);
        user1 = findViewById(R.id.namauser);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = getIntent();
                String user1 = n.getStringExtra("user1");
                Intent intent = new Intent(getApplicationContext(), profile.class);
                intent.putExtra("user1", user1);
                startActivity(intent);
                finish();
            }
        });



        scan1.setOnClickListener((View.OnClickListener) this);
        Map1.setOnClickListener((View.OnClickListener) this);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.32.50/myapar/Android/api.php";

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
                user1.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



    @Override
    public void onClick(View v){
        Intent i ;
        Intent n = getIntent();
        String user1 = n.getStringExtra("user1");
        switch (v.getId()){
            case R.id.scan: i = new Intent(this,ScannerPage.class);
            i.putExtra("user1", user1);
            startActivity(i);
            break;
            case R.id.petaApar: i = new Intent(this,denah.class);
            startActivity(i);
            break;

        }
    }


    public void parseJson(String response){
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("users");
                Intent u = getIntent();
                String mUser = u.getStringExtra("user1");
                //JSONArray jsonArray = new JSONArray(response);
               for (int i = 0; i < jsonArray.length(); i++){
                   String usernameVar, fullnameVar;
                   JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                   usernameVar = jsonObject2.getString("username");
                   if(mUser.equals(usernameVar)){
                       fullnameVar = jsonObject2.getString("fullname");
                       user1.setText(fullnameVar);
                   }else{
                       continue;
                   }


                }
           } catch (JSONException e) {
                e.printStackTrace();
           }
    }
}