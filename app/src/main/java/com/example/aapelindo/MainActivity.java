package com.example.aapelindo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int waktu = 4000;
    String channelnotif = "channelku" ;
    String channelid = "default" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tglexpText.setText(getTodaysDate());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.32.50/myapar/Android/api_apar.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        parseJson(response);
//                        if (kode.isEmpty()){
//
//                        }else{
//                            notif();
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);




        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this, Login.class);
                startActivity(home);
                finish();
            }
        }, waktu);
    }
    public void parseJson(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("apar");
            Calendar calendar = Calendar.getInstance();
            int nowtahun = calendar.get(Calendar.YEAR);
            int nowbulan = calendar.get(Calendar.MONTH);
            nowbulan = nowbulan + 1;
            for (int i = 0 ; i < jsonArray.length(); i++){
                String  tempatVar,kodeVar, bulanexpVar, tahunexpVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                bulanexpVar = jsonObject2.getString("bulanexp");
                tahunexpVar = jsonObject2.getString("tahunexp");
                int bulanexpvarn = Integer.parseInt(bulanexpVar);
                int tahunexpvarn = Integer.parseInt(tahunexpVar);
                if( nowtahun > tahunexpvarn || (nowtahun == tahunexpvarn && nowbulan > bulanexpvarn - 1 )){
                    tempatVar = jsonObject2.getString("tempat");
                    kodeVar = jsonObject2.getString("kode");
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, channelid )
                            .setSmallIcon(R.mipmap.ic_new_launcher_round)
                            .setContentTitle("APAR Expired "+ kodeVar+ " Di ")
                            .setContentText(tempatVar);
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE ) ;
                    if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                        int importance = NotificationManager. IMPORTANCE_HIGH ;
                        NotificationChannel notificationChannel = new
                                NotificationChannel( channelnotif , "contoh channel" , importance) ;
                        notificationChannel.enableLights( true ) ;
                        notificationChannel.setLightColor(Color. RED ) ;
                        mBuilder.setChannelId( channelnotif ) ;
                        assert mNotificationManager != null;
                        mNotificationManager.createNotificationChannel(notificationChannel) ;
                    }
                    assert mNotificationManager != null;
                    mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;

                }else{
                    //for (int n = 0 ; n < jsonArray.length(); n++){
                    continue;
                }


                //int idVarInt = Integer.parseInt(idVar);

                //if(idVarInt == mResultInt) {
                //Object level = jsonArray.get(mResultInt);
                //namaVar = level.getString("nama");
                //String level1 = String.valueOf(level);
                //String[] nama = {level1};


                //resultText.setText(namaVar);
                //resultText.append(namaVar);
                //}else{
                //idVarInt++;
                //}

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}