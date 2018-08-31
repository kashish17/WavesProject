package com.example.kashish.posts;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kashish.posts.photoref.Photo;
import com.example.kashish.posts.photoref.PhotorefResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
   Timer timer;
    ImageView imageView;
    WaveSercice waveSercice;
    Retrofit retrofit;
    List<Photo> photo;
    ArrayList<String> photoref= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent= getIntent();
        String placeId= intent.getStringExtra("placeId");
        placeId="ChIJ_96h2BzqvzsR4ME17sBmcLo";
       // imageView =findViewById(R.id.image);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create());

          photo= new ArrayList<>();


        retrofit= builder.build();
        waveSercice = retrofit.create(WaveSercice.class);
        Call<PhotorefResponse> call= waveSercice.getPhotoref(placeId,Contract.API_KEY);
        call.enqueue(new Callback<PhotorefResponse>() {
            @Override
            public void onResponse(Call<PhotorefResponse> call, Response<PhotorefResponse> response) {
                Log.d("ref", response.toString());
             if(response.body()!=null){
                 Log.d("ref", "Size1"+String.valueOf(photo.size()));
                 photo= response.body().getResult().getPhotos();

                 Log.d("ref", "Size"+String.valueOf(photo.size()));
                 if(photo.size()!=0){
                     for(int i=0;i<photo.size();i++){
                         photoref.add(photo.get(i).getPhotoReference());


                     }
                     Toast.makeText(MainActivity.this,"lull"+photoref.size(),Toast.LENGTH_LONG);
                     Log.d("ref",photoref.size()+"");
                 }

             }

            }

            @Override
            public void onFailure(Call<PhotorefResponse> call, Throwable t) {

            }
        });


        CustomPager adapter = new CustomPager(MainActivity.this,photoref);


        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);

//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                viewPager.post(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%photoref.size());
//                    }
//                });
//            }
//        };
//        timer = new Timer();
//        timer.schedule(timerTask, 3000, 3000);


    }
}
