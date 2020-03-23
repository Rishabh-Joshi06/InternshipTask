package com.example.internshiptask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_details extends AppCompatActivity {
    private static final String TAG = "activity_details";
    String name;


    TextView cname, capital, region, subregion, population, area, nativename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra("value1").toString();
        Log.d(TAG, "NAme retreived is" + name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        cname = (TextView) findViewById(R.id.cname);
        capital = (TextView) findViewById(R.id.capital);
        region = (TextView) findViewById(R.id.region);
        subregion = (TextView) findViewById(R.id.subregion);
        population = (TextView) findViewById(R.id.population);
        area = (TextView) findViewById(R.id.area);
        nativename = (TextView) findViewById(R.id.nativename);

        getDetails();
    }

    private void getDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/name/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiUrl api = retrofit.create(ApiUrl.class);
        Call<List<CountriesDTO>> call = api.getInfo("https://restcountries.eu/rest/v2/name/" + name);

        call.enqueue(new Callback<List<CountriesDTO>>() {
            @Override
            public void onResponse(Call<List<CountriesDTO>> call, Response<List<CountriesDTO>> response) {
                Log.d(TAG, "INSIDE ONRESPONSE");
                List<CountriesDTO> countrylist = response.body();
                String[] Names = new String[6];
                double are = countrylist.get(0).getArea();
                String a=Double.toString(are);
                long popu = countrylist.get(0).getPopulation();
                String b =Long.toString(popu);
                Names[0] = countrylist.get(0).getName();
                Names[1] = countrylist.get(0).getCapital();
                Names[2] = countrylist.get(0).getRegion();
                Names[3] = countrylist.get(0).getSubregion();
                Names[4] = countrylist.get(0).getNumericCode();

           cname.setText(Names[0]);
           capital.setText(Names[1]);
           region.setText(Names[2]);
           subregion.setText(Names[3]);
           population.setText(b);
           area.setText(a);
           nativename.setText(Names[4]);

            }

            @Override
            public void onFailure(Call<List<CountriesDTO>> call, Throwable t) {
                Log.d(TAG, "INSIDE ONFAILURE");
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
