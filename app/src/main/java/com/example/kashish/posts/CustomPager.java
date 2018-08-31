package com.example.kashish.posts;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomPager extends PagerAdapter {

     Context context;
     ArrayList<String> photoref;


    public CustomPager(Context context , ArrayList<String> photoref){

        this.context = context;
        this.photoref = photoref;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_layout,container,false);

        ImageView imageView = (ImageView) rowLayout.findViewById(R.id.imageView);

        String url="https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference="+photoref.get(position)+"&key="+Contract.API_KEY;

        new Picasso.Builder(context).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                //Log.d(("lalalal"),exception.toString());
            }
        }).build().load(url.trim()).error(R.drawable.ic_launcher_foreground).into(imageView);

        ViewPager cp=(ViewPager) container;
        cp.addView(rowLayout,0);

        return rowLayout;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager cp=(ViewPager) container;
        View view = (View) object;
        cp.removeView(view);
    }

    @Override
    public int getCount() {
        return photoref.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }
}
