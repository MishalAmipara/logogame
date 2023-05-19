package com.example.logogame.Test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.logogame.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyAdapter_Test extends RecyclerView.Adapter<MyAdapter_Test.User_Holder> implements View.OnClickListener {

    Context c;
    ArrayList<String> listImages;
    int pos = 0;

    ViewPager2 viewPager;
    Button ans_button[];
    String[] ansarr;
    String finalans;
    char[] ans_chararr;

    Button btn[] = new Button[16];
    int t = 0;
    private ArrayList arrayList = new ArrayList<>();

    public MyAdapter_Test(Context c, ArrayList<String> listImages, ViewPager2 viewPager) {
        this.c = c;
        this.listImages = listImages;
        this.viewPager=viewPager;
        //System.out.println(listImages);
    }
    @NonNull
    @Override
    public MyAdapter_Test.User_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(c).inflate(R.layout.item, parent, false);
        //MyAdapter_Test.User_Holder holder=new User_Holder(view);
             User_Holder holder=new User_Holder(LayoutInflater.from(c).inflate(R.layout.item,parent,false));
             t=0;
             populateView(holder,pos);
            return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter_Test.User_Holder holder, int position) {
//        viewPager.removeOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                populateView(holder,position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    public class User_Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;
        Button btn[]=new Button[16];
        Button ans_Button[]=new Button[10];

        public User_Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            linearLayout=itemView.findViewById(R.id.linear);
            for (int i=0;i<btn.length;i++)
            {
                int id=c.getResources().getIdentifier("btn"+i,"id",c.getPackageName());
                btn[i]=itemView.findViewById(id);
            }
            for (int i=0;i<ans_Button.length;i++)
            {
                int id=c.getResources().getIdentifier("ans"+i,"id",c.getPackageName());
                ans_Button[i]=itemView.findViewById(id);
            }
        }
    }

    private void populateView(User_Holder holder, int position) {
        InputStream inputstream = null;
        try {
            inputstream = c.getAssets().open("test/" + listImages.get(position));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        holder.imageView.setImageDrawable(drawable);
        //pos = position;
        arrayList.clear();
        holder.linearLayout.removeAllViews();
        ansarr = listImages.get(position).split("\\.");
        finalans = ansarr[0];
        //ans_button = new Button[finalans.length()];
        ans_chararr = finalans.toCharArray();

        for (int i = 0; i < finalans.length(); i++) {
            //ans_button[i] = new Button(c);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            layoutParams.setMargins(5, 5, 5, 5);
            ans_button[i].setLayoutParams(layoutParams);
            ans_button[i].setBackgroundColor(c.getResources().getColor(R.color.purple_200));
            holder.linearLayout.addView(ans_button[i]);
        }

        for (int i = 0; i < finalans.length(); i++) {
            arrayList.add(ans_chararr[i]);
        }
        for (int i = 0; i < btn.length - finalans.length(); i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            arrayList.add("" + c);
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < btn.length; i++) {
            btn[i].setText("" + arrayList.get(i));
            btn[i].setOnClickListener(this);
        }
        //arrayList.clear();
    }

    @Override
    public void onClick(View view) {
        t=0;
        for (int i = 0; i < btn.length; i++) {
            if (btn[i].getId() == view.getId()) {
                if (!btn[i].getText().toString().isEmpty()) {
                    if (t < finalans.length()) {
                        ans_button[t].setText(btn[i].getText());
                        btn[i].setText("");
                        btn[i].setVisibility(Button.GONE);
                        t++;
                        System.out.println("t=" + t);
                    }
                }
            }
        }
    }
}
