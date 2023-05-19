package com.example.logogame.Test;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.logogame.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.holderClass> {

    Viewpagedemo_Test viewpagedemo_test;
    ArrayList<String> listImages;
    ViewPager2 viewPager2;
    Button ans_Button[];
    static StringBuffer ans=new StringBuffer();
    int t=0;
    int pos1;
    int cnt=0;
    int counter=0;
    public MyAdapter2(Viewpagedemo_Test viewpagedemo_test, ArrayList<String> listImages, ViewPager2 viewPager, int level) {
    this.viewpagedemo_test = viewpagedemo_test;
    this.listImages = listImages;
    this.viewPager2=viewPager;
    this.pos1=level;

    }

    @NonNull
    @Override
    public holderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        holderClass holder=new holderClass(LayoutInflater.from(viewpagedemo_test).inflate(R.layout.item,parent,false));
        t=0;
        cnt=0;
        //Log.d("POSSS", "onBindViewHolder: Holder Position="+holder.getAdapterPosition());
        //populateView(holder,holder.getAdapterPosition());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                super.onPageSelected(pos);
                t=0;
                Log.d("POSSS", "onBindViewHolder: Page Position="+pos);
                populateView(holder, pos);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderClass holder, int position) {
        t=0;
        //Log.d("POSSS", "onBindViewHolder: Position="+position);
        //populateView(holder,holder.getAdapterPosition());
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                t=0;
//                System.out.println("Page Changed call="+counter++);
//                populateView(holder, holder.getAdapterPosition());
//            }
//        });

    }

    private void populateView(holderClass holder, int position) {
        System.out.println("Fun called=="+(cnt++));
        if (position==-1)
        {
            position++;
        }
        InputStream inputstream = null;
        try {
            inputstream = viewpagedemo_test.getAssets().open("test/" + listImages.get(position));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        holder.imageView.setImageDrawable(drawable);
       // pos = position;
        holder.arrayList.clear();
        holder.linearLayout.removeAllViews();
        String[] ansarr = listImages.get(position).split("\\.");
        String finalans = ansarr[0];
        Toast.makeText(viewpagedemo_test, ""+finalans, Toast.LENGTH_SHORT).show();
        System.out.println("Final="+finalans);
        ans_Button = new Button[finalans.length()];
        char[] ans_chararr = finalans.toCharArray();

        for (int i = 0; i < finalans.length(); i++) {
            ans_Button[i] = new Button(viewpagedemo_test);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            layoutParams.setMargins(5, 5, 5, 5);
            ans_Button[i].setLayoutParams(layoutParams);
            ans_Button[i].setBackgroundColor(viewpagedemo_test.getResources().getColor(R.color.purple_200));
            holder.linearLayout.addView(ans_Button[i]);
        }

        for (int i = 0; i < finalans.length(); i++) {
            holder.arrayList.add(""+ans_chararr[i]);
        }
        for (int i = 0; i < holder.btn.length - finalans.length(); i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            holder.arrayList.add("" + c);
        }
        Collections.shuffle(holder.arrayList);
        Collections.shuffle(holder.arrayList);
        for (int i = 0; i < holder.btn.length; i++) {
            holder.btn[i].setVisibility(View.VISIBLE);
            holder.btn[i].setText("" + holder.arrayList.get(i));
            int finalPosition = position;
            holder.btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < holder.btn.length; i++) {
                        if (holder.btn[i].getId() == view.getId()) {
                            if (!holder.btn[i].getText().toString().isEmpty()) {
                                if (t <finalans.length()) {
                                    ans_Button[t].setText(holder.btn[i].getText());
                                    holder.btn[i].setText("");
                                    holder.btn[i].setVisibility(Button.INVISIBLE);
                                    System.out.println("t=" + t);
                                    ans.append(ans_Button[t].getText().toString());
                                    System.out.println("Answer="+ans);
                                    checkWin(ans);
                                    t++;
                                }
                            }
                        }
                    }
                }

                private void checkWin(StringBuffer ans)
                {
                    System.out.println("Ans="+ans+"\tFinalAns="+finalans);
                    if(finalans.equalsIgnoreCase(String.valueOf(ans)))
                    {
                        Toast.makeText(viewpagedemo_test, "Win", Toast.LENGTH_LONG).show();
                        ans.delete(0,finalans.length());
                        System.out.println("Now ans="+ans);
                        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(viewPager2.getContext());
                        builder.setMessage("Correct Answer...");
                        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent=new Intent(viewPager2.getContext(),Viewpagedemo_Test.class);
//                                intent.putExtra("level",position+1);
//                                viewpagedemo_test.startActivity(intent);
                                viewPager2.setCurrentItem(finalPosition +1);
                            }
                        });
                        builder.show();
                    }
                }
            });
        }
        //arrayList.clear();
    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    public class holderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;
        Button btn[]=new Button[16];
        ArrayList<String> arrayList=new ArrayList<>();
        public holderClass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            linearLayout=itemView.findViewById(R.id.linear);
            for (int i=0;i<btn.length;i++)
            {
                int id=viewpagedemo_test.getResources().getIdentifier("b"+i,"id",viewpagedemo_test.getPackageName());
                Log.d("IDD", "holderClass: "+id);
                btn[i]=itemView.findViewById(id);
            }
//            for (int i=0;i<ans_Button.length;i++)
//            {
//                int id=viewpagedemo_test.getResources().getIdentifier("ans"+i,"id",viewpagedemo_test.getPackageName());
//                Log.d("IDD", "holderClass: "+id);
//                ans_Button[i]=itemView.findViewById(id);
//            }
        }
    }
}
