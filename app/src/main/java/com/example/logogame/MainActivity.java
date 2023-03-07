package com.example.logogame;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> listImages;
    int pos=0;
    ImageView imageView;
    Button ans_button[];
    String[] ansarr;
    String finalans;
    char[] ans_chararr;
    LinearLayout linearLayout;
    Button btn[]=new Button[16];
    public static int t=0;
    ArrayList arrayList=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] images = new String[0];
        imageView=findViewById(R.id.img);
        linearLayout=findViewById(R.id.linear);
        try {
            images = getAssets().list("test");
            listImages= new ArrayList<String>(Arrays.asList(images));
            InputStream inputstream=getAssets().open(listImages.get(pos));
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            imageView.setImageDrawable(drawable);

            System.out.println("answer="+listImages.get(pos++));
            ansarr=listImages.get(pos++).split("\\.");
            System.out.println("ansArr="+ansarr[pos]);
            finalans=ansarr[0];
            ans_button=new Button[finalans.length()];
            ans_chararr=finalans.toCharArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<finalans.length();i++)
        {
            ans_button[i]=new Button(this);
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
            btn[i].setOnClickListener(this);
        }
        arrayList.clear();
    }

    @Override
    public void onClick(View view) {
        t=0;
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