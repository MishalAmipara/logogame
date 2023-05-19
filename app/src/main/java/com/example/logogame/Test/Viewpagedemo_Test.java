package com.example.logogame.Test;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.logogame.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Viewpagedemo_Test extends AppCompatActivity{

    ArrayList arrayList=new ArrayList();
    ArrayList<String> listImages;
    ViewPager2 viewPager;
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
        int level=getIntent().getIntExtra("level",0);
        viewPager = findViewById(R.id.viewPager);
        //linearLayout=findViewById(R.id.linear);
        try {
            images = getAssets().list("test");
            listImages = new ArrayList<String>(Arrays.asList(images));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //MyAdapter_Test pagerAdapter = new MyAdapter_Test(this, listImages, viewPager);
//        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        viewPager.startLayoutAnimation();
        //viewPager.setCurrentItem(0);
        viewPager.setAdapter(new MyAdapter2(this,listImages,viewPager,level));

        //create(0);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
    }
//    private void populateView(MyAdapter2.holderClass holder, int position) {
//
//        InputStream inputstream = null;
//        try {
//            inputstream = getAssets().open("test/" + listImages.get(position));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Drawable drawable = Drawable.createFromStream(inputstream, null);
//        holder.imageView.setImageDrawable(drawable);
//        // pos = position;
//        holder.arrayList.clear();
//        holder.linearLayout.removeAllViews();
//        String[] ansarr = listImages.get(position).split("\\.");
//        String finalans = ansarr[0];
//        Toast.makeText(Viewpagedemo_Test.this, ""+finalans, Toast.LENGTH_SHORT).show();
//        ans_Button = new Button[finalans.length()];
//        char[] ans_chararr = finalans.toCharArray();
//
//        for (int i = 0; i < finalans.length(); i++) {
//            ans_Button[i] = new Button(viewpagedemo_test);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            layoutParams.setMargins(5, 5, 5, 5);
//            ans_Button[i].setLayoutParams(layoutParams);
//            ans_Button[i].setBackgroundColor(viewpagedemo_test.getResources().getColor(R.color.purple_200));
//            holder.linearLayout.addView(ans_Button[i]);
//        }
//
//        for (int i = 0; i < finalans.length(); i++) {
//            holder.arrayList.add(""+ans_chararr[i]);
//        }
//        for (int i = 0; i < holder.btn.length - finalans.length(); i++) {
//            Random r = new Random();
//            char c = (char) (r.nextInt(26) + 'a');
//            holder.arrayList.add("" + c);
//        }
//        Collections.shuffle(holder.arrayList);
//        for (int i = 0; i < holder.btn.length; i++) {
//            holder.btn[i].setVisibility(View.VISIBLE);
//            holder.btn[i].setText("" + holder.arrayList.get(i));
//            holder.btn[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    for (int i = 0; i < holder.btn.length; i++) {
//                        if (holder.btn[i].getId() == view.getId()) {
//                            if (!holder.btn[i].getText().toString().isEmpty()) {
//                                if (t <=finalans.length()) {
//                                    ans_Button[t].setText(holder.btn[i].getText());
//                                    holder.btn[i].setText("");
//                                    holder.btn[i].setVisibility(Button.INVISIBLE);
//                                    System.out.println("t=" + t);
//                                    ans.append(ans_Button[t].getText().toString());
//                                    System.out.println("Answer="+ans);
//                                    checkWin(ans);
//                                    t++;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                private void checkWin(StringBuffer ans)
//                {
//                    System.out.println("Ans="+ans+"\tFinalAns="+finalans);
//                    if(finalans.equalsIgnoreCase(String.valueOf(ans)))
//                    {
//                        Toast.makeText(Viewpagedemo_Test.this, "Win", Toast.LENGTH_LONG).show();
//                        ans.delete(0,finalans.length());
//                        System.out.println("Now ans="+ans);
//                        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(viewPager2.getContext());
//                        builder.setMessage("Correct Answer...");
//                        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                Intent intent=new Intent(viewPager2.getContext(),Viewpagedemo_Test.class);
////                                intent.putExtra("level",position+1);
////                                viewpagedemo_test.startActivity(intent);
//                                viewPager.setCurrentItem(position+1);
//                            }
//                        });
//                        builder.show();
//                    }
//                }
//            });
//        }
//        //arrayList.clear();
//    }
}