package com.example.a07_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecentActivity extends AppCompatActivity {
    private ImageView imgView1,imgView2, imgView3;
    private TextView textTitle, textView1, textView2, textView3;
    private  String web;

    public void onClickHome (View V) {
        Intent intentR = new Intent();//
        intentR.putExtra("result", web);//
        setResult(RESULT_OK,intentR);//
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        imgView1 = findViewById(R.id.imageView1);
        imgView2 = findViewById(R.id.imageView2);
        imgView3 = findViewById(R.id.imageView3);
        textTitle = findViewById(R.id.textViewTitle);
        textView1 = findViewById(R.id.textView1);
        textView2= findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        Intent intentM = getIntent();
        if(intentM != null) {
             web = intentM.getStringExtra("web");
            switch (web) {
                case "interpark":
                    textTitle.setText(R.string.title_interpark);
                    imgView1.setImageResource(R.drawable.img1_1);
                    imgView2.setImageResource(R.drawable.img1_2);
                    imgView3.setImageResource(R.drawable.img1_3);
                    textView1.setText(R.string.img1_1);
                    textView2.setText(R.string.img1_2);
                    textView3.setText(R.string.img1_3);
                    break;
                case "naver_comic":
                    textTitle.setText(R.string.title_naver);
                    imgView1.setImageResource(R.drawable.img4_1);
                    imgView2.setImageResource(R.drawable.img4_2);
                    imgView3.setImageResource(R.drawable.img4_3);
                    textView1.setText(R.string.img2_1);
                    textView2.setText(R.string.img2_2);
                    textView3.setText(R.string.img2_3);
                    break;
                default:
                    return;
            }
        }

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(getApplicationContext(), InfoActivity.class);
                if(web.equals("interpark")){
                    intentR.putExtra("img",R.drawable.img1_1);
                    intentR.putExtra("text",getString(R.string.img1_1));
                } else{
                intentR.putExtra("img",R.drawable.img4_1);
                intentR.putExtra("text",getString(R.string.img2_1));
            }
            startActivity(intentR);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(getApplicationContext(), InfoActivity.class);
                if(web.equals("interpark")){
                    intentR.putExtra("img",R.drawable.img1_2);
                    intentR.putExtra("text",getString(R.string.img1_2));
                } else{
                    intentR.putExtra("img",R.drawable.img4_2);
                    intentR.putExtra("text",getString(R.string.img2_2));
                }
                startActivity(intentR);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(getApplicationContext(), InfoActivity.class);
                if(web.equals("interpark")){
                    intentR.putExtra("img",R.drawable.img1_3);
                    intentR.putExtra("text",getString(R.string.img1_3));
                } else{
                    intentR.putExtra("img",R.drawable.img4_3);
                    intentR.putExtra("text",getString(R.string.img2_3));
                }
                startActivity(intentR);
            }
        });

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int nVid = v.getId();
               Intent intentR = new Intent(getApplicationContext(),PurchaseActivity.class);
               switch (nVid) {
                   case R.id.imageView1:
                       intentR.putExtra("productNo",1);
                       break;
                    case R.id.imageView2:
                       intentR.putExtra("productNo",2);
                       break;
                   case R.id.imageView3:
                       intentR.putExtra("productNo",3);
                       break;
               }
               startActivityForResult(intentR,101);
            }
        };

        imgView1.setOnClickListener(ocl);
        imgView2.setOnClickListener(ocl);
        imgView3.setOnClickListener(ocl);
    } //onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101) {
            if(resultCode == RESULT_OK) {
                String sResult = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),sResult,Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getApplicationContext(),"구매 취소되었습니다.",Toast.LENGTH_LONG).show();

        }
    }
}