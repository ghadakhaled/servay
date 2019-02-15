package com.example.ghada.newdesigndentelclinic;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
public class viewpaperAdapter extends PagerAdapter
{
    Activity activity;
    String[] imagess;
    LayoutInflater inflater;

    public viewpaperAdapter(Activity activity, String[] imagess) {
        this.activity = activity;
        this.imagess = imagess;
    }

    @Override
    public int getCount() {
        return imagess.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        inflater= (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemvie = inflater.inflate(R.layout.layoutpaper,container,false);
      ImageView image=itemvie.findViewById(R.id.viewer);
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int Width =metrics.widthPixels;
        int Hight= metrics.heightPixels;
        image.setMinimumHeight(Hight);
        image.setMinimumWidth(Width);

        try {
            Picasso.with(activity.getApplicationContext())
                    .load(imagess[position])
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher)
                    .into(image);

        }
        catch (Exception e)
        {

        }
       container.addView(itemvie);
        return  itemvie;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
