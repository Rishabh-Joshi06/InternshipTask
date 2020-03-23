package com.example.internshiptask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
RecyclerView plist;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNames();
        plist=(RecyclerView)findViewById(R.id.plist);
        plist.setLayoutManager(new LinearLayoutManager(this));




    }

    private void getNames()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiUrl api=retrofit.create(ApiUrl.class);
        Call<List<CountriesDTO>> call = api.getName();
        call.enqueue(new Callback<List<CountriesDTO>>() {
            @Override
            public void onResponse(Call<List<CountriesDTO>> call, Response<List<CountriesDTO>> response) {
                Log.d(TAG,"INSIDE ONRESPONSE");
                List<CountriesDTO> countrylist = response.body();
                String Names[] = new String[countrylist.size()];

                for(int i=0;i<countrylist.size();i++)
                {
                    Names[i] = countrylist.get(i).getName();

                }

                plist.setAdapter(new ListAdapter(Names));
            }

            @Override
            public void onFailure(Call<List<CountriesDTO>> call, Throwable t) {
                Log.d(TAG,"INSIDE ONFAILURE");
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
