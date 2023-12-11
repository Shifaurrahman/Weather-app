package com.example.condition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText cityName;
    Button searchButton,forecast;
    TextView result,celcius;


    class Weather extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... address) {
            try {


            URL url = new URL(address[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is=connection.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);

                int data=isr.read();
                String content="";
                char ch;
                while (data!=-1){
                    ch=(char) data;
                    content=content+ch;
                    data=isr.read();

                }
                return content;

        }
                catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
            return null;
        }
    }




    public void search(View view){

        cityName=findViewById(R.id.cityName);
        searchButton=findViewById(R.id.searchButton);
        result=findViewById(R.id.result);
        celcius=findViewById(R.id.Celcius);

        celcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), celcius_new.class);
                startActivity(intent);

            }
        });

        forecast=findViewById(R.id.forecast);
        forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ForecasteActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Forecast weather information",Toast.LENGTH_SHORT).show();
            }
        });


        String cName=cityName.getText().toString();
        if (cName.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter City Name",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"Here this is details",Toast.LENGTH_SHORT).show();
        }



        String content;
        Weather weather = new Weather();
        try {


            content = weather.execute("https://api.openweathermap.org/data/2.5/weather?q="+cName+"&appid=43e0b02381d12c525fabef7f53359f87").get();
            Log.i("content",content);

            JSONObject jsonObject=new JSONObject(content);
            String weatherData=jsonObject.getString("weather");
            String mainTemperature=jsonObject.getString("main");
            String windSpeed=jsonObject.getString("wind");
            double visibility;
            Log.i("weatherData",weatherData);
            JSONArray array=new JSONArray(weatherData);

            String main ="";
            String description ="";
            String temperature="";
            String wind="";
            String presure="";


            for (int i=0;i<array.length();i++){
                JSONObject weatherPart=array.getJSONObject(i);
                main=weatherPart.getString("main");
                description=weatherPart.getString("description");


            }

            JSONObject mainPart=new JSONObject(mainTemperature);
            temperature=mainPart.getString("temp");
            presure=mainPart.getString("pressure");



            JSONObject windPart=new JSONObject(windSpeed);
            wind=windPart.getString("speed");

            visibility=Double.parseDouble(jsonObject.getString("visibility"));
//By default Visibility in meter
            int visibilityInKilometer=(int) visibility/1000;

            Log.i("Temperature",temperature);
            Log.i("Wind Speed",windSpeed);
            Log.i("Pressure",presure);
            /* Log.i("main",main);
            Log.i("description",description); */


            String resultText="Main : "+main+
                    "\nDescription : "+description+
                    "\nTemperature : "+temperature+"K"+
                    "\nVisibility : "+visibilityInKilometer+"km"+
                    "\nWind Speed : "+wind+"km/h"+
                    "\nPressure : "+presure+"hPa";

            result.setText(resultText);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
/*
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId())
        {
            case R.id.C1:
                Intent intent=new Intent(getApplicationContext(), celcius_new.class);
                startActivity(intent);
                return true;
            case R.id.K1:
                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

     }


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu){
       MenuInflater inflater=getMenuInflater();
       inflater.inflate(R.menu.menu,menu);
       return true;
    }

 */



}