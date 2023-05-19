package com.example.logogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Viewpagedemo extends AppCompatActivity implements View.OnClickListener {

    ArrayList arrayList=new ArrayList();
    ArrayList<String> listImages;
    ViewPager viewPager;
    String[] images = new String[0];
    int pos=0;
    ImageView imageView;
    Button ans_button[];
    String[] ansarr;
    String finalans;
    char[] ans_chararr;
    LinearLayout linearLayout;
    Button btn[]=new Button[16];
    int t=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagedemo);

        viewPager=findViewById(R.id.viewPager);
        //linearLayout=findViewById(R.id.linear);
        try {
            images = getAssets().list("test");
            listImages= new ArrayList<String>(Arrays.asList(images));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyAdapter m=new MyAdapter(this,listImages);
        viewPager.setAdapter(m);
        create(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                create(position);
                t=0;
                Toast.makeText(Viewpagedemo.this, "pos="+listImages.get(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    void create(int position)
    {
        pos=position;
        arrayList.clear();
        linearLayout.removeAllViews();
        ansarr=listImages.get(pos).split("\\.");
        finalans=ansarr[0];
        ans_button=new Button[finalans.length()];
        ans_chararr=finalans.toCharArray();

//        for(int i=0;i<listImages.size();i++)
//        {
//            System.out.println("Imgs="+listImages.get(i));
//        }
        for(int i=0;i<finalans.length();i++)
        {
            ans_button[i]=new Button(Viewpagedemo.this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1);
            layoutParams.setMargins(5,5,5,5);
            ans_button[i].setLayoutParams(layoutParams);
            ans_button[i].setBackgroundColor(getResources().getColor(R.color.purple_200));
            linearLayout.addView(ans_button[i]);
        }
        for(int i=0;i<btn.length;i++)
        {
            int id=getResources().getIdentifier("b"+i,"id",getPackageName());
            btn[i]=findViewById(id);
        }
        for(int i=0;i<finalans.length();i++)
        {
            arrayList.add(ans_chararr[i]);
        }
        for(int i=0;i<btn.length-finalans.length();i++)
        {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            arrayList.add(""+c);
        }
        Collections.shuffle(arrayList);
        for(int i=0;i<btn.length;i++)
        {
            btn[i].setText(""+arrayList.get(i));
            btn[i].setOnClickListener(Viewpagedemo.this);
        }
        //arrayList.clear();
    }

    @Override
    public void onClick(View view) {
        //t=0;
        for(int i=0;i<btn.length;i++)
        {
            if(btn[i].getId()==view.getId())
            {
                if(!btn[i].getText().toString().isEmpty())
                {
                    if(t<finalans.length())
                    {
                        ans_button[t].setText(btn[i].getText());
                        btn[i].setText("");
                        t++;
                        System.out.println("t="+t);
                    }
                }
            }
        }
    }
}