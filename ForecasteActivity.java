package com.example.condition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ForecasteActivity extends AppCompatActivity {

    EditText cityName;
    Button searchButton,logOut;
    TextView result,result8,result16,result24,result32,result40;



    class Weather extends AsyncTask<String,Void,String> {





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
        result8=findViewById(R.id.result8);
        result16=findViewById(R.id.result16);
        result24=findViewById(R.id.result24);
        result32=findViewById(R.id.result32);
        result40=findViewById(R.id.result40);

        logOut=findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Firstpage.class);
                startActivity(intent);
            }
        });








        String cName=cityName.getText().toString();
        if (cName.isEmpty()){
            Toast.makeText(ForecasteActivity.this,"Please Enter City Name",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ForecasteActivity.this,"Here this is details",Toast.LENGTH_SHORT).show();
        }


        String content;
        ForecasteActivity.Weather weather = new ForecasteActivity.Weather();
        try {


            content = weather.execute("https://api.openweathermap.org/data/2.5/forecast?q="+cName+"&units=metric&appid=43e0b02381d12c525fabef7f53359f87").get();
            Log.i("content",content);

            JSONObject jsonObject=new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("list");

            JSONObject weather0 = jsonArray.getJSONObject(0);
            JSONObject main0 = weather0.getJSONObject("main");
            String temperature0 = main0.getString("temp");
            String pressure0=main0.getString("pressure");
            JSONObject wind0=weather0.getJSONObject("wind");
            String speed0=wind0.getString("speed");
            String date0=weather0.getString("dt_txt");
            JSONArray sky0 = weather0.getJSONArray("weather");
            JSONObject skyIndex0 = sky0.getJSONObject(0);
            String skyView0 = skyIndex0.getString("main");
            String skyViewDes0 = skyIndex0.getString("description");


            //Next day 8
            JSONObject weather8 = jsonArray.getJSONObject(8);
            JSONObject main8 = weather8.getJSONObject("main");
            String temperature8 = main8.getString("temp");
            String pressure8=main8.getString("pressure");
            JSONObject wind8=weather8.getJSONObject("wind");
            String speed8=wind8.getString("speed");
            String date8=weather8.getString("dt_txt");
            JSONArray sky8 = weather8.getJSONArray("weather");
            JSONObject skyIndex8 = sky8.getJSONObject(0);
            String skyView8 = skyIndex8.getString("main");
            String skyViewDes8 = skyIndex8.getString("description");


            //Next day 16
            JSONObject weather16 = jsonArray.getJSONObject(16);
            JSONObject main16 = weather16.getJSONObject("main");
            String temperature16 = main16.getString("temp");
            String pressure16=main16.getString("pressure");
            JSONObject wind16=weather16.getJSONObject("wind");
            String speed16=wind16.getString("speed");
            String date16=weather16.getString("dt_txt");
            JSONArray sky16 = weather16.getJSONArray("weather");
            JSONObject skyIndex16 = sky16.getJSONObject(0);
            String skyView16= skyIndex16.getString("main");
            String skyViewDes16 = skyIndex16.getString("description");


            //Next day
            JSONObject weather24 = jsonArray.getJSONObject(24);
            JSONObject main24 = weather24.getJSONObject("main");
            String temperature24 = main24.getString("temp");
            String pressure24=main24.getString("pressure");
            JSONObject wind24=weather24.getJSONObject("wind");
            String speed24=wind24.getString("speed");
            String date24=weather24.getString("dt_txt");
            JSONArray sky24 = weather24.getJSONArray("weather");
            JSONObject skyIndex24 = sky24.getJSONObject(0);
            String skyView24 = skyIndex24.getString("main");
            String skyViewDes24 = skyIndex24.getString("description");


            //Next day
            JSONObject weather32 = jsonArray.getJSONObject(32);
            JSONObject main32 = weather32.getJSONObject("main");
            String temperature32 = main32.getString("temp");
            String pressure32=main32.getString("pressure");
            JSONObject wind32=weather32.getJSONObject("wind");
            String speed32=wind32.getString("speed");
            String date32=weather32.getString("dt_txt");
            JSONArray sky32= weather32.getJSONArray("weather");
            JSONObject skyIndex32 = sky32.getJSONObject(0);
            String skyView32 = skyIndex32.getString("main");
            String skyViewDes32 = skyIndex32.getString("description");






            String resultText="Date: "+date0+
                    "\tTemperature : " +temperature0+"°c";
            String resultText8="Date : "+date8+
                    "\t Temperature : "+temperature8+"°c";
            String resultText16="Date : "+date16+
                    "\t Temperature : "+temperature16+"°c";
            String resultText24="Date : "+date24+
                    "\t Temperature : "+temperature24+"°c";
            String resultText32="Date : "+date32+
                    "\t Temperature : "+temperature32+"°c";





            result.setText(resultText);
            result8.setText(resultText8);
            result16.setText(resultText16);
            result24.setText(resultText24);
            result32.setText(resultText32);


            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result_Text="Date: "+date0+
                            "\nTemperature : " +temperature0+"°c"+
                            "\nPressure : "+pressure0+"hPa"+
                            "\nWind Speed : "+speed0+"km/h"+
                            "\nMain : "+skyView0+
                            "\nDescription : "+skyViewDes0;
                    result40.setText(result_Text);
                }
            });

            result8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result_Text8="Date: "+date8+
                            "\nTemperature : " +temperature8+"°c"+
                            "\nPressure : "+pressure8+"hPa"+
                            "\nWind Speed : "+speed8+"km/h"+
                            "\nMain : "+skyView8+
                            "\nDescription : "+skyViewDes8;
                    result40.setText(result_Text8);
                }
            });

            result16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result_Text16="Date: "+date16+
                            "\nTemperature : " +temperature16+"°c"+
                            "\nPressure : "+pressure16+"hPa"+
                            "\nWind Speed : "+speed16+"km/h"+
                            "\nMain : "+skyView16+
                            "\nDescription : "+skyViewDes16;
                    result40.setText(result_Text16);
                }
            });

            result24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result_Text24="Date: "+date24+
                            "\nTemperature : " +temperature24+"°c"+
                            "\nPressure : "+pressure24+"hPa"+
                            "\nWind Speed : "+speed24+"km/h"+
                            "\nMain : "+skyView24+
                            "\nDescription : "+skyViewDes24;
                    result40.setText(result_Text24);
                }
            });

            result32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String result_Text32="Date: "+date32+
                            "\nTemperature : " +temperature32+"°c"+
                            "\nPressure : "+pressure32+"hPa"+
                            "\nWind Speed : "+speed32+"km/h"+
                            "\nMain : "+skyView32+
                            "\nDescription : "+skyViewDes32;
                    result40.setText(result_Text32);
                }
            });






        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecaste);






    }
}