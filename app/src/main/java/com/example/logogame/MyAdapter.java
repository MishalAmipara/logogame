package com.example.logogame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAdapter extends PagerAdapter
{

    Context c;
    ArrayList<String> listImages;
    int pos=0;

    Button ans_button[];
    String[] ansarr;
    String finalans;
    char[] ans_chararr;
    LinearLayout linearLayout;
    Button btn[]=new Button[16];
    int t=0;

    public MyAdapter(Context c, ArrayList<String> listImages) {
        this.c = c;
        this.listImages = listImages;
        //System.out.println(listImages);
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(c).inflate(R.layout.item,container,false);
        ImageView imageView=view.findViewById(R.id.img);
        InputStream inputstream= null;
        try {
            inputstream = c.getAssets().open("test/" +listImages.get(position));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        imageView.setImageDrawable(drawable);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
